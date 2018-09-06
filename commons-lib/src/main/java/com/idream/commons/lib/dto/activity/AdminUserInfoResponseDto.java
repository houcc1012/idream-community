package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "(后台管理)用户明细返回dto")
public class AdminUserInfoResponseDto {

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "用户角色名,1管理,2组织,3商户,4高级居民,5普通居民,7-普通用户,9游客")
    private Integer userRole;

    @ApiModelProperty(value = "所在小区")
    private List<String> communityName;

    @ApiModelProperty(value = "粉丝数")
    private Integer fanCount;

    @ApiModelProperty(value = "生活")
    private Integer lifeCount;

    @ApiModelProperty(value = "积分")
    private Integer score;

    @ApiModelProperty(value = "礼品券")
    private Integer couponCount;

    @ApiModelProperty(value = "累计签到")
    private Integer signCount;

    @ApiModelProperty(value = "累计参与次数")
    private Integer joinCount;

    public List<String> getCommunityName() {
        return communityName;
    }

    public void setCommunityName(List<String> communityName) {
        this.communityName = communityName;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Integer getFanCount() {
        return fanCount;
    }

    public void setFanCount(Integer fanCount) {
        this.fanCount = fanCount;
    }

    public Integer getLifeCount() {
        return lifeCount;
    }

    public void setLifeCount(Integer lifeCount) {
        this.lifeCount = lifeCount;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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

    public Integer getJoinCount() {
        return joinCount;
    }

    public void setJoinCount(Integer joinCount) {
        this.joinCount = joinCount;
    }

}
