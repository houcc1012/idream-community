package com.idream.commons.lib.model;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 */
@ApiModel("用户点赞维护表")
public class EgisLikeRecord {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Integer userId;

    /**
     * 维护点赞记录id
     */
    @ApiModelProperty(value = "维护点赞记录id")
    private Integer likeRecordId;

    /**
     * 清空id
     */
    @ApiModelProperty(value = "清空id")
    private Integer emptyId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getLikeRecordId() {
        return likeRecordId;
    }

    public void setLikeRecordId(Integer likeRecordId) {
        this.likeRecordId = likeRecordId;
    }

    public Integer getEmptyId() {
        return emptyId;
    }

    public void setEmptyId(Integer emptyId) {
        this.emptyId = emptyId;
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