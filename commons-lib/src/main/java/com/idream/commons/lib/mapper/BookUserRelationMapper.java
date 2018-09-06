package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.BookUserRelation;

public interface BookUserRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BookUserRelation record);

    int insertSelective(BookUserRelation record);

    BookUserRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BookUserRelation record);

    int updateByPrimaryKey(BookUserRelation record);
}