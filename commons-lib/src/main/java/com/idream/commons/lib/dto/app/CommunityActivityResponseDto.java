package com.idream.commons.lib.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel(value = "社区活动返回参数")
public class CommunityActivityResponseDto {

    @ApiModelProperty(value = "活动id")
    private Integer activityId;

    @ApiModelProperty(value = "活动标题")
    private String title;

    @ApiModelProperty(value = "活动副标题")
    private String subTitle;

    @ApiModelProperty(value = "活动状态  默认为1 | 进行中")
    private Integer activityStatus = 1;

    @ApiModelProperty(value = "活动类型 1:2:3:4:5 | 官方活动:书屋活动:第三方活动:团队活动:个人活动")
    private Integer type;

    @ApiModelProperty(value = "活动时间类型")
    private Integer timeType;

    @ApiModelProperty(value = "周期性活动 周几")
    private String weekDay;

    @ApiModelProperty(value = "活动地址")
    private String address;

    @ApiModelProperty(value = "活动经度")
    private BigDecimal activityLongitude;

    @ApiModelProperty(value = "活动纬度")
    private BigDecimal activityLatitude;

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

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Integer getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public BigDecimal getActivityLongitude() {
        return activityLongitude;
    }

    public void setActivityLongitude(BigDecimal activityLongitude) {
        this.activityLongitude = activityLongitude;
    }

    public BigDecimal getActivityLatitude() {
        return activityLatitude;
    }

    public void setActivityLatitude(BigDecimal activityLatitude) {
        this.activityLatitude = activityLatitude;
    }
}
