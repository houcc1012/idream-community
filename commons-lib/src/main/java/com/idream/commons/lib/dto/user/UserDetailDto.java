package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "前台用户详细资料")
public class UserDetailDto {

    @ApiModelProperty(value = "userId")
    private Integer userId;

    @ApiModelProperty(value = "userRole")
    private Integer userRole;


    @ApiModelProperty(value = "用户头像")
    private String image;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "生日")
    private Date birthday;

    @ApiModelProperty(value = "性别类型 : 1 : 男   2 : 女")
    private Integer gender;

    @ApiModelProperty(value = "职业名称")
    private String professionName;

    @ApiModelProperty(value = "身份证号码")
    private String identity;

    @ApiModelProperty(value = "注册时间")
    private Date registerTime;

    @ApiModelProperty(value = "最近访问时间")
    private Date lastLoginTime;

    @ApiModelProperty(value = "注册设备  1-ios ;2-andriod; 3-微信小程序；4-web")
    private Integer device;

    @ApiModelProperty(value = "IP")
    private String ip;

    @ApiModelProperty(value = "注册城市")
    private String city;

    @ApiModelProperty(value = "管理者是否已授权 1-是; 0-否")
    private Byte managerIsAuthorize;

    @ApiModelProperty(value = "是否实名认证 1-是; 0-否")
    private Byte isAuthenticate;

    @ApiModelProperty(value = "禁言Id,-1 代表非禁言")
    private Integer handleId;

    @ApiModelProperty(value = "积分余额")
    private Integer scoreBalance;

    @ApiModelProperty(value = "可用礼品劵张数")
    private Integer couponCount;

    @ApiModelProperty(value = "累计签到次数")
    private Integer signCount;

    @ApiModelProperty(value = "累计参与的活动次数")
    private Integer joinActivityCount;

    @ApiModelProperty(value = "粉丝数量")
    private Integer fansCount;

    @ApiModelProperty(value = "发布生活动态数量")
    private Integer communityLifeCount;

    @ApiModelProperty(value = "成就")
    private Integer achievementCount;


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getProfessionName() {
        return professionName;
    }

    public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getDevice() {
        return device;
    }

    public void setDevice(Integer device) {
        this.device = device;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Byte getManagerIsAuthorize() {
        return managerIsAuthorize;
    }

    public void setManagerIsAuthorize(Byte managerIsAuthorize) {
        this.managerIsAuthorize = managerIsAuthorize;
    }

    public Byte getIsAuthenticate() {
        return isAuthenticate;
    }

    public void setIsAuthenticate(Byte isAuthenticate) {
        this.isAuthenticate = isAuthenticate;
    }

    public Integer getHandleId() {
        return handleId;
    }

    public void setHandleId(Integer handleId) {
        this.handleId = handleId;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public Integer getScoreBalance() {
        return scoreBalance;
    }

    public void setScoreBalance(Integer scoreBalance) {
        this.scoreBalance = scoreBalance;
    }

    public Integer getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(Integer couponCount) {
        this.couponCount = couponCount;
    }

    public Integer getSignCount() {
        return signCount;
    }

    public void setSignCount(Integer signCount) {
        this.signCount = signCount;
    }

    public Integer getJoinActivityCount() {
        return joinActivityCount;
    }

    public void setJoinActivityCount(Integer joinActivityCount) {
        this.joinActivityCount = joinActivityCount;
    }

    public Integer getFansCount() {
        return fansCount;
    }

    public void setFansCount(Integer fansCount) {
        this.fansCount = fansCount;
    }

    public Integer getCommunityLifeCount() {
        return communityLifeCount;
    }

    public void setCommunityLifeCount(Integer communityLifeCount) {
        this.communityLifeCount = communityLifeCount;
    }

    public Integer getAchievementCount() {
        return achievementCount;
    }

    public void setAchievementCount(Integer achievementCount) {
        this.achievementCount = achievementCount;
    }
}
