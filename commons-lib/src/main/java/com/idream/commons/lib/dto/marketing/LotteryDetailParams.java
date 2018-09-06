package com.idream.commons.lib.dto.marketing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Description :
 * Created by DELL2018-003 on 2018/4/12.
 */
@ApiModel("抽奖口令")
public class LotteryDetailParams {

    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "抽奖id", hidden = true)
    private Integer lotteryId;

    @ApiModelProperty(value = "开奖类型:1口令2点击", hidden = true)
    private Integer type;

    @ApiModelProperty(value = "口令")
    private String speakSecret;

    @ApiModelProperty(value = "持续时间")
    @NotNull(message = "持续时间不能为空！")
    @Max(value = 1440, message = "最大不能超过1440分钟")
    private Integer lastTime;

    @ApiModelProperty(value = "开始时间")
    @NotNull(message = "开始时间不能为空！")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "开始日期")
    private Date startDate;

    @ApiModelProperty(value = "结束日期")
    private Date endDate;

    @ApiModelProperty(value = "0不限制,单次抽奖次数")
    @NotNull(message = "抽奖次数不能为空！")
    @Min(0)
    private Integer lotteryCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(Integer lotteryId) {
        this.lotteryId = lotteryId;
    }

    public Integer getLastTime() {
        return lastTime;
    }

    public void setLastTime(Integer lastTime) {
        this.lastTime = lastTime;
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
