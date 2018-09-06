package com.idream.service;

import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.activity.*;
import com.idream.commons.lib.dto.app.*;
import com.idream.commons.lib.dto.appactivity.*;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface AppActivityService {

    PagesDto<AppActivityListDto> getNearbyActivityByTypeIdOrLocation(Integer userId, AppActivityListRequestDto params);

    PagesDto<AppCommunityLifeDto> getGroupInteractionList(AppActivityGroupRequestDto requestDto);

    CommunityLifeLikeCountDto getUnreadInfo(Integer userId, Integer activityId);

    AppShareInfoResponseDto getUserInfoAndActivityInfo(Integer userId, Integer activityId);

    AppActivityImageResponseDto getImageInfoByActivityId(Integer userId, Integer activityId);

    Integer addUserToActivity(JSONPublicParam<UserJoinActivityRequestDto> param);

    List<AppActivityUserLikeTypeResponseDto> getAllActivityType();

    AppMyActivityTypeResponseDto getDifferentActivityTypeFromAll(Integer userId);

    void addUserDefaultActivityType(Integer userId);

    /**
     * 用户搜藏活动
     *
     * @param params
     */
    int addActivityCollection(JSONPublicParam<ActivityCollectionRequestParam> params);

    /**
     * 取消搜藏
     *
     * @param params
     */
    int removeActivityCollection(JSONPublicParam<ActivityCollectionRequestParam> params);

    /**
     * 活动详情底部,趣聊,搜藏,是否参加
     *
     * @param authUserId
     * @param activityId
     */
    ActivityDetailBottomResponseDto activityDetailBottom(Integer authUserId, Integer activityId);

    void updateMyInterestActivityType(JSONPublicParam<AppSaveTypeDto> param);

    /**
     * 小程序用户参加活动
     *d
     * @param params
     */
    //Integer miniAddUserToActivity(JSONPublicParam<UserJoinActivityRequestDto> params);


    List<AppActivityUserLikeTypeResponseDto> getTopBarActivityTypeList(Integer authUserId);

    boolean getPageOfActivityType(Integer authUserId);

    PagesDto<AppCommunityLifeDto> getDiscoverLifeDynamicList(AppExploreDynamicRequestDto param);

    PagesDto<AppCommunityLifeDto> getDiscoverActivityDynamicList(AppActivityDynamicRequestDto param);

    /**
     * 新增
     *
     * @param userId
     * @param requestParam
     */
    void addDisLike(Integer userId, AppDislikeParams requestParam);

    AppDiscoveryPageDto<AppDiscoveryDto> getDiscoverPage(AppDiscoveryParams params);

    AppActivityUserLikeTypeResponseDto getActivityTypeByTypeId(Integer typeId);

    AppActivityImageResponseDto getImageInfoByLifeId(Integer userId, Integer lifeId);
}
