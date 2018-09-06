package com.idream.commons.lib.model;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户站内信通知信息
 */
@ApiModel("用户站内信通知信息")
public class NotificationInfo {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 发送者id
     */
    @ApiModelProperty(value = "发送者id")
    private Integer sendId;

    /**
     * 接收者id
     */
    @ApiModelProperty(value = "接收者id")
    private Integer receiveId;

    /**
     * 系统通知id
     */
    @ApiModelProperty(value = "系统通知id")
    private Integer systemNoticeId;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    private String content;

    /**
     * 通知类型:1系统,2关注,3点赞
     */
    @ApiModelProperty(value = "通知类型:1系统,2关注,3点赞")
    private Byte type;

    /**
     * 通知状态,1未读,2已读,3已删除
     */
    @ApiModelProperty(value = "通知状态,1未读,2已读,3已删除")
    private Byte status;

    /**
     * 跳转地址
     */
    @ApiModelProperty(value = "跳转地址")
    private String goToUrl;

    /**
     * 额外信息
     */
    @ApiModelProperty(value = "额外信息")
    private String extraInfo;

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

    public Integer getSendId() {
        return sendId;
    }

    public void setSendId(Integer sendId) {
        this.sendId = sendId;
    }

    public Integer getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(Integer receiveId) {
        this.receiveId = receiveId;
    }

    public Integer getSystemNoticeId() {
        return systemNoticeId;
    }

    public void setSystemNoticeId(Integer systemNoticeId) {
        this.systemNoticeId = systemNoticeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getGoToUrl() {
        return goToUrl;
    }

    public void setGoToUrl(String goToUrl) {
        this.goToUrl = goToUrl == null ? null : goToUrl.trim();
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo == null ? null : extraInfo.trim();
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