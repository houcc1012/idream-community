package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

public class IntegrationRegionPool {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "积分抽奖池ID")
    private Integer integrationPoolId;

    @ApiModelProperty(value = "书屋ID,默认-1表示平台通用券")
    private Integer bookId;

    @ApiModelProperty(value = "数量")
    private Integer count;

    @ApiModelProperty(value = "概率")
    private BigDecimal probability;

    @ApiModelProperty(value = "状态 1-上架,2-下架")
    private Byte status;

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

    public Integer getIntegrationPoolId() {
        return integrationPoolId;
    }

    public void setIntegrationPoolId(Integer integrationPoolId) {
        this.integrationPoolId = integrationPoolId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getProbability() {
        return probability;
    }

    public void setProbability(BigDecimal probability) {
        this.probability = probability;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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