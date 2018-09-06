package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("网易云通信群组成员")
public class WximGroupMembers {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "accid")
    private String accId;

    @ApiModelProperty(value = "token 群主token")
    private String token;

    @ApiModelProperty(value = "群组ID")
    private Integer groupId;

    @ApiModelProperty(value = "群内备注")
    private String memo;

    @ApiModelProperty(value = "群内身份 1-群主;2-管路员;3-吃瓜群众")
    private Byte groupIdentity;

    @ApiModelProperty(value = "是否被禁言 0-否;1-是")
    private Boolean anExcuse;

    @ApiModelProperty(value = "创建时间")
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

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Byte getGroupIdentity() {
        return groupIdentity;
    }

    public void setGroupIdentity(Byte groupIdentity) {
        this.groupIdentity = groupIdentity;
    }

    public Boolean getAnExcuse() {
        return anExcuse;
    }

    public void setAnExcuse(Boolean anExcuse) {
        this.anExcuse = anExcuse;
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