package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "参加活动显示")
public class AppJoinActivityDto {
    @ApiModelProperty("活动id")
    private Integer activityId;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("副标题")
    private String subtitle;
    @ApiModelProperty("图片地址")
    private String image;
    @ApiModelProperty("社区名")
    private String communityName;
    @ApiModelProperty("开始日期")
    private Date startDate;
    @ApiModelProperty("结束日期")
    private Date endDate;
    @ApiModelProperty("开始时间,时分秒")
    private Date startTime;
    @ApiModelProperty("结束时间,时分秒")
    private Date endTime;
    @ApiModelProperty("是否跨天,默认false")
    private boolean acrossDay = false;
    @ApiModelProperty("活动状态,1活动未开始,2进行中,3活动已结束")
    private Integer status;

    public Integer getActivityId() {
        return activityId;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getImage() {
        return image;
    }

    public String getCommunityName() {
        return communityName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public boolean isAcrossDay() {
        return acrossDay;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setAcrossDay(boolean acrossDay) {
        this.acrossDay = acrossDay;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
