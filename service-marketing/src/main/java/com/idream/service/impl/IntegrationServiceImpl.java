package com.idream.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.idream.basic.BasicService;
import com.idream.commons.db.redis.RedisCache;
import com.idream.commons.db.redis.RedisKeyConstants;
import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.base.SystemConfigCodeConstans;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.marketing.BookHouseDto;
import com.idream.commons.lib.dto.marketing.BookHouseParams;
import com.idream.commons.lib.dto.marketing.CouponInfoDto;
import com.idream.commons.lib.dto.marketing.CouponInfoParam;
import com.idream.commons.lib.dto.marketing.CouponRecordDto;
import com.idream.commons.lib.dto.marketing.IntegrationDrawDto;
import com.idream.commons.lib.dto.marketing.IntegrationDrawParams;
import com.idream.commons.lib.dto.marketing.IntegrationPoolDto;
import com.idream.commons.lib.dto.marketing.IntegrationPoolParams;
import com.idream.commons.lib.dto.marketing.IntegrationRule;
import com.idream.commons.lib.dto.marketing.MarketingRecordDto;
import com.idream.commons.lib.dto.marketing.RegionPollParams;
import com.idream.commons.lib.dto.marketing.UserRegionPoolInfo;
import com.idream.commons.lib.dto.marketing.UserWinningRecord;
import com.idream.commons.lib.dto.rabbitmq.AwardAsyncDto;
import com.idream.commons.lib.dto.rabbitmq.IntegrationAwardAsyncInsertDto;
import com.idream.commons.lib.dto.token.AuthUserInfo;
import com.idream.commons.lib.enums.MarketingEnum;
import com.idream.commons.lib.enums.SystemEnum;
import com.idream.commons.lib.enums.UserEnum;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.mapper.BannerImageMapper;
import com.idream.commons.lib.mapper.CouponInfoMapper;
import com.idream.commons.lib.mapper.ExchangeRecordMapper;
import com.idream.commons.lib.mapper.InformationRuleMapper;
import com.idream.commons.lib.mapper.IntegrationInfoMapper;
import com.idream.commons.lib.mapper.IntegrationInformationRelationMapper;
import com.idream.commons.lib.mapper.IntegrationPoolMapper;
import com.idream.commons.lib.mapper.RegionGroupInfoMapper;
import com.idream.commons.lib.mapper.SystemConfigMapper;
import com.idream.commons.lib.mapper.UserInfoMapper;
import com.idream.commons.lib.mapper.UserManagerMapper;
import com.idream.commons.lib.mapper.UserRegionRelationMapper;
import com.idream.commons.lib.model.CouponInfo;
import com.idream.commons.lib.model.ExchangeRecord;
import com.idream.commons.lib.model.InformationRule;
import com.idream.commons.lib.model.IntegrationInfo;
import com.idream.commons.lib.model.IntegrationInformationRelation;
import com.idream.commons.lib.model.IntegrationPool;
import com.idream.commons.lib.model.UserInfo;
import com.idream.commons.lib.model.UserManager;
import com.idream.commons.lib.util.DateTimeUtils;
import com.idream.commons.lib.util.DistanceUtils;
import com.idream.commons.lib.util.DrawLotteryUtils;
import com.idream.rabbit.RabbitSendService;
import com.idream.rabbit.SocketSendService;
import com.idream.service.AwardService;
import com.idream.service.IntegrationService;
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
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Description :
 * Created by 肖刚 on 2018/4/11.
 */
@Service
public class IntegrationServiceImpl implements IntegrationService {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private IntegrationPoolMapper integrationPoolMapper;
    @Autowired
    private InformationRuleMapper informationRuleMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserManagerMapper userManagerMapper;
    @Autowired
    private IntegrationInformationRelationMapper integrationInformationRelationMapper;
    @Autowired
    private ExchangeRecordMapper exchangeRecordMapper;
    @Resource
    private RedisCache redisCache;
    @Resource
    private IntegrationInfoMapper integrationInfoMapper;
    @Resource
    private SystemConfigMapper systemConfigMapper;
    @Resource
    private UserScoreService userScoreService;
    @Resource
    private SocketSendService socketSendService;
    @Autowired
    private UserRegionRelationMapper userRegionRelationMapper;
    @Resource
    private AwardService awardService;
    @Resource
    private RegionGroupInfoMapper regionGroupInfoMapper;
    @Resource
    private CouponInfoMapper couponInfoMapper;
    @Resource
    private RabbitSendService rabbitSendService;
    @Resource
    private BannerImageMapper bannerImageMapper;

