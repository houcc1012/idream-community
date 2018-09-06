package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 用户积分记录表
 */
@ApiModel("用户积分记录表")
public class IntegrationRecord {
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
     * 分数
     */
    @ApiModelProperty(value = "分数")
    private Integer score;

    /**
     * 1获取,2使用
     */
    @ApiModelProperty(value = "1获取,2使用")
    private Byte type;

    /**
     * 业务id
     */
    @ApiModelProperty(value = "业务id")
    private Integer businessId;

    /**
     * 获得类型,1签到,2打卡,3抽奖,4兑换,5成就
     */
    @ApiModelProperty(value = "获得类型,1签到,2打卡,3抽奖,4兑换,5成就")
    private Byte fromType;

    /**
     * 渠道描述
     */
    @ApiModelProperty(value = "渠道描述")
    private String fromDescription;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Byte getFromType() {
        return fromType;
    }

    public void setFromType(Byte fromType) {
        this.fromType = fromType;
    }

    public String getFromDescription() {
        return fromDescription;
    }

    public void setFromDescription(String fromDescription) {
        this.fromDescription = fromDescription == null ? null : fromDescription.trim();
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