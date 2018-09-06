package com.idream.commons.lib.dto.activity;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @Auther: penghekai
 * @Date: 2018/8/14 16:51
 * @Description:
 */
@ApiModel("活动动态请求参数")
public class AppActivityDynamicRequestDto extends PagesParam{
    @ApiModelProperty(value = "业务id(动态id)", required = true)
    @NotNull(message = "业务id不能为空")
    private Integer businessId;

    @ApiModelProperty(value = "用户id", hidden = true)
    private Integer authUserId;

    @ApiModelProperty(value = "活动id", hidden = true)
    private Integer activityId;

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Integer getAuthUserId() {
        return authUserId;
    }

    public void setAuthUserId(Integer authUserId) {
        this.authUserId = authUserId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }
}

