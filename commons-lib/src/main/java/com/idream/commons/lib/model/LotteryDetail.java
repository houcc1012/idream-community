package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 现场开奖明细池
 */
@ApiModel("现场开奖明细池")
public class LotteryDetail {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 抽奖活动id
     */
    @ApiModelProperty(value = "抽奖活动id")
    private Integer lotteryId;

    /**
     * 开奖类型:1口令2点击
     */
    @ApiModelProperty(value = "开奖类型:1口令2点击")
    private Integer type;

    /**
     * 口令
     */
    @ApiModelProperty(value = "口令")
    private String speakSecret;

    /**
     * 开始时间,年月日
     */
    @ApiModelProperty(value = "开始时间,年月日")
    private Date startDate;

    /**
     * 开始时间,结束时间
     */
    @ApiModelProperty(value = "开始时间,时分秒")
    private Date startTime;

    /**
     * 结束日期,年月日
     */
    @ApiModelProperty(value = "结束日期,年月日")
    private Date endDate;

    /**
     * 结束时间,时分秒
     */
    @ApiModelProperty(value = "结束时间,时分秒 ")
    private Date endTime;

    /**
     * 0不限制,单次抽奖次数
     */
    @ApiModelProperty(value = "0不限制,单次抽奖次数")
    private Integer lotteryCount;

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

    public Integer getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(Integer lotteryId) {
        this.lotteryId = lotteryId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSpeakSecret() {
        return speakSecret;
    }

    public void setSpeakSecret(String speakSecret) {
        this.speakSecret = speakSecret == null ? null : speakSecret.trim();
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getLotteryCount() {
        return lotteryCount;
    }

    public void setLotteryCount(Integer lotteryCount) {
        this.lotteryCount = lotteryCount;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}