package com.idream.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.idream.commons.lib.base.IntegrationConfigCodeConstans;
import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.base.SystemConfigCodeConstans;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.activity.ActivityCardDto;
import com.idream.commons.lib.dto.activity.ActivityInfoDto;
import com.idream.commons.lib.dto.activity.PromotionRecordParams;
import com.idream.commons.lib.dto.amap.AmapLocation2AddressDto;
import com.idream.commons.lib.dto.app.*;
import com.idream.commons.lib.dto.appactivity.AppActivitySearchDto;
import com.idream.commons.lib.dto.appactivity.AppActivitySearchParams;
import com.idream.commons.lib.dto.token.AuthUserInfo;
import com.idream.commons.lib.enums.ActivityEnum;
import com.idream.commons.lib.enums.BusinessEnum;
import com.idream.commons.lib.enums.EventEnum;
import com.idream.commons.lib.enums.MarketingEnum;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.mapper.*;
import com.idream.commons.lib.model.*;
import com.idream.commons.lib.util.AmapUtils;
import com.idream.commons.lib.util.DistanceUtils;
import com.idream.commons.lib.util.RequestValueUtils;
import com.idream.commons.lib.util.StringFilterUtils;
import com.idream.commons.mvc.annotation.Achievement;
import com.idream.feign.FeignMarketingService;
import com.idream.service.ActivityInfoService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author jeffery
 */
@Service
public class ActivityInfoServiceImpl implements ActivityInfoService {

    private static final Logger logger = LoggerFactory.getLogger(ActivityInfoServiceImpl.class);
    //我的社区距离判断
    private final static Integer myCommunityDistance = 1000;
    @Autowired
    private UserActivityRecordMapper userActivityRecordMapper;
    @Autowired
    private RegionInfoMapper regionInfoMapper;
    @Autowired
    private RegionGroupInfoMapper regionGroupInfoMapper;
    @Autowired
    private ActivityJoinRelationMapper activityJoinRelationMapper;
    @Autowired
    private UserRegionRelationMapper userRegionRelationMapper;
    @Autowired
    private ActivityInformationRelationMapper activityInformationRelationMapper;
    @Autowired
    private InformationCollectionRecordMapper informationCollectionRecordMapper;
    @Autowired
    private ActivityTaskRecordMapper activityTaskRecordMapper;
    @Autowired
    private ActivityTaskMapper activityTaskMapper;
    @Autowired
    private IntegrationConfigMapper integrationConfigMapper;
    @Autowired
    private SystemConfigMapper systemConfigMapper;
    @Autowired
    private ActivityInfoMapper activityInfoMapper;
    @Autowired
    private UserCommunityRelationMapper userCommunityRelationMapper;
    @Autowired
    private FeignMarketingService feignMarketingService;
    @Autowired
    private PromotionRecordMapper promotionRecordMapper;

    @Override
    public List<ActivityInfo> getActivityInfoByCommunityId(Integer communityId) {
        List<ActivityInfo> activityInfoList = activityInfoMapper.getActivityInfoByCommunityId(communityId);
        return activityInfoList;
    }

    @Override
    public ActivityInfoDto getEntityById(Integer id) {
        ActivityInfoDto aid = new ActivityInfoDto();
        aid = activityInfoMapper.getActivityInfoByPrimaryKey(id);
        return aid;
    }

