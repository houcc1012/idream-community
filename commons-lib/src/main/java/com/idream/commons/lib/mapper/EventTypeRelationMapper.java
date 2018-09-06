package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.EventTypeRelation;

public interface EventTypeRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EventTypeRelation record);

    int insertSelective(EventTypeRelation record);

    EventTypeRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EventTypeRelation record);

    int updateByPrimaryKey(EventTypeRelation record);
}