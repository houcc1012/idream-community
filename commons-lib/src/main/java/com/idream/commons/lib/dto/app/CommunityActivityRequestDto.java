package com.idream.commons.lib.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@ApiModel(value = "社区活动列表请求参数")
public class CommunityActivityRequestDto {

    @ApiModelProperty(value = "经度")
    @NotNull(message = "longitude不能为空")
    private BigDecimal longitude;

    @ApiModelProperty(value = "纬度")
    @NotNull(message = "latitude不能为空")
    private BigDecimal latitude;

    @ApiModelProperty(value = "城市编码")
    @NotBlank(message = "城市编码不为空")
    private String cityCode;

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

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


}
