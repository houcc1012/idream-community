package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

@ApiModel(value = "查询已发布活动明细返回参数")
public class FindPublishedActivityDetailResponseDto {

    @ApiModelProperty(value = "活动id")
    private Integer activityId;
    @ApiModelProperty(value = "活动类型")
    private String activityType;
    @ApiModelProperty(value = "活动标题")
    private String activityTitle;
    @ApiModelProperty(value = "活动主题")
    private String themeTitle;
    @ApiModelProperty(value = "高级社区名字")
    private String regionNames;
    @ApiModelProperty(value = "时间规则")
    private byte timeRule;
    @ApiModelProperty(value = "活动日期")
    private List<MultiDate> multiDate;
    @ApiModelProperty(value = "活动周期")
    private String weekDay;
    @ApiModelProperty(value = "活动开始时间")
    private Date startTime;
    @ApiModelProperty(value = "活动结束时间")
    private Date endTime;
    @ApiModelProperty(value = "开始日期")
    private Date startDate;
    @ApiModelProperty(value = "结束日期")
    private Date endDate;
    @ApiModelProperty(value = "1待审核，2审核成功，3审核失败")
    //审核状态 0已发布过1创建完成,2提交审核,3审核通过4上架,5下架,6审核失败,9删除数据
    private byte status;
    @ApiModelProperty(value = "活动状态 (1,待开始) (2,进行中) (3,已结束)")
    private byte activityStatus;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }


    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getThemeTitle() {
        return themeTitle;
    }

    public void setThemeTitle(String themeTitle) {
        this.themeTitle = themeTitle;
    }

    public String getRegionNames() {
        return regionNames;
    }

    public void setRegionNames(String regionNames) {
        this.regionNames = regionNames;
    }


    public byte getTimeRule() {
        return timeRule;
    }

    public void setTimeRule(byte timeRule) {
        this.timeRule = timeRule;
    }

    public List<MultiDate> getMultiDate() {
        return multiDate;
    }

    public void setMultiDate(List<MultiDate> multiDate) {
        this.multiDate = multiDate;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public byte getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(byte activityStatus) {
        this.activityStatus = activityStatus;
    }
}
