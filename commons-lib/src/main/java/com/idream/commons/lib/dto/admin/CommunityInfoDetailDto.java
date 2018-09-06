package com.idream.commons.lib.dto.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: jeffery
 * @Date: 2018/7/4 14:33
 */
@ApiModel(value = "管理端 终端 小区明细")
public class CommunityInfoDetailDto {
    @ApiModelProperty(value = "小区id")
    private Integer communityId;

    @ApiModelProperty(value = "小区名称")
    private String communityName;

    @ApiModelProperty(value = "所在城市")
    private String city;

    @ApiModelProperty(value = "所在区域")
    private String district;

    @ApiModelProperty(value = "小区详细地址")
    private String address;

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
}
