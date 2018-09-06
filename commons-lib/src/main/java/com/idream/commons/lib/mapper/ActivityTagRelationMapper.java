package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.activity.UserActivityTag;
import com.idream.commons.lib.model.ActivityTagRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityTagRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityTagRelation record);

    int insertSelective(ActivityTagRelation record);

    ActivityTagRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityTagRelation record);

    int updateByPrimaryKey(ActivityTagRelation record);

    void addActivityTagRelation(ActivityTagRelation bean);

    void deleteActivityTagRelationByActivityId(Integer activityId);

    List<Integer> listActivityTagIds(Integer activityId);

    List<UserActivityTag> selectUserActivityTag(@Param("userId") Integer userId,@Param("activityId") Integer activityId );
}