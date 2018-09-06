package com.idream.commons.lib.dto.marketing;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @Author: houcc
 * @Date: 2018/6/6
 */
@ApiModel("查询营销数据请求参数")
public class MarketDetailStaticsParams extends PagesParam {
    @ApiModelProperty(value = " 请求类型 0全部 1-未兑奖 ;2-已兑奖; 3-已过期")
    private int convertStatus;
    @ApiModelProperty(value = "开始时间 格式为20151211")
    @NotNull(message = "开始时间不能为空")
    private String startTime;
    @ApiModelProperty(value = "结束时间 格式为20151211")
    @NotNull(message = "结束时间不能为空")
    private String endTime;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "兑奖书屋")
    private String exchangeBookName;

    public int getConvertStatus() {
        return convertStatus;
    }

    public void setConvertStatus(int convertStatus) {
        this.convertStatus = convertStatus;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getExchangeBookName() {
        return exchangeBookName;
    }

    public void setExchangeBookName(String exchangeBookName) {
        this.exchangeBookName = exchangeBookName;
    }
}
