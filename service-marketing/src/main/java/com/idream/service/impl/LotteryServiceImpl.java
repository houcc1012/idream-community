package com.idream.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.idream.commons.db.redis.RedisCache;
import com.idream.commons.db.redis.RedisKeyConstants;
import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.marketing.ActivityInfoDto;
import com.idream.commons.lib.dto.marketing.AddLotteryParams;
import com.idream.commons.lib.dto.marketing.CommunityDto;
import com.idream.commons.lib.dto.marketing.CommunityInfoParams;
import com.idream.commons.lib.dto.marketing.CouponCollectionParams;
import com.idream.commons.lib.dto.marketing.InformationRuleDto;
import com.idream.commons.lib.dto.marketing.InformationRuleValueDto;
import com.idream.commons.lib.dto.marketing.LocationParams;
import com.idream.commons.lib.dto.marketing.LotteryActivityInfoDto;
import com.idream.commons.lib.dto.marketing.LotteryAwardBean;
import com.idream.commons.lib.dto.marketing.LotteryDetailParams;
import com.idream.commons.lib.dto.marketing.LotteryDetailTimeDto;
import com.idream.commons.lib.dto.marketing.LotteryInfoDto;
import com.idream.commons.lib.dto.marketing.LotteryInformationRelationDto;
import com.idream.commons.lib.dto.marketing.LotteryPoolDto;
import com.idream.commons.lib.dto.marketing.LotteryPoolParams;
import com.idream.commons.lib.dto.marketing.LotterySearchParams;
import com.idream.commons.lib.dto.marketing.LotteryTimeDto;
import com.idream.commons.lib.dto.marketing.LotteryTimeInfoDto;
import com.idream.commons.lib.dto.marketing.LotteryWinRecordDto;
import com.idream.commons.lib.dto.marketing.LotteryWinRecordSearchParams;
import com.idream.commons.lib.dto.marketing.OpenLotteryAwardDto;
import com.idream.commons.lib.dto.marketing.OpenLotteryAwardParams;
import com.idream.commons.lib.dto.marketing.SceneOpenAwardInfo;
import com.idream.commons.lib.dto.marketing.WeChatLotteryInfoDto;
import com.idream.commons.lib.dto.rabbitmq.AwardAsyncDto;
import com.idream.commons.lib.dto.rabbitmq.OpenLotteryAwardAsyncInsertDto;
import com.idream.commons.lib.dto.region.UnityLotteryDto;
import com.idream.commons.lib.dto.token.AuthUserInfo;
import com.idream.commons.lib.enums.MarketingEnum;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.mapper.CouponCollectionMapper;
import com.idream.commons.lib.mapper.CouponInfoMapper;
import com.idream.commons.lib.mapper.InformationRuleMapper;
import com.idream.commons.lib.mapper.LotteryDetailMapper;
import com.idream.commons.lib.mapper.LotteryDetailPoolRelationMapper;
import com.idream.commons.lib.mapper.LotteryInfoMapper;
import com.idream.commons.lib.mapper.LotteryInformationRelationMapper;
import com.idream.commons.lib.mapper.LotteryPoolMapper;
import com.idream.commons.lib.mapper.RegionGroupInfoMapper;
import com.idream.commons.lib.mapper.UserAgeInfoMapper;
import com.idream.commons.lib.mapper.UserTagMapper;
import com.idream.commons.lib.model.CouponCollection;
import com.idream.commons.lib.model.CouponInfo;
import com.idream.commons.lib.model.LotteryDetail;
import com.idream.commons.lib.model.LotteryDetailPoolRelation;
import com.idream.commons.lib.model.LotteryInfo;
import com.idream.commons.lib.model.LotteryInformationRelation;
import com.idream.commons.lib.model.LotteryPool;
import com.idream.commons.lib.model.TagInfoTree;
import com.idream.commons.lib.model.UserAgeInfo;
import com.idream.commons.lib.util.DateTimeUtils;
import com.idream.commons.lib.util.DateUtils;
import com.idream.commons.lib.util.DistanceUtils;
import com.idream.commons.lib.util.DrawLotteryUtils;
import com.idream.rabbit.RabbitSendService;
import com.idream.service.LotteryService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Description :
 * Created by 肖刚 on 2018/4/11.
 */

@Service
public class LotteryServiceImpl implements LotteryService {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private LotteryPoolMapper lotteryPoolMapper;
    @Autowired
    private LotteryDetailMapper lotteryDetailMapper;
    @Autowired
    private LotteryInfoMapper lotteryInfoMapper;
    @Autowired
    private LotteryInformationRelationMapper lotteryInformationRelationMapper;
    @Autowired
    private RegionGroupInfoMapper regoinGroupInfoMapper;
    @Autowired
    private CouponCollectionMapper couponCollectionMapper;
    @Autowired
    private CouponInfoMapper couponInfoMapper;
    @Autowired
    private InformationRuleMapper informationRuleMapper;
    @Autowired
    private UserTagMapper userTagMapper;
    @Autowired
    private RedisCache redisCache;
    @Resource
    private RabbitSendService rabbitSendService;
    @Resource
    private LotteryDetailPoolRelationMapper lotteryDetailPoolRelationMapper;
    @Resource
    private UserAgeInfoMapper userAgeInfoMapper;

