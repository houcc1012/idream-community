package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 区域信息表
 */
@ApiModel("区域信息表")
public class CommunityRegionInfo {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 区域名
     */
    @ApiModelProperty(value = "区域名")
    private String regionName;

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
     * 社区状态
     */
    @ApiModelProperty(value = "社区状态")
    private Byte status;

    /**
     * 保留字段,社区级别
     */
    @ApiModelProperty(value = "保留字段,社区级别")
    private Byte rangeLevel;

    /**
     * 大区图标
     */
    @ApiModelProperty(value = "大区图标")
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

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName == null ? null : regionName.trim();
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
        this.description = description == null ? null : description.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode == null ? null : provinceCode.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode == null ? null : districtCode.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getRangeLevel() {
        return rangeLevel;
    }

    public void setRangeLevel(Byte rangeLevel) {
        this.rangeLevel = rangeLevel;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
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