    /**
     * 新增抽奖
     *
     * @param bean
     */
    @Override
    public void saveIntegrationPool(IntegrationPoolParams bean) {
        // 参数校验
        if (bean.getType().equals(MarketingEnum.CouponTypeEnum.REGION.getCode()) && bean.getBookId() == null) {
            throw new BusinessException("区域券绑定书屋不能为空！");
        } else if (bean.getType().equals(MarketingEnum.CouponTypeEnum.COMMON.getCode()) && bean.getBookId() == null) {
            bean.setBookId(-1);
        }
        List<IntegrationPool> pools = null;
        JSONObject awardJson = new JSONObject();
        // 书屋管理平台券格式校验
        if (UserEnum.UserType.MANAGE_WEB_USER.getCode().equals(bean.getAddUserType())) {
            // 参数校验
            awardJson = getIntegrationAwardValueAndCheck(bean);
            //查询书屋管理员
            UserManager manager = userManagerMapper.selectByUserId(bean.getUserId());
            //管理者所属书屋下奖池里上架的所有奖品信息
            pools = integrationPoolMapper.selectListByStatusAndBookId(manager.getBookId());
        } else {
            //管理后台积分券校验
            if (MarketingEnum.AwardProperty.SCORE.getCode().equals(bean.getProperty())) {
                IntegrationRule awardRule = bean.getIntegrationRule();
                if (awardRule == null || StringUtils.isEmpty(awardRule.getScore())) {
                    throw new BusinessException("积分值不能为空！");
                }
                if (!Pattern.matches("\\d+", awardRule.getScore())) {
                    throw new BusinessException("积分值格式只能是正整数！");
                }
                awardJson.put(MarketingEnum.AwardValueKey.SCORE.getCode(), awardRule.getScore());
            }
            //查询平台奖池里上架的所有奖品信息
            pools = integrationPoolMapper.selectListByStatus();
        }
        BigDecimal total = BigDecimal.ZERO;
        for (int j = 0; j < pools.size(); j++) {
            total = total.add(pools.get(j).getProbability());
        }
        total = total.add(bean.getProbability());
        //如果已上架的商品概率和大于100则默认新增的奖品为下架状态
        if ((total.compareTo(new BigDecimal(MarketingEnum.ProbabilityEnum.All.getCode())) < 1)) {
            bean.setStatus(MarketingEnum.AwardPublishStatus.UP_PUBLISH.getCode().intValue());
        } else {
            bean.setStatus(MarketingEnum.AwardPublishStatus.DOWN_PUBLISH.getCode().intValue());
        }

        IntegrationPool poolEntity = new IntegrationPool();
        //对于中台而言如果为非实物券则保存规则数据为json格式
        poolEntity.setIntegrationValue(awardJson.toJSONString());
        Date date = new Date();
        poolEntity.setCreateTime(date);
        poolEntity.setUpdateTime(date);
        poolEntity.setUserId(bean.getUserId());
        poolEntity.setAddUserType(bean.getAddUserType().byteValue());
        poolEntity.setType(bean.getType());
        poolEntity.setProperty(bean.getProperty().byteValue());
        //poolEntity.setCount(bean.getCount());
        poolEntity.setDescription(bean.getDescription());
        poolEntity.setExpireDay(bean.getExpireDay());
        poolEntity.setImage(bean.getImage());
        poolEntity.setInfoEnable(bean.getInfoEnable());
        poolEntity.setInstructions(bean.getInstructions());
        poolEntity.setName(bean.getName());
        poolEntity.setIntroduce(bean.getIntroduce());
        poolEntity.setProbability(bean.getProbability());
        poolEntity.setBookId(bean.getBookId());
        poolEntity.setStartTime(bean.getStartTime());
        poolEntity.setEndTime(DateTimeUtils.getDateEndTime(bean.getEndTime()));

        List<Integer> regionIds = new ArrayList<>();
        if (bean.getBookId() == -1) {
            regionIds = awardService.getAllBookId();
            //把-1这条数据也插入到表中
            regionIds.add(-1);
        } else {
            regionIds.add(bean.getBookId());
        }
        for (Integer regionId : regionIds) {
            //批量同步到管理者列表时默认状态为下架
            if (bean.getType().equals(MarketingEnum.CouponTypeEnum.COMMON.getCode()) && regionId != -1) {
                poolEntity.setStatus(MarketingEnum.AwardPublishStatus.DOWN_PUBLISH.getCode());
                //数量同步0
                poolEntity.setCount(0);
            } else {
                poolEntity.setStatus(bean.getStatus().byteValue());
                poolEntity.setCount(bean.getCount());
            }
            poolEntity.setBookId(regionId);
            int result = integrationPoolMapper.insert(poolEntity);
            //存储抽奖奖品信息
            if (result > 0 && MarketingEnum.AwardPublishStatus.UP_PUBLISH.getCode().equals(poolEntity.getStatus())) {
                saveLotteryAward(poolEntity.getId(), poolEntity.getCount(), poolEntity.getEndTime());
            }
            //获得信息项并且进行保存
            Integer[] infoIds = bean.getInfoId();
            if (infoIds != null) {
                for (Integer id : infoIds) {
                    IntegrationInformationRelation cir = new IntegrationInformationRelation();
                    cir.setIntegrationPoolId(poolEntity.getId());
                    cir.setInfoId(id);
                    cir.setCreateTime(date);
                    cir.setUpdateTime(date);
                    integrationInformationRelationMapper.insert(cir);
                }
            }
        }
    }

