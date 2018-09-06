package com.idream.commons.lib.dto.appactivity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("精彩页活动封面信息")
public class AppActivityImageResponseDto {

    @ApiModelProperty("活动id")
    private Integer activityId;
    @ApiModelProperty("活动标题")
    private String title;
    @ApiModelProperty("活动封面")
    private String image;
    @ApiModelProperty("活动类型")
    private Integer typeId;
    @ApiModelProperty("是否参加了活动")
    private boolean joined;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isJoined() {
        return joined;
    }

    public void setJoined(boolean joined) {
        this.joined = joined;
    }
}
