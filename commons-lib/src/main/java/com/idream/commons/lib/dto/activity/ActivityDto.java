package com.idream.commons.lib.dto.activity;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>Title: ParticipateActivityDto.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: www.idream.com</p>
 *
 * @author huayuliang
 * @version 1.0
 */
@ApiModel(value = "活动打卡记录")
public class ActivityDto {
    @ApiModelProperty(value = "活动ID", required = true)
    private Integer activityId;
    @ApiModelProperty(value = "用户ID", required = true)
    private Integer userId;
    @ApiModelProperty(value = "活动打卡ID", required = true)
    private Integer taskId;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }


}