    @Override
    public void checkJoinActivity(Integer userId, Integer activityId, Integer cityCode) {
        boolean result = false;

        ActivityInfo activityInfo = activityInfoMapper.selectByPrimaryKey(activityId);
        if (activityInfo == null) {
            throw new BusinessException(RetCodeConstants.ACTIVITY_NOT_EXIST, "没有此活动");
        }
        if (!activityInfo.getStatus().equals(ActivityEnum.ActivityDBStatus.PUTAWAY.getCode())) {
            throw new BusinessException(RetCodeConstants.ACTIVITY_NO_PUTWAY, "活动已下架");
        }
        //查询全部活动
        List<ActivityJoinRelation> activity = activityJoinRelationMapper.selectByActivityId(activityId);

        //判断城市是否能参加
        Map<Byte, List<ActivityJoinRelation>> group = activity.stream().collect(Collectors.groupingBy(i -> i.getJoinType()));

        //全国的活动直接返回true
        if (!result) {
            boolean cityResult = group.containsKey(ActivityEnum.JoinDisplayType.ALL.getCode());
            if (cityResult) {
                result = true;
            }
        }
        //判断城市是否可参加
        if (!result) {
            boolean cityResult = group.containsKey(ActivityEnum.JoinDisplayType.CITY.getCode());
            if (cityResult) {
                boolean b = group.get(ActivityEnum.JoinDisplayType.CITY.getCode()).stream().anyMatch(i -> i.getJoinId().equals(cityCode));
                if (!b) {
                    logger.error("只有该城市的用户才可参加活动哦.......................userId: " + userId + "..activityId: " + activityId + "..cityCode: " + cityCode);
                    throw new BusinessException(RetCodeConstants.NOTALLOW, "只有该城市的用户才可参加活动哦");
                }
                result = b;
            }
        }

        //判断该活动有没有关联用户绑定的大社区
        if (!result) {
            boolean b = group.containsKey(ActivityEnum.JoinDisplayType.REGION.getCode());
            List<Integer> regionIds = userRegionRelationMapper.selectRegionIdByUserId(userId);
            if (b) {
                boolean b1 = group.get(ActivityEnum.JoinDisplayType.REGION.getCode()).stream().anyMatch(i -> regionIds.stream().anyMatch(r -> r.equals(i.getJoinId())));
                result = b1;
            }
        }

        //判断该用户绑定的的小区
        if (!result) {
            boolean b = group.containsKey(ActivityEnum.JoinDisplayType.GROUP.getCode());
            //查询用户关联的小区
            List<Integer> communityIds = userCommunityRelationMapper.selectCommunityIdByUserId(userId);
            if (b) {
                boolean b1 = group.get(ActivityEnum.JoinDisplayType.GROUP.getCode()).stream().anyMatch(i -> communityIds.stream().anyMatch(r -> r.equals(i.getJoinId())));
                if (!b1) {
                    logger.error("只有该小区的用户才可参加活动哦.......................userId: " + userId + "..activityId: " + activityId + "..cityCode: " + cityCode);
                    throw new BusinessException(RetCodeConstants.NOTALLOW, "只有该小区的用户才可参加活动哦");
                }
               // result = b1;
            }
        }

        Integer count = activityInfo.getCount();
        //当前活动已经参加的人数
        Integer i = userActivityRecordMapper.countUserRegist(activityId);
        //count==0 为报名人数没有上线
        if (count != 0 && i >= count) {
            logger.error("参加人数已达到上线.......................userId: " + userId + "..activityId: " + activityId + "..cityCode: " + cityCode);
            throw new BusinessException(RetCodeConstants.OVERCOUNT, "参加人数已达到上线");
        }

        //查询该用户有没有关联标签
        Integer countTag = activityInformationRelationMapper.checkJoinActivityGetActivityInformationRelationByActivityId(activityId);
        if (countTag != null && countTag >0) {
            //如果有标签, 就查询信息搜集表中有没有数据
            int recordCounts= informationCollectionRecordMapper.getInformationCollectionRecordByUserIdAndActivityId(userId, activityId);
            if (recordCounts==0) {
                logger.error("报名信息没有搜集.......................userId: " + userId + "..activityId: " + activityId + "..cityCode: " + cityCode);
                throw new BusinessException(RetCodeConstants.NO_INFORMATIONCOLLECTION, "报名信息没有搜集");
            }
        }
    }

    @Override
    public CommunityActivityListResponseDto selectCommunityActivityListByCommunityId(Integer userId, Integer communityId) {
        //通过communityId查询大社区(手动或者自动),取第一条(手动优先)
        RegionInfo r = regionInfoMapper.getRegionByCommunityId(communityId);
        //category=1为小区
        RegionGroupInfo regionGroupInfo = regionGroupInfoMapper.getRegionGroupInfoById(communityId, 1);
        CommunityActivityListResponseDto communityActivityListResponseDto = new CommunityActivityListResponseDto();
        if (r != null) {
            communityActivityListResponseDto.setRegionId(r.getId());
            communityActivityListResponseDto.setRegionName(r.getRegionName());
            communityActivityListResponseDto.setRegionLongitude(r.getLongitude());
            communityActivityListResponseDto.setRegionLatitude(r.getLatitude());
        }
        communityActivityListResponseDto.setCommunityId(regionGroupInfo.getId());
        communityActivityListResponseDto.setCommunityName(regionGroupInfo.getName());
        communityActivityListResponseDto.setLongitude(regionGroupInfo.getLongitude());
        communityActivityListResponseDto.setLatitude(regionGroupInfo.getLatitude());
        //button展示状态 0:1:2 | 不显示我的社区button : 显示我的社区button : 显示未添加我的社区button
        //不显示我的社区button
        communityActivityListResponseDto.setDisplayButton(BusinessEnum.MyCommunityButtonStatusEnum.NO_SHOW_MYCOMMUNITY_BUTTON.getCode());
        return communityActivityListResponseDto;
    }