    /**
     * @param : lotteryInfoDto
     */
    @Override
    public int saveLottery(AddLotteryParams lotteryInfoDto) {
        Date date = new Date();
        Date startTime = lotteryInfoDto.getStartTime();
        Date endTime = lotteryInfoDto.getEndTime();
        if (lotteryInfoDto.isBanded() && null == lotteryInfoDto.getActivityId()) {
            throw new BusinessException("绑定活动的ID不能为空！");
        }
        LotteryInfo linfo = new LotteryInfo();
        Integer activityId = lotteryInfoDto.getActivityId() == null ? null : (lotteryInfoDto.getActivityId() == 0 ? null : lotteryInfoDto.getActivityId());
        linfo.setActivityId(activityId);
        linfo.setAdCode(lotteryInfoDto.getAdCode());
        linfo.setCommunityId(lotteryInfoDto.getCommunityId());
        linfo.setCity(lotteryInfoDto.getCityId());
        linfo.setStatus(MarketingEnum.AwardPublishStatus.UP_PUBLISH.getCode());
        linfo.setStartTime(startTime);
        linfo.setEndTime(endTime);
        linfo.setCreateTime(date);
        linfo.setUpdateTime(date);
        linfo.setInstructions(lotteryInfoDto.getInstructions());
        int result = lotteryInfoMapper.insert(linfo);

        List<LotteryDetailParams> ldetail = lotteryInfoDto.getLotteryDetailList();
        Integer lotteryId = linfo.getId();
        if (ldetail.size() > 0) {
            for (int i = 0; i < ldetail.size(); i++) {
                LotteryDetailParams lotteryDetail = ldetail.get(i);
                //校验开奖时间
                if (i > 0) {
                    //当前段的开始时间
                    Date d1 = ldetail.get(i).getStartTime();
                    //前一段的结束时间
                    Date d2 = DateTimeUtils.getAfterMin(ldetail.get(i - 1).getStartTime(), ldetail.get(i - 1).getLastTime());
                    if (DateTimeUtils.parseDateToString(d1, DateTimeUtils.HH_MM_SS).compareTo(DateTimeUtils.parseDateToString(d2, DateTimeUtils.HH_MM_SS)) < 0) {
                        throw new BusinessException("第" + (i + 1) + "段开奖的开始时间应在上一段开奖结束时间之后");
                    }
                }
                LotteryDetail lottery = new LotteryDetail();
                lottery.setLotteryId(lotteryId);
                lottery.setType(lotteryInfoDto.getType());
                lottery.setSpeakSecret(lotteryDetail.getSpeakSecret());
                lottery.setLotteryCount(lotteryDetail.getLotteryCount());
                lottery.setStartTime(lotteryDetail.getStartTime());
                lottery.setEndTime(new Time(lotteryDetail.getStartTime().getTime() + lotteryDetail.getLastTime() * 60000));
                lottery.setStartDate(startTime);
                lottery.setEndDate(endTime);
                lottery.setCreateTime(date);
                lottery.setUpdateTime(date);
                lotteryDetailMapper.insert(lottery);
            }
        }
        return result;
    }

    /**
     * @param : lotteryInfoDto
     */
    @Override
    public int saveLotteryPool(LotteryPoolParams pool) {
        int result = 0;
        Date date = new Date();

        LotteryInfo lottery = lotteryInfoMapper.selectByPrimaryKey(pool.getLotteryId());
        LotteryPool lotteryPool = new LotteryPool();

        lotteryPool.setLotteryId(pool.getLotteryId());
        lotteryPool.setAwardName(pool.getAwardName());
        lotteryPool.setCount(pool.getCount());
        lotteryPool.setDescription(pool.getDescription());
        lotteryPool.setExpireDay(pool.getExpireDay());
        lotteryPool.setInfoEnable(pool.getInfoEnable());
        lotteryPool.setProbability(pool.getProbability());
        lotteryPool.setStatus(MarketingEnum.AwardPublishStatus.UP_PUBLISH.getCode());//上架
        lotteryPool.setStartTime(lottery.getStartTime());
        lotteryPool.setInstructions(pool.getInstructions());
        lotteryPool.setEndTime(DateTimeUtils.getDateEndTime(lottery.getEndTime()));
        lotteryPool.setImage(pool.getImage());
        lotteryPool.setCreateTime(date);
        lotteryPool.setUpdateTime(date);
        lotteryPool.setIntroduce(pool.getIntroduce());

        for (int m = 0; m < pool.getTimes().size(); m++) {
            List<LotteryPoolDto> lotteryPoolDtos =
                    lotteryPoolMapper.selectByLotteryIdAndTime(pool.getLotteryId(), pool.getTimes().get(m).getStartTime());
            if (lotteryPoolDtos.size() > 0) {
                BigDecimal total = new BigDecimal(0);
                for (int j = 0; j < lotteryPoolDtos.size(); j++) {
                    total = total.add(lotteryPoolDtos.get(j).getProbability());
                }
                total = total.add(lotteryPool.getProbability());
                if (total.compareTo(new BigDecimal(MarketingEnum.ProbabilityEnum.All.getCode())) == 1) {
                    throw new BusinessException("奖品概率总计大于100%");
                }
            }
        }
        result = lotteryPoolMapper.insert(lotteryPool);

        if (pool.getInfoEnable()) {
            for (int i = 0; i < pool.getInfoIds().length; i++) {
                LotteryInformationRelation lir = new LotteryInformationRelation();
                lir.setInfoId(pool.getInfoIds()[i]);
                lir.setLotteryPoolId(lotteryPool.getId());
                lir.setCreateTime(date);
                lir.setUpdateTime(date);
                //插入选填信息
                lotteryInformationRelationMapper.insert(lir);
            }
        }

        for (int m = 0; m < pool.getTimes().size(); m++) {
            //存入关联表
            LotteryDetail detail = lotteryDetailMapper.selectIdByLotteryIdAndTime(pool.getLotteryId(), pool.getTimes().get(m).getStartTime());
            if (detail == null || StringUtils.isEmpty(detail.getId())) {
                throw new BusinessException("时间段信息为空，请检查！");
            }
            LotteryDetailPoolRelation lotteryDetailPoolRelation = new LotteryDetailPoolRelation();
            lotteryDetailPoolRelation.setCreateTime(date);
            lotteryDetailPoolRelation.setUpdateTime(date);
            lotteryDetailPoolRelation.setDetailId(detail.getId());
            lotteryDetailPoolRelation.setPoolId(lotteryPool.getId());

            lotteryDetailPoolRelationMapper.insert(lotteryDetailPoolRelation);
        }
        //缓存奖品数量
        saveSceneOpenAwardRedis(lottery, lotteryPool.getId(), pool.getCount());
        return result;
    }

    /**
     * 保存 redis 现场开奖信息
     */
    private void saveSceneOpenAwardRedis(LotteryInfo lotteryInfo, Integer poolId, Integer count) {
//        String key = lotteryInfoId + "-" + lotteryDetailId + "-" + poolId;
        String key = lotteryInfo.getId() + "-" + poolId;
        redisCache.setString(key, count.toString(), RedisKeyConstants.SCENE_OPEN_AWARD_COUNT);
        //设置失效时间
        redisCache.pexpireAt(key, RedisKeyConstants.SCENE_OPEN_AWARD_COUNT, DateTimeUtils.getDateEndTime(lotteryInfo.getEndTime()).getTime());
    }

