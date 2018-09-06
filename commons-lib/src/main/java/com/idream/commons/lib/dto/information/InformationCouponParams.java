package com.idream.commons.lib.dto.information;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author charles
 */
@ApiModel("奖券录入信息")
public class InformationCouponParams {
    @ApiModelProperty("奖券id")
    private Integer couponId;
    @ApiModelProperty("录入信息")
    private List<InformationRuleParams> infos;

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public List<InformationRuleParams> getInfos() {
        return infos;
    }

    public void setInfos(List<InformationRuleParams> infos) {
        this.infos = infos;
    }
}
