package com.idream.commons.lib.dto.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Author: jeffery
 * @Date: 2018/6/13 9:33
 */
@ApiModel(value = "小区列表返回参数(管理端)")
public class SmallCommunityListDto {

    @ApiModelProperty(value = "小区id")
    private Integer communityId;

    @ApiModelProperty(value = "小区名称")
    private String communityName;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "省code")
    private String provinceCode;

    @ApiModelProperty(value = "所在城市")
    private String city;

    @ApiModelProperty(value = "所在城市code")
    private String cityCode;

    @ApiModelProperty(value = "所在区域")
    private String district;

    @ApiModelProperty(value = "所在区域code")
    private String districtCode;

    @ApiModelProperty(value = "小区详细地址")
    private String address;

    @ApiModelProperty(value = "高级社区id")
    private Integer regionId;

    @ApiModelProperty(value = "高级社区名")
    private String regionName;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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
}
