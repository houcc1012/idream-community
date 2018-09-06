package com.idream.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.idream.commons.db.redis.RedisCache;
import com.idream.commons.db.redis.RedisKeyConstants;
import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.marketing.*;
import com.idream.commons.lib.dto.token.AuthUserInfo;
import com.idream.commons.lib.enums.MarketingEnum;
import com.idream.commons.lib.enums.UserEnum;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.mapper.*;
import com.idream.commons.lib.model.*;
import com.idream.commons.lib.util.DateTimeUtils;
import com.idream.commons.lib.util.DateUtils;
import com.idream.commons.lib.util.DistanceUtils;
import com.idream.commons.lib.util.RandomUtils;
import com.idream.service.AwardService;
import com.idream.service.UserCouponService;
import com.idream.service.UserScoreService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Description :
 * Created by DELL2018-003 on 2018/4/12.
 */
@Service
public class AwardServiceImpl implements AwardService {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private AwardPoolMapper awardPoolMapper;

    @Autowired
    private CouponInfoMapper couponInfoMapper;

    @Autowired
    private IntegrationRecordMapper integrationRecordMapper;

    @Resource
    private RedisCache redisCache;

    @Resource
    private IntegrationInfoMapper integrationInfoMapper;

    @Resource
    private UserScoreService userScoreService;

    @Resource
    private UserCouponService userCouponService;

    @Autowired
    private AwardInformationRelationMapper awardInformationRelationMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserManagerMapper userManagerMapper;

    @Autowired
    private RegionGroupInfoMapper regionGroupInfoMapper;


    @Override
    public int saveAwardPool(AwardPoolParams awardPool) {

        // 参数校验
        if (awardPool.getType().equals(MarketingEnum.CouponTypeEnum.REGION.getCode()) && awardPool.getBookId() == null) {
            throw new BusinessException("区域券绑定书屋不能为空！");
        } else if (awardPool.getType().equals(MarketingEnum.CouponTypeEnum.COMMON.getCode()) && awardPool.getBookId() == null) {
            awardPool.setBookId(-1);
        }
        // 校验信息
        JSONObject awardJson = getAwardValueAndCheck(awardPool);

        AwardPool pool = new AwardPool();
        // pool.setCount(awardPool.getCount());
        Date date = new Date();
        pool.setCreateTime(date);
        pool.setUpdateTime(date);
        pool.setDescription(awardPool.getDescription());
        pool.setEndTime(DateTimeUtils.getDateEndTime(awardPool.getEndTime()));
        if (null == awardPool.getExchangeCount()) {
            awardPool.setExchangeCount((short) -1);
        }
        pool.setExchangeCount(awardPool.getExchangeCount().intValue());
        pool.setExchangeScore(awardPool.getExchangeScore());
        pool.setExpireDay(awardPool.getExpireDay());
        pool.setImage(awardPool.getImage());
        pool.setInfoEnable(awardPool.getInfoEnable());
        pool.setInstructions(awardPool.getInstructions());
        pool.setIntroduce(awardPool.getIntroduce());
        pool.setName(awardPool.getName());
        pool.setStartTime(awardPool.getStartTime());
        Short totalExchange = awardPool.getTotalExchangeConut();
        if (null == totalExchange || totalExchange == -1) {
            totalExchange = -1;
        } else {
            if (awardPool.getExchangeCount() == -1 || totalExchange < awardPool.getExchangeCount()) {
                throw new BusinessException("累计兑换次数不能小于每天兑换次数，请检查！");
            }
        }
        pool.setTotalExchangeConut(totalExchange);
        if (pool.getTotalExchangeConut() != -1 && pool.getTotalExchangeConut() < 0 || pool.getExchangeCount() != -1 && pool.getExchangeCount() < 0) {
            throw new BusinessException("次数不能小于0");
        }
        pool.setAddUserType(awardPool.getAddUserType());
        pool.setType(awardPool.getType());
        pool.setProperty(awardPool.getProperty());
        pool.setUserId(awardPool.getUserId());
        pool.setAwardValue(awardJson.toJSONString());
        List<Integer> regionIds = new ArrayList<>();
        if (awardPool.getBookId() == -1) {
            //如果是通用券则所有书屋皆插入一条记录
            regionIds = getAllBookId();
            //把-1这条数据也插入表中
            regionIds.add(-1);
        } else {
            //区域券直接插入当前数据
            regionIds.add(awardPool.getBookId());
        }
        int result = 0;
        for (Integer regionId : regionIds) {
            pool.setBookId(regionId);
            //批量同步到管理者列表时默认状态为下架
            if (awardPool.getType().equals(MarketingEnum.CouponTypeEnum.COMMON.getCode()) && regionId != -1) {
                pool.setStatus(MarketingEnum.AwardPublishStatus.DOWN_PUBLISH.getCode());
                //通用券数量同步0
                pool.setCount(0);
            } else {
                pool.setStatus(MarketingEnum.AwardPublishStatus.UP_PUBLISH.getCode());
                pool.setCount(awardPool.getCount());
            }
            //新增兑奖奖品
            result = awardPoolMapper.insertSelective(pool);
            //插入信息关联表
            Integer[] infoIds = awardPool.getInfoId();
            if (infoIds != null) {
                for (Integer id : infoIds) {
                    AwardInformationRelation air = new AwardInformationRelation();
                    air.setAwardPoolId(pool.getId());
                    air.setInfoId(id);
                    air.setCreateTime(date);
                    air.setUpdateTime(date);
                    //保存奖品领取所需要填写的信息项
                    awardInformationRelationMapper.insert(air);
                }
            }
            //兑奖奖品状态为上架时, 数量存入缓存
            if (result > 0 && MarketingEnum.AwardPublishStatus.UP_PUBLISH.getCode().equals(pool.getStatus())) {
                saveAwardRedis(pool);
            }
        }
        return result;
    }

