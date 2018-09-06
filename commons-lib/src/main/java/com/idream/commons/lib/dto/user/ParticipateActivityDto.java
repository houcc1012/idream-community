package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * <p>
 * Title: ParticipateActivityDto.java
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2017
 * </p>
 * <p>
 * Company: www.idream.com
 * </p>
 *
 * @author huayuliang
 * @version 1.0
 */
@ApiModel("查询参加的活动列表返回参数")
public class ParticipateActivityDto {

    @ApiModelProperty(value = "图片地址")
    private String image;
    @ApiModelProperty(value = "用户角色 : 默认1: 游客")
    private Integer userRole;
    @ApiModelProperty(value = "活动id")
    private Integer activityId;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "子标题")
    private String subtitle;
    @ApiModelProperty(value = "内容")
    private String content;
    @ApiModelProperty(value = "社区名称")
    private String communityName;
    @ApiModelProperty(value = "开始时间")
    private Date startTime;
    @ApiModelProperty(value = "结束时间")
    private Date endTime;
    //@ApiModelProperty(value = "参与者|发起者 : 0 : 1")
    //private boolean isPublishUser;
    @ApiModelProperty(value = "活动状态：1未开始，2进行中，3已结束，4已取消")
    private Integer activityStatus;

    @ApiModelProperty("时间类型 1:长期. 2:多次. 3:周期")
    private Integer timeType;

    @ApiModelProperty("当timeTpye=3时,这里1代表周一...")
    private String weekDay;

    @ApiModelProperty("距离打卡开始还有多少秒")
    private Integer distanceCurrentDay;

    @ApiModelProperty("活动地址")
    private String address;
   // @ApiModelProperty("社区名称,该活动绑定多个社区用逗号隔开")
   // private String regionName;
   // @ApiModelProperty("小区名称,该活动绑定多个小区用逗号隔开")
   // private String groupName;
   // @ApiModelProperty("书屋名称,该活动绑定多个书屋用逗号隔开")
   // private String bookName;

    public Integer getDistanceCurrentDay() {
        return distanceCurrentDay;
    }

    public void setDistanceCurrentDay(Integer distanceCurrentDay) {
        this.distanceCurrentDay = distanceCurrentDay;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public Integer getTimeType() {
        return timeType;
    }

    public void setTimeType(Integer timeType) {
        this.timeType = timeType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
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

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
