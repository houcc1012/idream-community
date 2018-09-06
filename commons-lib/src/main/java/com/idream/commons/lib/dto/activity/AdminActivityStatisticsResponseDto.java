package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: penghekai
 * @Date: 2018/7/21 10:08
 * @Description:
 */
@ApiModel("活动数据统计返回参数")
public class AdminActivityStatisticsResponseDto {

    @ApiModelProperty("活动id")
    private Integer activityId;
    @ApiModelProperty("活动类型")
    private String activityType;
    @ApiModelProperty("活动标题")
    private String activityTitle;
    @ApiModelProperty(value = "浏览量")
    private Integer visitCount;
    @ApiModelProperty(value = "报名数")
    private Integer applyNum;
    @ApiModelProperty(value = "打卡数")
    private Integer taskNum;
    @ApiModelProperty(value = "邀请数")
    private Integer invitationNum;
    @ApiModelProperty(value = "活动精彩数")
    private Integer messageNum;
    @ApiModelProperty(value = "发布书屋")
    private String bookName;

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

    public Integer getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
    }

    public Integer getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(Integer applyNum) {
        this.applyNum = applyNum;
    }

    public Integer getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(Integer taskNum) {
        this.taskNum = taskNum;
    }

    public Integer getInvitationNum() {
        return invitationNum;
    }

    public void setInvitationNum(Integer invitationNum) {
        this.invitationNum = invitationNum;
    }

    public Integer getMessageNum() {
        return messageNum;
    }

    public void setMessageNum(Integer messageNum) {
        this.messageNum = messageNum;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}

