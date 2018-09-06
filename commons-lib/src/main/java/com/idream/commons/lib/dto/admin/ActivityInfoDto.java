package com.idream.commons.lib.dto.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: jeffery
 * @Date: 2018/6/25 17:03
 */
@ApiModel(value = "活动列表 返回参数")
public class ActivityInfoDto {

    @ApiModelProperty(value = "活动id")
    private Integer activityId;

    @ApiModelProperty(value = "活动主题")
    private String title;

    @ApiModelProperty(value = "活动副标题")
    private String subtitle;

    @ApiModelProperty(value = "活动图片")
    private String image;

    @ApiModelProperty(value = "活动内容")
    private String content;

    @ApiModelProperty(value = "活动开始时间")
    private Date startTime;

    @ApiModelProperty(value = "活动结束时间")
    private Date endTime;

    @ApiModelProperty(value = "距离当前几天 单位:second")
    private Integer distanceCurrentDay;

    @ApiModelProperty(value = "发布者id")
    private Integer userId;

    @ApiModelProperty(value = "活动状态 1:2:3 | 活动未开始:活动进行中:活动结束")
    private Integer activityStatus;

    @ApiModelProperty(value = "活动时间类型")
    private Integer timeType;

    @ApiModelProperty(value = "活动 周")
    private String weekDay;

    @ApiModelProperty(value = "活动 地址")
    private String address;

    @ApiModelProperty(value = "活动距离")
    private BigDecimal distance;

    @ApiModelProperty(value = "判断每个活动相对当前用户的状态 0:1 | 未参加:已参加")
    private Integer joinedStatus = 0;

    public Integer getDistanceCurrentDay() {
        return distanceCurrentDay;
    }

    public void setDistanceCurrentDay(Integer distanceCurrentDay) {
        this.distanceCurrentDay = distanceCurrentDay;
    }

    public Integer getJoinedStatus() {
        return joinedStatus;
    }

    public void setJoinedStatus(Integer joinedStatus) {
        this.joinedStatus = joinedStatus;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
    }

    public Integer getTimeType() {
        return timeType;
    }

    public void setTimeType(Integer timeType) {
        this.timeType = timeType;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }
}