    /**
     * 抽奖奖券校验
     *
     * @param bean
     */
    private JSONObject getIntegrationAwardValueAndCheck(IntegrationPoolParams bean) {
        JSONObject awardJson = new JSONObject();
        String pattern = "\\d+(\\.\\d+)?";
        if (MarketingEnum.AwardProperty.FULL_CUT.getCode().equals(bean.getProperty())) {
            IntegrationRule awardRule = bean.getIntegrationRule();
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
        } else if (MarketingEnum.AwardProperty.DISCOUNT.getCode().equals(bean.getProperty())) {
            IntegrationRule awardRule = bean.getIntegrationRule();
            if (awardRule == null || StringUtils.isEmpty(awardRule.getDiscount())) {
                throw new BusinessException("折扣信息不能为空！");
            }
            if (!Pattern.matches(pattern, awardRule.getDiscount())) {
                throw new BusinessException("折扣信息格式只能是大于0的整数或小数！");
            }
            awardJson.put(MarketingEnum.AwardValueKey.DISCOUNT.getCode(), awardRule.getDiscount());
        } else if (MarketingEnum.AwardProperty.REPLACE_CASH.getCode().equals(bean.getProperty())) {
            IntegrationRule awardRule = bean.getIntegrationRule();
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
     * 存储抽奖奖品数量信息
     *
     * @param id
     * @param count
     * @param expireTime 失效时间
     */
    private void saveLotteryAward(int id, int count, Date expireTime) {
        //保存抽奖奖品数量
        boolean saveFlag = redisCache.setString(String.valueOf(id), String.valueOf(count), RedisKeyConstants.INTEGRATION_AWARD_COUNT);
        //设置失效时间
        redisCache.pexpireAt(String.valueOf(id), RedisKeyConstants.INTEGRATION_AWARD_COUNT, expireTime.getTime());
    }

    /**
     * 删除抽奖奖品数量
     *
     * @param id
     */
    private void delLotteryAward(int id) {
        long saveLotteryCount = redisCache.del(String.valueOf(id), RedisKeyConstants.INTEGRATION_AWARD_COUNT);
    }

    /**
     * 修改抽奖
     *
     * @param bean
     */
    @Override
    public int updateIntegrationPool(IntegrationPoolParams bean) {
        // 参数校验
        if (bean.getType().equals(MarketingEnum.CouponTypeEnum.REGION.getCode()) && bean.getBookId() == null) {
            throw new BusinessException("区域券绑定书屋不能为空！");
        } else if (bean.getType().equals(MarketingEnum.CouponTypeEnum.COMMON.getCode()) && bean.getBookId() == null) {
            bean.setBookId(-1);
        }
        JSONObject awardJson = new JSONObject();
        // 书屋管理平台券格式校验
        if (UserEnum.UserType.MANAGE_WEB_USER.getCode().equals(bean.getAddUserType())) {
            awardJson = getIntegrationAwardValueAndCheck(bean);
        } else {
            //管理后台积分券校验
            if (MarketingEnum.AwardProperty.SCORE.getCode().equals(bean.getProperty())) {
                IntegrationRule awardRule = bean.getIntegrationRule();
                if (awardRule == null || StringUtils.isEmpty(awardRule.getScore())) {
                    throw new BusinessException("积分值不能为空！");
                }
                if (!Pattern.matches("\\d+", awardRule.getScore())) {
                    throw new BusinessException("积分值格式只能是正整数！");
                }
                awardJson.put(MarketingEnum.AwardValueKey.SCORE.getCode(), awardRule.getScore());
            }
        }
        IntegrationPool poolEntity = new IntegrationPool();
        // 管理后台请求 或者 区域券类型的 所有信息都可改
        if (UserEnum.UserType.MOBILE_USER.getCode().equals(bean.getAddUserType())
                || MarketingEnum.CouponTypeEnum.REGION.getCode().equals(bean.getType())) {
            poolEntity.setType(bean.getType().byteValue());
            poolEntity.setProperty(bean.getProperty().byteValue());
            poolEntity.setDescription(bean.getDescription());
            poolEntity.setImage(bean.getImage());
            poolEntity.setInfoEnable(bean.getInfoEnable());
            poolEntity.setInstructions(bean.getInstructions());
            poolEntity.setName(bean.getName());
            poolEntity.setIntroduce(bean.getIntroduce());
            poolEntity.setBookId(bean.getBookId());
            poolEntity.setIntroduce(bean.getIntroduce());
            poolEntity.setIntegrationValue(awardJson.toJSONString());
        }
        Date date = new Date();
        poolEntity.setCount(bean.getCount());
        poolEntity.setProbability(bean.getProbability());
        poolEntity.setExpireDay(bean.getExpireDay());
        poolEntity.setStartTime(bean.getStartTime());
        poolEntity.setEndTime(DateTimeUtils.getDateEndTime(bean.getEndTime()));
        poolEntity.setId(bean.getId());
        poolEntity.setUpdateTime(date);
        //修改后默认为下架
        poolEntity.setStatus(MarketingEnum.AwardPublishStatus.DOWN_PUBLISH.getCode());
        int result = integrationPoolMapper.updateBean(poolEntity);

        //根据奖池ID删除多条
        integrationInformationRelationMapper.deleteByPoolId(bean.getId());
        //获得奖品需要填写的信息项并且进行保存
        Integer[] ids = bean.getInfoId();
        if (ids != null) {
            for (Integer id : ids) {
                IntegrationInformationRelation cir = new IntegrationInformationRelation();
                cir.setIntegrationPoolId(bean.getId());
                cir.setInfoId(id);
                cir.setCreateTime(date);
                cir.setUpdateTime(date);
                integrationInformationRelationMapper.insert(cir);
            }
        }
        //修改信息
        if (result > 0 && MarketingEnum.AwardPublishStatus.UP_PUBLISH.getCode().equals(poolEntity.getStatus())) {
            saveLotteryAward(poolEntity.getId(), poolEntity.getCount(), poolEntity.getEndTime());
        }
        return result;
    }

    @Override
    public int updateIntegrationPoolByBookId(IntegrationPoolParams bean) {
        // 参数校验
        if (bean.getType().equals(MarketingEnum.CouponTypeEnum.REGION.getCode()) && bean.getBookId() == null) {
            throw new BusinessException("区域券绑定书屋不能为空！");
        } else if (bean.getType().equals(MarketingEnum.CouponTypeEnum.COMMON.getCode()) && bean.getBookId() == null) {
            bean.setBookId(-1);
        }
        // 书屋管理平台券格式校验
        JSONObject awardJson = getIntegrationAwardValueAndCheck(bean);
        //积分券校验
        if (MarketingEnum.AwardProperty.SCORE.getCode().equals(bean.getProperty())) {
            IntegrationRule awardRule = bean.getIntegrationRule();
            if (awardRule == null || StringUtils.isEmpty(awardRule.getScore())) {
                throw new BusinessException("积分值不能为空！");
            }
            if (!Pattern.matches("\\d+", awardRule.getScore())) {
                throw new BusinessException("积分值格式只能是正整数！");
            }
            awardJson.put(MarketingEnum.AwardValueKey.SCORE.getCode(), awardRule.getScore());
        }


        IntegrationPool poolEntity = new IntegrationPool();
        // 管理后台请求 或者 区域券类型的 所有信息都可改
        if (UserEnum.UserType.MOBILE_USER.getCode().equals(bean.getAddUserType())
                || MarketingEnum.CouponTypeEnum.REGION.getCode().equals(bean.getType())) {
            poolEntity.setType(bean.getType().byteValue());
            poolEntity.setProperty(bean.getProperty().byteValue());
            poolEntity.setDescription(bean.getDescription());
            poolEntity.setImage(bean.getImage());
            poolEntity.setInfoEnable(bean.getInfoEnable());
            poolEntity.setInstructions(bean.getInstructions());
            poolEntity.setName(bean.getName());
            poolEntity.setIntroduce(bean.getIntroduce());
            poolEntity.setBookId(bean.getBookId());
            poolEntity.setIntroduce(bean.getIntroduce());
            poolEntity.setIntegrationValue(awardJson.toJSONString());
        }
        Date date = new Date();
        poolEntity.setCount(bean.getCount());
        poolEntity.setProbability(bean.getProbability());
        poolEntity.setExpireDay(bean.getExpireDay());
        poolEntity.setStartTime(bean.getStartTime());
        poolEntity.setEndTime(DateTimeUtils.getDateEndTime(bean.getEndTime()));
        poolEntity.setId(bean.getId());
        poolEntity.setUpdateTime(date);
        //修改后默认为下架
        poolEntity.setStatus(MarketingEnum.AwardPublishStatus.DOWN_PUBLISH.getCode());
        int result = integrationPoolMapper.updateBean(poolEntity);

        //根据奖池ID删除多条
        integrationInformationRelationMapper.deleteByPoolId(bean.getId());
        //获得奖品需要填写的信息项并且进行保存
        Integer[] ids = bean.getInfoId();
        if (ids != null) {
            for (Integer id : ids) {
                IntegrationInformationRelation cir = new IntegrationInformationRelation();
                cir.setIntegrationPoolId(bean.getId());
                cir.setInfoId(id);
                cir.setCreateTime(date);
                cir.setUpdateTime(date);
                integrationInformationRelationMapper.insert(cir);
            }
        }
        //修改信息
        if (result > 0 && MarketingEnum.AwardPublishStatus.UP_PUBLISH.getCode().equals(poolEntity.getStatus())) {
            saveLotteryAward(poolEntity.getId(), poolEntity.getCount(), poolEntity.getEndTime());
        }
        return result;
    }

    /**
     * @param :
     */
    @Override
    public void updateOutDateStatus() {
        List<IntegrationPool> apool = integrationPoolMapper.selectOutDateGuys();
        for (IntegrationPool pool : apool) {
            updateStatus(null, pool.getId(), MarketingEnum.AwardPublishStatus.DOWN_PUBLISH.getCode().intValue());
        }
    }

    /**
     * 上下架奖品
     *
     * @param userId,id,status
     */
    @Override
    public int updateStatus(Integer userId, Integer id, Integer status) {
        int result = 0;
        if (MarketingEnum.AwardPublishStatus.UP_PUBLISH.getCode().equals(status.byteValue())) {
            IntegrationPool pool = integrationPoolMapper.selectByPrimaryKey(id);
            if (DateTimeUtils.isOutDate(pool.getEndTime())) {
                throw new BusinessException(RetCodeConstants.SAVE_FAILED, "该奖品已过期，不能上架");
            }
        }
        //上架商品时，判断当前奖池的奖品概率是否大过100
        if (status == MarketingEnum.AwardPublishStatus.UP_PUBLISH.getCode().intValue()) {
            UserInfo user = userInfoMapper.selectByPrimaryKey(userId);
            List<IntegrationPool> pool = new ArrayList<>();
            if (user.getUserType().equals(UserEnum.UserType.MANAGE_WEB_USER.getCode())) {
                //平台通用券
                pool = integrationPoolMapper.selectListByStatus();
            } else {
                //管理者所在书屋奖券
                pool = integrationPoolMapper.selectListByUserId(userId);
            }
            if (pool.size() > 0) {
                BigDecimal total = new BigDecimal(0);
                for (int j = 0; j < pool.size(); j++) {
                    total = total.add(pool.get(j).getProbability());
                }
                IntegrationPool bean = integrationPoolMapper.selectByPrimaryKey(id);
                total = total.add(bean.getProbability());
                if ((total.compareTo(new BigDecimal(MarketingEnum.ProbabilityEnum.All.getCode())) == 1)) {
                    throw new BusinessException(RetCodeConstants.SAVE_FAILED, "总概率大于100，上架失败！");
                }
            }
        }
        result = integrationPoolMapper.updateStatus(id, status);
        if (result > 0) {
            if (MarketingEnum.AwardPublishStatus.UP_PUBLISH.getCode().equals(status.byteValue())) {
                IntegrationPool bean = integrationPoolMapper.selectByPrimaryKey(id);
                //修改信息
                saveLotteryAward(bean.getId(), bean.getCount(), bean.getEndTime());
            } else {
                delLotteryAward(id);
            }
        }
        return result;
    }

    @Override
    public void updateStatusByBookId(int authUserId, Integer id, Byte status, Integer bookId) {
        int result = 0;
        if (MarketingEnum.AwardPublishStatus.UP_PUBLISH.getCode().equals(status)) {
            IntegrationPool pool = integrationPoolMapper.selectByPrimaryKey(id);
            if (DateTimeUtils.isOutDate(pool.getEndTime())) {
                throw new BusinessException(RetCodeConstants.SAVE_FAILED, "该奖品已过期，不能上架");
            }
            //上架商品时，判断当前奖池的奖品概率是否大过100
            List<IntegrationPool> pools = integrationPoolMapper.selectListByBookId(bookId);
            if (pools.size() > 0) {
                BigDecimal total = new BigDecimal(0);
                for (IntegrationPool p : pools) {
                    total = total.add(p.getProbability());
                }
                IntegrationPool bean = integrationPoolMapper.selectByPrimaryKey(id);
                total = total.add(bean.getProbability());
                if ((total.compareTo(new BigDecimal(MarketingEnum.ProbabilityEnum.All.getCode())) > 0)) {
                    throw new BusinessException(RetCodeConstants.SAVE_FAILED, "总概率大于100，上架失败！");
                }
            }
        }
        result = integrationPoolMapper.updateStatus(id, status.intValue());
        if (result > 0) {
            if (MarketingEnum.AwardPublishStatus.UP_PUBLISH.getCode().equals(status)) {
                IntegrationPool bean = integrationPoolMapper.selectByPrimaryKey(id);
                //修改信息
                saveLotteryAward(bean.getId(), bean.getCount(), bean.getEndTime());
            } else {
                delLotteryAward(id);
            }
        }
    }

    /**
     * 兑奖
     *
     * @param code, authUserId
     */
    @Override
    public void updatePrizeStatus(String code, Integer authUserId) {
        CouponInfo info = couponInfoMapper.selectOneByCode(code);
        if (info == null) {
            throw new BusinessException(RetCodeConstants.SAVE_FAILED, "奖券信息不存在，兑换失败！");
        }
        if (MarketingEnum.ConvertStatus.CONVERT_SUCCESS.getCode().equals(info.getStatus())) {
            throw new BusinessException(RetCodeConstants.SAVE_FAILED, "该奖券已兑换!");
        }
        if (MarketingEnum.ConvertStatus.EXPIRE.getCode().equals(info.getStatus())) {
            throw new BusinessException(RetCodeConstants.SAVE_FAILED, "该奖券已过期!");
        }
        //积分券不用兑换人信息
        if (info.getInfoAble() && !info.getInfoCompleted()) {
            throw new BusinessException(RetCodeConstants.SAVE_FAILED, "该奖券需要补充兑换人信息!");
        }
        //修改兑奖状态
        int result = couponInfoMapper.updateStatusAndCouponUserIdById(
                MarketingEnum.ConvertStatus.CONVERT_SUCCESS.getCode(), authUserId, info.getId());
        if (result > 0) {
            //插入兑奖记录表
            ExchangeRecord record = new ExchangeRecord();
            Date date = new Date();
            record.setCreateTime(date);
            record.setUpdateTime(date);
            record.setauthUserId(authUserId);
            record.setPhone(getUserPhone(info.getUserId()).getPhone());
            record.setCouponCode(info.getCouponCode());
            record.setCouponId(info.getId());
            record.setAwardName(info.getAwardName());
            record.setUserId(info.getUserId());
            exchangeRecordMapper.insert(record);
        } else {
            throw new BusinessException(RetCodeConstants.SAVE_FAILED, "兑换失败, 请稍后重试!");
        }
    }

    /**
     * 查询奖品状态
     *
     * @param code
     */
    @Override
    public String selectPrizeStatus(String code) {
        String retCode = null;
        Integer result = couponInfoMapper.selectStatus(code);
        if (result != null) {
            if (result == MarketingEnum.ConvertStatus.NOT_CONVERT.getCode().intValue()) {
                retCode = RetCodeConstants.EXCHANGEED_WAIT;
            } else if (result == MarketingEnum.ConvertStatus.CONVERT_SUCCESS.getCode().intValue()) {
                retCode = RetCodeConstants.EXCHANGEED_ERROR;
            } else {
                retCode = RetCodeConstants.EXCHANGE_OUTDATE;
            }
        } else {
            retCode = RetCodeConstants.UNKOWN_ERROR;
        }
        return retCode;
    }

    /**
     * 获取奖品所有选填信息项
     *
     * @param
     */
    @Override
    public List<InformationRule> getAwardInformationRules() {
        return informationRuleMapper.getAwardInformationRules();
    }

    /**
     * 查询中奖奖券记录
     *
     * @param couponInfoParam
     */
    @Override
    public PagesDto<CouponInfoDto> selectCouponList(CouponInfoParam couponInfoParam) {
        PageHelper.startPage(couponInfoParam.getPage(), couponInfoParam.getRows());
        List<CouponInfoDto> list = couponInfoMapper.selectCouponList(couponInfoParam);
        PageInfo<CouponInfoDto> pageInfo = new PageInfo<>(list);

        return new PagesDto(list, pageInfo.getTotal(), couponInfoParam.getPage(), couponInfoParam.getRows());
    }

    /**
     * 查询用户中奖奖券记录
     *
     * @param userId
     */
    @Override
    public PagesDto<CouponInfoDto> selectCouponByUserId(int page, int rows, int userId) {
        PageHelper.startPage(page, rows);
        List<CouponInfoDto> list = couponInfoMapper.selectCouponByUserId(userId);
        PageInfo<CouponInfoDto> pageInfo = new PageInfo<>(list);

        return new PagesDto(list, pageInfo.getTotal(), page, rows);
    }

    /**
     * 查询抽奖奖池
     *
     * @param
     */
    @Override
    public PagesDto<IntegrationPoolDto> selectIntegrationPoolList(int page, int rows, int userId, Integer type) {
        UserManager manager = userManagerMapper.selectByUserId(userId);
        UserInfo user = userInfoMapper.selectByPrimaryKey(userId);
        Integer bookId = -1;
        List<IntegrationPoolDto> list = new ArrayList<>();
        if (user.getUserType() == null) {
            throw new BusinessException("用户类型为空，查询失败！");
        }
        if (user.getUserType().equals(UserEnum.UserType.MOBILE_USER.getCode())) {
            if (manager != null && manager.getBookId() == null) {
                return new PagesDto(list, 0, page, rows);
            }
            bookId = manager.getBookId();
        }
        PageHelper.startPage(page, rows);
        list = integrationPoolMapper.selectIntegrationPoolList(bookId, type);
        if (list.size() > 0) {
            for (IntegrationPoolDto pool : list) {
                List<Integer> infoIds = integrationInformationRelationMapper.selectByPoolId(pool.getId());
                Integer[] ids = new Integer[infoIds.size()];
                for (int i = 0; i < infoIds.size(); i++) {
                    ids[i] = infoIds.get(i);
                }
                pool.setInfoId(ids);
            }
        }
        PageInfo<IntegrationPoolDto> pageInfo = new PageInfo<>(list);

        return new PagesDto(list, pageInfo.getTotal(), page, rows);
    }

    /**
     * 查询抽奖奖池
     *
     * @param
     */
    @Override
    public PagesDto<IntegrationPool> selectMiniIntegrationPoolList(int page, int rows) {
        Date date = new Date();
        PageHelper.startPage(page, rows);
        List<IntegrationPool> list = integrationPoolMapper.selectMiniIntegrationPoolList();
        PageInfo<IntegrationPool> pageInfo = new PageInfo<>(list);

        return new PagesDto(list, pageInfo.getTotal(), page, rows);
    }

    /**
     * 查询用户电话
     *
     * @param authUserId
     */
    @Override
    public UserInfo getUserPhone(int authUserId) {
        return userInfoMapper.selectByPrimaryKey(authUserId);
    }

    /**
     * 查询奖券明细
     *
     * @param couponId
     */
    @Override
    public CouponInfoDto selectOneByCouponId(int couponId) {
        return couponInfoMapper.selectOneByCouponId(couponId);
    }

    /**
     * 查询抽奖奖品明细
     *
     * @param id
     */
    @Override
    public IntegrationPoolDto selectIntegrationById(int id) {
        IntegrationPoolDto ipp = integrationPoolMapper.selectIntegrationById(id);
        if (ipp != null) {
            //查询需要填写的信息项
            List<Integer> infoIds = integrationInformationRelationMapper.selectByPoolId(ipp.getId());
            Integer[] ids = new Integer[infoIds.size()];
            for (int i = 0; i < infoIds.size(); i++) {
                ids[i] = infoIds.get(i);
            }
            ipp.setInfoId(ids);
        }
        return ipp;
    }

    /**
     * @Author: hejiang
     * @Description: 积分抽奖
     * @Date: 20:46 2018/4/16
     */
    @Override
    public IntegrationDrawDto doIntegrationDraw(JSONPublicParam<IntegrationDrawParams> params) {
        //用户参数
        AuthUserInfo userInfo = params.getAuthUserInfo();
        //业务参数
        IntegrationDrawParams param = params.getRequestParam();
        //获取用户积分信息
        IntegrationInfo integrationInfo = integrationInfoMapper.selectByUserId(userInfo.getUserId());
        if (integrationInfo == null) {
            logger.error("用户积分信息不存在！，params=" + JSON.toJSONString(params));
            throw new BusinessException(RetCodeConstants.INTEGRATION_ERROR, "积分信息不存在");
        }
        //获取用户消耗抽奖积分
        Integer awardScore = systemConfigMapper.selectIntegerValueByConfigCode(SystemConfigCodeConstans.LOTTERY_CODE);
        if (awardScore == null) {
            logger.error("抽奖消耗积分信息不存在！，params=" + JSON.toJSONString(params));
            throw new BusinessException(RetCodeConstants.INTEGRATION_ERROR, "抽奖消耗积分未配置!");
        }
        //积分抽奖
        if (MarketingEnum.IntegrationAwardType.SCORE.getCode().equals(param.getIntegrationDrawType())) {
            //校验用户积分
            if (awardScore > integrationInfo.getScore()) {
                logger.error("用户积分不足！，params=" + JSON.toJSONString(params));
                throw new BusinessException(RetCodeConstants.INTEGRATION_NOT_ENOUGH, "积分不足");
            }
            //扣除用户积分并记录
            userScoreService.updateUserScoreAndRecord(awardScore,
                    MarketingEnum.ScoreFromType.LOTTERY_DRAW.getCode(),
                    MarketingEnum.CouponRecordType.USE.getCode(), userInfo.getUserId());
        } else if (MarketingEnum.IntegrationAwardType.FREE.getCode().equals(param.getIntegrationDrawType())) {
            //免费抽奖
            //校验免费抽奖次数
            if (integrationInfo.getFreeLottery() <= 0) {
                logger.error("免费抽奖次数不够！，params=" + JSON.toJSONString(params));
                throw new BusinessException(RetCodeConstants.INTEGRATION_ERROR, "您当前没有免费抽奖机会");
            }
            //扣除用户免费抽奖次数
            userScoreService.updateUserScoreAndFreeLottery(0, -1, userInfo.getUserId());
        }

        //获取当前奖池ID的所有抽奖奖品
        List<IntegrationPool> awards = getIntegerationAward(param.getBookId());
        //根据概率抽奖
        IntegrationPool lotteryAward = DrawLotteryUtils.drawGift(awards);
        //组装返回数据
        IntegrationDrawDto dto = new IntegrationDrawDto();
        dto.setAwardName(lotteryAward.getName());
        dto.setType(lotteryAward.getProperty());
        dto.setAwardImage(lotteryAward.getImage());
        dto.setIntegrationValue(lotteryAward.getIntegrationValue());
        if (lotteryAward != null && lotteryAward.getId() > 0) {
            //redis数量减一
            Long result = redisCache.decr(String.valueOf(lotteryAward.getId()), RedisKeyConstants.INTEGRATION_AWARD_COUNT);
            if (result < 0) {
                logger.error("兑奖奖品扣除剩余数量失败！，params=" + JSON.toJSONString(params));
                throw new BusinessException(RetCodeConstants.INTEGRATION_ERROR, "很遗憾，未挖到哦～");
            }
            //广播中奖纪录
            try {
                UserInfo user = userInfoMapper.selectByPrimaryKey(userInfo.getUserId());
                String drawMessage = new StringBuffer()
                        .append("{")
                        .append("\"userName\":\"").append(user.getNickName()).append("\",")
                        .append("\"couponName\":\"").append(lotteryAward.getName()).append("\"")
                        .append("}").toString();
                socketSendService.sendSocketMessage(drawMessage, SystemEnum.SocketType.DRAW.getCode());
            } catch (Exception e) {
                logger.error("广播用户中奖纪录失败", e);
            }
            //异步记录中奖信息
            AwardAsyncDto asyncDto = new AwardAsyncDto(
                    MarketingEnum.AynscInsertCouponType.INTEGRATION_AWARD.getCode(),
                    new IntegrationAwardAsyncInsertDto(userInfo.getUserId(), lotteryAward.getId()));
            rabbitSendService.sendIntegrationAward(JSON.toJSONString(asyncDto));
        }
        return dto;

    }

    /**
     * 根据书屋ID获取对应的积分抽奖奖品
     *
     * @param bookId
     */
    private List<IntegrationPool> getIntegerationAward(int bookId) {
        //获得当前书屋的所有抽奖奖品
        List<IntegrationPool> awards = integrationPoolMapper.selectIntegrationPoolByBookId(bookId);
        if (CollectionUtils.isEmpty(awards)) {
            awards = Lists.newArrayList();
        }
        //抽奖奖品概率统计
        BigDecimal entityProb = BigDecimal.ZERO;
        for (IntegrationPool ip : awards) {
            entityProb = entityProb.add(ip.getProbability());
        }
        //计算概率 不满100% 空奖补全
        BigDecimal prob = new BigDecimal("100").subtract(entityProb);
        if (prob.compareTo(BigDecimal.ZERO) > 0) {
            IntegrationPool bean = new IntegrationPool();
            bean.setName(BasicService.NULL_AWARD_NAME);
            bean.setId(-1);
            bean.setProbability(prob);
            bean.setCount(1);
            bean.setType(MarketingEnum.AwardProperty.NULL.getCode());
            awards.add(bean);
        }
        return awards;
    }


    @Override
    public Integer getDrawBookId(RegionPollParams param) {
        //查询认证社区
        int bookId = -1;
        boolean isNearPool = false;
        List<UserRegionPoolInfo> integrationPools = null;
        List<UserRegionPoolInfo> regions = userRegionRelationMapper.selectRegionByUserId(param.getAuthUserId());
        //无认证社区
        if (CollectionUtils.isEmpty(regions)) {
            isNearPool = true;
        } else {
            StringBuilder regionIds = new StringBuilder();
            regions.forEach(item -> regionIds.append(item.getRegionId()).append(","));
            if (regionIds.toString().endsWith(",")) {
                regionIds.deleteCharAt(regionIds.length() - 1);
            }
            //认证社区关联的书屋奖池
            integrationPools = integrationPoolMapper.selectIntegrationPoolByRegions(regionIds.toString());
            //认证社区未关联书屋
            if (CollectionUtils.isEmpty(integrationPools)) {
                isNearPool = true;
            }
        }
        if (isNearPool) {
            //附近5km距离最近的奖池
            integrationPools = integrationPoolMapper.selectIntegrationPoolByCity(param.getCityCode());
        }
        //无社区关联的书屋奖池
        if (CollectionUtils.isEmpty(integrationPools)) {
            return bookId;
        }
        //根据regionId分组
        Map<Integer, List<UserRegionPoolInfo>> regionBooks =
                integrationPools.stream().collect(Collectors.groupingBy(UserRegionPoolInfo::getRegionId));
        double minDistance;
        int minRegionId = -1;
        if (isNearPool) {
            minDistance = 5 * 1000;
        } else {
            minRegionId = regionBooks.entrySet().stream().findFirst().get().getKey();
            UserRegionPoolInfo firstRegion = regionBooks.entrySet().stream().findFirst().get().getValue().get(0);
            minDistance = DistanceUtils.getDistanceToDouble(firstRegion.getLongitude(), firstRegion.getLatitude(),
                    param.getLongitude(), param.getLatitude());
        }
        //获取最近距离对应的regionId
        for (Integer key : regionBooks.keySet()) {
            double distance = DistanceUtils.getDistanceToDouble(regionBooks.get(key).get(0).getLongitude(), regionBooks.get(key).get(0).getLatitude(),
                    param.getLongitude(), param.getLatitude());
            if (distance < minDistance) {
                minDistance = distance;
                minRegionId = key;
            }
        }
        if (minRegionId != -1) {
            List<UserRegionPoolInfo> subList = regionBooks.get(minRegionId);
            Collections.shuffle(subList);
            bookId = subList.get(0).getBookId();
        }
        return bookId;
    }

    @Override
    public List<String> get24HoursWinningRecord() {
        List<UserWinningRecord> userWinningRecords = couponInfoMapper.select24HoursWinningRecord();
        if (CollectionUtils.isNotEmpty(userWinningRecords)) {
            return userWinningRecords.stream().map(w -> "用户:" + w.getNickName() + ", 已获得" + w.getAwardName()).collect(Collectors.toList());
        }
        return null;
    }

    /**
     * @param : code
     * @param : authUserId
     */
    @Override
    public JSONPublicDto updatePrizeStatusByCode(String code, int type, int authUserId) {
        //查询奖券所在书屋
        Integer couponRegion = couponInfoMapper.selectCouponRegion(code, type);
        //查询管理者所在书屋
        List<Integer> userRegions = couponInfoMapper.selectUserRegion(authUserId);
        if (userRegions == null) {
            throw new BusinessException("未查询到管理者信息，兑换失败！");
        }
        if (couponRegion == null) {
            throw new BusinessException("未查询到奖券书屋信息，兑换失败！");
        }
        //查询奖券信息
        CouponInfo info = couponInfoMapper.selectOneByCode(code);

        if (info.getStatus().equals(MarketingEnum.ConvertStatus.NOT_CONVERT.getCode())) {
            //如果该奖券未兑换并且奖券的兑换范围在本人管辖范围之内才能兑换
            if ((userRegions.contains(couponRegion) || couponRegion == -1)) {
                couponInfoMapper.updateStatus(code, authUserId);
                //插入兑奖记录表
                ExchangeRecord record = new ExchangeRecord();
                Date date = new Date();
                record.setCreateTime(date);
                record.setUpdateTime(date);
                record.setauthUserId(authUserId);
                if (null != getUserPhone(info.getUserId())) {
                    record.setPhone(getUserPhone(info.getUserId()).getPhone());
                } else {
                    record.setPhone("");
                }
                record.setCouponCode(info.getCouponCode());
                record.setCouponId(info.getId());
                record.setAwardName(info.getAwardName());
                record.setUserId(info.getUserId());
                exchangeRecordMapper.insert(record);
            } else {
                throw new BusinessException(RetCodeConstants.NOTAPPLY_ERROR, "该奖券不适用于此地区");
            }
        } else if (info.getStatus().equals(MarketingEnum.ConvertStatus.CONVERT_SUCCESS.getCode())) {
            throw new BusinessException(RetCodeConstants.EXCHANGEED_ERROR, "该奖券已兑换");
        } else {
            throw new BusinessException(RetCodeConstants.EXCHANGE_OUTDATE, "该奖券已过期");
        }
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "兑换成功", null);
    }

    /**
     * @param : score
     */
    @Override
    public void updateNeedScore(Integer score) {
        if (score < 0) {
            throw new BusinessException("抽奖所耗积分不能小于0");
        }
        integrationInfoMapper.updateNeedScore(score);
    }

    /**
     * @param :
     */
    @Override
    public Integer selectScore() {
        return integrationInfoMapper.selectScore();
    }

    /**
     * @param : code
     */
    @Override
    public List<BookHouseDto> selectBookHouse(BookHouseParams param) {
        return regionGroupInfoMapper.selectBookHouse(param);
    }

    @Override
    public Integer selectIntegrationCountByUser(int authUserId) {
        return couponInfoMapper.selectIntegrationCouponCountByUserId(authUserId);
    }

    @Override
    public Integer selectAwardCostScore() {
        return systemConfigMapper.selectIntegerValueByConfigCode(SystemConfigCodeConstans.LOTTERY_CODE);
    }

    @Override
    public List<MarketingRecordDto> listLastRecord() {

        return couponInfoMapper.selectLastRecord(null);
    }

    @Override
    public void deleteIntegrationById(int id) {
        IntegrationPool pool = integrationPoolMapper.selectByPrimaryKey(id);
        if (pool == null) {
            throw new BusinessException("未查询到id为：" + id + "的奖品，请检查！");
        }
        if (pool.getStatus().equals(MarketingEnum.AwardPublishStatus.DOWN_PUBLISH.getCode())) {
            int res = integrationPoolMapper.deleteByPrimaryKey(id);
            logger.info("删除了" + res + "条数据！");
        } else {
            throw new BusinessException("该奖品未下架不能删除！");
        }
    }

    @Override
    public List<CouponRecordDto> selectCouponRecord() {
        return couponInfoMapper.selectCouponRecord();
    }

    @Override
    public List<String> selectIntegrationImage() {
        return bannerImageMapper.selectIntegrationImage();
    }

    @Override
    public void deleteCouponById(int couponId) {
        CouponInfo couponInfo = couponInfoMapper.selectByPrimaryKey(couponId);
        if (couponInfo == null) {
            throw new BusinessException(RetCodeConstants.PARAMS_ERROR, "礼品券不存在!");
        }
        couponInfoMapper.updateDeleteFlag(couponId);
    }

    @Override
    public PagesDto<CouponInfoDto> selectNotDeletedCouponByUserId(int page, int rows, int userId) {
        PageHelper.startPage(page, rows);
        List<CouponInfoDto> list = couponInfoMapper.selectNotDeletedCouponByUserId(userId);
        PageInfo<CouponInfoDto> pageInfo = new PageInfo<>(list);

        return new PagesDto(list, pageInfo.getTotal(), page, rows);
    }
}
