package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 活动显示
 */
@ApiModel("活动显示")
public class ActivityDisplayRelation {
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
     * 显示id,默认社区id
     */
    @ApiModelProperty(value = "显示id,默认社区id")
    private Integer displayId;

    /**
     * 显示级别,1全部,10省,20市,30区,40社区,60小区,100个人
     */
    @ApiModelProperty(value = "显示级别,1全部,10省,20市,30区,40社区,60小区,100个人")
    private Byte displayType;

    /**
     * 展示开始时间
     */
    @ApiModelProperty(value = "展示开始时间")
    private Date startTime;

    /**
     * 显示结束时间
     */
    @ApiModelProperty(value = "显示结束时间")
    private Date endTime;

    /**
     * 来自哪个社区
     */
    @ApiModelProperty(value = "来自哪个社区")
    private Integer fromRegionId;

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

    public Integer getDisplayId() {
        return displayId;
    }

    public void setDisplayId(Integer displayId) {
        this.displayId = displayId;
    }

    public Byte getDisplayType() {
        return displayType;
    }

    public void setDisplayType(Byte displayType) {
        this.displayType = displayType;
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

    public Integer getFromRegionId() {
        return fromRegionId;
    }

    public void setFromRegionId(Integer fromRegionId) {
        this.fromRegionId = fromRegionId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}