    /**
     * 兑奖奖券信息校验
     *
     * @param awardPool
     */
    private JSONObject getAwardValueAndCheck(AwardPoolParams awardPool) {
        JSONObject awardJson = new JSONObject();
        String pattern = "\\d+(\\.\\d+)?";
        if (MarketingEnum.AwardProperty.FULL_CUT.getCode().equals(awardPool.getProperty())) {
            AwardRule awardRule = awardPool.getAwardValue();
            if (awardRule == null || StringUtils.isEmpty(awardRule.getFull()) || StringUtils.isEmpty(awardRule.getCut())) {
                throw new BusinessException("满减信息不能为空！");
            }
            if (!Pattern.matches(pattern, awardRule.getFull()) || !Pattern.matches(pattern, awardRule.getCut())) {
                throw new BusinessException("满减信息格式只能是大于0的整数或小数！");
            }
            if (new BigDecimal(awardRule.getFull()).compareTo(new BigDecimal(awardRule.getCut())) < 0) {
                throw new BusinessException("满减信息不合理，请检查！");
            }
            awardJson.put(MarketingEnum.AwardValueKey.FULL.getCode(), awardRule.getFull());
            awardJson.put(MarketingEnum.AwardValueKey.CUT.getCode(), awardRule.getCut());
        } else if (MarketingEnum.AwardProperty.DISCOUNT.getCode().equals(awardPool.getProperty())) {
            AwardRule awardRule = awardPool.getAwardValue();
            if (awardRule == null || StringUtils.isEmpty(awardRule.getDiscount())) {
                throw new BusinessException("折扣信息不能为空！");
            }
            if (!Pattern.matches(pattern, awardRule.getDiscount())) {
                throw new BusinessException("折扣信息格式只能是大于0的整数或小数！");
            }
            awardJson.put(MarketingEnum.AwardValueKey.DISCOUNT.getCode(), awardRule.getDiscount());
        } else if (MarketingEnum.AwardProperty.REPLACE_CASH.getCode().equals(awardPool.getProperty())) {
            AwardRule awardRule = awardPool.getAwardValue();
            if (awardRule == null || StringUtils.isEmpty(awardRule.getCash())) {
                throw new BusinessException("代金券信息不能为空！");
            }
            if (!Pattern.matches(pattern, awardRule.getCash())) {
                throw new BusinessException("代金券信息格式只能是大于0的整数或小数！");
            }
            awardJson.put(MarketingEnum.AwardValueKey.CASH.getCode(), awardRule.getCash());
        }
        return awardJson;
    }

