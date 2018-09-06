package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.CommunityUnrecord;

public interface CommunityUnrecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommunityUnrecord record);

    int insertSelective(CommunityUnrecord record);

    CommunityUnrecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommunityUnrecord record);

    int updateByPrimaryKey(CommunityUnrecord record);
}