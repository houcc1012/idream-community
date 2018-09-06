package com.idream.commons.lib.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "app端 通知列表返回dto")
public class NoticeListResponseDto {

    @ApiModelProperty(value = "系统消息id")
    private Integer id;

    @ApiModelProperty(value = "发送人id")
    private Integer sendId;

    @ApiModelProperty(value = "发送人昵称")
    private String nickName;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "发布时间")
    private Date publishTime;

    @ApiModelProperty(value = "状态")
    private Integer status;

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