    /**
     * @param : lotteryInfoDto
     */
    @Override
    public int updateLotteryBean(AddLotteryParams lotteryInfoDto) {

        int result = 0;
        Date date = new Date();
        if (lotteryInfoDto.isBanded() && null == lotteryInfoDto.getActivityId()) {
            throw new BusinessException("绑定活动的ID不能为空！");
        }
        LotteryInfo linfo = new LotteryInfo();

        linfo.setId(lotteryInfoDto.getId());
        Integer activityId = lotteryInfoDto.getActivityId() == null ? null : (lotteryInfoDto.getActivityId() == 0 ? null : lotteryInfoDto.getActivityId());
        linfo.setActivityId(activityId);
        linfo.setAdCode(lotteryInfoDto.getAdCode());
        linfo.setCommunityId(lotteryInfoDto.getCommunityId());
        linfo.setCity(lotteryInfoDto.getCity());
        linfo.setStatus(lotteryInfoDto.getActivityStatus());
        linfo.setStartTime(lotteryInfoDto.getStartTime());
        linfo.setEndTime(lotteryInfoDto.getEndTime());
        linfo.setUpdateTime(date);
        linfo.setInstructions(lotteryInfoDto.getInstructions());
        //修改开奖活动主表
        result = lotteryInfoMapper.updateByEntity(linfo);
        List<LotteryDetailParams> ldetail = lotteryInfoDto.getLotteryDetailList();

        List<LotteryDetailParams> details = lotteryDetailMapper.selectByLotteryId(lotteryInfoDto.getId());
        //删除开奖明细时间表数据并新增
        if (ldetail.size() > 0) {
            for (int i = 0; i < ldetail.size(); i++) {
                LotteryDetailParams lotteryDetail = ldetail.get(i);
                //校验开奖时间
                if (i > 0) {
                    //当前段的开始时间
                    Date d1 = ldetail.get(i).getStartTime();
                    //前一段的结束时间
                    Date d2 = DateTimeUtils.getAfterMin(ldetail.get(i - 1).getStartTime(), ldetail.get(i - 1).getLastTime());
                    if (DateTimeUtils.parseDateToString(d1, DateTimeUtils.HH_MM_SS).compareTo(DateTimeUtils.parseDateToString(d2, DateTimeUtils.HH_MM_SS)) < 0) {
                        throw new BusinessException("第" + (i + 1) + "段开奖的开始时间应在上一段开奖结束时间之后");
                    }
                }
                List<LotteryDetailPoolRelation> relations = new ArrayList<>();
                if (lotteryDetail.getId() != null) {
                    Iterator<LotteryDetailParams> iter = details.iterator();
                    while (iter.hasNext()) {
                        Integer id = iter.next().getId();
                        if (id.equals(lotteryDetail.getId())) {
                            iter.remove();
                        }
                    }
                    //查询开奖时间明细关联奖池信息数据，拿到所有关联此时间段的奖池ID
                    relations = lotteryDetailPoolRelationMapper.selectByDetailId(lotteryDetail.getId());
                    //删除关联表，以及明细表数据
                    int totalCount = lotteryDetailPoolRelationMapper.deleteByDetailId(lotteryDetail.getId());
                    //删除时间明细表，重新插入
                    lotteryDetailMapper.deleteByPrimaryKey(lotteryDetail.getId());
                    logger.info("删除了" + totalCount + "条开奖明细时间关联奖池数据");
                }
                LotteryDetail lottery = new LotteryDetail();
                lottery.setLotteryId(lotteryInfoDto.getId());
                lottery.setType(lotteryInfoDto.getType());
                lottery.setSpeakSecret(lotteryDetail.getSpeakSecret());
                lottery.setStartDate(lotteryInfoDto.getStartTime());
                lottery.setEndDate(lotteryInfoDto.getEndTime());
                lottery.setStartTime(lotteryDetail.getStartTime());
                lottery.setLotteryCount(lotteryDetail.getLotteryCount());
                lottery.setEndTime(new Time(lotteryDetail.getStartTime().getTime() + lotteryDetail.getLastTime() * 60000));
                lottery.setCreateTime(date);
                lottery.setUpdateTime(date);
                lotteryDetailMapper.insert(lottery);
                // //新增时间明细关联奖池数据
                if (relations.size() > 0) {
                    for (LotteryDetailPoolRelation relation : relations) {
                        LotteryDetailPoolRelation lotteryDetailPoolRelation = new LotteryDetailPoolRelation();
                        lotteryDetailPoolRelation.setCreateTime(date);
                        lotteryDetailPoolRelation.setUpdateTime(date);
                        lotteryDetailPoolRelation.setDetailId(lottery.getId());
                        lotteryDetailPoolRelation.setPoolId(relation.getPoolId());
                        lotteryDetailPoolRelationMapper.insert(lotteryDetailPoolRelation);
                    }
                }
            }
            for (LotteryDetailParams detail : details) {
                lotteryDetailPoolRelationMapper.deleteByDetailId(detail.getId());
                lotteryDetailMapper.deleteByPrimaryKey(detail.getId());
            }
        }
        //缓存奖品数量
        saveSceneOpenAwardRedisByLottery(linfo);
        return result;
    }

    private void saveSceneOpenAwardRedisByLottery(LotteryInfo lotteryInfo) {
        List<LotteryPool> lotteryPools = lotteryPoolMapper.selectByLotteryId(lotteryInfo.getId());
        if (CollectionUtils.isNotEmpty(lotteryPools)) {
            long expireTime = DateTimeUtils.getDateEndTime(lotteryInfo.getEndTime()).getTime();
            for (LotteryPool pool : lotteryPools) {
                saveSceneOpenAwardRedis(lotteryInfo, pool.getId(), pool.getCount());
            }
        }
    }

    /**
     * @param : params
     */
    @Override
    public PagesDto<LotteryInfoDto> selectLotteryPoolList(LotterySearchParams params) {
        PageHelper.startPage(params.getPage(), params.getRows());
        List<LotteryInfoDto> lotteryInfoDto = lotteryInfoMapper.selectLotteryPoolList(params);
        Calendar cl = Calendar.getInstance();
        //当前时分秒的毫秒数
        int time = (cl.get(Calendar.HOUR_OF_DAY) * 3600 + cl.get(Calendar.MINUTE) * 60 + cl.get(Calendar.SECOND)) * 1000;
        if (lotteryInfoDto != null) {
            for (LotteryInfoDto lotteryInfo : lotteryInfoDto) {
                Integer lotteryId = lotteryInfo.getId();
                List<LotteryDetailParams> detailList = lotteryDetailMapper.selectByLotteryId(lotteryId);
                for (LotteryDetailParams detail : detailList) {
                    //反显持续时间
                    long min = (detail.getEndTime().getTime() - detail.getStartTime().getTime()) / 60000;
                    detail.setLastTime((int) min);
                }
                lotteryInfo.setLotteryDetailList(detailList);
            }
        }
        PageInfo<LotteryInfoDto> pageInfo = new PageInfo<>(lotteryInfoDto);

        return new PagesDto(lotteryInfoDto, pageInfo.getTotal(), params.getPage(), params.getRows());
    }

