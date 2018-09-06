package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("用户信息")
public class UserInfo {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "姓名")
    private String realName;

    @ApiModelProperty(value = "1男,2女")
    private Byte gender;

    @ApiModelProperty(value = "生日")
    private Date birthday;

    @ApiModelProperty(value = "职业id")
    private Integer professionId;

    @ApiModelProperty(value = "职业名称,沉余字段")
    private String professionName;

    @ApiModelProperty(value = "头像")
    private String image;

    @ApiModelProperty(value = "用户角色名,1管理,2组织,3商户,4高级居民,5普通居民,7-普通用户,9游客")
    private Byte userRole;

    @ApiModelProperty(value = "用户类型 1-普通用户；2-官方用户")
    private Byte userType;

    @ApiModelProperty(value = "省份编码")
    private String provinceCode;

    @ApiModelProperty(value = "省份名称")
    private String provinceName;

    @ApiModelProperty(value = "城市编码")
    private String cityCode;

    @ApiModelProperty(value = "城市名称")
    private String cityName;

    @ApiModelProperty(value = "地区编码")
    private String districtCode;

    @ApiModelProperty(value = "地区名称")
    private String districtName;

    @ApiModelProperty(value = "身份证")
    private String identity;

    @ApiModelProperty(value = "用户是否修改过资料0-否；1-是")
    private Byte userInfoUpdate;

    @ApiModelProperty(value = "生成时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getProfessionId() {
        return professionId;
    }

    public void setProfessionId(Integer professionId) {
        this.professionId = professionId;
    }

    public String getProfessionName() {
        return professionName;
    }

    public void setProfessionName(String professionName) {
        this.professionName = professionName == null ? null : professionName.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Byte getUserRole() {
        return userRole;
    }

    public void setUserRole(Byte userRole) {
        this.userRole = userRole;
    }

    public Byte getUserType() {
        return userType;
    }

    public void setUserType(Byte userType) {
        this.userType = userType;
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

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity == null ? null : identity.trim();
    }

    public Byte getUserInfoUpdate() {
        return userInfoUpdate;
    }

    public void setUserInfoUpdate(Byte userInfoUpdate) {
        this.userInfoUpdate = userInfoUpdate;
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