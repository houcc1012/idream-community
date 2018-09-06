package com.idream.commons.lib.dto.marketing;

import com.idream.commons.lib.model.CouponCollection;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * 现场中奖纪录表
 */
@ApiModel("现场中奖纪录表")
public class LotteryWinRecordDto {

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String name;

    /**
     * 抽奖id
     */
    @ApiModelProperty(value = "抽奖id")
    private Integer lotteryId;

    /**
     * 奖池id
     */
    @ApiModelProperty(value = "奖池id")
    private Integer poolId;

    /**
     * 奖品名
     */
    @ApiModelProperty(value = "奖品名")
    private String awardName;

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
     * 券id
     */
    @ApiModelProperty(value = "券id")
    private Integer couponId;

    @ApiModelProperty(value = "券码")
    private String couponCode;


    /**
     * 开奖时间
     */
    @ApiModelProperty(value = "开奖时间")
    private Date lotteryTime;

    /**
     * 兑奖时间
     */
    @ApiModelProperty(value = "兑奖时间")
    private Date exchangeTime;

    /**
     * 默认1未兑奖,2已兑奖
     */
    @ApiModelProperty(value = "默认1未兑奖,2已兑奖")
    private Integer status;

    @ApiModelProperty(value = "查证信息")
    private List<CouponCollection> couponCollection;

    public Integer getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(Integer lotteryId) {
        this.lotteryId = lotteryId;
    }

    public Integer getPoolId() {
        return poolId;
    }

    public void setPoolId(Integer poolId) {
        this.poolId = poolId;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName == null ? null : awardName.trim();
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

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Date getLotteryTime() {
        return lotteryTime;
    }

    public void setLotteryTime(Date lotteryTime) {
        this.lotteryTime = lotteryTime;
    }

    public Date getExchangeTime() {
        return exchangeTime;
    }

    public void setExchangeTime(Date exchangeTime) {
        this.exchangeTime = exchangeTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public List<CouponCollection> getCouponCollection() {
        return couponCollection;
    }

    public void setCouponCollection(List<CouponCollection> couponCollection) {
        this.couponCollection = couponCollection;
    }
}