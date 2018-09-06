package com.idream.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.base.SystemConfigCodeConstans;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.activity.*;
import com.idream.commons.lib.dto.app.*;
import com.idream.commons.lib.dto.appactivity.*;
import com.idream.commons.lib.dto.token.AuthUserInfo;
import com.idream.commons.lib.enums.ActivityEnum;
import com.idream.commons.lib.enums.EventEnum;
import com.idream.commons.lib.enums.UserEnum;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.mapper.*;
import com.idream.commons.lib.model.*;
import com.idream.commons.lib.util.PageRowsUtils;
import com.idream.commons.mvc.annotation.Achievement;
import com.idream.service.ActivityInfoService;
import com.idream.service.AppActivityService;
import com.netflix.discovery.converters.Auto;
import org.apache.catalina.User;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AppActivityServiceImpl implements AppActivityService {

    private static final int EXIST_STATUS = 1;
    private static final Logger logger = LoggerFactory.getLogger(AppActivityServiceImpl.class);
    @Autowired
    private ActivityInfoMapper activityInfoMapper;
    @Autowired
    private ActivityTypeMapper activityTypeMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserActivityRecordMapper userActivityRecordMapper;
    @Autowired
    private CommunityLifeMapper communityLifeMapper;
    @Autowired
    private CommunityLifeLikeRecordMapper communityLifeLikeRecordMapper;
    @Autowired
    private EgisLikeRecordMapper egisLikeRecordMapper;
    @Autowired
    private InformationCollectionRecordMapper informationCollectionRecordMapper;
    @Autowired
    private ActivityInfoService activityInfoService;
    @Autowired
    private UserLikeTypeMapper userLikeTypeMapper;
    @Autowired
    private SystemConfigMapper systemConfigMapper;
    @Autowired
    private UserActivityCollectionMapper userActivityCollectionMapper;
    @Autowired
    private ActivityInformationRelationMapper activityInformationRelationMapper;
    @Autowired
    private UserDislikeRecordMapper userDislikeRecordMapper;
    @Autowired
    private ActivityTagRelationMapper activityTagRelationMapper;
    @Autowired
    private UserTagRelationMapper userTagRelationMapper;
    @Override
    public PagesDto<AppActivityListDto> getNearbyActivityByTypeIdOrLocation(Integer userId, AppActivityListRequestDto params) {
        BigDecimal longitude = params.getLongitude();
        BigDecimal latitude = params.getLatitude();
        Integer typeId = params.getTypeId();
        String cityCode = params.getCityCode();

        PageHelper.startPage(params.getPage(), params.getRows());
        List<AppActivityListDto> list = activityInfoMapper.getNearbyActivityByTypeIdAndCityCode(userId, typeId, longitude, latitude, cityCode);
        PageInfo<AppActivityListDto> info = new PageInfo<>(list);
        return new PagesDto<>(info.getList(), info.getTotal(), params.getPage(), params.getRows());
    }

    @Override
    public PagesDto<AppCommunityLifeDto> getGroupInteractionList(AppActivityGroupRequestDto requestDto) {

        AppActivityDynamicRequestDto params = new AppActivityDynamicRequestDto();
        params.setActivityId(requestDto.getActivityId());
        params.setAuthUserId(requestDto.getAuthUserId());
        List<AppCommunityLifeDto> list = communityLifeMapper.selectActivityDynamicListByActivityId(params);
        List<AppCommunityLifeDto> dtoList = PageRowsUtils.getPageObj(list, requestDto.getPage(), requestDto.getRows());
        return new PagesDto<>(dtoList, list.size(), requestDto.getPage(), requestDto.getRows());
    }

    @Override
    public CommunityLifeLikeCountDto getUnreadInfo(Integer userId, Integer activityId) {

        EgisLikeRecord egisLikeRecord = egisLikeRecordMapper.getByUserId(userId);
        List<CommunityLifeLikeRecord> list = new ArrayList<>();
        CommunityLifeLikeCountDto dto = new CommunityLifeLikeCountDto();
        if (egisLikeRecord == null) {
            list = communityLifeLikeRecordMapper.selectLikeListByUserIdAndActivity(userId, activityId, 0);
        } else {
            list = communityLifeLikeRecordMapper.selectLikeListByUserIdAndActivity(userId, activityId, egisLikeRecord.getLikeRecordId());
        }
        dto.setUnCheckedCount(list.size());
        if (list.isEmpty()) {
            return dto;
        }
        String image = communityLifeLikeRecordMapper.selectUserImage(list.get(list.size() - 1).getUserId());
        dto.setImage(image);
        return dto;
    }

    @Override
    public AppShareInfoResponseDto getUserInfoAndActivityInfo(Integer userId, Integer activityId) {

        AppShareInfoResponseDto dto = new AppShareInfoResponseDto();

        UserInfo userInfo = userInfoMapper.selectUserInfoByUserId(userId);
        if (userInfo != null) {
            dto.setUserImage(userInfo.getImage());
            dto.setNickName(userInfo.getNickName());
        }
        AppActivityInfoDto infoDto = activityInfoMapper.getByActivityId(activityId);
        if (infoDto != null) {
            dto.setTitle(infoDto.getTitle());
            dto.setAddress(infoDto.getAddress());
            dto.setStartTime(infoDto.getStartTime());
            dto.setEndTime(infoDto.getEndTime());
        }
        return dto;
    }

    @Override
    public AppActivityImageResponseDto getImageInfoByActivityId(Integer userId, Integer activityId) {
        AppActivityImageResponseDto dto = activityInfoMapper.getImageInfoByActivityId(userId, activityId);
        return dto;
    }

    @Override
    @Achievement(eventType = EventEnum.EventType.ACTIVITY)
    public Integer addUserToActivity(JSONPublicParam<UserJoinActivityRequestDto> param) {

        //用户id
        Integer userId = param.getAuthUserInfo().getUserId();
        UserJoinActivityRequestDto requestParams = param.getRequestParam();
        //活动id
        Integer activityId = requestParams.getActivityId();
        //城市编码
        Integer cityCode = requestParams.getCityCode();

        //获取活动信息
        ActivityInfo activityInfo = activityInfoMapper.selectByPrimaryKey(activityId);
        if (activityInfo == null) {
            throw new BusinessException(RetCodeConstants.ACTIVITY_NOT_EXIST, "该活动不存在");
        }
        //活动状态.  状态为4才能参加活动
        if (!activityInfo.getStatus().equals(ActivityEnum.ActivityDBStatus.PUTAWAY.getCode())) {
            throw new BusinessException(RetCodeConstants.ACTIVITY_NO_PUTWAY, "该活动临时下架");
        }
        if (System.currentTimeMillis() > activityInfo.getEndTime().getTime()) {
            throw new BusinessException(RetCodeConstants.ERROR_ACTIVITEND, "活动已结束");
        }
        //校验用户能否参加活动
        activityInfoService.checkJoinActivity(userId, activityId, cityCode);

        Date date = new Date();
        /////////////
        //修改或增加用户活动记录
        UserActivityRecord record = userActivityRecordMapper.getUserActivityRecord(activityId, userId);
        logger.error("record......." + JSON.toJSONString(record));
        if (record == null) {
            UserActivityRecord userActivityRecord = new UserActivityRecord();
            userActivityRecord.setActivityId(activityId);
            userActivityRecord.setUserId(userId);
            userActivityRecord.setExitStatus(EXIST_STATUS);
            userActivityRecord.setCreateTime(date);
            userActivityRecord.setUpdateTime(date);
            userActivityRecordMapper.insertSelective(userActivityRecord);
        } else {
            throw new BusinessException(RetCodeConstants.ALDREAY_JOIN_ACTIVITY, "您已经参加了该活动");
        }
        //保存标签
        saveUserActivityTag(userId,activityId);
        return activityId;
    }
    private void saveUserActivityTag(Integer userId, Integer activityId) {
        List<UserActivityTag> userActivityTags = activityTagRelationMapper.selectUserActivityTag(userId, activityId);
        if (CollectionUtils.isNotEmpty(userActivityTags)) {
            Date date = new Date();
            userActivityTags.forEach( i -> {
                UserTagRelation userTagRelation = new UserTagRelation();
                userTagRelation.setUserId(userId);
                userTagRelation.setTagId(i.getTagId());
                userTagRelation.setTagName(i.getTagName());
                userTagRelation.setCategory((byte)1);
                userTagRelation.setCreateTime(date);
                userTagRelation.setUpdateTime(date);
                userTagRelationMapper.insert(userTagRelation);
            });
        }
    }



    @Override
    public ActivityDetailBottomResponseDto activityDetailBottom(Integer authUserId, Integer activityId) {
        logger.info("活动详情底部的(趣聊,搜藏,是否参加)状态........userId: " + authUserId + "  ...activityId: " + activityId);
        ActivityDetailBottomResponseDto activityDetailBottomResponseDto = activityInfoMapper.activityDetailBottom(authUserId, activityId);
        if (activityDetailBottomResponseDto != null) {
            //查询是否加入了聊天群组
            Integer result = activityInfoMapper.selectJoinedGroupStatus(authUserId, activityId);
            activityDetailBottomResponseDto.setJoinedChatGroupStatus(result);
        }
        return activityDetailBottomResponseDto;
    }

    @Override
    public int addActivityCollection(JSONPublicParam<ActivityCollectionRequestParam> params) {
        Integer userId = params.getAuthUserInfo().getUserId();
        Integer activityId = params.getRequestParam().getActivityId();
        logger.info("添加活动收藏......userId: " + userId + " ...activityId" + activityId);
        //查询该活动有没有被搜藏
        UserActivityCollection userActivityCollection = userActivityCollectionMapper.selectByUserIdAndActivityId(userId, activityId);
        int result;
        if (userActivityCollection == null) {
            UserActivityCollection collection = new UserActivityCollection();
            collection.setUserId(userId);
            collection.setActivityId(activityId);
            collection.setCreateTime(new Date());
            result = userActivityCollectionMapper.insert(collection);
        } else {
            logger.info("该活动您已经搜藏过..................userId:" + userId + "....activityId: " + activityId);
            throw new BusinessException(RetCodeConstants.ACTIVITY_ALREADY_COLLECTION, "该活动您已经搜藏过");
        }
        return result;
    }

    @Override
    public int removeActivityCollection(JSONPublicParam<ActivityCollectionRequestParam> params) {
        Integer userId = params.getAuthUserInfo().getUserId();
        Integer activityId = params.getRequestParam().getActivityId();
        logger.info("取消活动收藏....userId: " + userId + "  ...activityId" + activityId);
        UserActivityCollection userActivityCollection = userActivityCollectionMapper.selectByUserIdAndActivityId(userId, activityId);
        //删除记录
        int result;
        if (userActivityCollection != null) {
            result = userActivityCollectionMapper.deleteByUserIdAndActivityId(userId, activityId);
        } else {
            logger.info("该活动您还没有收藏..................userId:" + userId + "....activityId: " + activityId);
            throw new BusinessException(RetCodeConstants.ACTIVITY_NO_COLLECTION, "该活动您还没有收藏");
        }
        return result;
    }


    @Override
    public List<AppActivityUserLikeTypeResponseDto> getAllActivityType() {
        List<AppActivityUserLikeTypeResponseDto> list = activityTypeMapper.getAllActivityType();
        return list;
    }

    @Override
    public AppMyActivityTypeResponseDto getDifferentActivityTypeFromAll(Integer userId) {

        AppMyActivityTypeResponseDto dto = new AppMyActivityTypeResponseDto();
        //我感兴趣的活动类型
        List<AppActivityUserLikeTypeResponseDto> likeTypeList = userLikeTypeMapper.getInterestTypeByUserId(userId);
        if (likeTypeList.isEmpty()) {
            String typeIdsString = systemConfigMapper.selectByConfigCode(SystemConfigCodeConstans.USER_DEFAULT_LIKE);
            String[] typeIds = typeIdsString.split(",");
            for (String typeId : typeIds) {
                AppActivityUserLikeTypeResponseDto typeResponseDto = new AppActivityUserLikeTypeResponseDto();
                ActivityType activityType = activityTypeMapper.selectByPrimaryKey(Integer.valueOf(typeId));
                typeResponseDto.setTypeId(activityType.getId());
                typeResponseDto.setIcon(activityType.getIcon());
                typeResponseDto.setIconLight(activityType.getIconLight());
                typeResponseDto.setTypeName(activityType.getName());
                likeTypeList.add(typeResponseDto);
            }
        }
        //查询其它的活动类型
        List<AppActivityUserLikeTypeResponseDto> allActivityType = activityTypeMapper.getAllActivityType();
        List<Integer> collect = likeTypeList.stream().map(AppActivityUserLikeTypeResponseDto::getTypeId).collect(Collectors.toList());
        List<AppActivityUserLikeTypeResponseDto> otherList = allActivityType.stream().filter(i -> !collect.contains(i.getTypeId())).collect(Collectors.toList());
        dto.setLikeTypeList(likeTypeList);
        dto.setOtherList(otherList);
        return dto;
    }

    @Override
    public void addUserDefaultActivityType(Integer userId) {

        Date date = new Date();
        String typeIdsString = systemConfigMapper.selectByConfigCode(SystemConfigCodeConstans.USER_DEFAULT_LIKE);
        String[] typeIds = typeIdsString.split(",");
        for (String typeId : typeIds) {
            UserLikeType record = userLikeTypeMapper.selectByUserIdAndTypeId(Integer.valueOf(typeId), userId);
            if (record == null) {
                UserLikeType userLikeType = new UserLikeType();
                userLikeType.setTypeId(Integer.valueOf(typeId));
                userLikeType.setUserId(userId);
                userLikeType.setCreateTime(date);
                userLikeTypeMapper.insertSelective(userLikeType);
            }
        }
    }

    @Override
    public void updateMyInterestActivityType(JSONPublicParam<AppSaveTypeDto> param) {

        Integer userId = param.getAuthUserInfo().getUserId();
        userLikeTypeMapper.deleteLikeTypeByUserId(userId);
        Date date = new Date();
        param.getRequestParam().getTypes().stream().map(AppActivityUserLikeTypeRequestDto::getTypeId).filter(i->i>0).distinct().forEach(i->{
            UserLikeType userLikeType = new UserLikeType();
            userLikeType.setTypeId(i);
            userLikeType.setUserId(userId);
            userLikeType.setCreateTime(date);
            userLikeTypeMapper.insertSelective(userLikeType);
        });
    }

    @Override
    public boolean getPageOfActivityType(Integer userId) {

        UserInfo userInfo = userInfoMapper.selectUserInfoByUserId(userId);
        if (!userInfo.getUserRole().equals(UserEnum.UserRoleEnum.VISITOR.getCode())) {
            Integer num = userLikeTypeMapper.countLikeActivityTypeByUserId(userId);
            if (num.equals(0)) {
                //未选择类型，跳转到兴趣选择页
                return true;
            } else {
                //选择了类型，不跳转
                return false;
            }
        }
        return false;
    }

    @Override
    public List<AppActivityUserLikeTypeResponseDto> getTopBarActivityTypeList(Integer userId) {

        UserInfo userInfo = userInfoMapper.selectUserInfoByUserId(userId);
        List<AppActivityUserLikeTypeResponseDto> resultTypeList = new ArrayList<>();
        //用户为注册用户,展示用户感兴趣的活动类型
        if (userInfo != null && !userInfo.getUserRole().equals(UserEnum.UserRoleEnum.VISITOR.getCode())) {
            resultTypeList = userLikeTypeMapper.getInterestTypeByUserId(userId);
        } else {
            //用户为游客，展示默认6种类型
            String typeIdsString = systemConfigMapper.selectByConfigCode(SystemConfigCodeConstans.USER_DEFAULT_LIKE);
            String[] typeIds = typeIdsString.split(",");
            for (String typeId : typeIds) {
                AppActivityUserLikeTypeResponseDto typeResponseDto = new AppActivityUserLikeTypeResponseDto();
                ActivityType activityType = activityTypeMapper.selectByPrimaryKey(Integer.valueOf(typeId));
                typeResponseDto.setTypeId(activityType.getId());
                typeResponseDto.setIcon(activityType.getIcon());
                typeResponseDto.setIconLight(activityType.getIconLight());
                typeResponseDto.setTypeName(activityType.getName());
                resultTypeList.add(typeResponseDto);
            }
        }
        AppActivityUserLikeTypeResponseDto discoverType = new AppActivityUserLikeTypeResponseDto();
        String discoverIcon = systemConfigMapper.selectByConfigCode(SystemConfigCodeConstans.DISCOVER_PAGE_ICON);
        String discoverIconLight = systemConfigMapper.selectByConfigCode(SystemConfigCodeConstans.DISCOVER_PAGE_ICON_LIGHT);
        discoverType.setTypeName("发现");
        discoverType.setIcon(discoverIcon);
        discoverType.setIconLight(discoverIconLight);
        resultTypeList.add(0, discoverType);
        return resultTypeList;
    }

    @Override
    public PagesDto<AppCommunityLifeDto> getDiscoverLifeDynamicList(AppExploreDynamicRequestDto param) {

        //根据lifeId获取typeId
        Integer typeId = communityLifeMapper.selectByPrimaryKey(param.getBusinessId()).getTypeId();
        param.setTypeId(typeId);

        List<AppCommunityLifeDto> list = communityLifeMapper.selectDiscoverLifeDynamicListByTypeId(param);
        List<AppCommunityLifeDto> dtoList = PageRowsUtils.getPageObj(list, param.getPage(), param.getRows());
        return new PagesDto<>(dtoList, list.size(), param.getPage(), param.getRows());
    }

    @Override
    public PagesDto<AppCommunityLifeDto> getDiscoverActivityDynamicList(AppActivityDynamicRequestDto param) {

        Integer activityId = communityLifeMapper.selectByPrimaryKey(param.getBusinessId()).getActivityId();
        param.setActivityId(activityId);

        List<AppCommunityLifeDto> list = communityLifeMapper.selectActivityDynamicListByActivityId(param);
        List<AppCommunityLifeDto> dtoList = PageRowsUtils.getPageObj(list, param.getPage(), param.getRows());
        return new PagesDto<>(dtoList, list.size(), param.getPage(), param.getRows());
    }

    @Override
    public void addDisLike(Integer userId, AppDislikeParams param) {
        UserDislikeRecord record = new UserDislikeRecord();
        record.setBusinessId(param.getBusinessId());
        record.setBusinessType(param.getBusinessType().byteValue());
        record.setUserId(userId);
        record.setCreateTime(new Date());
        userDislikeRecordMapper.insertSelective(record);
    }

    @Override
    public AppDiscoveryPageDto<AppDiscoveryDto> getDiscoverPage(AppDiscoveryParams params) {
        Set<AppDiscoveryDto> collection = new HashSet<>();

        int total = params.getRows();
        int lifePage = params.getLifePage();
        int activityLifePage = params.getActivityLifePage();
        int activityPage = params.getActivityPage();

        PageHelper.startPage(lifePage, params.getLifeSize());
        Set<AppDiscoveryDto> life = communityLifeMapper.selectDiscoverLife(params);


        PageHelper.startPage(activityLifePage, params.getActivityLifeSize());
        Set<AppDiscoveryDto> activityLife = communityLifeMapper.selectDiscoverActivityLife(params);

        PageHelper.startPage(activityPage, params.getActivitySize());
        Set<AppDiscoveryDto> activity = activityInfoMapper.selectDiscoverActivity(params);


        collection.addAll(life);
        collection.addAll(activityLife);
        collection.addAll(activity);
        //校验是否满足数量
        int lastNum = total - collection.size();
        if (lastNum > 0) {
            Set<AppDiscoveryDto> lastCollection = new HashSet<>();
            boolean lifeFlag = life.size() == params.getLifeSize();
            boolean activityLifeFlag = activityLife.size() == params.getActivityLifeSize();
            boolean activityFlag = activity.size() == params.getActivitySize();

            while (lifeFlag && lastCollection.size() < lastNum) {
                PageHelper.startPage(++lifePage, params.getLifeSize());
                Set<AppDiscoveryDto> list = communityLifeMapper.selectDiscoverLife(params);
                lastCollection.addAll(list);
                lifeFlag = !list.isEmpty();
            }

            while (activityLifeFlag && lastCollection.size() < lastNum) {
                PageHelper.startPage(++activityLifePage, params.getActivityLifeSize());
                Set<AppDiscoveryDto> list = communityLifeMapper.selectDiscoverActivityLife(params);
                lastCollection.addAll(list);
                activityLifeFlag = !list.isEmpty();
            }
            while (activityFlag && lastCollection.size() < lastNum) {
                PageHelper.startPage(++activityPage, params.getActivitySize());
                Set<AppDiscoveryDto> list = activityInfoMapper.selectDiscoverActivity(params);
                lastCollection.addAll(list);
                activityFlag = !list.isEmpty();
            }

            collection.addAll(lastCollection);
        }
        if (collection.size()>total) {
            collection=collection.stream().limit(total).collect(Collectors.toSet());
        }
        return new AppDiscoveryPageDto<>(collection, activityPage, activityLifePage, lifePage);
    }

    @Override
    public AppActivityUserLikeTypeResponseDto getActivityTypeByTypeId(Integer typeId) {

        ActivityType activityType = activityTypeMapper.selectByPrimaryKey(typeId);
        AppActivityUserLikeTypeResponseDto dto = new AppActivityUserLikeTypeResponseDto();
        if (activityType == null) {
            return dto;
        }
        dto.setIcon(activityType.getIcon());
        dto.setIconLight(activityType.getIconLight());
        dto.setTypeName(activityType.getName());
        dto.setTypeId(activityType.getId());
        return dto;
    }

    @Override
    public AppActivityImageResponseDto getImageInfoByLifeId(Integer userId, Integer lifeId) {
        Integer activityId = communityLifeMapper.selectActivityIdByLifeId(lifeId);
        AppActivityImageResponseDto dto = activityInfoMapper.getImageInfoByActivityId(userId, activityId);
        return dto;
    }

}