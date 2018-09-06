package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.information.InformationRuleDto;
import com.idream.commons.lib.dto.marketing.CouponCollectionParams;
import com.idream.commons.lib.model.CouponCollection;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CouponCollectionMapper {
    int deleteByPrimaryKey(Integer couponId);

    int insert(CouponCollection record);

    int insertSelective(CouponCollection record);

    CouponCollection selectByPrimaryKey(Integer couponId);

    int updateByPrimaryKeySelective(CouponCollection record);

    int updateByPrimaryKey(CouponCollection record);

    List<CouponCollection> selectDetailByCouponId(Integer couponId);

    int updatePersonalInfo(CouponCollectionParams params);

    List<InformationRuleDto> selectInformationRule(@Param("couponId") Integer couponId);

    void updateCouponCollection(@Param("couponId") Integer couponId, @Param("infoId") Integer infoId, @Param("detail") String detail);
}