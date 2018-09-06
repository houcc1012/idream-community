package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 活动取消记录表
 */
@ApiModel("活动取消记录表")
public class ActivityCancelRecord {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 活动id
     */
    @ApiModelProperty(value = "活动id")
    private Integer activityId;

    /**
     * 取消内容
     */
    @ApiModelProperty(value = "取消内容")
    private String content;

    /**
     * 操作人id
     */
    @ApiModelProperty(value = "操作人id")
    private Integer userId;

    /**
     * 操作人
     */
    @ApiModelProperty(value = "操作人")
    private String realName;

    /**
     * 生成日期
     */
    @ApiModelProperty(value = "生成日期")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}