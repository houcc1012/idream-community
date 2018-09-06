package com.idream.commons.lib.dto.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author: jeffery
 * @Date: 2018/6/13 11:28
 */
@ApiModel(value = "书屋列表返回参数(管理端)")
public class BookHouseListDto {

    @ApiModelProperty(value = "书屋id")
    private Integer bookHouseId;

    @ApiModelProperty(value = "书屋名称")
    private String bookHouseName;

    @ApiModelProperty(value = "所在省")
    private String province;

    @ApiModelProperty(value = "所在省编码")
    private String provinceCode;

    @ApiModelProperty(value = "所在城市")
    private String city;

    @ApiModelProperty(value = "所在城市code")
    private String cityCode;

    @ApiModelProperty(value = "所在区域")
    private String district;

    @ApiModelProperty(value = "所在区域code")
    private String districtCode;

    @ApiModelProperty(value = "书屋管理者")
    private List<String> realName;

    @ApiModelProperty(value = "书屋详细地址")
    private String address;

    @ApiModelProperty(value = "高级社区信息")
    private List<RegionInfoDto> regionInfoDtos;

    @ApiModelProperty(value = "书屋码")
    private String bookHouseCode;

    @ApiModelProperty(value = "书屋小程序编码")
    private String qrCode;

    @ApiModelProperty(value = "累计发布活动")
    private Integer activityTotal;

    @ApiModelProperty(value = "抽奖奖品设置")
    private Integer integrationTotal;

    @ApiModelProperty(value = "兑换奖品设置")
    private Integer awardTotal;

    public Integer getActivityTotal() {
        return activityTotal;
    }

    public void setActivityTotal(Integer activityTotal) {
        this.activityTotal = activityTotal;
    }

    public Integer getIntegrationTotal() {
        return integrationTotal;
    }

    public void setIntegrationTotal(Integer integrationTotal) {
        this.integrationTotal = integrationTotal;
    }

    public Integer getAwardTotal() {
        return awardTotal;
    }

    public void setAwardTotal(Integer awardTotal) {
        this.awardTotal = awardTotal;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
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

    public List<String> getRealName() {
        return realName;
    }

    public void setRealName(List<String> realName) {
        this.realName = realName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<RegionInfoDto> getRegionInfoDtos() {
        return regionInfoDtos;
    }

    public void setRegionInfoDtos(List<RegionInfoDto> regionInfoDtos) {
        this.regionInfoDtos = regionInfoDtos;
    }

    public String getBookHouseCode() {
        return bookHouseCode;
    }

    public void setBookHouseCode(String bookHouseCode) {
        this.bookHouseCode = bookHouseCode;
    }
}
