package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.ActivityTaskRelation;

public interface ActivityTaskRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityTaskRelation record);

    int insertSelective(ActivityTaskRelation record);

    ActivityTaskRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityTaskRelation record);

    int updateByPrimaryKey(ActivityTaskRelation record);
}