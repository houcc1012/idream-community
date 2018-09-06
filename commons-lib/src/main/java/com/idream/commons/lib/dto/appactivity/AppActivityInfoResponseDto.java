package com.idream.commons.lib.dto.appactivity;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;

@ApiModel("App活动信息返回参数")
public class AppActivityInfoResponseDto {

    @ApiModelProperty("活动id")
    private Integer activityId;
    @ApiModelProperty("活动标题")
    private String title;
    @ApiModelProperty("副标题")
    private String subtitle;
    @ApiModelProperty("活动封面")
    private String image;
    @ApiModelProperty("活动来源,默认1为官方")
    private String fromType;
    @ApiModelProperty("活动内容")
    private String content;
    @ApiModelProperty("开始时间")
    private Date startTime;
    @ApiModelProperty("结束时间")
    private Date endTime;
    @ApiModelProperty("时间类型: 1一次,2多次,3周期")
    private Integer timeType;
    @ApiModelProperty("周期")
    private String weekDay;
    @ApiModelProperty("城市")
    private String city;
    @ApiModelProperty("区域")
    private String district;
    @ApiModelProperty("详细地址")
    private String address;
    @ApiModelProperty("距离")
    private BigDecimal distance;
    @ApiModelProperty("社区名")
    private String regionName;

    @ApiModelProperty("书屋名字")
    private String bookName;

    @ApiModelProperty("当前活动状态 1未开始，2进行中，3已结束")
    private Integer activityStatus;
    @ApiModelProperty("是否参加活动")
    private boolean joined;
    @ApiModelProperty("是否需要收集报名信息")
    private boolean infoCollection;

    @ApiModelProperty("该活动关联的小区")
    private String communityName;

    @ApiModelProperty("用户访问的记录数")
    private Integer userVisitCount;

    @ApiModelProperty("发布者的昵称")
    private String publishName;
    @ApiModelProperty("发布者的头像")
    private String publishImage;

    private Integer distanceCurrentDay;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public boolean isInfoCollection() {
        return infoCollection;
    }

    public void setInfoCollection(boolean infoCollection) {
        this.infoCollection = infoCollection;
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

    public String getFromType() {
        return fromType;
    }

    public void setFromType(String fromType) {
        this.fromType = fromType;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
    }

    public boolean isJoined() {
        return joined;
    }

    public void setJoined(boolean joined) {
        this.joined = joined;
    }


    public Integer getTimeType() {
        return timeType;
    }

    public void setTimeType(Integer timeType) {
        this.timeType = timeType;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public Integer getUserVisitCount() {
        return userVisitCount;
    }

    public void setUserVisitCount(Integer userVisitCount) {
        this.userVisitCount = userVisitCount;
    }

    public String getPublishName() {
        return publishName;
    }

    public void setPublishName(String publishName) {
        this.publishName = publishName;
    }

    public String getPublishImage() {
        return publishImage;
    }

    public void setPublishImage(String publishImage) {
        this.publishImage = publishImage;
    }

    public Integer getDistanceCurrentDay() {
        return distanceCurrentDay;
    }

    public void setDistanceCurrentDay(Integer distanceCurrentDay) {
        this.distanceCurrentDay = distanceCurrentDay;
    }

}
