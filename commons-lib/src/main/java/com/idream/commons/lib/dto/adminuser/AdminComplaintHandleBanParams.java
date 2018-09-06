package com.idream.commons.lib.dto.adminuser;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author charles
 */
@ApiModel("禁言操作参数")
public class AdminComplaintHandleBanParams {
    @NotNull(message = "用户id不能为空")
    @ApiModelProperty("禁言用户id")
    private Integer userId;
    @ApiModelProperty("天数")
    @NotNull(message = "天数不能为空")
    @Min(value = 1, message = "天数必须大于0")
    private Integer days;

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getDays() {
        return days;
    }
}
