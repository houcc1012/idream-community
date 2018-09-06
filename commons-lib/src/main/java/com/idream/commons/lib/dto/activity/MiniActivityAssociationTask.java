package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author: ZhengGaosheng
 * @date: 2018/7/3 13:53
 * @description:
 */
@ApiModel("活动社群打卡信息")
public class MiniActivityAssociationTask {

    @ApiModelProperty("距现在还有多少秒开始")
    private Integer distanceCurrentDay;

    @ApiModelProperty("用户是否打卡.  ture已经打卡,false:没打卡")
    private boolean userTask;

    @ApiModelProperty("打卡信息id")
    private Integer taskId;

    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date endTime;

    @ApiModelProperty("是否开启打卡")
    private Boolean enabled;

    public Integer getDistanceCurrentDay() {
        return distanceCurrentDay;
    }

    public void setDistanceCurrentDay(Integer distanceCurrentDay) {
        this.distanceCurrentDay = distanceCurrentDay;
    }

    public boolean isUserTask() {
        return userTask;
    }

    public void setUserTask(boolean userTask) {
        this.userTask = userTask;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}

