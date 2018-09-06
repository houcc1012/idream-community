package com.idream.commons.lib.dto.marketing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author houcc
 */
@ApiModel("获取抽奖奖池请求参数")
public class RegionPollParams {
    @ApiModelProperty(value = "用户ID", hidden = true)
    private Integer authUserId;

    @NotNull(message = "longitude不能为空")
    private BigDecimal longitude;

    @NotNull(message = "latitude不能为空")
    private BigDecimal latitude;

    @ApiModelProperty(value = "cityCode")
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

    public Integer getAuthUserId() {
        return authUserId;
    }

    public void setAuthUserId(Integer authUserId) {
        this.authUserId = authUserId;
    }
}
