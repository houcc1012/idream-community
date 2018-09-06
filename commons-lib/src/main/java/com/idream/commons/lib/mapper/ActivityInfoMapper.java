package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.activity.*;
import com.idream.commons.lib.dto.activity.Manager.ActivityListRequestDto;
import com.idream.commons.lib.dto.app.*;
import com.idream.commons.lib.dto.app.CommunityActivityResponseDto;
import com.idream.commons.lib.dto.appactivity.*;
import com.idream.commons.lib.model.ActivityInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface ActivityInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityInfo record);

    int insertSelective(ActivityInfo record);

    ActivityInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityInfo record);

    int updateByPrimaryKey(ActivityInfo record);

    //通过社区id查询社区活动
    List<ActivityInfo> getActivityInfoByCommunityId(Integer communityId);

    ActivityInfo getActivityInfoByPrimary(Integer activityId);

    @Select("SELECT `at`.`name` FROM activity_type at LEFT JOIN activity_info ai ON `at`.id = ai.type_id WHERE ai.id = #{activityId}")
    String getActivityTypeNameByActivityId(@Param("activityId") Integer activityId);

    ActivityInfoDto getActivityInfoByPrimaryKey(Integer id);

    @Select("SELECT DISTINCT type_id FROM activity_info")
    List<Integer> getTypeId();

    /*
     * 查询所有的活动信息
     */
    List<ActivityInfo> getAllActivityInfos();

    @Select("SELECT ui.nick_name FROM activity_info ai INNER JOIN user_info ui ON ui.id = ai.user_id WHERE ai.id = #{activityId}")
    String getPublisherByActivityId(Integer id);

    List<FindPublishedActivityDetailResponseDto> getPublishedActivityDetailByUserId(@Param("userId") Integer userId);

    /**
     * 通过相关条件查询所有的活动
     *
     * @param query
     *
     * @return List<ActivityInfo>
     */
    List<FindAllActivityResponseDto> selectActivityByQuery(QueryActivityPage query);

    //查询用户参与的活动详细
    List<FindParticipateActivityDetailResponseDto> participateActivityDetailList(@Param("userId") Integer userId);

    AppActivityInfoResponseDto getActivityDetailByActivityId(@Param("userId") Integer userId, @Param("activityId") Integer activityId);

    Integer getActivityStatus(@Param("activityId") Integer activityId);

    //app(社区)查询社区活动
    List<CommunityActivityResponseDto> selectCommunityActivityList(@Param(value = "longitude") BigDecimal longitude, @Param(value = "latitude") BigDecimal latitude);

    //app(社区)通过communityId查询社区活动
    List<CommunityActivityResponseDto> selectCommunityActivityListByCommunityId(@Param(value = "communityId") Integer communityId);

    //App查询附近的活动
    List<AppActivityListDto> getNearbyActivityByTypeIdAndCityCode(@Param("userId") Integer userId, @Param("typeId") Integer typeId, @Param(value = "longitude") BigDecimal longitude, @Param(value = "latitude") BigDecimal latitude, @Param("cityCode") String cityCode);

    AppActivityInfoDto getByActivityId(@Param("activityId") Integer activityId);

    AppActivityImageResponseDto getImageInfoByActivityId(@Param("userId") Integer userId, @Param("activityId") Integer activityId);

    @Select("SELECT a.* FROM activity_info a LEFT JOIN activity_theme b ON a.id=b.activity_id WHERE b.id=#{themeId}")
    ActivityInfo getActivityTitleAndImageByThemeId(@Param("themeId") Integer themeId);

    //查询该社区中 活动数量
    @Select("SELECT count(*) FROM activity_info a INNER JOIN activity_group_relation b ON a.id=b.activity_id AND group_type = 40 INNER JOIN region_info c ON c.id = b.group_id WHERE c.id = #{regionId}")
    Integer getActivityCountByRegionId(@Param("regionId") Integer regionId);

    //查询该社区中 活动报名数量统计
    @Select("SELECT count(*) FROM user_activity_record a INNER JOIN activity_info b ON a.activity_id = b.id INNER JOIN activity_group_relation c ON c.activity_id = b.id AND group_type = 40 INNER JOIN region_info d ON d.id = c.group_id WHERE d.id = #{regionId}")
    Integer getCountActivityRecordByRegionId(@Param("regionId") Integer regionId);

    //查询该社区中 活动圈数量
    @Select("SELECT count(*) FROM community_life a INNER JOIN activity_info b ON b.id = a.activity_id INNER JOIN activity_group_relation c ON c.activity_id = b.id AND group_type = 40 INNER JOIN region_info d ON d.id = c.group_id WHERE a.from_type = 2 AND a.`status` < 3 AND d.id = #{regionId}")
    Integer getCountCommunityLifeByRegionId(@Param("regionId") Integer regionId);

    //查询该社区中 邻里圈数(动态圈)
    @Select("SELECT count(*) FROM community_life a INNER JOIN activity_info b ON b.id = a.activity_id INNER JOIN activity_group_relation c ON c.activity_id = b.id AND group_type = 40 INNER JOIN region_info d ON d.id = c.group_id WHERE a.from_type = 1 AND a.`status` < 3 AND d.id = #{regionId}")
    Integer getCountNeighborLifeByRegionId(@Param("regionId") Integer regionId);

    //查询该社区中 社区用户数
    @Select("SELECT count(*) FROM user_region_relation a INNER JOIN region_info b ON b.id = a.region_id WHERE b.id = #{regionId}")
    Integer getCountCommunityUser(@Param("regionId") Integer regionId);

    //app(社区)通过regionId查询社区活动
    List<CommunityActivityResponseDto> selectCommunityActivityListByRegionId(@Param(value = "regionId") Integer regionId);

    //根据社区id查询活动
    List<ActivityInfo> getActivityByRegionId(@Param("activityIds") List<Integer> activityIds);

    //通过大社区id 城市编码 查询活动列表
    List<RegionActivityDto> getActivityInfoByRegionId(RegionActivityParams regionActivityParams);

    List<ActivityListCheckResponseDto> selectActivityCheckListByQuery(ActivityListCheckRequestDto param);

    @Update("update activity_info SET status = #{status} WHERE id = #{activityId}")
    void updateActivityStatus(@Param("activityId") Integer activityId, @Param("status") Byte status);

    //管理者平台活动列表查询
    List<FindAllActivityResponseDto> selectManagerActivityListByQuery(ActivityListRequestDto param);

    //活动详情展示的 趣聊, 搜藏, 是否参加状态
    ActivityDetailBottomResponseDto activityDetailBottom(@Param("userId") Integer userId, @Param("activityId") Integer activityId);

    List<AppActivitySearchDto> selectActivityByTitleAndCityCode(@Param("userId") Integer userId, @Param("title") String title, @Param("cityCode") String cityCode);

    //查询是否加入了该群组
    Integer selectJoinedGroupStatus(@Param("userId") Integer userId, @Param("activityId") Integer activityId);

    //特色活动
    List<EspeciallyActivityResponseDto> especiallyActivity(@Param("ids") String ids, @Param("userId") Integer userId);

    //小程序根据活动类型查询活动
    List<MiniActivityDiscoverypageResponseDto> miniActivityByType(DiscoveryPageRequestParams params);

    //有定位的情况下查询活动列表
    List<MiniActivityDiscoverypageResponseDto> selectMiniDiscoverpageByLocation(@Param("cityCode") String cityCode, @Param("userId") int userId, @Param("longitude") String longitude, @Param("latitude") String latitude);

    //查询最近是哪个已经结束的时间
    List<MiniActivityDiscoverypageResponseDto> selectMiniDiscoverpageOverEndTime(@Param("cityCode") String cityCode, @Param("authUserId") int authUserId);

    Set<AppDiscoveryDto> selectDiscoverActivity(AppDiscoveryParams params);

    AdminActivityStatisticsResponseDto getActivityStatisticsByActivityId(@Param("activityId") Integer activityId);

    //小程序发现页展示活动
    List<MiniActivityDiscoverypageResponseDto> selectMiniDiscoverpage(DiscoveryPageRequestParams params);
}