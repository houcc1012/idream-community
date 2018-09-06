package com.idream.commons.lib.dto.appactivity;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class AppActivityInfoDto {

    @ApiModelProperty("活动标题")
    private String title;
    @ApiModelProperty("开始时间")
    private Date startTime;
    @ApiModelProperty("结束时间")
    private Date endTime;
    @ApiModelProperty("详细地址")
    private String address;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
