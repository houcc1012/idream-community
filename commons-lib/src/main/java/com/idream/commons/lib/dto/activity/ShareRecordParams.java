package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
/**
 * @author charles
 */
@ApiModel("活动分享积分入参")
public class ShareRecordParams {
    @ApiModelProperty("活动id")
    @NotNull(message = "活动id不能为空")
    private Integer activityId;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }
}
