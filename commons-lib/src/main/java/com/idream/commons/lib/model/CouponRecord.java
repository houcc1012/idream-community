package com.idream.commons.lib.model;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 奖券记录表
 */
@ApiModel("奖券记录表")
public class CouponRecord {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 奖券id
     */
    @ApiModelProperty(value = "奖券id")
    private Integer couponId;

    /**
     * 券码
     */
    @ApiModelProperty(value = "券码")
    private String couponCode;

    /**
     * 奖券途径:1获得,2使用
     */
    @ApiModelProperty(value = "奖券途径:1获得,2使用")
    private Integer type;

    /**
     * 渠道类型
     */
    @ApiModelProperty(value = "渠道类型")
    private Integer fromType;

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

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode == null ? null : couponCode.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getFromType() {
        return fromType;
    }

    public void setFromType(Integer fromType) {
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