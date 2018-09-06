package com.idream.commons.lib.model;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 */
@ApiModel("")
public class UserFeedback {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Integer userId;

    /**
     * 反馈内容
     */
    @ApiModelProperty(value = "反馈内容")
    private String feedbackContent;

    /**
     * 反馈图片, 多张图以逗号隔开
     */
    @ApiModelProperty(value = "反馈图片, 多张图以逗号隔开")
    private String feedbackImage;

    /**
     * 用户联系方式
     */
    @ApiModelProperty(value = "用户联系方式")
    private String userContactInfo;

    /**
     * 反馈信息是否处理 0-否;1-是
     */
    @ApiModelProperty(value = "反馈信息是否处理 0-否;1-是")
    private Boolean infoIsHandle;

    /**
     * 生成时间
     */
    @ApiModelProperty(value = "生成时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

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

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent == null ? null : feedbackContent.trim();
    }

    public String getFeedbackImage() {
        return feedbackImage;
    }

    public void setFeedbackImage(String feedbackImage) {
        this.feedbackImage = feedbackImage == null ? null : feedbackImage.trim();
    }

    public String getUserContactInfo() {
        return userContactInfo;
    }

    public void setUserContactInfo(String userContactInfo) {
        this.userContactInfo = userContactInfo == null ? null : userContactInfo.trim();
    }

    public Boolean getInfoIsHandle() {
        return infoIsHandle;
    }

    public void setInfoIsHandle(Boolean infoIsHandle) {
        this.infoIsHandle = infoIsHandle;
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
}