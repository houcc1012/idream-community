package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @Author: houcc
 * @Date: 2018/6/6
 */
@ApiModel("查询活动数据请求参数")
public class AdminActivityStatisticsParams {
    @ApiModelProperty(value = " 请求类型 1-活动浏览量 ;2-活动报名数; 3-活动打卡数；4-活动邀请数 5 活动留言数")
    private int type;
    @ApiModelProperty(value = "开始时间 格式为20151211")
    @NotNull(message = "开始时间不能为空")
    private String startTime;
    @ApiModelProperty(value = "结束时间 格式为20151211")
    @NotNull(message = "结束时间不能为空")
    private String endTime;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
