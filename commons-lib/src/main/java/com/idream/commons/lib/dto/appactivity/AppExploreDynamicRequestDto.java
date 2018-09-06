package com.idream.commons.lib.dto.appactivity;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotNull;

/**
 * @Auther: penghekai
 * @Date: 2018/7/19 10:39
 * @Description:
 */

@ApiModel("发现页查看类型动态列表请求参数")
public class AppExploreDynamicRequestDto extends PagesParam {

    @ApiModelProperty(value = "业务id(动态id)", required = true)
    @NotNull(message = "业务id不能为空")
    private Integer businessId;

    @ApiModelProperty(value = "用户id", hidden = true)
    private Integer authUserId;

    @ApiModelProperty(value = "类型id", hidden = true)
    private Integer typeId;

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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }
}

