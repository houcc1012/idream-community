package com.idream.commons.lib.dto.user;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description :
 * @Created by xiaogang on 2018/4/27.
 */
@ApiModel("获取IP的详细信息")
public class GetInfoByIpDto {

    @ApiModelProperty("国家")
    private String country;

    @ApiModelProperty("省名")
    private String region;

    @ApiModelProperty("城市")
    private String city;

    @ApiModelProperty("城市ID")
    @JSONField(name = "city_id")
    private String cityId;

    @ApiModelProperty("国家ID")
    @JSONField(name = "country_id")
    private String countryId;

    @ApiModelProperty("省ID")
    @JSONField(name = "region_id")
    private String regionId;


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }
}
