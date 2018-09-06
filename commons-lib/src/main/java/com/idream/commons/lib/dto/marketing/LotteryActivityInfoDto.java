package com.idream.commons.lib.dto.marketing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * Description :
 * Created by DELL2018-003 on 2018/7/3.
 */
@ApiModel(value = "根据抽奖活动id查询关联活动返回参数")
public class LotteryActivityInfoDto {

    @ApiModelProperty(value = "抽奖活动Id")
    private Integer lotteryId;

    @ApiModelProperty(value = "社区id")
    private Integer communityId;

    @ApiModelProperty(value = "社区名称")
    private String communityName;

    @ApiModelProperty(value = "活动id")
    private Integer activityId;

    @ApiModelProperty(value = "活动名称")
    private String activityName;
    @ApiModelProperty(value = "活动介绍")
    private String activityContent;
    @ApiModelProperty(value = "活动状态（1未开始，2进行中，3已结束）")
    private Integer activityStatus;

    @ApiModelProperty(value = "活动开始时间")
    private Date activityStartTime;

    @ApiModelProperty(value = "活动结束时间")
    private Date activityEndTime;

    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "开奖状态（0未开始,1开启,2进行中，3结束）")
    private Integer lotteryStatus;

    @ApiModelProperty(value = "开奖时间及状态")
    private List<LotteryDetailTimeDto> lotteryDetail;
    @ApiModelProperty(value = "开奖次数")
    private int countNum;

    @ApiModelProperty(value = "剩余开奖次数")
    private int lotteryNumber;

    @ApiModelProperty(value = "开奖介绍")
    private String lotteryInstructions;

    public String getLotteryInstructions() {
        return lotteryInstructions;
    }

    public void setLotteryInstructions(String lotteryInstructions) {
        this.lotteryInstructions = lotteryInstructions;
    }

    public int getCountNum() {
        return countNum;
    }

    public void setCountNum(int countNum) {
        this.countNum = countNum;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
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

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public Integer getLotteryStatus() {
        return lotteryStatus;
    }

    public void setLotteryStatus(Integer lotteryStatus) {
        this.lotteryStatus = lotteryStatus;
    }


    public Date getActivityEndTime() {
        return activityEndTime;
    }

    public void setActivityEndTime(Date activityEndTime) {
        this.activityEndTime = activityEndTime;
    }

    public Date getActivityStartTime() {
        return activityStartTime;
    }

    public void setActivityStartTime(Date activityStartTime) {
        this.activityStartTime = activityStartTime;
    }

    public Integer getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(Integer lotteryId) {
        this.lotteryId = lotteryId;
    }

    public String getActivityContent() {
        return activityContent;
    }

    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent;
    }

    public List<LotteryDetailTimeDto> getLotteryDetail() {
        return lotteryDetail;
    }

    public void setLotteryDetail(List<LotteryDetailTimeDto> lotteryDetail) {
        this.lotteryDetail = lotteryDetail;
    }

    public int getLotteryNumber() {
        return lotteryNumber;
    }

    public void setLotteryNumber(int lotteryNumber) {
        this.lotteryNumber = lotteryNumber;
    }
}
