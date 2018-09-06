package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 活动操作纪律表
 */
@ApiModel("活动操作纪律表")
public class ActivityOperateRecord {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 操作人id
     */
    @ApiModelProperty(value = "操作人id")
    private Integer userId;

    /**
     * 活动id
     */
    @ApiModelProperty(value = "活动id")
    private Integer activityId;

    /**
     * 1取消操作,2审核失败
     */
    @ApiModelProperty(value = "1取消操作,2审核失败")
    private Byte type;

    /**
     * 分类,操作分类,1取消(下架),2审核
     */
    @ApiModelProperty(value = "分类,操作分类,1取消(下架),2审核")
    private Byte category;

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    private String content;

    /**
     * 生成时间
     */
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

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getCategory() {
        return category;
    }

    public void setCategory(Byte category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}