package com.idream.service;

import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.activity.*;
import com.idream.commons.lib.dto.admin.ActivitySearchBookByExampleRequestDto;
import com.idream.commons.lib.dto.wangyi.CreateGroupRequestParam;
import com.idream.commons.lib.model.ActivityType;
import com.idream.commons.lib.model.InformationRule;
import com.idream.commons.lib.model.RegionGroupInfo;
import com.idream.commons.lib.model.RegionInfo;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author penghekai
 */
public interface ActivityAdminService {

    List<ActivityType> findPublishedActivityType();

    Integer addActivity(Integer createId, AdminActivityPublishDto aapd, Byte deviceType);

    Integer updateActivity(JSONPublicParam<AdminActivityPublishDto> aapd, Byte deviceType);

    PagesDto<FindThemeResponseDto> findActivityTheme(Integer activityId, Integer page, Integer rows);

    PagesDto<FindThemeMessageResponseDto> findThemeMessage(Integer themeId, Integer page, Integer rows);

    void addTheme(JSONPublicParam<AddThemeRequestDto> params);

    PagesDto<FindRegisterResponseDto> getRegistInfo(Integer activityId, Integer page, Integer rows);

    PagesDto<FindInvitationResponseDto> getInvitationInfo(Integer activityId, Integer page, Integer rows);

    void updateTaskScore(Integer taskScore);

    PagesDto<FindPublishedActivityDetailResponseDto> getPublishedActivityDetail(UserPublishActivityRequestDto param);

    AdminActivityDisplayDto getByActivityId(Integer activityId);

    /**
     * 条件查询活动统计信息
     *
     * @param query
     *
     * @return PagesDto<ActivityCountDto>
     */
    PagesDto<FindAllActivityResponseDto> getActivityCount(QueryActivityPage query);

    Integer updateActivityStatusPutWay(Integer activityId);

    /**
     * 返回添加主题的信息
     *
     * @param activityId
     *
     * @return PreCreateThemeDto
     */
    PreCreateThemeDto getPreCreateThemeDtoByActivityId(Integer activityId);

    /**
     * 查询所有的活动设计信息
     *
     * @param
     *
     * @return List<InformationRule>
     */
    List<InformationRule> listInformations();

    /**
     * 查询当前社区的管理者
     *
     * @param regionIds
     *
     * @return List<ActivityManagerDto>
     */
    List<ActivityManagerDto> listActivityManagerByRegionId(List<Integer> regionIds);

    /**
     * @param userId
     * @param page
     * @param rows
     */
    PagesDto<FindParticipateActivityDetailResponseDto> getParticipateActivityDetail(Integer userId, Integer page, Integer rows);

    /**
     * 查询主题的相关信息
     *
     * @param themeId
     *
     * @return FindThemeDetailResponseDto
     */
    FindThemeDetailResponseDto getByThemeId(Integer themeId);

    /**
     * 修改主题
     *
     * @param param void
     */
    void updateTheme(JSONPublicParam<FindThemeDetailResponseDto> param);

    /**
     * 返回打卡积分
     *
     * @return Integer
     */
    Integer getTaskScore();

    PagesDto<FindActivityMessageResponseDto> findActivityCommunityLife(ActivityAdminCommunityLifeRequestDto requestDto);

    Integer deleteActivity(Integer activityId);

    List<RegionInfo> selectRegionInfoList(AdminActivityCommunityListRequestDto param);

    List<RegionGroupInfo> selectRegionGroupByRegionId(Integer regionId);

    Integer checkActivityFailByActivityId(AdminActivityOperateRecordRequestDto param);

    PagesDto<ActivityListCheckResponseDto> getCheckActivityList(ActivityListCheckRequestDto param);

    void updateActivityStatusSubmit(Integer activityId);

    List<ActivityType> getActivityTypeList();

    List<AdminActivityStatisticsResponseDto> getActivityStatisticsByActivityId(Integer activityId);

    Integer checkActivityPassByActivityId(Integer activityId, Integer userId);

    Integer operateActivityCancel(AdminActivityOperateRecordRequestDto param);

    void addCreateGroupChat(Integer userId, CreateGroupRequestParam param);

    void updateGroupNameByGroupId(UpdateGroupNameRequestParam param);

    UpdateGroupNameRequestParam getGroupInfoByActivityId(Integer activityId);

    List<RegionGroupInfoExtention> getBookNameByExample(ActivitySearchBookByExampleRequestDto param);

    void updateCommunityLifeStatusShield(Integer lifeId);

    void updateCommunityLifeStatusCancelShield(Integer lifeId);
}
