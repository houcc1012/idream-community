package com.idream.commons.lib.dto.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Author: jeffery
 * @Date: 2018/6/26 13:50
 */
@ApiModel(value = "小程序发现页 活动为多次时 返回离当前时间最近的一次")
public class ActivityRecentOneDto {

    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty("距离开始的时间还有几天")
    private Integer distanceCurrentDay;

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

    public Integer getDistanceCurrentDay() {
        return distanceCurrentDay;
    }

    public void setDistanceCurrentDay(Integer distanceCurrentDay) {
        this.distanceCurrentDay = distanceCurrentDay;
    }
}
