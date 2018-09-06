package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author: ZhengGaosheng
 * @date: 2018/6/20 16:27
 * @description:
 */
@ApiModel(value = "type=2, 最近一次活动开始的时间")
public class ActivityRecentOneMapperDto {

    @ApiModelProperty("开始时间")
    private Date startTime;
    @ApiModelProperty("结束时间")
    private Date endTime;

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

