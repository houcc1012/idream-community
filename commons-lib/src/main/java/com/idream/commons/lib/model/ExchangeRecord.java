package com.idream.commons.lib.model;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户奖券兑换记录表
 */
@ApiModel("用户奖券兑换记录表")
public class ExchangeRecord {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 经办人id
     */
    @ApiModelProperty(value = "经办人id")
    private Integer authUserId;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Integer userId;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phone;

    /**
     * 奖品名称
     */
    @ApiModelProperty(value = "奖品名称")
    private String awardName;

    /**
     * 券码id
     */
    @ApiModelProperty(value = "券码id")
    private Integer couponId;

    /**
     * 券码
     */
    @ApiModelProperty(value = "券码")
    private String couponCode;

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

    public Integer getauthUserId() {
        return authUserId;
    }

    public void setauthUserId(Integer authUserId) {
        this.authUserId = authUserId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName == null ? null : awardName.trim();
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