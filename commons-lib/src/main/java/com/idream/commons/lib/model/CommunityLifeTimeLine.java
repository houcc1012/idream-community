package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class CommunityLifeTimeLine {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "邻里圈id")
    private Integer lifeId;

    @ApiModelProperty(value = "用户id 用户ID和社区ID不能同时为空")
    private Integer userId;

    @ApiModelProperty(value = "社区id")
    private Integer communityId;

    @ApiModelProperty(value = "发布时间")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLifeId() {
        return lifeId;
    }

    public void setLifeId(Integer lifeId) {
        this.lifeId = lifeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}