package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * 保存邀请记录中间表
 *
 * @author idream
 *         描述： 自动生成的代码
 */
@ApiModel("保存邀请记录中间表")
public class ShareInfoDto {

    @ApiModelProperty(value = "活动ID")
    @NotNull(message = "活动id不能为空")
    private Integer activityId;

    @ApiModelProperty(value = "分享人Id")
    @NotNull(message = "分享人Id不能为空")
    private Integer sharedUserId;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getSharedUserId() {
        return sharedUserId;
    }

    public void setSharedUserId(Integer sharedUserId) {
        this.sharedUserId = sharedUserId;
    }
}
