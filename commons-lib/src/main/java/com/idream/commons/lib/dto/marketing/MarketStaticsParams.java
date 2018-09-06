package com.idream.commons.lib.dto.marketing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @Author: houcc
 * @Date: 2018/6/6
 */
@ApiModel("查询营销数据请求参数")
public class MarketStaticsParams {
    @ApiModelProperty(value = " 请求类型 1-抽奖次数 ;2-兑奖; 3-开奖次数")
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
