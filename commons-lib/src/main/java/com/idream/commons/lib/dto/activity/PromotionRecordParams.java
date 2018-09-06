package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: jeffery
 * @Date: 2018/8/6 16:22
 */
@ApiModel(value = "小程序推广 请求参数")
public class PromotionRecordParams {
    @ApiModelProperty(value = "书屋id 或者 地推团队id")
    private Integer businessId;

    @ApiModelProperty(value = "1书屋,2地推团队")
    private Byte businessType;

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Byte getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Byte businessType) {
        this.businessType = businessType;
    }
}
