package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel("开通城市参数列表")
public class AdminOpenCityParams {

    @ApiModelProperty(value = "城市编码")
    @NotBlank(message = "城市编码不能为空")
    private String cityCode;

    @ApiModelProperty(value = "城市名")
    @NotBlank(message = "城市名不能为空")
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