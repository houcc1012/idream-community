package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.ActivityCommunityRelation;

public interface ActivityCommunityRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityCommunityRelation record);

    int insertSelective(ActivityCommunityRelation record);

    ActivityCommunityRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityCommunityRelation record);

    int updateByPrimaryKey(ActivityCommunityRelation record);

    ActivityCommunityRelation getActivityCommunityRelationByActivityId(Integer activityId);

    String getCommunityNameByActivityId(Integer activityId);

    String getActivityPlaceByActivityId(Integer activityId);

    void deleteByActivityId(Integer activityId);
}