package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.user.HelpTypeDto;
import com.idream.commons.lib.model.HelpType;

import java.util.List;

public interface HelpTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HelpType record);

    int insertSelective(HelpType record);

    HelpType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HelpType record);

    int updateByPrimaryKey(HelpType record);

    List<HelpTypeDto> selectAll();
}