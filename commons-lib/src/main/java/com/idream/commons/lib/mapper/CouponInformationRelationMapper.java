package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.CouponInformationRelation;

public interface CouponInformationRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CouponInformationRelation record);

    int insertSelective(CouponInformationRelation record);

    CouponInformationRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CouponInformationRelation record);

    int updateByPrimaryKey(CouponInformationRelation record);
}