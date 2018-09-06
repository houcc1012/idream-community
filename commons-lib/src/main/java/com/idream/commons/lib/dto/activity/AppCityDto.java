package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("城市显示")
public class AppCityDto {
    @ApiModelProperty("城市编码")
    private String cityCode;
    @ApiModelProperty("城市名")
    private String cityName;

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getCityName() {
        return cityName;
    }
}