    @Override
    public PagesDto<LotteryInfoDto> selectLotteryListByUser(LotterySearchParams params) {
        PageHelper.startPage(params.getPage(), params.getRows());
        List<LotteryInfoDto> lotteryInfoDto = lotteryInfoMapper.selectLotteryListByUser(params);
        Calendar cl = Calendar.getInstance();
        //当前时分秒的毫秒数
        if (lotteryInfoDto.size() > 0) {
            for (LotteryInfoDto lotteryInfo : lotteryInfoDto) {
                Integer lotteryId = lotteryInfo.getId();
                List<LotteryDetailParams> detailList = lotteryDetailMapper.selectByLotteryId(lotteryId);
                for (LotteryDetailParams detail : detailList) {
                    long min = (detail.getEndTime().getTime() - detail.getStartTime().getTime()) / 60000;
                    detail.setLastTime((int) min);
                }
                lotteryInfo.setLotteryDetailList(detailList);
            }

        }

        PageInfo<LotteryInfoDto> pageInfo = new PageInfo<>(lotteryInfoDto);

        return new PagesDto(lotteryInfoDto, pageInfo.getTotal(), params.getPage(), params.getRows());
    }

    /**
     * @param : lotterySearchParams
     */
    @Override
    public PagesDto<LotteryWinRecordDto> selectLotteryList(LotteryWinRecordSearchParams lotterySearchParams) {
        PageHelper.startPage(lotterySearchParams.getPage(), lotterySearchParams.getRows());
        List<LotteryWinRecordDto> lotteryWinRecordDto = lotteryInfoMapper.getLotteryList(lotterySearchParams);
        if (lotteryWinRecordDto.size() > 0) {
            for (LotteryWinRecordDto lottery : lotteryWinRecordDto) {
                lottery.setCouponCollection(selectDetailByCouponId(lottery.getCouponId()));
            }
        }
        PageInfo<LotteryWinRecordDto> pageInfo = new PageInfo<>(lotteryWinRecordDto);

        return new PagesDto(lotteryWinRecordDto, pageInfo.getTotal(), lotterySearchParams.getPage(), lotterySearchParams.getRows());
    }

    /**
     * @param : code
     */
    @Override
    public List<CommunityDto> selectCommunityList(CommunityInfoParams code) {

        return regoinGroupInfoMapper.selectCommunityList(code);
    }

    /**
     * @param : communityid
     */
    @Override
    public List<ActivityInfoDto> selectActivityList(Integer communityid) {

        return lotteryInfoMapper.selectActivityList(communityid);
    }


    /**
     * @param : id
     */
    @Override
    public LotteryInfoDto selectLotteryById(Integer id) {
        LotteryInfoDto lotteryInfoDto = lotteryInfoMapper.selectLotteryById(id);
        if (lotteryInfoDto == null) {
            throw new BusinessException("未找到id为" + id + "的开奖记录！");
        }
        Integer lotteryId = lotteryInfoDto.getId();
        List<LotteryDetailParams> detailList = lotteryDetailMapper.selectByLotteryId(lotteryId);
        if (detailList.size() > 0) {
            for (LotteryDetailParams detail : detailList) {
                long min = (detail.getEndTime().getTime() - detail.getStartTime().getTime()) / 60000;
                detail.setLastTime((int) min);
                detail.setId(detail.getId());
            }
        }
        lotteryInfoDto.setLotteryDetailList(detailList);
        return lotteryInfoDto;
    }

    /**
     * @param : couponId
     */
    @Override
    public List<CouponCollection> selectDetailByCouponId(Integer couponId) {
        List<CouponCollection> collections = couponCollectionMapper.selectDetailByCouponId(couponId);
        if (collections != null) {
            for (CouponCollection item : collections) {
                if ("age".equals(item.getInfoCode()) && !item.getDetail().isEmpty()) {
                    UserAgeInfo ageInfo = userAgeInfoMapper.selectByPrimaryKey(Integer.parseInt(item.getDetail()));
                    if (ageInfo != null) {
                        item.setDetail(ageInfo.getName());
                    }
                }
                if (("tag").equals(item.getInfoCode())) {
                    if (!StringUtils.isEmpty(item.getDetail())) {
                        StringBuilder Infos = new StringBuilder();
                        if (item.getDetail().contains(",")) {
                            String[] ids = item.getDetail().split(",");
                            for (String id : ids) {
                                String info = userTagMapper.getTagInfoById(Integer.parseInt(id));
                                Infos.append(",").append(info);
                            }
                        } else {
                            String info = userTagMapper.getTagInfoById(Integer.parseInt(item.getDetail()));
                            Infos.append(",").append(info);
                        }
                        item.setDetail(Infos.substring(1));
                    }
                }
            }
        }
        return collections;
    }

    /**
     * @param : poolId
     * @param : type
     */
    @Override
    public boolean wetherNeedWriteInfo(Integer poolId, Integer type) {
        boolean res = false;
        Integer count = couponInfoMapper.wetherNeedWriteInfo(poolId, type);
        if (count != null && count == 1) {
            res = true;
        } else {
            throw new BusinessException(RetCodeConstants.SELECT_FAILED, "查询失败,id为" + poolId + "的奖品不存在");
        }
        return res;
    }

    /**
     * @param : cuoponId
     */
    @Override
    public List<InformationRuleDto> getInfoItems(Integer cuoponId) {
        List<InformationRuleDto> list = informationRuleMapper.getInfoItemsByCouponId(cuoponId);
        if (list.size() > 0) {
            for (InformationRuleDto rule : list) {
                if (("tag").equals(rule.getInfoCode())) {
                    rule.setTagInfoList(getAll());
                }
                if (("age").equals(rule.getInfoCode())) {
                    rule.setAgeList(userAgeInfoMapper.getAgeInfo());
                }
            }
        }

        return list;
    }

