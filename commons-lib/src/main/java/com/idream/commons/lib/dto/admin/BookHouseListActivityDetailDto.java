package com.idream.commons.lib.dto.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: jeffery
 * @Date: 2018/8/23 17:30
 */
@ApiModel(value = "后台管理 书屋列表 活动详情返回参数")
public class BookHouseListActivityDetailDto {
    @ApiModelProperty(value = "活动id")
    private Integer activityId;

    @ApiModelProperty(value = "活动类型")
    private String activityType;

    @ApiModelProperty(value = "活动主题")
    private String title;

    @ApiModelProperty(value = "活动日期 1.( 2018-08-14 至 2018-08-15 ) 2.( 6,7 | 星期六,星期天 )")
    private String activityDate;

    @ApiModelProperty(value = "活动时间")
    private String activityTime;

    @ApiModelProperty(value = "活动状态 1:2:3 | 活动未开始:活动进行中:活动结束")
    private Integer activityStatus;

    @ApiModelProperty(value = "时间类型:1一次,2多次,3周期,4长期")
    private Integer timeType;

    public Integer getTimeType() {
        return timeType;
    }

    public void setTimeType(Integer timeType) {
        this.timeType = timeType;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(String activityDate) {
        this.activityDate = activityDate;
    }

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
    }

    public Integer getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
    }
}
