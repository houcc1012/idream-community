package com.idream.commons.lib.model;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户收货地址
 */
@ApiModel("用户收货地址")
public class UserReceiveAddress {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Integer userId;

    /**
     * 收货人
     */
    @ApiModelProperty(value = "收货人")
    private String receiveName;

    /**
     * 收货手机
     */
    @ApiModelProperty(value = "收货手机")
    private String receivePhone;

    /**
     * 省编码
     */
    @ApiModelProperty(value = "省编码")
    private String provinceCode;

    /**
     * 省
     */
    @ApiModelProperty(value = "省")
    private String provinceName;

    /**
     * 市编码
     */
    @ApiModelProperty(value = "市编码")
    private String cityCode;

    /**
     * 市
     */
    @ApiModelProperty(value = "市")
    private String cityName;

    /**
     * 区编码
     */
    @ApiModelProperty(value = "区编码")
    private String districtCode;

    /**
     * 区
     */
    @ApiModelProperty(value = "区")
    private String districtName;

    /**
     * 社区id
     */
    @ApiModelProperty(value = "社区id")
    private Integer communityId;

    /**
     * 邮政编码
     */
    @ApiModelProperty(value = "邮政编码")
    private String postalCode;

    /**
     * 详细地址
     */
    @ApiModelProperty(value = "详细地址")
    private String address;

    /**
     * 默认
     */
    @ApiModelProperty(value = "默认")
    private Boolean isDefaulted;

    /**
     * 生成时间
     */
    @ApiModelProperty(value = "生成时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName == null ? null : receiveName.trim();
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone == null ? null : receivePhone.trim();
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode == null ? null : provinceCode.trim();
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode == null ? null : districtCode.trim();
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName == null ? null : districtName.trim();
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode == null ? null : postalCode.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Boolean getIsDefaulted() {
        return isDefaulted;
    }

    public void setIsDefaulted(Boolean isDefaulted) {
        this.isDefaulted = isDefaulted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}