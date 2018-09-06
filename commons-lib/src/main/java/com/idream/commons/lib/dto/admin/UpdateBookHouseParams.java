package com.idream.commons.lib.dto.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * @Author: jeffery
 * @Date: 2018/6/13 14:15
 */
@ApiModel(value = "编辑书屋请求参数(管理端)")
public class UpdateBookHouseParams {

    @ApiModelProperty(value = "书屋id")
    private Integer bookHouseId;

    @ApiModelProperty(value = "省")
    @NotBlank(message = "省不能为空...")
    private String province;

    @ApiModelProperty(value = "省编码")
    @NotBlank(message = "省不能为空...")
    private String provinceCode;

    @ApiModelProperty(value = "市")
    @NotBlank(message = "市不能为空...")
    private String city;

    @ApiModelProperty(value = "市编码")
    @NotBlank(message = "市不能为空...")
    private String cityCode;

    @ApiModelProperty(value = "区")
    @NotBlank(message = "书屋 区必须指定到区/县下")
    private String district;

    @ApiModelProperty(value = "区编码")
    @NotBlank(message = "书屋 区必须指定到区/县下")
    private String districtCode;

    @ApiModelProperty(value = "书屋名称")
    @NotBlank(message = "书屋名称不能为空...")
    @Length(max = 15, message = "书屋名称长度不得超过15个字符...")
    private String bookHouseName;

    @ApiModelProperty(value = "详细地址")
    @NotBlank(message = "书屋详细地址不能为空...")
    @Length(max = 50, message = "详细地址长度不得超过50个字符...")
    private String address;

    @ApiModelProperty(value = "经度")
    private BigDecimal longitude;

    @ApiModelProperty(value = "纬度")
    private BigDecimal latitude;

    @ApiModelProperty(value = "书屋介绍")
    @Length(max = 1000, message = "书屋介绍长度不得超过1000个字符...")
    private String description;

    @ApiModelProperty(value = "书屋icon")
    private String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getBookHouseId() {
        return bookHouseId;
    }

    public void setBookHouseId(Integer bookHouseId) {
        this.bookHouseId = bookHouseId;
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

    public String getBookHouseName() {
        return bookHouseName;
    }

    public void setBookHouseName(String bookHouseName) {
        this.bookHouseName = bookHouseName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
}
