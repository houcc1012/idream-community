package com.idream.commons.lib.model;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 邻里点赞记录
 */
@ApiModel("邻里点赞记录")
public class CommunityLifeLikeRecord {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 生活主人id
     */
    @ApiModelProperty(value = "生活主人id")
    private Integer ownerId;

    /**
     * 生活id
     */
    @ApiModelProperty(value = "生活id")
    private Integer lifeId;

    /**
     * 点赞用户id
     */
    @ApiModelProperty(value = "点赞用户id")
    private Integer userId;

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

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
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