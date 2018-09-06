package com.idream.commons.lib.dto.integration;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author hejiang
 */
@ApiModel("查询用户签到积分详情")
public class FindUserIntergrationInfoDto {

    @ApiModelProperty(value = "用户积分统计")
    private int intergrationCount;

    @ApiModelProperty(value = "用户持续签到天数")
    private int continuedSignDay;

    @ApiModelProperty(value = "用户当天是否签到 0-否 ；1-是")
    private Byte todayIsSign;

    public Byte getTodayIsSign() {
        return todayIsSign;
    }

    public void setTodayIsSign(Byte todayIsSign) {
        this.todayIsSign = todayIsSign;
    }

    public int getIntergrationCount() {
        return intergrationCount;
    }

    public void setIntergrationCount(int intergrationCount) {
        this.intergrationCount = intergrationCount;
    }

    public int getContinuedSignDay() {
        return continuedSignDay;
    }

    public void setContinuedSignDay(int continuedSignDay) {
        this.continuedSignDay = continuedSignDay;
    }

}
