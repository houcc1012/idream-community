package com.idream.commons.lib.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: jeffery
 * @Date: 2018/6/29 10:55
 */
@ApiModel(value = "app 大社区下的所有小区活动请求参数")
public class RegionActivityParams {

    @ApiModelProperty(value = "城市编码")
    private String cityCode;

    @ApiModelProperty(value = "大社区id")
    private Integer regionId;

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }
}
