package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("用户网易云通讯账号表")
public class UserWyimAccount {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "accid")
    private String accId;

    @ApiModelProperty(value = "token")
    private String token;

    @ApiModelProperty(value = "推送消息方式 桌面端在线时，移动端是否不推送： 0-否;1-是")
    private Boolean donnopOpen;

    @ApiModelProperty(value = "好友申请同意方式 1-允许任何人;2-需要验证;3-拒绝加好友")
    private Byte friendArgeeType;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAccId() {
        return accId;
    }

    public void setAccId(String accId) {
        this.accId = accId == null ? null : accId.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public Boolean getDonnopOpen() {
        return donnopOpen;
    }

    public void setDonnopOpen(Boolean donnopOpen) {
        this.donnopOpen = donnopOpen;
    }

    public Byte getFriendArgeeType() {
        return friendArgeeType;
    }

    public void setFriendArgeeType(Byte friendArgeeType) {
        this.friendArgeeType = friendArgeeType;
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