    /**
     * 保存 奖品到redis
     *
     * @param awardPool
     */
    private void saveAwardRedis(AwardPool awardPool) {
        if (MarketingEnum.AwardPublishStatus.UP_PUBLISH.getCode().equals(awardPool.getStatus())) {
            //存储奖品信息
            String key = awardPool.getId() + "";
            boolean flag = redisCache.setString(key, awardPool.getCount().toString(), RedisKeyConstants.AWARD_PRE);
            //设置失效时间
            Date expireTime = awardPool.getEndTime();
            long expireConut = redisCache.pexpireAt(key, RedisKeyConstants.AWARD_PRE, expireTime.getTime());
        }
    }

    /**
     * 删除redis兑奖奖品信息
     *
     * @param id
     */
    private void deleteAwardRedis(int id) {
        //存储奖品信息
        long flag = redisCache.del(id + "", RedisKeyConstants.AWARD_PRE);
    }


    @Override
    public int updateAwardBean(AwardPoolParams awardPool, int userId) {
        if (awardPool.getType().equals(MarketingEnum.CouponTypeEnum.REGION.getCode()) && awardPool.getBookId() == null) {
            throw new BusinessException("区域券绑定书屋不能为空！");
        } else if (awardPool.getType().equals(MarketingEnum.CouponTypeEnum.COMMON.getCode()) && awardPool.getBookId() == null) {
            awardPool.setBookId(-1);
        }
        //校验信息
        JSONObject awardValue = getAwardValueAndCheck(awardPool);

        //组装参数信息
        AwardPool pool = new AwardPool();
        if (UserEnum.UserType.MOBILE_USER.getCode().equals(awardPool.getAddUserType())
                || MarketingEnum.CouponTypeEnum.REGION.getCode().equals(awardPool.getType())) {
            pool.setDescription(awardPool.getDescription());
            if (null == awardPool.getExchangeCount()) {
                awardPool.setExchangeCount((short) -1);
            }
            pool.setExchangeCount(awardPool.getExchangeCount().intValue());
            pool.setExchangeScore(awardPool.getExchangeScore());
            pool.setImage(awardPool.getImage());
            pool.setInfoEnable(awardPool.getInfoEnable());
            pool.setInstructions(awardPool.getInstructions());
            pool.setIntroduce(awardPool.getIntroduce());
            pool.setName(awardPool.getName());
            pool.setBookId(awardPool.getBookId());
            Short totalExchange = awardPool.getTotalExchangeConut();
            if (null == totalExchange || totalExchange == -1) {
                totalExchange = -1;
            } else {
                if (awardPool.getExchangeCount() == -1 || totalExchange < awardPool.getExchangeCount()) {
                    throw new BusinessException("累计兑换次数不能小于每天兑换次数，请检查！");
                }
            }
            pool.setTotalExchangeConut(totalExchange);
            if (pool.getTotalExchangeConut() != -1 && pool.getTotalExchangeConut() < 0 || pool.getExchangeCount() != -1 && pool.getExchangeCount() < 0) {
                throw new BusinessException("次数不能小于0");
            }
            pool.setType(awardPool.getType());
            pool.setProperty(awardPool.getProperty());
            pool.setUserId(awardPool.getUserId());
            pool.setAwardValue(awardValue.toJSONString());
        }
        pool.setCount(awardPool.getCount());
        pool.setExpireDay(awardPool.getExpireDay());
        pool.setId(awardPool.getId());
        pool.setStatus(MarketingEnum.AwardPublishStatus.UP_PUBLISH.getCode());
        pool.setStartTime(awardPool.getStartTime());
        pool.setEndTime(DateTimeUtils.getDateEndTime(awardPool.getEndTime()));
        Date date = new Date();
        pool.setUpdateTime(date);
        int result = awardPoolMapper.updateBean(pool);

        awardInformationRelationMapper.deleteByPoolId(awardPool.getId());
        Integer[] ids = awardPool.getInfoId();
        if (ids != null) {
            for (Integer id : ids) {
                AwardInformationRelation cir = new AwardInformationRelation();
                cir.setAwardPoolId(awardPool.getId());
                cir.setInfoId(id);
                cir.setCreateTime(date);
                cir.setUpdateTime(date);
                awardInformationRelationMapper.insert(cir);
            }
        }
        if (result > 0) {
            //兑奖奖品存入redis
            saveAwardRedis(awardPoolMapper.selectByPrimaryKey(awardPool.getId()));
        }
        return result;
    }

