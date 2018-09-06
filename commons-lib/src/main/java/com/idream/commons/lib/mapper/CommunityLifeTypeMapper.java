package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.activity.AppLifeTypeDto;
import com.idream.commons.lib.model.CommunityLifeType;

import java.util.List;

public interface CommunityLifeTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommunityLifeType record);

    int insertSelective(CommunityLifeType record);

    CommunityLifeType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommunityLifeType record);

    int updateByPrimaryKey(CommunityLifeType record);

    List<AppLifeTypeDto> selectAll();
}