package com.idream.commons.lib.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by DELL2018-005 on 2018/5/18.
 */
@ApiModel("维护点赞记录表")
public class EgisLikeRecordDto {

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "维护点赞记录id")
    private Integer likeRecordId;

    @ApiModelProperty(value = "清空id")
    private Integer emptyId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

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

    public Integer getEmptyId() {
        return emptyId;
    }

    public void setEmptyId(Integer emptyId) {
        this.emptyId = emptyId;
    }
}
