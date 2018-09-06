package com.idream.commons.lib.dto.marketing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Description :
 * Created by xiaogang 2018/4/18.
 */
@ApiModel("当前位置参数表")
public class LocationParams {

    @ApiModelProperty(value = "经度")
    @NotNull
    private BigDecimal latitude;

    @ApiModelProperty(value = "纬度")
    @NotNull
    private BigDecimal longitude;

    @ApiModelProperty(value = "城市编码")
    private String cityCode;

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
}
