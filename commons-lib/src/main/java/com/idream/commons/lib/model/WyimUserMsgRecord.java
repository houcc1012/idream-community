package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("网易用户群消息记录")
public class WyimUserMsgRecord {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "发送用户accID")
    private String fromAccount;

    @ApiModelProperty(value = "发送消息设备类型 AOS, IOS, PC, WINPHONE, WEB, REST")
    private String sendClientType;

    @ApiModelProperty(value = "发送设备编码")
    private String sendDeviceCode;

    @ApiModelProperty(value = "消息类型")
    private String msgType;

    @ApiModelProperty(value = "消息内容")
    private String content;

    @ApiModelProperty(value = "附加消息")
    private String attach;

    @ApiModelProperty(value = "发送时间")
    private Date sendTime;

    @ApiModelProperty(value = "是否重发")
    private Boolean resendFlag;

    @ApiModelProperty(value = "网易服务端消息ID")
    private String msgId;

    @ApiModelProperty(value = "接收消息用户账号")
    private String toAccount;

    @ApiModelProperty(value = "消息扩展字段")
    private String ext;

    @ApiModelProperty(value = "生成时间")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount == null ? null : fromAccount.trim();
    }

    public String getSendClientType() {
        return sendClientType;
    }

    public void setSendClientType(String sendClientType) {
        this.sendClientType = sendClientType == null ? null : sendClientType.trim();
    }

    public String getSendDeviceCode() {
        return sendDeviceCode;
    }

    public void setSendDeviceCode(String sendDeviceCode) {
        this.sendDeviceCode = sendDeviceCode == null ? null : sendDeviceCode.trim();
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType == null ? null : msgType.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach == null ? null : attach.trim();
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Boolean getResendFlag() {
        return resendFlag;
    }

    public void setResendFlag(Boolean resendFlag) {
        this.resendFlag = resendFlag;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId == null ? null : msgId.trim();
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount == null ? null : toAccount.trim();
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext == null ? null : ext.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}