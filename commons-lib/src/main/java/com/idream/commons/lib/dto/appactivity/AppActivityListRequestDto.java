package com.idream.commons.lib.dto.appactivity;

import java.math.BigDecimal;

import com.idream.commons.lib.dto.PagesParam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("根据活动类型id，经纬度，用户信息查询活动列表请求参数")
public class AppActivityListRequestDto extends PagesParam {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "活动类型id")
    private Integer typeId;
    @ApiModelProperty(value = "经度")
    private BigDecimal longitude;
    @ApiModelProperty(value = "纬度")
    private BigDecimal latitude;
    @ApiModelProperty("当前城市编码")
    private String cityCode;


    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
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
