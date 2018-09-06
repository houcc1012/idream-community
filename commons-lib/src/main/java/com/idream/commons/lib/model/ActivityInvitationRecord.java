package com.idream.commons.lib.model;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 活动邀请表
 */
@ApiModel("活动邀请表")
public class ActivityInvitationRecord {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 活动id
     */
    @ApiModelProperty(value = "活动id")
    private Integer activityId;

    /**
     * 邀请id
     */
    @ApiModelProperty(value = "邀请id")
    private Integer invitationId;

    /**
     * 接受id
     */
    @ApiModelProperty(value = "接受id")
    private Integer acceptId;

    /**
     * 邀请状态1成功
     */
    @ApiModelProperty(value = "邀请状态1成功")
    private Integer status;

    /**
     * 生成时间
     */
    @ApiModelProperty(value = "生成时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(Integer invitationId) {
        this.invitationId = invitationId;
    }

    public Integer getAcceptId() {
        return acceptId;
    }

    public void setAcceptId(Integer acceptId) {
        this.acceptId = acceptId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
}