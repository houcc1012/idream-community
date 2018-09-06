package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author penghekai
 */
@ApiModel(value = "展示主题相关信息返回参数")
public class FindThemeResponseDto {

    @ApiModelProperty("主题id")
    private Integer themeId;
    @ApiModelProperty("活动标题")
    private String activityTitle;
    @ApiModelProperty(value = "主题标题")
    private String title;
    @ApiModelProperty(value = "主题图片")
    private String image;
    @ApiModelProperty(value = "发布者")
    private String publisher;
    @ApiModelProperty(value = "打卡数")
    private Integer taskRecordCount;
    @ApiModelProperty(value = "留言数")
    private Integer messageCount;

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getTaskRecordCount() {
        return taskRecordCount;
    }

    public void setTaskRecordCount(Integer taskRecordCount) {
        this.taskRecordCount = taskRecordCount;
    }

    public Integer getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(Integer messageCount) {
        this.messageCount = messageCount;
    }

}
