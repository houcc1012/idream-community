package com.idream.commons.lib.dto.activity;

import com.idream.commons.lib.model.ActivityGroupRelation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * @Auther: penghekai
 * @Date: 2018/6/30 15:00
 * @Description:
 */
@ApiModel("活动审核页面列表展示返回参数")
public class ActivityListCheckResponseDto {

    @ApiModelProperty(value = "活动id")
    private Integer activityId;
    @ApiModelProperty(value = "活动类型")
    private String activityType;
    @ApiModelProperty(value = "活动标题")
    private String title;
    @ApiModelProperty(value = "所在城市")
    private String city;
    @ApiModelProperty(value = "所在区域")
    private String district;
    @ApiModelProperty(value = "所在社区")
    private List<ActivityGroupRelation> regions;
    @ApiModelProperty(value = "所在书屋")
    private String regionGroupBook;
    @ApiModelProperty(value = "时间规则")
    private Integer timeRule;
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
    @ApiModelProperty(value = "发布者")
    private String publisher;
    @ApiModelProperty(value = "活动状态")
    private Integer status;
    @ApiModelProperty(value = "审核状态 1待审核 2 审核成功 3审核失败  中台：1待审核 2审核中 3审核成功 4审核失败")
    private Integer checkStatus;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public List<ActivityGroupRelation> getRegions() {
        return regions;
    }

    public void setRegions(List<ActivityGroupRelation> regions) {
        this.regions = regions;
    }

    public String getRegionGroupBook() {
        return regionGroupBook;
    }

    public void setRegionGroupBook(String regionGroupBook) {
        this.regionGroupBook = regionGroupBook;
    }

    public Integer getTimeRule() {
        return timeRule;
    }

    public void setTimeRule(Integer timeRule) {
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }
}

