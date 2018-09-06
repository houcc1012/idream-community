package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 开通城市返回列表
 */
@ApiModel("开通城市返回列表")
public class AdminOpenCityDto {

    @ApiModelProperty(value = "城市编码")
    private String cityCode;

    @ApiModelProperty(value = "城市名")
    private String cityName;

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }
}