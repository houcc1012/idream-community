package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 活动扩展表
 */
@ApiModel("活动扩展表")
public class ActivityExtension {
    /**
     *
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 活动id
     */
    @ApiModelProperty(value = "活动id")
    private Integer activityId;

    /**
     * 访问数量
     */
    @ApiModelProperty(value = "访问数量")
    private Integer visitNum;

    /**
     * 报名数量
     */
    @ApiModelProperty(value = "报名数量")
    private Integer signNum;

    /**
     * 打卡数量
     */
    @ApiModelProperty(value = "打卡数量")
    private Integer taskNum;

    /**
     * 邀请数量
     */
    @ApiModelProperty(value = "邀请数量")
    private Integer inviteNum;

    /**
     * 主题数量
     */
    @ApiModelProperty(value = "主题数量")
    private Integer themeNum;

    /**
     * 留言数量
     */
    @ApiModelProperty(value = "留言数量")
    private Integer messageNum;

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

    public Integer getVisitNum() {
        return visitNum;
    }

    public void setVisitNum(Integer visitNum) {
        this.visitNum = visitNum;
    }

    public Integer getSignNum() {
        return signNum;
    }

    public void setSignNum(Integer signNum) {
        this.signNum = signNum;
    }

    public Integer getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(Integer taskNum) {
        this.taskNum = taskNum;
    }

    public Integer getInviteNum() {
        return inviteNum;
    }

    public void setInviteNum(Integer inviteNum) {
        this.inviteNum = inviteNum;
    }

    public Integer getThemeNum() {
        return themeNum;
    }

    public void setThemeNum(Integer themeNum) {
        this.themeNum = themeNum;
    }

    public Integer getMessageNum() {
        return messageNum;
    }

    public void setMessageNum(Integer messageNum) {
        this.messageNum = messageNum;
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