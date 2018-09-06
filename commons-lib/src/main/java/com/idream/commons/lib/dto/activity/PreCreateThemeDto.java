package com.idream.commons.lib.dto.activity;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("主题添加信息返回")
public class PreCreateThemeDto {
    @ApiModelProperty(value = "活动类型")
    private String activityType;
    @ApiModelProperty(value = "活动标题")
    private String title;
    @ApiModelProperty(value = "待选时间")
    private List<Long> dates;

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

    public List<Long> getDates() {
        return dates;
    }

    public void setDates(List<Long> dates) {
        this.dates = dates;
    }
}
