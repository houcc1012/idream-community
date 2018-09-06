package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 管理端 社区书屋返回参数
 *
 * @param
 *
 * @author zhanfeifei
 */
@ApiModel(value = "社区书屋参数")
public class CommunityBookHouseDto {

    @ApiModelProperty(value = "社区id")
    private Integer communityId;

    @ApiModelProperty(value = "社区名称")
    private String communityName;

    @ApiModelProperty(value = "所在省份")
    private String province;

    @ApiModelProperty(value = "所在城市")
    private String city;

    @ApiModelProperty(value = "所在区域")
    private String district;

    @ApiModelProperty(value = "区域编码")
    private String adCode;

    @ApiModelProperty(value = "社区详细地址")
    private String communityAddress;

    @ApiModelProperty(value = "社区介绍")
    private String communityDescription;

    @ApiModelProperty(value = "书屋id")
    private Integer bookHouseId;

    @ApiModelProperty(value = "书屋编码")
    private Integer bookHouseCode;

    @ApiModelProperty(value = "书屋名称")
    private String bookHouseName;

    @ApiModelProperty(value = "书屋管理者")
    private List<String> realName;

    @ApiModelProperty(value = "书屋详细地址")
    private String bookHouseAddress;

    @ApiModelProperty(value = "经度")
    private BigDecimal longitude;

    @ApiModelProperty(value = "纬度")
    private BigDecimal latitude;

    @ApiModelProperty(value = "经度")
    private BigDecimal bookHouseLongitude;

    @ApiModelProperty(value = "纬度")
    private BigDecimal bookHouseLatitude;

    @ApiModelProperty(value = "书屋描述")
    private String description;

    @ApiModelProperty(value = "书屋创建时间")
    private Date bookHouseCreateTime;

    @ApiModelProperty(value = "社区创建时间")
    private Date communityCreateTime;

    public Integer getBookHouseCode() {
        return bookHouseCode;
    }

    public void setBookHouseCode(Integer bookHouseCode) {
        this.bookHouseCode = bookHouseCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getCommunityAddress() {
        return communityAddress;
    }

    public void setCommunityAddress(String communityAddress) {
        this.communityAddress = communityAddress;
    }

    public Integer getBookHouseId() {
        return bookHouseId;
    }

    public void setBookHouseId(Integer bookHouseId) {
        this.bookHouseId = bookHouseId;
    }

    public String getBookHouseName() {
        return bookHouseName;
    }

    public void setBookHouseName(String bookHouseName) {
        this.bookHouseName = bookHouseName;
    }

    public List<String> getRealName() {
        return realName;
    }

    public void setRealName(List<String> realName) {
        this.realName = realName;
    }

    public String getBookHouseAddress() {
        return bookHouseAddress;
    }

    public void setBookHouseAddress(String bookHouseAddress) {
        this.bookHouseAddress = bookHouseAddress;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getAdCode() {
        return adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }

    public String getCommunityDescription() {
        return communityDescription;
    }

    public void setCommunityDescription(String communityDescription) {
        this.communityDescription = communityDescription;
    }

    public Date getBookHouseCreateTime() {
        return bookHouseCreateTime;
    }

    public void setBookHouseCreateTime(Date bookHouseCreateTime) {
        this.bookHouseCreateTime = bookHouseCreateTime;
    }

    public Date getCommunityCreateTime() {
        return communityCreateTime;
    }

    public void setCommunityCreateTime(Date communityCreateTime) {
        this.communityCreateTime = communityCreateTime;
    }
}
