package com.idream.commons.lib.dto.activity;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @Author: houcc
 * @Date: 2018/6/6
 */
@ApiModel("查询活动报名请求参数")
public class AdminActivityTaskStatisticsParams extends PagesParam {
    @ApiModelProperty(value = " 报名客户端 0 -全部 1-ios ;2-andriod; 3-微信小程序；4-web 5 unkown")
    private int deviceType;
    @ApiModelProperty(value = "开始时间 格式为20151211")
    @NotNull(message = "开始时间不能为空")
    private String startTime;
    @ApiModelProperty(value = "结束时间 格式为20151211")
    @NotNull(message = "结束时间不能为空")
    private String endTime;
    @ApiModelProperty(value = "活动标题")
    private String activityTitle;
    @ApiModelProperty(value = "发布书屋")
    private String publishBook;

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getPublishBook() {
        return publishBook;
    }

    public void setPublishBook(String publishBook) {
        this.publishBook = publishBook;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