    /**
     * @param : params
     */
    @Override
    public String savePersonalInfo(List<CouponCollectionParams> params) {
        String result = "";
        int res = 0;
        for (int i = 0; i < params.size(); i++) {
            if (params.get(i) != null) {
                CouponCollection param = couponCollectionMapper.selectByPrimaryKey(params.get(i).getId());
                if (null != param) {
                    res = couponCollectionMapper.updatePersonalInfo(params.get(i));
                } else {
                    throw new BusinessException(RetCodeConstants.SELECT_FAILED, "未查询到id为" + params.get(i).getId() + "的必填项信息，请检查");
                }
            }
        }
        CouponInfo coupon = new CouponInfo();
        coupon.setId(params.get(0).getCouponId());
        coupon.setInfoCompleted(true);
        couponInfoMapper.updateByPrimaryKeySelective(coupon);
        if (res == 1) {
            result = "保存成功";
        } else {
            result = "保存失败";
        }
        return result;
    }

    /**
     * @Author: hejiang
     * @Description: 现场开奖
     * @Date: 16:04 2018/4/18
     */
    @Override
    public OpenLotteryAwardDto doOpenLotteryAward(JSONPublicParam<OpenLotteryAwardParams> params) {
        OpenLotteryAwardDto dto = new OpenLotteryAwardDto();
        //用户参数
        AuthUserInfo userInfo = params.getAuthUserInfo();
        //业务参数
        OpenLotteryAwardParams param = params.getRequestParam();
        //查询现场开奖奖品信息
        SceneOpenAwardInfo awardInfo = lotteryInfoMapper.selectSceneOpenAwardInfo(param.getLotteryId(), new Date());
        if (awardInfo == null) {
            logger.error("开奖活动不存在！，params=" + JSON.toJSONString(params));
            throw new BusinessException(RetCodeConstants.AWARD_ERROR, "本轮抽奖未开始或已结束");
        }
        //距离开奖社区(或者绑定活动位置)500米内才可开奖
        double distance = DistanceUtils.getDistance(param.getLongitude().doubleValue(), param.getLatitude().doubleValue(),
                awardInfo.getLongitude().doubleValue(), awardInfo.getLatitude().doubleValue());
        if (distance > 500) {
            logger.error("距离开奖社区距离超过500米!");
            throw new BusinessException(RetCodeConstants.LOTTERY_DISTANCE_EXCEED, "距离开奖社区距离超过500米");
        }
        //校验开奖活动是否结束
        if (MarketingEnum.LotteryActivityStatus.CLOSE.getCode().equals(awardInfo.getStatus().byteValue())) {
            logger.error("开奖活动已结束!");
            throw new BusinessException(RetCodeConstants.AWARD_ERROR, "开奖活动已结束");
        }
        //根据开奖活动Id查询当前的开奖活动的所有奖品
        String key = param.getLotteryId() + "-" + awardInfo.getLotteryDetails().get(0).getId();

        //获取对应时段开奖活动的所有开奖奖品信息
        int lotteryDetailId = awardInfo.getLotteryDetails().get(0).getId();
        List<LotteryAwardBean> lotteryAwards = getLotteryAwardByDetailId(lotteryDetailId);
        if (CollectionUtils.isEmpty(lotteryAwards)) {
            logger.error("开奖活动未设置奖品信息！，params=" + JSON.toJSONString(params));
            throw new BusinessException(RetCodeConstants.LOTTERY_AWARD_ERROR, "开奖活动未设置奖品信息");
        }

        //判断用户累计开奖次数
        int userTotalLotteryCount = awardInfo.getLotteryDetails().get(0).getLotteryCount();
        if (userTotalLotteryCount > 0) {
            String drawKey = userInfo.getUserId() + "-" + key;
            Long drawCount = redisCache.incr(drawKey, RedisKeyConstants.SCENE_OPEN_DRAW_COUNT);
            if (drawCount == 1) {
                //首次计数设置失效时间为下一天零点
                redisCache.expire(drawKey, RedisKeyConstants.SCENE_OPEN_DRAW_COUNT, DateUtils.getRemainSecondsOneDay(new Date()));
            }
            if (drawCount > userTotalLotteryCount) {
                throw new BusinessException(RetCodeConstants.LOTTERY_NO_COUNT, "本次开奖累计开奖次数已用完");
            }
            dto.setRemainCount(userTotalLotteryCount - drawCount.intValue());
        }

        // 抽取奖品
        LotteryAwardBean lotteryAward = DrawLotteryUtils.drawSenceOpenGift(lotteryAwards);
        //奖品数量/key
        String lotteryCountKey = param.getLotteryId() + "-" + lotteryAward.getId();
        if (lotteryAward != null && lotteryAward.getId() > 0) {
            //redis数量减一
            Long result = redisCache.decr(lotteryCountKey, RedisKeyConstants.SCENE_OPEN_AWARD_COUNT);
            if (result < 0) {
                logger.error("现场开奖奖品扣剩余数量不足!");
                throw new BusinessException(RetCodeConstants.AWARD_ERROR, "很遗憾，未中奖哦～");
            }
            // 异步发放用户奖券
            AwardAsyncDto asyncDto = new AwardAsyncDto(
                    MarketingEnum.AynscInsertCouponType.OPEN_DRAM_AWARD.getCode(),
                    new OpenLotteryAwardAsyncInsertDto(lotteryAward.getId(), userInfo.getUserId(), lotteryCountKey));
            rabbitSendService.sendLotteryDraw(JSON.toJSONString(asyncDto));
            //返回信息
            dto.setAwardName(lotteryAward.getName());
            return dto;
        } else {
            throw new BusinessException(RetCodeConstants.AWARD_ERROR, "很遗憾，未中奖哦～");
        }
    }

    /**
     * 获得现场开奖奖品信息
     *
     * @param lotteryDetailId
     */
    public List<LotteryAwardBean> getLotteryAwardByDetailId(int lotteryDetailId) {
        List<LotteryAwardBean> lotteryAwards = lotteryDetailPoolRelationMapper.selectByLotteryDetailId(lotteryDetailId);
        if (CollectionUtils.isNotEmpty(lotteryAwards)) {
            //统计奖品总概率
            BigDecimal sumProb = BigDecimal.ZERO;
            for (LotteryAwardBean bean : lotteryAwards) {
                sumProb = sumProb.add(bean.getProb());
            }
            //设置空奖
            BigDecimal prob = new BigDecimal("100").subtract(sumProb);
            if (prob.compareTo(BigDecimal.ZERO) > 0) {
                LotteryAwardBean bean = new LotteryAwardBean();
                bean.setProb(prob);
                bean.setCount(1);
                bean.setId(-1);
                bean.setName("很遗憾，未中奖哦～");
                lotteryAwards.add(bean);
            }
        }
        return lotteryAwards;
    }


