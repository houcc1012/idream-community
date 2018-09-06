package com.idream.commons.lib.dto.marketing;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author hejiang
 */
public class WeChatLotteryDetailDto {

    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "开奖活动状态 1-进行中;2-未开始;3-已结束")
    private Byte status;

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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
