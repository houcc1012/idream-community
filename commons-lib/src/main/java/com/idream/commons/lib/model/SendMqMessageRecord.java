package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class SendMqMessageRecord {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "消息编号")
    private String msgId;

    @ApiModelProperty(value = "消息内容")
    private String content;

    @ApiModelProperty(value = "发送状态 1-发送中;2-成功;3-失败;4-丢弃")
    private Byte status;

    @ApiModelProperty(value = "重试最大次数")
    private Short retryMaxNo;

    @ApiModelProperty(value = "重试次数")
    private Short retryNo;

    @ApiModelProperty(value = "业务类型 1-网易IM调用;")
    private Byte businessType;

    @ApiModelProperty(value = "生成时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId == null ? null : msgId.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Short getRetryMaxNo() {
        return retryMaxNo;
    }

    public void setRetryMaxNo(Short retryMaxNo) {
        this.retryMaxNo = retryMaxNo;
    }

    public Short getRetryNo() {
        return retryNo;
    }

    public void setRetryNo(Short retryNo) {
        this.retryNo = retryNo;
    }

    public Byte getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Byte businessType) {
        this.businessType = businessType;
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