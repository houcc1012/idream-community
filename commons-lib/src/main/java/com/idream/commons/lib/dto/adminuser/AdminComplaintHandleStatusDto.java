package com.idream.commons.lib.dto.adminuser;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("处理状态")
public class AdminComplaintHandleStatusDto {
    @ApiModelProperty("处理类型,禁言:ban")
    private String code;
    @ApiModelProperty("总天数")
    private Integer totalDays;
    @ApiModelProperty("持续天数")
    private Integer durationDays;
    @ApiModelProperty("状态结束")
    private Boolean finished;

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setTotalDays(Integer totalDays) {
        this.totalDays = totalDays;
    }

    public void setDurationDays(Integer durationDays) {
        this.durationDays = durationDays;
    }

    public String getCode() {
        return code;
    }

    public Integer getTotalDays() {
        return totalDays;
    }

    public Integer getDurationDays() {
        return durationDays;
    }
}
