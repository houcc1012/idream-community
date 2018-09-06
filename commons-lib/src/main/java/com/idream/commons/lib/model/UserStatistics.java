package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 用户统计表
 */
@ApiModel("用户统计表")
public class UserStatistics {
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
     * 粉丝数
     */
    @ApiModelProperty(value = "粉丝数")
    private Short fansCount;

    /**
     * 生活动态数
     */
    @ApiModelProperty(value = "生活动态数")
    private Short lifeCount;

    /**
     * 成就数
     */
    @ApiModelProperty(value = "成就数")
    private Short achievementCount;

    /**
     * 积分统计
     */
    @ApiModelProperty(value = "积分统计")
    private Short scoreCount;

    /**
     * 礼品券统计
     */
    @ApiModelProperty(value = "礼品券统计")
    private Short couponCount;

    /**
     * 累计签到统计
     */
    @ApiModelProperty(value = "累计签到统计")
    private Short signCount;

    /**
     * 累计参与活动统计
     */
    @ApiModelProperty(value = "累计参与活动统计")
    private Short joinActivityCount;

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

    public Short getFansCount() {
        return fansCount;
    }

    public void setFansCount(Short fansCount) {
        this.fansCount = fansCount;
    }

    public Short getLifeCount() {
        return lifeCount;
    }

    public void setLifeCount(Short lifeCount) {
        this.lifeCount = lifeCount;
    }

    public Short getAchievementCount() {
        return achievementCount;
    }

    public void setAchievementCount(Short achievementCount) {
        this.achievementCount = achievementCount;
    }

    public Short getScoreCount() {
        return scoreCount;
    }

    public void setScoreCount(Short scoreCount) {
        this.scoreCount = scoreCount;
    }

    public Short getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(Short couponCount) {
        this.couponCount = couponCount;
    }

    public Short getSignCount() {
        return signCount;
    }

    public void setSignCount(Short signCount) {
        this.signCount = signCount;
    }

    public Short getJoinActivityCount() {
        return joinActivityCount;
    }

    public void setJoinActivityCount(Short joinActivityCount) {
        this.joinActivityCount = joinActivityCount;
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