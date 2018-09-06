package com.idream.commons.lib.dto.activity;

import java.util.List;


public class ActivityThemeDto {

    //活动id
    private Integer activityId;

    //主题id
    private Integer themeId;

    //主题标题
    private String title;

    //主题内容
    private List<String> content;

    //主题绑定的打卡状态
    private Integer status;

    //活动打卡开始结束时间
    private List<TimeDto> timeDto;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    public List<TimeDto> getTimeDto() {
        return timeDto;
    }

    public void setTimeDto(List<TimeDto> timeDto) {
        this.timeDto = timeDto;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
