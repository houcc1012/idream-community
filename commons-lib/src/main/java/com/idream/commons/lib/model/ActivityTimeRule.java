package com.idream.commons.lib.model;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 活动时间规则表
 */
@ApiModel("活动时间规则表")
public class ActivityTimeRule {
    /**
     * 主题id
     */
    @ApiModelProperty(value = "主题id")
    private Integer activityId;

    /**
     * 时间类型:1一次,2多次,3周期,4长期
     */
    @ApiModelProperty(value = "时间类型:1一次,2多次,3周期,4长期")
    private Integer type;

    /**
     * 当type为3时,保留的每周周几,数组,1表示周一
     */
    @ApiModelProperty(value = "当type为3时,保留的每周周几,数组,1表示周一")
    private String weekDay;

    /**
     *
     */
    @ApiModelProperty(value = "")
    private Date createTime;

    /**
     *
     */
    @ApiModelProperty(value = "")
    private Date updateTime;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay == null ? null : weekDay.trim();
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