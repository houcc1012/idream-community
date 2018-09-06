package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.IntegrationInformationRelation;

import java.util.List;

public interface IntegrationInformationRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IntegrationInformationRelation record);

    int insertSelective(IntegrationInformationRelation record);

    IntegrationInformationRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IntegrationInformationRelation record);

    int updateByPrimaryKey(IntegrationInformationRelation record);

    List<Integer> selectByPoolId(Integer poolId);

    List<Integer> selectIdByPoolId(Integer poolId);

    int deleteByPoolId(Integer poolId);
}