    /**
     * @param :
     */
    @Override
    public void updateOutDateStatus() {
        List<AwardPool> apool = awardPoolMapper.selectOutDateGuys();
        for (AwardPool pool : apool) {
            updateStatus(pool.getId(), MarketingEnum.AwardPublishStatus.DOWN_PUBLISH.getCode().intValue());
        }
    }

    /*
     *上下架奖品
     */
    @Override
    public int updateStatus(Integer id, Integer status) {
        int result = 0;
        if (MarketingEnum.AwardPublishStatus.UP_PUBLISH.getCode().equals(status.byteValue())) {
            AwardPool pool = awardPoolMapper.selectByPrimaryKey(id);
            if (DateTimeUtils.isOutDate(pool.getEndTime())) {
                throw new BusinessException(RetCodeConstants.SAVE_FAILED, "该奖品已过期，不能上架");
            }
        }
        result = awardPoolMapper.updateStatus(id, status);
        if (result > 0) {
            if (MarketingEnum.AwardPublishStatus.UP_PUBLISH.getCode().equals(status.byteValue())) {
                //兑奖奖品存入redis
                saveAwardRedis(awardPoolMapper.selectByPrimaryKey(id));
            } else {
                deleteAwardRedis(id);
            }
        } else {
            throw new BusinessException(RetCodeConstants.SAVE_FAILED, "操作失败,该奖品已是上/下架状态！");
        }
        return result;
    }

    /*
     *后端系统查询兑奖奖池
     */
    @Override
    public PagesDto<AdminAwardPoolDto> selectAwardList(MyCouponInfoParam myCouponInfoParam) {
        List<AdminAwardPoolDto> list = new ArrayList<>();
        int page = myCouponInfoParam.getPage();
        int rows = myCouponInfoParam.getRows();
        UserInfo user = userInfoMapper.selectByPrimaryKey(myCouponInfoParam.getAuthUserId());
        // 后台用户默认书屋ID 为-1
        Integer bookId = -1;
        // 管理者后台查询时 给bookId赋值
        if (user.getUserType().equals(UserEnum.UserType.MOBILE_USER.getCode())) {
            UserManager managerInfo = userManagerMapper.selectByUserId(myCouponInfoParam.getAuthUserId());
            // 管理者没有书屋关联时 直接返回
            if (null != managerInfo && managerInfo.getBookId() == null) {
                return new PagesDto(list, 0, page, rows);
            }
            bookId = managerInfo.getBookId();
        }
        PageHelper.startPage(page, rows);
        list = awardPoolMapper.selectAwardListByBookId(bookId, myCouponInfoParam.getType(), myCouponInfoParam.getStatus());
        PageInfo<AdminAwardPoolDto> pageInfo = new PageInfo<>(list);
        return new PagesDto(list, pageInfo.getTotal(), page, rows);
    }

    /*
     * 前台查询兑奖奖池
     */
    @Override
    public List<AwardPoolDto> selectAwardList(DrawAwardPoolParams params) {
        List<RegionGroupInfo> books = getUserAwardPoolBookInfo(params);
        //平台的通用券
        if (books.isEmpty()) {
            return awardPoolMapper.selectAwardsByBookId(-1);
        } else {
            StringBuilder bookIds = new StringBuilder();
            books.forEach(item -> bookIds.append(item.getId()).append(","));
            bookIds.deleteCharAt(bookIds.toString().length() - 1);
            return awardPoolMapper.selectAwardsByUserAndBookIds(params.getAuthUserId(), bookIds.toString());
        }
    }