    /**
     * @param : params
     */
    @Override
    public List<WeChatLotteryInfoDto> selectLotteryPoolListByNear(LocationParams params) {
        //根据经纬度获取所有有奖社区的开奖信息
        List<WeChatLotteryInfoDto> lotteryInfo = lotteryInfoMapper.selectLotteryListByNear(params.getLatitude(), params.getLongitude(), params.getCityCode());
        if (CollectionUtils.isNotEmpty(lotteryInfo)) {
            //分组 有开奖社区和无开奖社区,优先显示有开奖社区
            Map<Boolean, List<WeChatLotteryInfoDto>> partitionedMap =
                    lotteryInfo.stream().collect(Collectors.partitioningBy(item -> item.getLotteryId() == null));
            List<WeChatLotteryInfoDto> noLottery = partitionedMap.get(true);
            List<WeChatLotteryInfoDto> hasLottery = partitionedMap.get(false);
            StringBuilder ids = new StringBuilder();
            //查询detail信息
            hasLottery.forEach(item -> ids.append(item.getLotteryId()).append(","));
            if (ids.toString().endsWith(",")) {
                ids.deleteCharAt(ids.length() - 1);
                List<LotteryDetailParams> details = lotteryDetailMapper.selectByLotteryIds(ids.toString());
                if (CollectionUtils.isNotEmpty(details)) {
                    Map<Integer, List<LotteryDetailParams>> detailsByIdMap =
                            details.stream().collect(Collectors.groupingBy(item -> item.getLotteryId()));
                    for (WeChatLotteryInfoDto lottery : hasLottery) {
                        List<LotteryDetailTimeDto> lotteryTime = new ArrayList<>();
                        List<LotteryDetailParams> subDetails = detailsByIdMap.get(lottery.getLotteryId());
                        if (subDetails != null) {
                            lotteryTime = setLotteryDetailData(subDetails, lotteryTime);
                            lottery.setLottryDetail(lotteryTime);
                            lottery.setCountNum(lotteryTime.size());
                            //当前开奖活动的状态 用于分组(正在开奖、等待开奖)
                            lottery.setLotteryStatus(MarketingEnum.LotteryStatusEnum.ENDED.getCode());
                            for (LotteryDetailTimeDto dto : lotteryTime) {

                                if (dto.getStatus().equals(MarketingEnum.LotteryStatusEnum.WILL.getCode())) {
                                    lottery.setLotteryStatus(MarketingEnum.LotteryStatusEnum.WILL.getCode());
                                    lottery.setStartTime(dto.getStartTime());
                                    break;
                                }
                                if (dto.getStatus().equals(MarketingEnum.LotteryStatusEnum.ING.getCode())) {
                                    lottery.setLotteryStatus(MarketingEnum.LotteryStatusEnum.ING.getCode());
                                    lottery.setStartTime(dto.getStartTime());
                                    break;
                                }
                            }
                        }
                    }
                    List<WeChatLotteryInfoDto> endLottery = new ArrayList<>();
                    for (WeChatLotteryInfoDto item : hasLottery) {
                        if (CollectionUtils.isEmpty(item.getLottryDetail()) || MarketingEnum.LotteryStatusEnum.ENDED.getCode().equals(item.getLotteryStatus())) {
                            item.setLotteryId(null);
                            endLottery.add(item);
                        }
                    }
                    hasLottery.removeAll(endLottery);
                    endLottery.removeIf(item -> hasLottery.stream().anyMatch(subitem -> subitem.getCommunityId().equals(item.getCommunityId())));
                    List<WeChatLotteryInfoDto> endUniqeLottery = new ArrayList<>();
                    for (WeChatLotteryInfoDto item : endLottery) {
                        if (!endUniqeLottery.stream().anyMatch(subItem -> subItem.getCommunityId().equals(item.getCommunityId()))) {
                            endUniqeLottery.add(item);
                        }
                    }
                    //分组 正在开奖社区和等待开奖社区  等待开奖的按开奖时间正序排列
                    Map<Boolean, List<WeChatLotteryInfoDto>> lotteryMap =
                            hasLottery.stream().collect(Collectors.partitioningBy(item -> item.getLotteryStatus().equals(MarketingEnum.LotteryStatusEnum.ING.getCode())));
                    List<WeChatLotteryInfoDto> lotteryingInfos = lotteryMap.get(true);
                    List<WeChatLotteryInfoDto> lotteryWaittingInfos = lotteryMap.get(false);
                    lotteryWaittingInfos = lotteryWaittingInfos.stream().sorted(Comparator.comparing(WeChatLotteryInfoDto::getStartTime)).collect(Collectors.toList());
                    hasLottery.clear();
                    hasLottery.addAll(lotteryingInfos);
                    hasLottery.addAll(lotteryWaittingInfos);
                    hasLottery.addAll(endUniqeLottery);
                }
            }
            hasLottery.addAll(noLottery);
            return hasLottery;
        }
        return lotteryInfo;
    }

    /**
     * @param :
     */
    @Override
    public List<TagInfoTree> getAll() {
        List<TagInfoTree> parent = userTagMapper.getAllParent();
        List<TagInfoTree> children = userTagMapper.getAllChildren();
        List<TagInfoTree> childs = new ArrayList<TagInfoTree>();
        for (int i = 0; i < parent.size(); i++) {
            List<TagInfoTree> tree = new ArrayList<>();
            for (TagInfoTree child : children) {
                if (child.getPid().equals(parent.get(i).getId())) {
                    child.setChildren(childs);
                    tree.add(child);
                }
            }
            parent.get(i).setChildren(tree);
        }
        return parent;
    }

    /**
     * @param : lotteryId 可空
     * @param : poolId 可空
     */
    @Override
    public List<LotteryPoolDto> selectPoolByLotteryId(Integer lotteryId, Integer poolId) {
        List<LotteryPoolDto> lotteryPools = lotteryPoolMapper.selectByLotteryIdAndPoolId(lotteryId, poolId);
        if (lotteryPools.size() > 0) {
            for (LotteryPoolDto pool : lotteryPools) {
                //List<LotteryTimeDto> detail = lotteryDetailMapper.selectTimeByPoolId(pool.getId());
                pool.setTimes(lotteryDetailMapper.selectTimeByPoolId(pool.getId()));
                List<LotteryInformationRelationDto> infoId = lotteryInformationRelationMapper.selectByLotteryId(pool.getId());
                Integer[] infoIds = new Integer[infoId.size()];
                for (int k = 0; k < infoId.size(); k++) {
                    infoIds[k] = infoId.get(k).getInfoId();
                }
                pool.setInfoIds(infoIds);
            }
        } else {
            List<LotteryDetailParams> detail = lotteryDetailMapper.selectByLotteryId(lotteryId);
            List<LotteryTimeDto> lotteryTimeDtos = new ArrayList<>();
            for (LotteryDetailParams det : detail) {
                LotteryTimeDto lotteryTimeDto = new LotteryTimeDto();
                lotteryTimeDto.setStartTime(det.getStartTime());
                lotteryTimeDto.setEndTime(det.getEndTime());
                lotteryTimeDtos.add(lotteryTimeDto);
            }
            LotteryPoolDto lotteryPoolDto = new LotteryPoolDto();
            lotteryPoolDto.setEndTime(detail.get(0).getEndDate());
            lotteryPoolDto.setStartTime(detail.get(0).getStartDate());
            lotteryPoolDto.setTimes(lotteryTimeDtos);
        }
        return lotteryPools;
    }

