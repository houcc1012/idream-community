package com.idream.commons.lib.dto.marketing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 社区信息表
 */
@ApiModel("社区信息表")
public class CommunityDto {
    @ApiModelProperty(value = "省ID")
    private String provinceId;

    @ApiModelProperty(value = "省名")
    private String provinceName;

    @ApiModelProperty(value = "市ID")
    private String cityId;

    @ApiModelProperty(value = "市名")
    private String cityName;

    @ApiModelProperty(value = "区ID")
    private String regionId;

    @ApiModelProperty(value = "区名")
    private String regionName;

    @ApiModelProperty(value = "社区ID")
    private Integer communityId;

    @ApiModelProperty(value = "社区名称")
    private String communityName;

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }
}