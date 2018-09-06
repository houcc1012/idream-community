package com.idream.service;

import com.idream.commons.lib.dto.information.*;

import java.util.List;

public interface InformationMarketService {
    List<InformationRuleDto> listInformationRulesByCouponId(Integer couponId);

    void saveCouponInformations(Integer userId, InformationCouponParams couponInformations);

    InformationRuleDetail getInformationRuleDetail();

    List<InformationUserRecordDto> getLastUserRecord(Integer userId);

    AppInformationRuleDetail getAppInformationRuleDetail();
}
