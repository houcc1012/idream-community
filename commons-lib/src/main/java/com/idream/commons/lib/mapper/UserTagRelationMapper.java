package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.user.AppUserSearchTagDto;
import com.idream.commons.lib.model.UserTagRelation;

import java.util.List;

public interface UserTagRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserTagRelation record);

    int insertSelective(UserTagRelation record);

    UserTagRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserTagRelation record);

    int updateByPrimaryKey(UserTagRelation record);
}