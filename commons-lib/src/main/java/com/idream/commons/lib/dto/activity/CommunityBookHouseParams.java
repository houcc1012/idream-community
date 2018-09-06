package com.idream.commons.lib.dto.activity;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "新增社区和书屋请求参数")
public class CommunityBookHouseParams {
    @ApiModelProperty(value = "社区id")
    private Integer communityId;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "区")
    private String district;

    @ApiModelProperty(value = "城市信息adCode")
    private String adCode;

    @ApiModelProperty(value = "社区")
    private String communityName;

    @ApiModelProperty(value = "详细地址")
    private String communityAddress;

    @ApiModelProperty(value = "经度")
    private BigDecimal longitude;

    @ApiModelProperty(value = "纬度")
    private BigDecimal latitude;

    @ApiModelProperty(value = "社区描述")
    private String communityDescription;

    @ApiModelProperty(value = "书屋id")
    private Integer bookHouseId;

    @ApiModelProperty(value = "书屋")
    private String bookHouseName;

    @ApiModelProperty(value = "书屋详细地址")
    private String bookHouseAddress;

    @ApiModelProperty(value = "经度")
    private BigDecimal bookHouseLongitude;

    @ApiModelProperty(value = "纬度")
    private BigDecimal bookHouseLatitude;

    @ApiModelProperty(value = "书屋介绍")
    private String description;

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

    public String getAdCode() {
        return adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getCommunityAddress() {
        return communityAddress;
    }

    public void setCommunityAddress(String communityAddress) {
        this.communityAddress = communityAddress;
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

    public String getBookHouseName() {
        return bookHouseName;
    }

    public void setBookHouseName(String bookHouseName) {
        this.bookHouseName = bookHouseName;
    }

    public String getBookHouseAddress() {
        return bookHouseAddress;
    }

    public void setBookHouseAddress(String bookHouseAddress) {
        this.bookHouseAddress = bookHouseAddress;
    }

    public BigDecimal getBookHouseLongitude() {
        return bookHouseLongitude;
    }

    public void setBookHouseLongitude(BigDecimal bookHouseLongitude) {
        this.bookHouseLongitude = bookHouseLongitude;
    }

    public BigDecimal getBookHouseLatitude() {
        return bookHouseLatitude;
    }

    public void setBookHouseLatitude(BigDecimal bookHouseLatitude) {
        this.bookHouseLatitude = bookHouseLatitude;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCommunityDescription() {
        return communityDescription;
    }

    public void setCommunityDescription(String communityDescription) {
        this.communityDescription = communityDescription;
    }


}
