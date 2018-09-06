package com.idream.commons.lib.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author charles
 */
@ApiModel("不喜欢参数")
public class AppDislikeParams {
    @ApiModelProperty("业务类型,1活动,2活动精彩,3生活动态,4不感兴趣的群")
    private Integer businessType;
    @ApiModelProperty("业务id")
    private Integer businessId;

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }
}
