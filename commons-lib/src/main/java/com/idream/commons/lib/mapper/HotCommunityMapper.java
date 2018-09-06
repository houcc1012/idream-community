package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.HotCommunity;

public interface HotCommunityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HotCommunity record);

    int insertSelective(HotCommunity record);

    HotCommunity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HotCommunity record);

    int updateByPrimaryKey(HotCommunity record);

}