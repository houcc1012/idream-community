package com.idream.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.activity.*;
import com.idream.commons.lib.dto.admin.ActivityRecentOneDto;
import com.idream.commons.lib.dto.appactivity.AppActivityTypeResponseDto;
import com.idream.commons.lib.dto.user.ParticipateActivityDto;
import com.idream.commons.lib.enums.ActivityTimeEnum;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.mapper.ActivityGroupRelationMapper;
import com.idream.commons.lib.mapper.ActivityInfoMapper;
import com.idream.commons.lib.mapper.ActivityTaskMapper;
import com.idream.commons.lib.mapper.ActivityTaskRecordMapper;
import com.idream.commons.lib.mapper.ActivityTypeMapper;
import com.idream.commons.lib.mapper.LotteryInfoMapper;
import com.idream.commons.lib.mapper.UserActivityRecordMapper;
import com.idream.commons.lib.mapper.UserInfoMapper;
import com.idream.commons.lib.mapper.UserManagerMapper;
import com.idream.commons.lib.mapper.UserVisitRecordMapper;
import com.idream.commons.lib.model.ActivityInfo;
import com.idream.commons.lib.model.UserActivityRecord;
import com.idream.commons.lib.model.UserInfo;
import com.idream.service.ActivityDisplayService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ActivityDisplayServiceImpl implements ActivityDisplayService {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private ActivityInfoMapper activityInfoMapper;
    @Autowired
    private ActivityTaskMapper activityTaskMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserActivityRecordMapper userActivityRecordMapper;

    @Autowired
    private ActivityGroupRelationMapper activityGroupRelationMapper;
    @Autowired
    private UserVisitRecordMapper userVisitRecordMapper;

    @Autowired
    private ActivityTaskRecordMapper activityTaskRecordMapper;

    @Autowired
    private LotteryInfoMapper lotteryInfoMapper;

    @Autowired
    private UserManagerMapper userManagerMapper;

    @Autowired
    private ActivityTypeMapper activityTypeMapper;

    /**
     * 定位权限未定义
     */
    private final static String NO_LOCATION = "undefined";

    //展示活动信息
    @Override
    public DisplayActivityResponseDto displayActivity(Integer activityId, Integer authUserId) {

        DisplayActivityResponseDto displayActivityDto = new DisplayActivityResponseDto();
        ActivityInfo activityInfo = activityInfoMapper.selectByPrimaryKey(activityId);
        Integer activityStatus = activityInfoMapper.getActivityStatus(activityId);
        if (activityInfo != null) {
            displayActivityDto.setImage(activityInfo.getImage());
            displayActivityDto.setSubtitle(activityInfo.getSubtitle());
            displayActivityDto.setContent(activityInfo.getContent());
            displayActivityDto.setTitle(activityInfo.getTitle());
            displayActivityDto.setStartTime(activityInfo.getStartTime().getTime());
            displayActivityDto.setEndTime(activityInfo.getEndTime().getTime());
            displayActivityDto.setAddress(activityInfo.getAddress());
            //(1,未开始) (2,进行中) (3,已结束) (4,已取消)
            displayActivityDto.setActivityStatus(activityStatus);
            displayActivityDto.setActivityId(activityInfo.getId());
            //活动地址
            displayActivityDto.setAddress(activityInfo.getAddress());
            displayActivityDto.setWeekDay(activityInfo.getWeekDay());
            Byte type = activityInfo.getTimeType();
            displayActivityDto.setTimeType(type.intValue());
            Integer timeType = Integer.valueOf(type.intValue());
            if (timeType.equals(ActivityTimeEnum.ActivityTimeRuleStatus.SINGLE_TYPE.getCode())) {
                //一次活动
                ActivityRecentOneDto activityRecentOne;
                if (System.currentTimeMillis() > activityInfo.getEndTime().getTime()) {
                    activityRecentOne = activityTaskMapper.getMiniActivityRecentOne(activityId);
                } else {
                    activityRecentOne = activityTaskMapper.getActivityRecentOne(activityId);
                }
                if (activityRecentOne != null) {
                    displayActivityDto.setDistanceCurrentDay(activityRecentOne.getDistanceCurrentDay());
                }
            }
            if (timeType.equals(ActivityTimeEnum.ActivityTimeRuleStatus.MUTIPLE_TYPE.getCode())) {
                //多次
                ActivityRecentOneDto activityRecentOne;
                if (System.currentTimeMillis() > activityInfo.getEndTime().getTime()) {
                    activityRecentOne = activityTaskMapper.getMiniActivityRecentOne(activityId);
                } else {
                    activityRecentOne = activityTaskMapper.getActivityRecentOne(activityId);
                }
                if (activityRecentOne != null) {
                    displayActivityDto.setStartTime(activityRecentOne.getStartTime().getTime());
                    displayActivityDto.setEndTime(activityRecentOne.getEndTime().getTime());
                    displayActivityDto.setDistanceCurrentDay(activityRecentOne.getDistanceCurrentDay());
                }
            }
            if (timeType.equals(ActivityTimeEnum.ActivityTimeRuleStatus.PERIOD_TYPE.getCode())) {
                //周期的
                ActivityRecentOneDto activityRecentOne;
                if (System.currentTimeMillis() > activityInfo.getEndTime().getTime()) {
                    activityRecentOne = activityTaskMapper.getMiniActivityRecentOne(activityId);
                } else {
                    activityRecentOne = activityTaskMapper.getActivityRecentOne(activityId);
                }
                if (activityRecentOne != null) {
                    displayActivityDto.setDistanceCurrentDay(activityRecentOne.getDistanceCurrentDay());
                }
            }
            //查询用户是否参加该活动以及当前活动已经报名人数
            ActivityJoinedStatusAndCount activityJoinedStatusAndCount = userActivityRecordMapper.getActivityJoinedAndCurrentCountPeople(authUserId, activityId);
            if (activityJoinedStatusAndCount != null) {
                displayActivityDto.setJoinedStatus(activityJoinedStatusAndCount.getJoinedStatus());
                displayActivityDto.setCurrentActivityCountPeople(activityJoinedStatusAndCount.getCurrentActivityCountPeople());
            }
            //活动允许参加多少人
            displayActivityDto.setCount(activityInfo.getCount());
            //查询活动发布者书   2是凤翮筑梦书屋, 直接把这个昵称返回
            Integer userId = activityInfo.getUserId();
            if (userId.equals(2)) {
                UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
                if (userInfo != null) {
                    displayActivityDto.setPublisher(userInfo.getNickName());
                    displayActivityDto.setPublisherImage(userInfo.getImage());
                }
            } else {
                PublisherInfo publisherInfo = userManagerMapper.selectActivityPublisherBook(userId);
                if (publisherInfo != null) {
                    displayActivityDto.setPublisher(publisherInfo.getPublishName());
                    displayActivityDto.setPublisherImage(publisherInfo.getPublishImage());
                }
            }
            //社群信息
            displayActivityDto.setTask(activityAssociationTask(authUserId, activityId));
        }

        //查询活动绑定的社区, 小区, 书屋
        List<String> regionName = activityGroupRelationMapper.getRegionNameByActivityId(activityId);
        List<String> communityName = activityGroupRelationMapper.getGroupNameByActivityId(activityId);
        List<String> bookName = activityGroupRelationMapper.getBookNameByActivityId(activityId);
        displayActivityDto.setRegionName(regionName);
        displayActivityDto.setCommunityName(communityName);
        displayActivityDto.setBookName(bookName);
        //活动的访问量
        Integer activityVisitCount = userVisitRecordMapper.getActivityVisitCount(activityId);
        displayActivityDto.setVisitCount(activityVisitCount);
        return displayActivityDto;
    }

    private MiniActivityAssociationTask activityAssociationTask(Integer authUserId, Integer activityId) {
        //查询打卡信息
        MiniActivityAssociationTask miniActivityAssociationTask = activityTaskMapper.selectActiviyAssociationByActiviyId(activityId);
        if (miniActivityAssociationTask != null) {
            //查询用户有么有打卡
            int i = activityTaskRecordMapper.countByUserIdAndTaskId(authUserId, miniActivityAssociationTask.getTaskId());
            if (i > 0) {
                miniActivityAssociationTask.setUserTask(true);
            } else {
                miniActivityAssociationTask.setUserTask(false);
            }
        } else {
            return new MiniActivityAssociationTask();
        }
        return miniActivityAssociationTask;
    }

    @Override
    public PagesDto<ParticipateActivityDto> getUserActivityPartake(int userId, int page, int rows) {
        // 开启分页
        PageHelper.startPage(page, rows);
        // 查询用户参加活动
        List<ParticipateActivityDto> data = userInfoMapper.getUserActivityPartake(userId);
        PageInfo<ParticipateActivityDto> pageInfo = new PageInfo<>(data);
        return new PagesDto<ParticipateActivityDto>(data, pageInfo.getTotal(), page, rows);

    }

    @Override
    public MiniActivityAssociationDto activityAssociation(Integer authUserId, Integer activityId) {
        logger.info("活动社群..........userId: " + authUserId + " .....activityId" + activityId);
        //查询活动图片,标题
        ActivityInfo activityInfo = activityInfoMapper.selectByPrimaryKey(activityId);
        if (activityInfo == null) {
            logger.info("该活动不存在..........................activityId: " + activityId);
            throw new BusinessException(RetCodeConstants.ACTIVITY_NOT_EXIST, "该活动不存在");
        }
        MiniActivityAssociationActivityInfo associationActivityInfo = new MiniActivityAssociationActivityInfo();
        associationActivityInfo.setTitle(activityInfo.getTitle());
        associationActivityInfo.setImage(activityInfo.getImage());

        //查询打卡信息
        MiniActivityAssociationTask miniActivityAssociationTask = activityTaskMapper.selectActiviyAssociationByActiviyId(activityId);
        if (miniActivityAssociationTask != null) {
            //查询用户有么有打卡
            int i = activityTaskRecordMapper.countByUserIdAndTaskId(authUserId, miniActivityAssociationTask.getTaskId());
            if (i > 0) {
                miniActivityAssociationTask.setUserTask(true);
            } else {
                miniActivityAssociationTask.setUserTask(false);
            }
        }

        //查询活动开奖信息
        List<MiniActivityAssociationLottery> associationLotteries = lotteryInfoMapper.selectByActviity(activityId);
        MiniActivityAssociationDto associationDto = new MiniActivityAssociationDto();
        associationDto.setActivityInfo(associationActivityInfo);
        if (miniActivityAssociationTask != null) {
            associationDto.setTask(miniActivityAssociationTask);
        } else {
            associationDto.setTask(new MiniActivityAssociationTask());
        }

        associationDto.setLottery(associationLotteries);

        return associationDto;
    }

    @Override
    public List<EspeciallyActivityResponseDto> especiallyActivity(Integer authUserId, String activityIds) {
        //正则校验
        String regex = "\\d+(,\\d+)*";
        List<EspeciallyActivityResponseDto> list = new ArrayList<>();
        if (StringUtils.isNoneBlank(activityIds) && activityIds.matches(regex)) {
            list = activityInfoMapper.especiallyActivity(activityIds, authUserId);
        }
        return list;
    }


    @Override
    public List<AppActivityTypeResponseDto> getMiniActivityTypeByCityCode(String cityCode) {
        return activityTypeMapper.getMiniActivityTypeByCityCode(cityCode);
    }

    @Override
    public PagesDto<MiniActivityDiscoverypageResponseDto> miniDiscoverypage(DiscoveryPageRequestParams params) {
        PageHelper.startPage(params.getPage(), params.getRows());
        List<MiniActivityDiscoverypageResponseDto> list = activityInfoMapper.selectMiniDiscoverpage(params);
        // 未找到查询已结束的最近三条活动
        if (params.getPage() == 1 && CollectionUtils.isEmpty(list)) {
            list = activityInfoMapper.selectMiniDiscoverpageOverEndTime(params.getCityCode(), params.getAuthUserId());
        }
        PageInfo<MiniActivityDiscoverypageResponseDto> pageInfo = new PageInfo<>(list);
        return new PagesDto(list, pageInfo.getTotal(), params.getPage(), params.getRows());
    }

    @Override
    public PagesDto<MiniActivityDiscoverypageResponseDto> miniActivityByType(DiscoveryPageRequestParams params) {
        PageHelper.startPage(params.getPage(), params.getRows());
        String cityCode = params.getCityCode();
        String typeId = params.getTypeId();
        logger.info("小程序根据活动类型查询活动...............userId: " + params.getAuthUserId() + "   ...cityCode" + cityCode + " ...typeId:" + typeId);
        List<MiniActivityDiscoverypageResponseDto> list = activityInfoMapper.miniActivityByType(params);
        PageInfo<MiniActivityDiscoverypageResponseDto> pageInfo = new PageInfo<>(list);
        return new PagesDto(list, pageInfo.getTotal(), params.getPage(), params.getRows());
    }


}
