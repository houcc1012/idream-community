package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 开通城市返回列表
 */
@ApiModel("小程序开通城市返回列表")
public class MiniProgramOpenCityDto {

    @ApiModelProperty(value = "城市编码")
    private String cityCode;

    @ApiModelProperty(value = "城市名")
    private String cityName;

    @ApiModelProperty(value = "是否绑定活动，true是false否")
    private boolean bandActivity;

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

    public boolean isBandActivity() {
        return bandActivity;
    }

    public void setBandActivity(boolean bandActivity) {
        this.bandActivity = bandActivity;
    }
}