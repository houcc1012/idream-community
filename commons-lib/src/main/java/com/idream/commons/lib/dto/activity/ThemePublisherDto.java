package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "活动主题和发布者")
public class ThemePublisherDto {

    @ApiModelProperty(value = "活动主题")
    private String theme;
    @ApiModelProperty(value = "主题发布者")
    private String publisher;
    @ApiModelProperty(value = "活动标题")
    private String activityTitle;

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

}
