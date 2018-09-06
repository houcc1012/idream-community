package com.idream.commons.lib.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@ApiModel(value = "通过社区id和经纬度查询社区活动信息")
public class MyCommunityParams {

    @ApiModelProperty(value = "经度")
    @NotNull(message = "longitude不能为空")
    private BigDecimal longitude;

    @ApiModelProperty(value = "纬度")
    @NotNull(message = "latitude不能为空")
    private BigDecimal latitude;

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