    @Override
    public List<AwardPoolDto> selectAwardListMiniProgram(DrawAwardPoolParams params) {
        //最多显示6个
        List<RegionGroupInfo> books = getUserAwardPoolBookInfoMiniProgram(params);
        List<AwardPoolDto> awardPoolDtoList;
        //平台的通用券
        if (books.isEmpty()) {
            awardPoolDtoList = awardPoolMapper.selectAwardsByBookId(-1);
        } else {
            StringBuilder bookIds = new StringBuilder();
            books.forEach(item -> bookIds.append(item.getId()).append(","));
            bookIds.deleteCharAt(bookIds.toString().length() - 1);
            awardPoolDtoList = awardPoolMapper.selectAwardsByUserAndBookIds(params.getAuthUserId(), bookIds.toString());
        }
        if (CollectionUtils.isNotEmpty(awardPoolDtoList)) {
            if (awardPoolDtoList.size() > 6) {
                awardPoolDtoList = awardPoolDtoList.subList(0, 6);
            }
        }
        return awardPoolDtoList;
    }

    /**
     * 小程序推荐兑奖列表
     *
     * @param params
     */
    @Override
    public List<AwardPoolDto> selectRecommendedAwardListMiniProgram(DrawAwardPoolParams params) {
        //平台的通用券  兑换次数比较多的靠前,最多6条
        List<AwardPoolDto> awards = awardPoolMapper.selectRecommendedAwardsByBookIds(String.valueOf(-1));
        return awards;
    }

    /**
     * app推荐兑奖列表
     *
     * @param params
     */
    @Override
    public List<AwardPoolDto> selectRecommendedAwardList(DrawAwardPoolParams params) {
        //用户认证社区对应的书屋
        List<RegionGroupInfo> books = getUserAwardPoolBookInfo(params);
        if (books.isEmpty()) {
            return awardPoolMapper.selectRecommendedAwardsByBookIds(String.valueOf(-1));
        } else {
            StringBuilder bookIds = new StringBuilder();
            books.forEach(item -> bookIds.append(item.getId()).append(","));
            bookIds.deleteCharAt(bookIds.toString().length() - 1);
            return awardPoolMapper.selectRecommendedAwardsByBookIds(bookIds.toString());
        }
    }

    @Override
    public List<AwardPoolDto> selectNearAwardListMiniProgram(DrawAwardPoolParams params) {
        RegionGroupInfo bookHouse = getNearestBookInfo(params.getCityCode(), params.getLongitude(), params.getLatitude());
        double minDistance = 0;
        if (bookHouse != null) {
            minDistance = DistanceUtils.getDistance(bookHouse.getLongitude().doubleValue(), bookHouse.getLatitude().doubleValue(),
                    params.getLongitude().doubleValue(), params.getLatitude().doubleValue());
        }
        List<AwardPoolDto> awards = awardPoolMapper.selectAwardsByBookId(-1);
        if (awards.size() >= 6) {
            awards = awards.subList(0, 6);
        }
        for (AwardPoolDto award : awards) {
            award.setDistance(minDistance);
        }
        return awards;
    }

    @Override
    public List<AwardPoolDto> selectNearAwardList(DrawAwardPoolParams params) {
        return awardPoolMapper.selectNearAwards(params.getLatitude(), params.getLongitude(), params.getCityCode());
    }

    /*
     *后端根据奖品ID查询兑奖奖品信息
     */
    @Override
    public AdminAwardPoolDto selectAwardById(int id) {
        AdminAwardPoolDto app = awardPoolMapper.selectAwardById(id);
        if (app != null) {
            List<Integer> infoIds = awardInformationRelationMapper.selectByPoolId(app.getId());
            Integer[] ids = new Integer[infoIds.size()];
            for (int i = 0; i < infoIds.size(); i++) {
                ids[i] = infoIds.get(i);
            }
            app.setInfoId(ids);
        }
        return app;
    }

