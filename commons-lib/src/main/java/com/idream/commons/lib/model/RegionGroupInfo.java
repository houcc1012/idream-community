package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 社区成员表
 */
@ApiModel("社区成员表")
public class RegionGroupInfo {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 分类,1小区,2书屋,99未知
     */
    @ApiModelProperty(value = "分类,1小区,2书屋,99未知")
    private Byte category;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    private BigDecimal longitude;

    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度")
    private BigDecimal latitude;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;

    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String address;

    /**
     * 省
     */
    @ApiModelProperty(value = "省")
    private String province;

    /**
     * 省编码
     */
    @ApiModelProperty(value = "省编码")
    private String provinceCode;

    /**
     * 市
     */
    @ApiModelProperty(value = "市")
    private String city;

    /**
     * 市编码
     */
    @ApiModelProperty(value = "市编码")
    private String cityCode;

    /**
     * 地区
     */
    @ApiModelProperty(value = "地区")
    private String district;

    /**
     * 地区编码
     */
    @ApiModelProperty(value = "地区编码")
    private String districtCode;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private String status;

    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    private String icon;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getCategory() {
        return category;
    }

    public void setCategory(Byte category) {
        this.category = category;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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