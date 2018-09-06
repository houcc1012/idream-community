package com.idream.commons.lib.dto.marketing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Description :
 * Created by DELL2018-003 on 2018/4/12.
 */

@ApiModel("开奖时间明细")
public class LotteryTimeInfoDto {

    @ApiModelProperty(value = "开始时间")
    private Date startDate;

    @ApiModelProperty(value = "结束展示时间")
    private Date endDate;

    @ApiModelProperty(value = "开奖时间点")
    private List<LotteryTimeDto> times;


    public List<LotteryTimeDto> getTimes() {
        return times;
    }

    public void setTimes(List<LotteryTimeDto> times) {
        this.times = times;
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
}
