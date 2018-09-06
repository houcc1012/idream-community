package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>Title: ActivityCommunityDto.java</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2017</p >
 * <p>Company: www.idream.com</p >
 *
 * @author huayuliang
 * @version 1.0
 */
@ApiModel(value = "500米内打卡")
public class ActivityCommunityDto {
    @ApiModelProperty(value = "活动ID")
    private Integer activityId;
    @ApiModelProperty(value = "社区ID")
    private Integer communityId;
    @ApiModelProperty(value = "用户ID")
    private Integer userId;
    @ApiModelProperty(value = "经度")
    private double longitude;
    @ApiModelProperty(value = "纬度")
    private double latitude;
    @ApiModelProperty(value = "大标题")
    private String title;
    @ApiModelProperty(value = "活动地址")
    private String address;
    @ApiModelProperty(value = "社区名称")
    private String communityName;
    @ApiModelProperty(value = "展示内容")
    private String content;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


}