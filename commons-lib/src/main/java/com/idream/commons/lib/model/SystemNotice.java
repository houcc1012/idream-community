package com.idream.commons.lib.model;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 系统消息
 */
@ApiModel("系统消息")
public class SystemNotice {
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
     * 通知方式 1-全渠道通知; 2-站内信 ; 3-app推送
     */
    @ApiModelProperty(value = "通知方式 1-全渠道通知; 2-站内信 ; 3-app推送")
    private Byte noticeWay;

    /**
     * 消息类型 1-全局消息 ;2-社区消息(社区管理员发送的对本社区用户可见的消息)
     */
    @ApiModelProperty(value = "消息类型 1-全局消息 ;2-社区消息(社区管理员发送的对本社区用户可见的消息)")
    private Byte type;

    /**
     * 通知状态, 0-未发布;1-已发布
     */
    @ApiModelProperty(value = "通知状态, 0-未发布;1-已发布")
    private Byte status;

    /**
     * 发布时间
     */
    @ApiModelProperty(value = "发布时间")
    private Date publishTime;

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

    public Byte getNoticeWay() {
        return noticeWay;
    }

    public void setNoticeWay(Byte noticeWay) {
        this.noticeWay = noticeWay;
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

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
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