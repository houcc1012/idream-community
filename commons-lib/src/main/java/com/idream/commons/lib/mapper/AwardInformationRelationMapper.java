package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.AwardInformationRelation;

import java.util.List;

public interface AwardInformationRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AwardInformationRelation record);

    int insertSelective(AwardInformationRelation record);

    AwardInformationRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AwardInformationRelation record);

    int updateByPrimaryKey(AwardInformationRelation record);

    List<Integer> selectByPoolId(Integer poolId);

    int deleteByPoolId(Integer poolId);
}