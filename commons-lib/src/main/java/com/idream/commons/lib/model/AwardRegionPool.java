package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class AwardRegionPool {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "兑奖池id")
    private Integer awardPoolId;

    @ApiModelProperty(value = "书屋ID,默认-1表示平台通用券")
    private Integer bookId;

    @ApiModelProperty(value = "兑换积分")
    private Integer exchangeScore;

    @ApiModelProperty(value = "奖品数量")
    private Integer count;

    @ApiModelProperty(value = "状态 1上架2下架")
    private Byte status;

    @ApiModelProperty(value = "每日兑换次数(每天)")
    private Short exchangeCount;

    @ApiModelProperty(value = "总计兑换次数 -1,表示无限次数")
    private Short totalExchangeConut;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "生成时间")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAwardPoolId() {
        return awardPoolId;
    }

    public void setAwardPoolId(Integer awardPoolId) {
        this.awardPoolId = awardPoolId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getExchangeScore() {
        return exchangeScore;
    }

    public void setExchangeScore(Integer exchangeScore) {
        this.exchangeScore = exchangeScore;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Short getExchangeCount() {
        return exchangeCount;
    }

    public void setExchangeCount(Short exchangeCount) {
        this.exchangeCount = exchangeCount;
    }

    public Short getTotalExchangeConut() {
        return totalExchangeConut;
    }

    public void setTotalExchangeConut(Short totalExchangeConut) {
        this.totalExchangeConut = totalExchangeConut;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}