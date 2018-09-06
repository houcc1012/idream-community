package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author: ZhengGaosheng
 * @date: 2018/6/27 09:29
 * @description:
 */
@ApiModel("活动展示")
public class DisplayActivityResponseDto {

    @ApiModelProperty(value = "活动id")
    private Integer activityId;
    @ApiModelProperty(value = "活动标题")
    private String title;
    @ApiModelProperty(value = "副标题")
    private String subtitle;
    @ApiModelProperty(value = "活动封面url")
    private String image;
    @ApiModelProperty(value = "活动内容")
    private String content;
    @ApiModelProperty(value = "参加人数,0表示该活动参加人数不收限制")
    private Integer count;
    @ApiModelProperty(value = "开始时间")
    private Long startTime;
    @ApiModelProperty(value = "结束时间")
    private Long endTime;

    @ApiModelProperty("用户头像")
    private String userImage;
    @ApiModelProperty("用户昵称")
    private String nickName;
    @ApiModelProperty("活动状态(1,未开始) (2,进行中) (3,已结束) (4,已取消)")
    private Integer activityStatus;
    @ApiModelProperty("访问数量")
    private Integer visitCount;
    @ApiModelProperty("大社区")
    private List<String> regionName;
    @ApiModelProperty("小区")
    private List<String> communityName;
    @ApiModelProperty("书屋")
    private List<String> bookName;
    @ApiModelProperty("时间类型:1一次,2多次,3周期,4长期")
    private Integer timeType;
    @ApiModelProperty("当time_type为3时,保留的每周周几,数组,1表示周一")
    private String weekDay;

    @ApiModelProperty("距离开始的时间还有几天")
    private Integer distanceCurrentDay = -1;

    @ApiModelProperty("活动地址")
    private String address;

    @ApiModelProperty("是否参加活动. 0:未参加,1参加")
    private Integer joinedStatus;

    @ApiModelProperty("当前活动当前参加的人数")
    private Integer currentActivityCountPeople;

    @ApiModelProperty("活动的发布者")
    private String publisher;

    @ApiModelProperty("发布者的头像")
    private String publisherImage;

    @ApiModelProperty("打卡信息")
    private MiniActivityAssociationTask task;

    public String getPublisherImage() {
        return publisherImage;
    }

    public void setPublisherImage(String publisherImage) {
        this.publisherImage = publisherImage;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Integer getJoinedStatus() {
        return joinedStatus;
    }

    public void setJoinedStatus(Integer joinedStatus) {
        this.joinedStatus = joinedStatus;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
    }

    public Integer getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
    }

    public List<String> getRegionName() {
        return regionName;
    }

    public void setRegionName(List<String> regionName) {
        this.regionName = regionName;
    }

    public List<String> getBookName() {
        return bookName;
    }

    public void setBookName(List<String> bookName) {
        this.bookName = bookName;
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

    public Integer getDistanceCurrentDay() {
        return distanceCurrentDay;
    }

    public void setDistanceCurrentDay(Integer distanceCurrentDay) {
        this.distanceCurrentDay = distanceCurrentDay;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getCommunityName() {
        return communityName;
    }

    public void setCommunityName(List<String> communityName) {
        this.communityName = communityName;
    }


    public Integer getCurrentActivityCountPeople() {
        return currentActivityCountPeople;
    }

    public void setCurrentActivityCountPeople(Integer currentActivityCountPeople) {
        this.currentActivityCountPeople = currentActivityCountPeople;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public MiniActivityAssociationTask getTask() {
        return task;
    }

    public void setTask(MiniActivityAssociationTask task) {
        this.task = task;
    }

}