    /**
     * @param : lotteryPoolDto
     */
    @Override
    public int updatePool(LotteryPoolParams lotteryPoolDto) {
        //查询开奖活动
        LotteryInfo lottery = lotteryInfoMapper.selectByPrimaryKey(lotteryPoolDto.getLotteryId());
        if (lottery == null) {
            throw new BusinessException(RetCodeConstants.PARAMS_ERROR, "开奖活动不存在");
        }
        int result = 0;
        Date date = new Date();

        LotteryPool pool = new LotteryPool();
        pool.setId(lotteryPoolDto.getId());
        pool.setInstructions(lotteryPoolDto.getInstructions());
        pool.setAwardName(lotteryPoolDto.getAwardName());
        pool.setCount(lotteryPoolDto.getCount());
        pool.setDescription(lotteryPoolDto.getDescription());
        pool.setExpireDay(lotteryPoolDto.getExpireDay());
        pool.setInfoEnable(lotteryPoolDto.getInfoEnable());
        //校验概率
        for (int m = 0; m < lotteryPoolDto.getTimes().size(); m++) {
            List<LotteryPoolDto> lotteryPools = new ArrayList<>();
            List<LotteryPoolDto> lotteryPool =
                    lotteryPoolMapper.selectByLotteryIdAndTime(lotteryPoolDto.getLotteryId(), lotteryPoolDto.getTimes().get(m).getStartTime());
            for (LotteryPoolDto poolDetail : lotteryPool) {
                if (!poolDetail.getId().equals(lotteryPoolDto.getId())) {
                    lotteryPools.add(poolDetail);
                }
            }
            if (lotteryPools.size() > 0) {
                BigDecimal total = new BigDecimal(0);
                for (int j = 0; j < lotteryPools.size(); j++) {
                    total = total.add(lotteryPools.get(j).getProbability());
                }
                total = total.add(lotteryPoolDto.getProbability());
                if (total.compareTo(new BigDecimal(MarketingEnum.ProbabilityEnum.All.getCode())) == 1) {
                    throw new BusinessException("奖品概率总计大于100%");
                }
            }
        }
        pool.setProbability(lotteryPoolDto.getProbability());
        pool.setImage(lotteryPoolDto.getImage());
        pool.setIntroduce(lotteryPoolDto.getIntroduce());
        pool.setUpdateTime(date);
        result = lotteryPoolMapper.updateBean(pool);
        //删除关联表信息
        lotteryDetailPoolRelationMapper.deleteByPoolId(lotteryPoolDto.getId());
        //新增关联信息
        for (int m = 0; m < lotteryPoolDto.getTimes().size(); m++) {
            LotteryDetail detail = lotteryDetailMapper.selectIdByLotteryIdAndTime(lotteryPoolDto.getLotteryId(), lotteryPoolDto.getTimes().get(m).getStartTime());
            if (null != detail && !StringUtils.isEmpty(detail.getId())) {
                LotteryDetailPoolRelation lotteryDetailPoolRelation = new LotteryDetailPoolRelation();
                lotteryDetailPoolRelation.setCreateTime(date);
                lotteryDetailPoolRelation.setUpdateTime(date);
                lotteryDetailPoolRelation.setDetailId(detail.getId());
                lotteryDetailPoolRelation.setPoolId(lotteryPoolDto.getId());
                lotteryDetailPoolRelationMapper.insert(lotteryDetailPoolRelation);

            } else {
                logger.info("未查询到LotteryId为" + lotteryPoolDto.getLotteryId() + "开始时间为" + lotteryPoolDto.getTimes().get(m).getStartTime() + "的开奖明细");
                throw new BusinessException("时间段信息为空，请检查！");
            }
        }
        if (pool.getInfoEnable()) {
            //删掉之前的所有选填信息项
            lotteryInformationRelationMapper.deleteByPoolId(lotteryPoolDto.getLotteryId());
            for (int i = 0; i < lotteryPoolDto.getInfoIds().length; i++) {
                LotteryInformationRelation lir = new LotteryInformationRelation();
                lir.setInfoId(lotteryPoolDto.getInfoIds()[i]);
                lir.setLotteryPoolId(lotteryPoolDto.getId());
                lir.setCreateTime(date);
                lir.setUpdateTime(date);
                //新增选填信息项
                lotteryInformationRelationMapper.insert(lir);

            }
        }
        //缓存奖品数量
        saveSceneOpenAwardRedis(lottery, pool.getId(), pool.getCount());
        return result;
    }

    /**
     * @param : lotteryId
     */
    @Override
    public List<LotteryTimeDto> selectTime(Integer lotteryId) {
        List<LotteryTimeDto> times = lotteryDetailMapper.selectTimeByLotteryId(lotteryId);
        return times;
    }

    /**
     * @param : lotteryId
     */
    @Override
    public LotteryTimeInfoDto selectTimeInfoByLotteryId(Integer lotteryId) {

        List<LotteryDetailParams> detail = lotteryDetailMapper.selectByLotteryId(lotteryId);
        List<LotteryTimeDto> lotteryTimeDtos = new ArrayList<>();
        LotteryTimeInfoDto lotteryTimeInfoDto = new LotteryTimeInfoDto();
        for (LotteryDetailParams det : detail) {
            LotteryTimeDto lotteryTimeDto = new LotteryTimeDto();
            lotteryTimeDto.setStartTime(det.getStartTime());
            lotteryTimeDto.setEndTime(det.getEndTime());
            lotteryTimeDto.setLastTime((det.getEndTime().getTime() - det.getStartTime().getTime()) / 60000);
            lotteryTimeDto.setType(det.getType());
            lotteryTimeDto.setLotteryCount(det.getLotteryCount());
            lotteryTimeDtos.add(lotteryTimeDto);
        }

        lotteryTimeInfoDto.setEndDate(detail.get(0).getEndDate());
        lotteryTimeInfoDto.setStartDate(detail.get(0).getStartDate());
        lotteryTimeInfoDto.setTimes(lotteryTimeDtos);
        return lotteryTimeInfoDto;
    }

