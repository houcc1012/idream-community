package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 活动参加
 */
@ApiModel("活动参加")
public class ActivityJoinRelation {
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
     * 参加的id
     */
    @ApiModelProperty(value = "参加的id")
    private Integer joinId;

    /**
     * 参与级别,1全部,10省,20市,30区,40社区,60小区,,70书屋,100个人
     */
    @ApiModelProperty(value = "参与级别,1全部,10省,20市,30区,40社区,60小区,,70书屋,100个人")
    private Byte joinType;

    /**
     * 报名开始时间
     */
    @ApiModelProperty(value = "报名开始时间")
    private Date startTime;

    /**
     * 报名结束时间
     */
    @ApiModelProperty(value = "报名结束时间")
    private Date endTime;

    /**
     * 生成时间
     */
    @ApiModelProperty(value = "生成时间")
    private Date createTime;

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

    public Integer getJoinId() {
        return joinId;
    }

    public void setJoinId(Integer joinId) {
        this.joinId = joinId;
    }

    public Byte getJoinType() {
        return joinType;
    }

    public void setJoinType(Byte joinType) {
        this.joinType = joinType;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}