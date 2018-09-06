package com.idream.commons.lib.dto.appactivity;

import javax.validation.constraints.NotNull;

import com.idream.commons.lib.dto.PagesParam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("发布活动圈请求参数")
public class AppActivityGroupRequestDto extends PagesParam {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("活动id")
    @NotNull(message = "活动id不能为空")
    private Integer activityId;
    @ApiModelProperty(hidden = true)
    private Integer authUserId;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getAuthUserId() {
        return authUserId;
    }

    public void setAuthUserId(Integer authUserId) {
        this.authUserId = authUserId;
    }

}
