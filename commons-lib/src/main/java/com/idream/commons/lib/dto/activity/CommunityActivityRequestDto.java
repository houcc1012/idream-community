package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("社区列表请求参数实体")
public class CommunityActivityRequestDto {

    @ApiModelProperty(value = "城市")
    private String city;
    @ApiModelProperty(value = "经度")
    private String longitude;
    @ApiModelProperty(value = "纬度")
    private String latitude;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }


}
