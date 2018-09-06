package com.idream.commons.lib.dto.appactivity;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("当前城市活动类型查询请求参数")
public class AppActivityTypeRequestDto {

    @ApiModelProperty(value = "经度")
    private BigDecimal longitude;
    @ApiModelProperty(value = "纬度")
    private BigDecimal latitude;
    @ApiModelProperty("当前城市编码")
    private String cityCode;

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

}
