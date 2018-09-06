package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author: ZhengGaosheng
 * @date: 2018/7/18 15:09
 * @description:
 */
@ApiModel("特色活动返回模型")
public class EspeciallyActivityResponseDto {

    @ApiModelProperty("活动id")
    private Integer activityId;
    @ApiModelProperty("活动图片")
    private String image;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("副标题")
    private String subtitle;
    @ApiModelProperty("活动地址")
    private String address;
    @ApiModelProperty("开始时间")
    private Date startTime;
    @ApiModelProperty("结束时间")
    private Date endTime;

    @ApiModelProperty("时间类型")
    private Integer timeType;

    @ApiModelProperty("timeType为3时, 1表示周一")
    private String weekDay;

    @ApiModelProperty("是否参加活动. 0:未参加,1参加")
    private Integer joined;

    @ApiModelProperty("距离开始的时间还有几天")
    private Integer distanceCurrentDay;

    @ApiModelProperty("活动状态")
    private Integer activityStatus;

    public Integer getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
    }

    public Integer getDistanceCurrentDay() {
        return distanceCurrentDay;
    }

    public void setDistanceCurrentDay(Integer distanceCurrentDay) {
        this.distanceCurrentDay = distanceCurrentDay;
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

    public Integer getJoined() {
        return joined;
    }

    public void setJoined(Integer joined) {
        this.joined = joined;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
}

