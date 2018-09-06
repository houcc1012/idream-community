package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "已发布活动明细返回参数")
public class FindPublishedActivityResponseDto {

    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "昵称")
    private String nickName;
    @ApiModelProperty(value = "累计发布活动数量")
    private String publishCount;
    @ApiModelProperty(value = "活动类型")
    private String activityType;
    @ApiModelProperty(value = "活动标题")
    private String title;
    @ApiModelProperty(value = "活动主题")
    private String theme;
    @ApiModelProperty(value = "活动所在社区")
    private String community;
    @ApiModelProperty(value = "活动时间")
    private String startTime;
    @ApiModelProperty(value = "活动状态")
    private String status;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPublishCount() {
        return publishCount;
    }

    public void setPublishCount(String publishCount) {
        this.publishCount = publishCount;
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

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
