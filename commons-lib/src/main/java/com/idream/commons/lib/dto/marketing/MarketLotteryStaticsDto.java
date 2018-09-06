package com.idream.commons.lib.dto.marketing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Author: houcc
 * @Date: 2018/6/6
 */
@ApiModel("查询营销现场开奖明细数据返回")
public class MarketLotteryStaticsDto {
    @ApiModelProperty(value = "手机号")
    private String  phone;
    @ApiModelProperty(value = "姓名")
    private String nickName;
    @ApiModelProperty(value = "地址")
    private String address;
    @ApiModelProperty(value = "中奖时间")
    private Date awardTime;
    @ApiModelProperty(value = "中奖奖品")
    private String awardName;
    @ApiModelProperty(value = "有效期")
    private Date expireTime;
    @ApiModelProperty(value = "状态 ")
    private Integer convertStatus;
    @ApiModelProperty(value = "兑奖时间")
    private Date exchangeTime;
    @ApiModelProperty(value = "兑奖地点")
    private String exchangeBookName;
    @ApiModelProperty(value = "奖池")
    private String awardPool;

    public String getAwardPool() {
        return awardPool;
    }

    public void setAwardPool(String awardPool) {
        this.awardPool = awardPool;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getAwardTime() {
        return awardTime;
    }

    public void setAwardTime(Date awardTime) {
        this.awardTime = awardTime;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Integer getConvertStatus() {
        return convertStatus;
    }

    public void setConvertStatus(Integer convertStatus) {
        this.convertStatus = convertStatus;
    }

    public Date getExchangeTime() {
        return exchangeTime;
    }

    public void setExchangeTime(Date exchangeTime) {
        this.exchangeTime = exchangeTime;
    }

    public String getExchangeBookName() {
        return exchangeBookName;
    }

    public void setExchangeBookName(String exchangeBookName) {
        this.exchangeBookName = exchangeBookName;
    }
}
