package com.idream.commons.lib.dto.app;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.idream.commons.lib.dto.PagesParam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "社区列表查询请求参数")
public class CommunityListSearchRequestDto extends PagesParam {

    @ApiModelProperty(value = "经度")
    @NotNull
    private BigDecimal longitude;

    @ApiModelProperty(value = "纬度")
    @NotNull
    private BigDecimal latitude;

    @ApiModelProperty(value = "城市编码")
    private String cityCode;

    @ApiModelProperty(value = "社区名")
    private String communityName;

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

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

}
