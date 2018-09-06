package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@ApiModel("书屋认证参数")
public class AppAuthBookHouseParams {
    @ApiModelProperty(value = "书屋编码")
    @NotBlank
    private String code;
    @ApiModelProperty(value = "经度")
    @NotNull
    private BigDecimal longitude;

    @ApiModelProperty(value = "纬度")
    @NotNull
    private BigDecimal latitude;

    public String getCode() {
        return code;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }
}