    @Override
    public AdminAwardPoolDto selectAwardByIdAndUserId(int id, int userId) {
        AdminAwardPoolDto app = awardPoolMapper.selectAwardByIdAndUserId(id, userId);
        if (app != null) {
            List<Integer> infoIds = awardInformationRelationMapper.selectByPoolId(app.getId());
            Integer[] ids = new Integer[infoIds.size()];
            for (int i = 0; i < infoIds.size(); i++) {
                ids[i] = infoIds.get(i);
            }
            app.setInfoId(ids);
            //用户累计的兑换次数  -1表示无限次数
            if (app.getTotalExchangeConut() > -1) {
                int convertCount = couponInfoMapper.countConvertCountByAwardId(userId, id);
                int remainCount = app.getTotalExchangeConut() - convertCount;
                app.setTotalExchangeConut(remainCount >= 0 ? remainCount : 0);
            }
        }
        return app;
    }

    /*
     *查询用户是否签过到和参加的活动是否有奖
     */
    @Override
    public FoundStatusDto getFoundStatus(int authUserId) {
        int prizeCount = awardPoolMapper.countPrizeActivityByUserId(authUserId);
        int signCount = integrationRecordMapper.selectByUserIdAndToday(authUserId);
        FoundStatusDto fsd = new FoundStatusDto();
        if (prizeCount > 0) {
            fsd.setHavePrize(true);
        } else {
            fsd.setHavePrize(false);
        }
        if (signCount > 0) {
            fsd.setSigned(true);
        } else {
            fsd.setSigned(false);
        }
        return fsd;
    }

    /**
     * 奖券兑换
     *
     * @param params
     */
    @Override
    public int doConvertCoupon(JSONPublicParam<ConvertCouponParams> params) {
        //用户参数
        AuthUserInfo userInfo = params.getAuthUserInfo();
        //业务参数
        ConvertCouponParams param = params.getRequestParam();
        //查看奖品详情
        AwardPool awardPool = awardPoolMapper.selectByPrimaryKey(param.getId());
        if (awardPool == null) {
            logger.error("奖品不存在，params=" + JSON.toJSONString(params));
            throw new BusinessException(RetCodeConstants.AWARD_ERROR, "奖品不存在");
        }
        //查询产品是否上下架
        if (MarketingEnum.AwardPublishStatus.DOWN_PUBLISH.getCode().equals(awardPool.getStatus().byteValue())) {
            logger.error("奖品已下架，params=" + JSON.toJSONString(params));
            throw new BusinessException(RetCodeConstants.AWARD_ERROR, "奖品已下架");
        }
        //统计用户每天的兑换次数
        int convertCount = couponInfoMapper.countEveryDayConvertCountByUserIdAndAwardId(userInfo.getUserId(), param.getId());
        if (awardPool.getExchangeCount() > -1 && convertCount >= awardPool.getExchangeCount()) {
            logger.error("用户当天兑换次数已到上限！");
            throw new BusinessException(RetCodeConstants.AWARD_ERROR, "用户当天兑换次数已到上限");
        }
        //统计总计兑换次数
        if (awardPool.getTotalExchangeConut() > -1) {
            //-1表示无限次数
            convertCount = couponInfoMapper.countConvertCountByAwardId(userInfo.getUserId(), param.getId());
            if (convertCount >= awardPool.getTotalExchangeConut()) {
                logger.error("用户累计兑换次数已到上限！");
                throw new BusinessException(RetCodeConstants.AWARD_ERROR, "用户累计兑换次数已到上限");
            }
        }
        //兑换积分验证
        IntegrationInfo integrationInfo = integrationInfoMapper.selectByUserId(userInfo.getUserId());
        if (integrationInfo == null || integrationInfo.getScore() < awardPool.getExchangeScore()) {
            logger.error("用户积分信息不存在或用户积分不足");
            throw new BusinessException(RetCodeConstants.INTEGRATION_NOT_ENOUGH, "用户积分不足");
        }
        //兑换
        long result = redisCache.decr(awardPool.getId() + "", RedisKeyConstants.AWARD_PRE);
        if (result < 0) {
            logger.error("兑奖奖品扣除剩余数量失败！，params=" + JSON.toJSONString(params));
            throw new BusinessException(RetCodeConstants.INTEGRATION_ERROR, "手太慢已经抢光了");
        }
        try {
            Date date = new Date();
            //记录用户奖券信息
            CouponInfo couponInfo = new CouponInfo();
            couponInfo.setAwardId(awardPool.getId());
            couponInfo.setCouponCode(RandomUtils.getCouponCode());
            couponInfo.setUserId(userInfo.getUserId());
            couponInfo.setType(awardPool.getProperty());
            couponInfo.setAwardName(awardPool.getName());
            couponInfo.setDescription(awardPool.getDescription());
            couponInfo.setInstructions(awardPool.getInstructions());
            if (awardPool.getExpireDay() == null || awardPool.getExpireDay() == 0) {
                couponInfo.setExpireDate(awardPool.getEndTime());
            } else {
                couponInfo.setExpireDate(DateUtils.getAfterADate(date, awardPool.getExpireDay()));
            }
            couponInfo.setCouponValue(awardPool.getAwardValue());
            couponInfo.setInfoAble(awardPool.getInfoEnable());
            couponInfo.setInfoCompleted(false);
            couponInfo.setStatus(MarketingEnum.ConvertStatus.NOT_CONVERT.getCode());
            couponInfo.setFromType(MarketingEnum.CouponFromType.INTEGRATION.getCode());
            couponInfo.setCreateTime(date);
            couponInfo.setUpdateTime(date);
            couponInfo.setImage(awardPool.getImage());
            couponInfoMapper.insertSelective(couponInfo);

            //记录用户奖券获得记录
            userCouponService.addUserCouponRecord(couponInfo.getCouponCode(),
                    MarketingEnum.CouponRecordType.GET.getCode().intValue(),
                    couponInfo.getFromType().intValue(),
                    couponInfo.getId());

            //记录奖券关联信息
            userCouponService.addUserCouponCollectionInfo(MarketingEnum.CouponFromType.INTEGRATION.getCode(), couponInfo);

            //修改奖品剩余数量
            awardPoolMapper.updateCountById(awardPool.getId(), -1);
            //扣除用户积分并记录
            userScoreService.updateUserScoreAndRecord(awardPool.getExchangeScore(),
                    MarketingEnum.ScoreFromType.CONVERT.getCode(),
                    MarketingEnum.CouponRecordType.USE.getCode(),
                    userInfo.getUserId());
            return couponInfo.getId();
        } catch (Exception e) {
            //回滚数量
            redisCache.incr(awardPool.getId() + "", RedisKeyConstants.AWARD_PRE);
            logger.error("该奖品已全部兑完！，params=" + JSON.toJSONString(params), e);
            throw new BusinessException(RetCodeConstants.INTEGRATION_ERROR, "兑换失败，请稍后重试");
        }
    }

