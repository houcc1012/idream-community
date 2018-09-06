package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.activity.*;
import com.idream.commons.lib.dto.appactivity.AppExploreDynamicRequestDto;
import com.idream.commons.lib.dto.app.AppDiscoveryDto;
import com.idream.commons.lib.dto.app.AppDiscoveryParams;
import com.idream.commons.lib.model.CommunityLife;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface CommunityLifeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommunityLife record);

    int insertSelective(CommunityLife record);

    CommunityLife selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommunityLife record);

    int updateByPrimaryKey(CommunityLife record);

    List<AppMyCommunityLifeByTimeDto> getMyDynamicList(AppNeighborRequestParams params);

    Integer getMyDynamicListCount(AppNeighborRequestParams params);

    List<AppCommunityLifeDto> getMyNeighborDynamicList(AppNeighborRequestParams params);

    AppCommunityLifeDetailDto selectById(@Param("id") Integer id, @Param("authUserId") Integer authUserId);

    List<CommunityLife> selectPublishLifeByUserId(Integer userId);

    int getWetherAuthenticated(Integer userId);

    List<AdminMyDynamicDto> getMyDynamicByUserId(AppNeighborRequestParams params);

    Integer getMyDynamicByUserIdCount(AppNeighborRequestParams params);

    List<AdminMyNeighborDynamicDto> getAllNeighborDynamicList(AdminMyNeighborDynamicParam param);

//    Integer  getAllDynamicListCount(AdminMyNeighborDynamicParam param);

    int updateStatus(CommunityLife life);

    @Select("SELECT * FROM community_life WHERE activity_id = #{activityId}")
    CommunityLife getByActivityId(@Param("activityId") Integer activityId);

    @Select("SELECT count(*) from community_life WHERE activity_id = #{activityId} AND from_type = 2")
    Integer getActivityMessageCountByActivityId(@Param("activityId") Integer activityId);

    /**
     * 活动留言数
     */
    List<AdminActivityStatisticsDto> selectCommentsByDate(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    Integer selectTotalComments();

    List<FindActivityMessageResponseDto> getCommunityLife(ActivityAdminCommunityLifeRequestDto requestDto);

    //发现页生活动态列表
    List<AppCommunityLifeDto> selectDiscoverLifeDynamicListByTypeId(AppExploreDynamicRequestDto param);

    //发现页活动动态列表
    List<AppCommunityLifeDto> selectActivityDynamicListByActivityId(AppActivityDynamicRequestDto param);

    Set<AppDiscoveryDto> selectDiscoverLife(AppDiscoveryParams params);

    Set<AppDiscoveryDto> selectDiscoverActivityLife(AppDiscoveryParams params);

    @Select("SELECT activity_id FROM community_life WHERE id = #{lifeId}")
    Integer selectActivityIdByLifeId(@Param("lifeId") Integer lifeId);


}