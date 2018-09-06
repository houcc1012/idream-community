package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel("用户默认地址信息")
public class DefaultUserLocationDto {
    @ApiModelProperty("城市编码")
    private String cityCode;
    @ApiModelProperty("城市名")
    private String cityName;
    @ApiModelProperty("经度")
    private BigDecimal longitude;
    @ApiModelProperty("纬度")
    private BigDecimal latitude;

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }


    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getCityCode() {
        return cityCode;
    }


    public BigDecimal getLatitude() {
        return latitude;
    }
}