    @Override
    public List<Integer> getRegionIdByUserId(int userId) {
        return awardPoolMapper.getRegionIdByUserId(userId);
    }

    @Override
    public List<Integer> getAllBookId() {
        return awardPoolMapper.getAllRegionId();
    }

    @Override
    public PagesDto<MyAwardRecordDto> selectMyRecord(int userId, int type, String couponCode, int page, int rows) {

        PageHelper.startPage(page, rows);
        List<MyAwardRecordDto> list = couponInfoMapper.selectMyRecord(userId, type, couponCode);
        PageInfo<MyAwardRecordDto> pageInfo = new PageInfo<>(list);

        return new PagesDto(list, pageInfo.getTotal(), page, rows);
    }

    @Override
    public JSONPublicDto updatePrizeStatusByCode(String code, int authUserId) {
        return null;
    }

    /**
     * @param params
     * @return bookId  empty 代表平台
     */
    private List<RegionGroupInfo> getUserAwardPoolBookInfo(DrawAwardPoolParams params) {
        //认证社区对应的书屋兑奖奖池
        List<UserRegionPoolInfo> awardPools = awardPoolMapper.selectAwardPoolsByUserId(params.getAuthUserId());
        List<RegionGroupInfo> bookInfos = getNearestAwardPoolBookInfo(awardPools, params);
        return bookInfos;
    }

    private List<RegionGroupInfo> getUserAwardPoolBookInfoMiniProgram(DrawAwardPoolParams params) {
        //认证社区对应的书屋兑奖奖池
        List<UserRegionPoolInfo> awardPools = awardPoolMapper.selectShelfAwardPoolsByUserId(params.getAuthUserId());
        List<RegionGroupInfo> bookInfos = getNearestAwardPoolBookInfo(awardPools, params);
        return bookInfos;
    }

