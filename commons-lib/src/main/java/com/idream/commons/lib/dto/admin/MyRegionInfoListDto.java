package com.idream.commons.lib.dto.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: jeffery
 * @Date: 2018/7/4 14:11
 */
@ApiModel(value = "管理端 我的社区信息返回参数")
public class MyRegionInfoListDto {
    @ApiModelProperty(value = "社区id")
    private Integer regionId;

    @ApiModelProperty(value = "社区名")
    private String regionName;

    @ApiModelProperty(value = "社区icon")
    private String icon;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "区")
    private String district;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "社区小区数量")
    private Integer communityCount;

    @ApiModelProperty(value = "社区用户数量")
    private Integer regionUserCount;

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCommunityCount() {
        return communityCount;
    }

    public void setCommunityCount(Integer communityCount) {
        this.communityCount = communityCount;
    }

    public Integer getRegionUserCount() {
        return regionUserCount;
    }

    public void setRegionUserCount(Integer regionUserCount) {
        this.regionUserCount = regionUserCount;
    }

}
