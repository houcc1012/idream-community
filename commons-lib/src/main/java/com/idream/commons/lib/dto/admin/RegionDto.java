package com.idream.commons.lib.dto.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: jeffery
 * @Date: 2018/6/14 14:52
 */
@ApiModel(value = "高级社区 参数dto")
public class RegionDto {

    @ApiModelProperty(value = "高级社区id")
    private Integer regionId;

    @ApiModelProperty(value = "高级社区名")
    private String regionName;

    @ApiModelProperty(value = "社区icon")
    private String icon;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "区")
    private String district;

    @ApiModelProperty(value = "关联小区")
    private Integer connectCommunityCount;

    @ApiModelProperty(value = "关联书屋")
    private Integer connectBookHouseCount;

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

    public Integer getConnectCommunityCount() {
        return connectCommunityCount;
    }

    public void setConnectCommunityCount(Integer connectCommunityCount) {
        this.connectCommunityCount = connectCommunityCount;
    }

    public Integer getConnectBookHouseCount() {
        return connectBookHouseCount;
    }

    public void setConnectBookHouseCount(Integer connectBookHouseCount) {
        this.connectBookHouseCount = connectBookHouseCount;
    }
}
