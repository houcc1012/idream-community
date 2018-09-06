package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("活动展示")
public class DisplayActivityDto extends PublishDto {

    @ApiModelProperty("动类型名")
    private String activityTypeName;
    @ApiModelProperty("社区名称")
    private String communityName;
    @ApiModelProperty("活动场地")
    private String activityPlace;
    @ApiModelProperty("报名信息")
    private String infoNames;
    @ApiModelProperty("用户头像")
    private String userImage;
    @ApiModelProperty("用户昵称")
    private String nickName;
    @ApiModelProperty("活动状态(1,未开始) (2,进行中) (3,已结束) (4,已取消)")
    private Integer activityStatus;

    public Integer getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getActivityTypeName() {
        return activityTypeName;
    }

    public void setActivityTypeName(String activityTypeName) {
        this.activityTypeName = activityTypeName;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getActivityPlace() {
        return activityPlace;
    }

    public void setActivityPlace(String activityPlace) {
        this.activityPlace = activityPlace;
    }

    public String getInfoNames() {
        return infoNames;
    }

    public void setInfoNames(String infoNames) {
        this.infoNames = infoNames;
    }

}
