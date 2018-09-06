package com.idream.commons.lib.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "现在开奖列表返回dto")
public class LotteryListResponseDto {

    @ApiModelProperty(value = "社区id")
    private Integer communityId;

    @ApiModelProperty(value = "社区名")
    private String communityName;

    @ApiModelProperty(value = "社区经度")
    private BigDecimal longitude;

    @ApiModelProperty(value = "社区纬度")
    private BigDecimal latitude;

    @ApiModelProperty(value = "活动id")
    private Integer activityId;

    @ApiModelProperty(value = "活动名")
    private String activityName;

    @ApiModelProperty(value = "现场开奖id")
    private Integer lotteryId;

    @ApiModelProperty(value = "开奖开始时间")
    private Date startTime;

    @ApiModelProperty(value = "开奖结束时间")
    private Date endTime;

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Integer getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(Integer lotteryId) {
        this.lotteryId = lotteryId;
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

}
