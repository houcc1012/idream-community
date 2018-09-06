package com.idream.commons.lib.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: jeffery
 * @Date: 2018/6/29 10:59
 */
@ApiModel(value = "app 大社区下的小区活动列表 返回dto")
public class RegionActivityDto {

    @ApiModelProperty(value = "活动id")
    private Integer activityId;

    @ApiModelProperty(value = "活动主题")
    private String title;

    @ApiModelProperty(value = "活动副标题")
    private String subTitle;

    @ApiModelProperty(value = "活动开始时间")
    private Date startTime;

    @ApiModelProperty(value = "活动结束时间")
    private Date endTime;

    @ApiModelProperty(value = "活动地址")
    private String address;

    @ApiModelProperty(value = "活动经度")
    private BigDecimal longitude;

    @ApiModelProperty(value = "活动纬度")
    private BigDecimal latitude;

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
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

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