    @Override
    public List<InformationRuleValueDto> getInfoItemsValues(Integer couponId, int userId) {
        List<InformationRuleDto> list1 = informationRuleMapper.getInfoItemsByCouponId(couponId);
        List<InformationRuleValueDto> list = new ArrayList<>();
        //查询信息项的最新值
        for (InformationRuleDto rule : list1) {
            InformationRuleValueDto itemValue = new InformationRuleValueDto();
            itemValue.setInfoCode(rule.getInfoCode());
            itemValue.setItemValue(informationRuleMapper.getInfoItemsValueByUserId(userId, rule.getInfoCode()));
            list.add(itemValue);
        }
        return list;
    }

    @Override
    public LotteryActivityInfoDto selectActivityInfoByLotteryId(Integer userId, Integer lotteryId) {
        LotteryActivityInfoDto lotteryActivityInfoDto = lotteryInfoMapper.selectActivityInfoByLotteryId(lotteryId);
        if (lotteryActivityInfoDto == null) {
            return null;
        }
        List<LotteryDetailParams> lotteryDetails = lotteryDetailMapper.selectByLotteryId(lotteryId);
        List<LotteryDetailTimeDto> lotteryTime = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(lotteryDetails)) {
            lotteryTime = setLotteryDetailData(lotteryDetails, lotteryTime);
            //判断用户累计开奖次数
            LotteryDetailParams currentLottery = null;
            String nowTime = DateUtils.format(new Date(), DateUtils.HHMMSS);
            for (LotteryDetailParams detail : lotteryDetails) {
                String endTime = DateUtils.format(detail.getEndTime(), DateUtils.HHMMSS);
                if (nowTime.compareTo(endTime) <= 0) {
                    currentLottery = detail;
                    break;
                }
            }
            if (currentLottery != null) {
                int userTotalLotteryCount = currentLottery.getLotteryCount();
                if (userTotalLotteryCount > 0) {
                    String drawKey = userId + "-" + lotteryId + "-" + currentLottery.getId();
                    String drawCount = redisCache.getString(drawKey, RedisKeyConstants.SCENE_OPEN_DRAW_COUNT);
                    if (StringUtils.isEmpty(drawCount)) {
                        lotteryActivityInfoDto.setLotteryNumber(userTotalLotteryCount);
                    } else {
                        lotteryActivityInfoDto.setLotteryNumber(userTotalLotteryCount - Integer.valueOf(drawCount));
                    }
                    if (lotteryActivityInfoDto.getLotteryNumber() < 0) {
                        lotteryActivityInfoDto.setLotteryNumber(0);
                    }
                } else {
                    lotteryActivityInfoDto.setLotteryNumber(-1);
                }
            }
        }
        lotteryActivityInfoDto.setLotteryDetail(lotteryTime);
        lotteryActivityInfoDto.setCountNum(lotteryTime.size());
        return lotteryActivityInfoDto;
    }

    @Override
    public List<UnityLotteryDto> selectLotteryByCommunityId(Integer communityId) {
        List<UnityLotteryDto> lotteryInfo = lotteryInfoMapper.selectLotteryInfoListByCommunity(communityId);
        if (lotteryInfo != null) {
            for (UnityLotteryDto dto : lotteryInfo) {
                List<LotteryDetailParams> lotteryDetails = lotteryDetailMapper.selectByLotteryId(dto.getLotteryId());
                List<LotteryDetailTimeDto> lotteryTime = new ArrayList<>();
                if (lotteryDetails != null) {
                    lotteryTime = setLotteryDetailData(lotteryDetails, lotteryTime);
                }
                dto.setDetails(lotteryTime);
            }
            lotteryInfo.removeIf(item -> item.getDetails().stream()
                    .allMatch(dto -> MarketingEnum.LotteryStatusEnum.ENDED.getCode().equals(dto.getStatus())));
        }
        return lotteryInfo;
    }

    private List<LotteryDetailTimeDto> setLotteryDetailData(List<LotteryDetailParams> subDetails, List<LotteryDetailTimeDto> lotteryTime) {
        Date nowDate = new Date();
        for (LotteryDetailParams det : subDetails) {
            int i = DateUtils.betweenDays(det.getStartDate(), det.getEndDate());
            for (int j = 0; j <= i; j++) {
                LotteryDetailTimeDto lotteryTimeDto = new LotteryDetailTimeDto();
                Date nextDate = DateUtils.getAfterADate(det.getStartDate(), j);
                Date startTime = DateUtils.mergeDateTime(nextDate, new Date(det.getStartTime().getTime()));
                Date endTime = DateUtils.mergeDateTime(nextDate, new Date(det.getEndTime().getTime()));
                lotteryTimeDto.setStartTime(startTime);
                lotteryTimeDto.setEndTime(endTime);
                if (System.currentTimeMillis() < startTime.getTime()) {
                    //未开始
                    lotteryTimeDto.setStatus(MarketingEnum.LotteryStatusEnum.WILL.getCode());
                    lotteryTime.add(lotteryTimeDto);
                } else if ((System.currentTimeMillis() > startTime.getTime()) && (System.currentTimeMillis() < endTime.getTime())) {
                    //进行中
                    lotteryTimeDto.setStatus(MarketingEnum.LotteryStatusEnum.ING.getCode());
                    lotteryTime.add(lotteryTimeDto);
                } else {
                    //已结束  同一天的
                    if (DateUtils.sameDay(nowDate, endTime)) {
                        lotteryTimeDto.setStatus(MarketingEnum.LotteryStatusEnum.ENDED.getCode());
                        lotteryTime.add(lotteryTimeDto);
                    }
                }
            }
        }
        //按开奖时间排序
        return lotteryTime.stream().sorted(Comparator.comparing(LotteryDetailTimeDto::getStartTime)).collect(Collectors.toList());
    }

    @Override
    public void deleteLotteryById(int id) {
        lotteryDetailPoolRelationMapper.deleteByPoolId(id);
        int res = lotteryPoolMapper.deleteByPrimaryKey(id);
        if (res == 0) {
            throw new BusinessException("删除失败，数据不存在或者已经删除");
        }
        logger.info("删除成功！");
    }

}
