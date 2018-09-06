package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author hejiang
 */
@ApiModel("查询管理者")
public class UserManagerDto {

    @ApiModelProperty(value = "用户编号", required = true)
    private Integer userId;

    @ApiModelProperty(value = "管理者是否授权 1-是;0-否", required = true)
    private Byte authStatus;

    @ApiModelProperty(value = "电话", required = true)
    private String phone;

    @ApiModelProperty(value = "昵称", required = true)
    private String nickName;

    @ApiModelProperty(value = "真实姓名", required = true)
    private String realName;

    @ApiModelProperty(value = "身份证号", required = true)
    private String identity;

    @ApiModelProperty(value = "省份编码", required = true)
    private String provinceCode;

    @ApiModelProperty(value = "省份名称", required = true)
    private String province;

    @ApiModelProperty(value = "城市名称", required = true)
    private String city;

    @ApiModelProperty(value = "城市编码", required = true)
    private String cityCode;

    @ApiModelProperty(value = "地区名称", required = true)
    private String district;

    @ApiModelProperty(value = "地区编码", required = true)
    private String districtCode;

    @ApiModelProperty(value = "社区名称", required = true)
    private String communityName;

    @ApiModelProperty(value = "社区ID", required = true)
    private int communityId;

    @ApiModelProperty(value = "书屋名称", required = true)
    private String bookHouseName;

    @ApiModelProperty(value = "书屋ID", required = true)
    private int bookHouseId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Byte getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(Byte authStatus) {
        this.authStatus = authStatus;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
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

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public int getCommunityId() {
        return communityId;
    }

    public void setCommunityId(int communityId) {
        this.communityId = communityId;
    }

    public String getBookHouseName() {
        return bookHouseName;
    }

    public void setBookHouseName(String bookHouseName) {
        this.bookHouseName = bookHouseName;
    }

    public int getBookHouseId() {
        return bookHouseId;
    }

    public void setBookHouseId(int bookHouseId) {
        this.bookHouseId = bookHouseId;
    }
}
