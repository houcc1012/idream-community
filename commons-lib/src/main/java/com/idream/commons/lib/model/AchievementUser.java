package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 *
 */
@ApiModel("")
public class AchievementUser {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Integer userId;

    /**
     * 成就id
     */
    @ApiModelProperty(value = "成就id")
    private Integer achieveId;

    /**
     * 成就名称
     */
    @ApiModelProperty(value = "成就名称")
    private String achieveName;

    /**
     * 1完成,0未完成
     */
    @ApiModelProperty(value = "1完成,0未完成")
    private Boolean completed;

    /**
     * 完成时间
     */
    @ApiModelProperty(value = "完成时间")
    private Date completeTime;

    /**
     * 完成排名
     */
    @ApiModelProperty(value = "完成排名")
    private Integer completeNum;

    /**
     * 接收奖励,1接收,0未接受
     */
    @ApiModelProperty(value = "接收奖励,1接收,0未接受")
    private Boolean received;

    /**
     * 接收时间
     */
    @ApiModelProperty(value = "接收时间")
    private Date receiveTime;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAchieveId() {
        return achieveId;
    }

    public void setAchieveId(Integer achieveId) {
        this.achieveId = achieveId;
    }

    public String getAchieveName() {
        return achieveName;
    }

    public void setAchieveName(String achieveName) {
        this.achieveName = achieveName == null ? null : achieveName.trim();
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public Integer getCompleteNum() {
        return completeNum;
    }

    public void setCompleteNum(Integer completeNum) {
        this.completeNum = completeNum;
    }

    public Boolean getReceived() {
        return received;
    }

    public void setReceived(Boolean received) {
        this.received = received;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
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