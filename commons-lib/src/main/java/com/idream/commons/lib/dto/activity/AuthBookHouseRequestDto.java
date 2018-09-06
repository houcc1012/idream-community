package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "认证书屋请求参数")
public class AuthBookHouseRequestDto {

    @ApiModelProperty(value = "社区id")
    private Integer communityId;

    @ApiModelProperty(value = "书屋id")
    private Integer bookHouseId;

    @ApiModelProperty(value = "社区名")
    private String communityName;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "区")
    private String district;

    @ApiModelProperty(value = "地区编码")
    private String adCode;

    @ApiModelProperty(value = "社区详细地址")
    private String communityAddress;

    @ApiModelProperty(value = "书屋详细地址")
    private String bookHouseAddress;

    @ApiModelProperty(value = "书屋名称")
    private String bookHouseName;

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public Integer getBookHouseId() {
        return bookHouseId;
    }

    public void setBookHouseId(Integer bookHouseId) {
        this.bookHouseId = bookHouseId;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
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

    public String getAdCode() {
        return adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }

    public String getCommunityAddress() {
        return communityAddress;
    }

    public void setCommunityAddress(String communityAddress) {
        this.communityAddress = communityAddress;
    }

    public String getBookHouseAddress() {
        return bookHouseAddress;
    }

    public void setBookHouseAddress(String bookHouseAddress) {
        this.bookHouseAddress = bookHouseAddress;
    }

    public String getBookHouseName() {
        return bookHouseName;
    }

    public void setBookHouseName(String bookHouseName) {
        this.bookHouseName = bookHouseName;
    }


}