    /**
     * 计算最近的书屋
     *
     * @param awardPools
     * @return
     */
    private List<RegionGroupInfo> getNearestAwardPoolBookInfo(List<UserRegionPoolInfo> awardPools, DrawAwardPoolParams params) {
        List<RegionGroupInfo> bookInfos = new ArrayList<>();
        boolean isNearAwardPool = false;
        if (CollectionUtils.isEmpty(awardPools)) {
            //附近5km的社区对应书屋兑奖奖池
            isNearAwardPool = true;
            awardPools = awardPoolMapper.selectAllAwardPoolsByRegion(params.getCityCode());
        }
        double minDistance;
        int minRegionId = -1;
        if (CollectionUtils.isNotEmpty(awardPools)) {
            //根据region分组
            Map<Integer, List<UserRegionPoolInfo>> regionBooks =
                    awardPools.stream().collect(Collectors.groupingBy(item -> item.getRegionId()));
            if (isNearAwardPool) {
                minDistance = 5 * 1000;
            } else {
                List<UserRegionPoolInfo> first = regionBooks.entrySet().iterator().next().getValue();
                minDistance = DistanceUtils.getDistance(first.get(0).getLongitude().doubleValue(), first.get(0).getLatitude().doubleValue(),
                        params.getLongitude().doubleValue(), params.getLatitude().doubleValue());
                minRegionId = regionBooks.entrySet().iterator().next().getKey();
            }
            //查找最近的社区
            for (Integer key : regionBooks.keySet()) {
                double distance = DistanceUtils.getDistance(regionBooks.get(key).get(0).getLongitude().doubleValue(), regionBooks.get(key).get(0).getLatitude().doubleValue(),
                        params.getLongitude().doubleValue(), params.getLatitude().doubleValue());
                if (distance < minDistance) {
                    minDistance = distance;
                    minRegionId = key;
                }
            }
            //附近5km社区奖有池
            if (minRegionId != -1) {
                List<UserRegionPoolInfo> subList = regionBooks.get(minRegionId);
                for (UserRegionPoolInfo userRegionPoolInfo : subList) {
                    RegionGroupInfo regionGroupInfo = new RegionGroupInfo();
                    regionGroupInfo.setId(userRegionPoolInfo.getBookId());
                    regionGroupInfo.setLatitude(userRegionPoolInfo.getBookLatitude());
                    regionGroupInfo.setLongitude(userRegionPoolInfo.getBookLongitude());
                    bookInfos.add(regionGroupInfo);
                }
            }
        }
        return bookInfos;
    }


    /**
     * 查找距离最近的书屋
     *
     * @param cityCode
     * @param longitude
     * @param latitude
     */
    private RegionGroupInfo getNearestBookInfo(String cityCode, BigDecimal longitude, BigDecimal latitude) {
        List<RegionGroupInfo> bookHouses = regionGroupInfoMapper.selectBookHouseByCityCode(cityCode);
        //距离最近的书屋
        double minDistance = 0;
        int index = -1;
        for (int i = 0; i < bookHouses.size(); i++) {
            double distance = DistanceUtils.getDistance(bookHouses.get(i).getLongitude().doubleValue(), bookHouses.get(i).getLatitude().doubleValue(),
                    longitude.doubleValue(), latitude.doubleValue());
            if (i == 0) {
                minDistance = distance;
                index = 0;
            } else if (distance < minDistance) {
                minDistance = distance;
                index = i;
            }
        }
        if (index > -1) {
            return bookHouses.get(index);
        }
        return null;
    }

    @Override
    public void deleteAwardById(int id) {
        AwardPool awardPool = awardPoolMapper.selectByPrimaryKey(id);
        if (awardPool == null) {
            throw new BusinessException("未查询到id为：" + id + "的奖品，请检查！");
        }
        if (awardPool.getStatus().equals(MarketingEnum.AwardPublishStatus.DOWN_PUBLISH.getCode())) {
            int res = awardPoolMapper.deleteByPrimaryKey(id);
            logger.info("删除了" + res + "条数据！");
        } else {
            throw new BusinessException("该奖品未下架不能删除！");
        }
    }

}
