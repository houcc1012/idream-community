package com.idream.commons.lib.dto.activity;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Copyright: Copyright (c) 2018
 * Company: www.idream.com
 *
 * @author huayuliang
 * @version 1.0
 */
@ApiModel(value = "活动打卡信息")
public class ActivityTaskDto {
    @ApiModelProperty(value = "活动打卡表ID")
    private Integer id;
    @ApiModelProperty(value = "用户ID")
    private Integer userId;
    @ApiModelProperty(value = "主标题")
    private String title;
    @ApiModelProperty(value = "副标题")
    private String subtitle;
    @ApiModelProperty(value = "用户角色权限")
    private String userRole;
    @ApiModelProperty(value = "头像图片")
    private String image;
    @ApiModelProperty(value = "打卡内容")
    private String contents;
    @ApiModelProperty(value = "活动开始时间")
    private Date startTime;
    @ApiModelProperty(value = "活动结束时间")
    private Date endTime;
    @ApiModelProperty(value = "开始时间")
    private Date createTime;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    @ApiModelProperty(value = "发起人|参与者 : true : false")
    //活动: 发起人|参与者
    private boolean isPublisher = false;
    //活动: 开始|活动未开始
    @ApiModelProperty(value = "活动开始|活动未开始 : true : false")
    private boolean isTaskStatus = false;
    //用户: 打卡|未打卡
    @ApiModelProperty(value = "活动已打卡|活动未打卡 : true : false")
    private boolean isUserRecord = false;
    //主题类型:默认主题1,自定义2
    @ApiModelProperty(value = "主题类型:默认主题1,自定义2")
    private Integer type;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isPublisher() {
        return isPublisher;
    }

    public void setPublisher(boolean isPublisher) {
        this.isPublisher = isPublisher;
    }

    public boolean isTaskStatus() {
        return isTaskStatus;
    }

    public void setTaskStatus(boolean isTaskStatus) {
        this.isTaskStatus = isTaskStatus;
    }

    public boolean isUserRecord() {
        return isUserRecord;
    }

    public void setUserRecord(boolean isUserRecord) {
        this.isUserRecord = isUserRecord;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


}