    @Override
    public CommunityActivityListResponseDto selectCommunityActivityListByRegionId(Integer userId, NeighborRegionParams neighborRegionParams) {
        List<CommunityInfoListDto> communityInfoList = regionGroupInfoMapper.getCommunityInfoByRegionId(neighborRegionParams.getRegionId(), neighborRegionParams.getLongitude(), neighborRegionParams.getLatitude());
        RegionInfo r = regionInfoMapper.selectByPrimaryKey(neighborRegionParams.getRegionId());
        CommunityActivityListResponseDto communityActivityListResponseDto = new CommunityActivityListResponseDto();
        communityActivityListResponseDto.setRegionId(neighborRegionParams.getRegionId());
        if (r != null) {
            communityActivityListResponseDto.setRegionName(r.getRegionName());
            communityActivityListResponseDto.setRegionLongitude(r.getLongitude());
            communityActivityListResponseDto.setRegionLatitude(r.getLatitude());
        }
        if (CollectionUtils.isNotEmpty(communityInfoList)) {
            communityActivityListResponseDto.setCommunityId(communityInfoList.get(0).getCommunityId());
            communityActivityListResponseDto.setCommunityName(communityInfoList.get(0).getCommunityName());
            communityActivityListResponseDto.setLongitude(communityInfoList.get(0).getLongitude());
            communityActivityListResponseDto.setLatitude(communityInfoList.get(0).getLatitude());
        }
        //button展示状态 0:1:2 | 不显示我的社区button : 显示我的社区button : 显示未添加我的社区button
        //显示我的社区button
        communityActivityListResponseDto.setDisplayButton(BusinessEnum.MyCommunityButtonStatusEnum.SHOW_MYCOMMUNITY_BUTTON.getCode());
        return communityActivityListResponseDto;
    }

    @Override
    public List<RegionActivityDto> selectActivityInfoByRegionId(RegionActivityParams regionActivityParams) {
        List<RegionActivityDto> activityInfoList = activityInfoMapper.getActivityInfoByRegionId(regionActivityParams);
        return activityInfoList;
    }

    @Override
    @Achievement(eventType = EventEnum.EventType.TASK)
    public JSONPublicDto addUserTask(JSONPublicParam<ActivityCardDto> dto) {
        JSONPublicDto<Integer> data = new JSONPublicDto<>();
        ActivityTaskRecord taskRecord = new ActivityTaskRecord();
        ActivityCardDto param = dto.getRequestParam();
        AuthUserInfo info = dto.getAuthUserInfo();
        Integer userId = info.getUserId();

        //校验是否已打卡
        int count = activityTaskRecordMapper.countByUserIdAndTaskId(userId, param.getTaskId());
        if (count > 0) {
            throw new BusinessException("9999", "您已打过卡,请勿重复打卡");
        }
        //获取指定的打卡
        ActivityTask task = activityTaskMapper.selectByPrimaryKey(param.getTaskId());
        // 查询指定活动地址
        ActivityInfo activityInfo = activityInfoMapper.selectByPrimaryKey(task.getActivityId());
        Byte level = activityInfo.getLevel();
        if (ActivityEnum.ActivityLevel.COUNTRY.getCode().equals(level)) {
            //全国活动 , 直接打卡
            saveUserTaskRecord(data, taskRecord, param, info, userId, activityInfo);
        } else if (ActivityEnum.ActivityLevel.CITY.getCode().equals(level)) {
            //根据传来的经纬度获取当前用户所在的城市
            AmapLocation2AddressDto address = AmapUtils.getAddress(param.getLongitude(), param.getLatitude());
            if (activityInfo.getCity().equals(address.getCity())) {
                //若用户在当前城市直接打卡
                saveUserTaskRecord(data, taskRecord, param, info, userId, activityInfo);
            } else {
                //若不在当前城市,提示不在当前活动所在的城市
                throw new BusinessException(RetCodeConstants.NOT_IN_ACTIVITY_CITY, "您不在当前活动举办的城市哦");
            }
        } else {
            //社区或者小区的 距活动地址距离范围打卡
            double distance = DistanceUtils.getDistance(param.getLongitude().doubleValue(), param.getLatitude().doubleValue(),
                    activityInfo.getLongitude().doubleValue(), activityInfo.getLatitude().doubleValue());
            // 计算距离
            int defaultDistance = systemConfigMapper.selectIntegerValueByConfigCode(SystemConfigCodeConstans.ACTIVITY_TASK_DISTANCE);
            if (distance > defaultDistance) {
                throw new BusinessException(RetCodeConstants.ERROR_ACTIVITY_TASK_RECORD, "温馨提示：请到活动现场附近才能签到成功哦");
            } else {
                saveUserTaskRecord(data, taskRecord, param, info, userId, activityInfo);
            }
        }
        return data;
    }

