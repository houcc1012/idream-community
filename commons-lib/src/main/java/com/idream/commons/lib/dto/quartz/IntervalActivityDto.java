package com.idream.commons.lib.dto.quartz;

import java.util.Date;

public class IntervalActivityDto {
    private Integer activityId;
    private Date startTime;
    private Date endTime;
    private Byte timeType;
    private String weekDay;

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setTimeType(Byte timeType) {
        this.timeType = timeType;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public Integer getActivityId() {

        return activityId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Byte getTimeType() {
        return timeType;
    }

    public String getWeekDay() {
        return weekDay;
    }
}
