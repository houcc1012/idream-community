package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author: ZhengGaosheng
 * @date: 2018/7/18 17:11
 * @description:
 */
@ApiModel("小程序发现页返回模型")
public class MiniActivityDiscoverypageResponseDto {

    @ApiModelProperty("活动id")
    private Integer activityId;

    @ApiModelProperty("活动标题")
    private String title;

    @ApiModelProperty("副标题")
    private String subtitle;

    @ApiModelProperty("活动封面")
    private String image;

    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date endTime;

    @ApiModelProperty("时间类型: 1一次,2多次,3周期")
    private Integer timeType;

    @ApiModelProperty("周期")
    private String weekDay;

    @ApiModelProperty("城市")
    private String city;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("当前活动状态 1未开始，2进行中，3已结束")
    private Integer activityStatus;

    @ApiModelProperty("是否参加活动")
    private Integer joined;

    @ApiModelProperty(value = "距离当前几天 单位:second")
    private Integer distanceCurrentDay;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
    }

    public Integer getJoined() {
        return joined;
    }

    public void setJoined(Integer joined) {
        this.joined = joined;
    }

    public Integer getDistanceCurrentDay() {
        return distanceCurrentDay;
    }

    public void setDistanceCurrentDay(Integer distanceCurrentDay) {
        this.distanceCurrentDay = distanceCurrentDay;
    }
}

