package com.idream.commons.lib.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("活动显示")
public class AppActivityRecommendDto {
    @ApiModelProperty("活动标题")
    private String title;
    @ApiModelProperty("活动封面")
    private String image;
    @ApiModelProperty("活动id")
    private Integer activityId;
    @ApiModelProperty("是否参与活动")
    private Boolean joined;
    @ApiModelProperty("活动状态,1未开始,2进行中,3已结束")
    private Integer activityStatus;

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setJoined(Boolean joined) {
        this.joined = joined;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getJoined() {
        return joined;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public Integer getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
    }
}
