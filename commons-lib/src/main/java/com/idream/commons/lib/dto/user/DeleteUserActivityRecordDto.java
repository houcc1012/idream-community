package com.idream.commons.lib.dto.user;

import java.util.Date;

/**
 * <p>Title: ParticipateActivityDto.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: www.idream.com</p>
 *
 * @author huayuliang
 * @version 1.0
 */
public class DeleteUserActivityRecordDto {
    private Integer activityId;
    private Integer userId;
    private Integer exitStatus;
    private Date createTime;
    private Date updateTime;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getExitStatus() {
        return exitStatus;
    }

    public void setExitStatus(Integer exitStatus) {
        this.exitStatus = exitStatus;
    }


}
