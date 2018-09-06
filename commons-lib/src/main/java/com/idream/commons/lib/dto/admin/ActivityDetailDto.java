package com.idream.commons.lib.dto.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Author: jeffery
 * @Date: 2018/7/4 15:16
 */
@ApiModel(value = "管理端 终端 活动明细")
public class ActivityDetailDto {
    @ApiModelProperty(value = "活动类型")
    private Integer activityType;

    @ApiModelProperty(value = "活动标题")
    private String activityTitle;

    @ApiModelProperty(value = "主题")
    private String themeTitle;

    @ApiModelProperty(value = "所在社区")
    private String regionName;

    @ApiModelProperty(value = "活动时间")
    private Date activityTime;

    @ApiModelProperty(value = "活动状态 (1,未发布) (2,待开始) (3,进行中) (4,已结束)")
    private Integer activityStatus;

    public Integer getActivityType() {
        return activityType;
    }

    public void setActivityType(Integer activityType) {
        this.activityType = activityType;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getThemeTitle() {
        return themeTitle;
    }

    public void setThemeTitle(String themeTitle) {
        this.themeTitle = themeTitle;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Date getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(Date activityTime) {
        this.activityTime = activityTime;
    }

    public Integer getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
    }
}
