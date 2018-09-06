package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("用户网易云好友关系")
public class UserWyimFriendRel {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "朋友id")
    private Integer friendUserId;

    @ApiModelProperty(value = "好友备注")
    private String friendMemo;

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

    public Integer getFriendUserId() {
        return friendUserId;
    }

    public void setFriendUserId(Integer friendUserId) {
        this.friendUserId = friendUserId;
    }

    public String getFriendMemo() {
        return friendMemo;
    }

    public void setFriendMemo(String friendMemo) {
        this.friendMemo = friendMemo == null ? null : friendMemo.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}