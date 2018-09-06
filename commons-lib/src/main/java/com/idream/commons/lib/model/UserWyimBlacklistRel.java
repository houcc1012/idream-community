package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("用户网易云黑名单关系")
public class UserWyimBlacklistRel {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "黑名单用户id")
    private Integer blacklistUserId;

    @ApiModelProperty(value = "备注")
    private String memo;

    @ApiModelProperty(value = "生成时间")
    private Date createTime;

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

    public Integer getBlacklistUserId() {
        return blacklistUserId;
    }

    public void setBlacklistUserId(Integer blacklistUserId) {
        this.blacklistUserId = blacklistUserId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}