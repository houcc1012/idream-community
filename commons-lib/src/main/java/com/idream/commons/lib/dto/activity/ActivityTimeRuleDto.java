package com.idream.commons.lib.dto.activity;

import java.util.Date;
import java.util.List;

public class ActivityTimeRuleDto {

    //活动id
    private Integer activityId;

    //活动时间类型
    private Integer type;

    //若是周期性活动,显示周几
    private String weekDay;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;

    //活动开始时间,活动结束时间
    private List<TimeDto> timeDto;

    public List<TimeDto> getTimeDto() {
        return timeDto;
    }

    public void setTimeDto(List<TimeDto> timeDto) {
        this.timeDto = timeDto;
    }

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
        this.weekDay = weekDay;
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
