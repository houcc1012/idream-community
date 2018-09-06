package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by DELL2018-005 on 2018/5/14.
 */
@ApiModel(value = "热门城市返回dto")
public class HotCityResponseDto {

    @ApiModelProperty(value = "热门城市名")
    private String city;

    @ApiModelProperty(value = "热门城市编码")
    private String cityCode;

    @ApiModelProperty(value = "热门城市的数量")
    private Integer count;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
}
