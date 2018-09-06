package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author: ZhengGaosheng
 * @date: 2018/7/13 15:13
 * @description:
 */
@ApiModel("社区查询活动")
public class ActivityByRegionAndCityCodeResonDto {

    @ApiModelProperty("活动id")
    private Integer activityId;
    @ApiModelProperty("活动标题")
    private String title;
    @ApiModelProperty("活动背景图片")
    private String image;
    @ApiModelProperty("开始时间")
    private Date startTime;
    @ApiModelProperty("结束时间")
    private Date endTime;

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
}