    private void saveUserTaskRecord(JSONPublicDto<Integer> data, ActivityTaskRecord taskRecord, ActivityCardDto param, AuthUserInfo info, Integer userId, ActivityInfo activityInfo) {
        Date date = new Date();
        taskRecord.setLongitude(param.getLongitude());
        taskRecord.setLatitude(param.getLatitude());
        taskRecord.setTaskId(param.getTaskId());
        taskRecord.setUserId(info.getUserId());
        taskRecord.setCreateTime(date);
        taskRecord.setActivityId(activityInfo.getId());
        activityTaskRecordMapper.insertSelective(taskRecord);

        //添加打卡记录
        Integer score = addTaskScore(userId, param.getTaskId());
        //勿删,aop中使用
        RequestValueUtils.setAttributeValue("activityId", activityInfo.getId());
        data.setRetMsg("打卡成功");
        data.setResponseData(score);
    }
    private Integer addTaskScore(Integer userId, Integer taskId) {
        Integer score = integrationConfigMapper.getIntegerByCode(IntegrationConfigCodeConstans.ACTIVITY_TASK_SCORE);
        if (score != null && score > 0) {
            feignMarketingService.addUserScore(score, MarketingEnum.ScoreFromType.CLOCK_ON.getCode(), MarketingEnum.CouponRecordType.GET.getCode(), taskId, userId);
        }
        return score;
    }

    @Override
    public PagesDto<AppActivitySearchDto> getActivityByActivityTitle(Integer userId, AppActivitySearchParams params) {
        PageHelper.startPage(params.getPage(), params.getRows());
        String title = StringFilterUtils.emojiAndEscapeFilter(params.getTitle());
        List<AppActivitySearchDto> list = activityInfoMapper.selectActivityByTitleAndCityCode(userId, title, params.getCityCode());
        PageInfo<AppActivitySearchDto> info = new PageInfo<>(list);
        return new PagesDto<>(info.getList(), info.getTotal(), params.getPage(), params.getRows());
    }

    @Override
    public int addPromotionTeam(JSONPublicParam<PromotionRecordParams> param) {
        Integer userId = param.getAuthUserInfo().getUserId();
        Byte businessType = param.getRequestParam().getBusinessType();
        Integer count = promotionRecordMapper.getPromotionRecord(userId);
        if (count > 0) {
            logger.error("该用户的userId: " + userId + "..businessType: " + businessType);
            throw new BusinessException(RetCodeConstants.WX_PROMOTION_ERROR, "该用户已经通过该方式注册小程序成功,请勿重复操作...");
        }
        PromotionRecord promotionRecord = new PromotionRecord();
        promotionRecord.setBusinessId(param.getRequestParam().getBusinessId());
        promotionRecord.setBusinessType(businessType);
        promotionRecord.setCreateTime(new Date());
        promotionRecord.setUserId(userId);
        int i = promotionRecordMapper.insertSelective(promotionRecord);
        return i;
    }


}
