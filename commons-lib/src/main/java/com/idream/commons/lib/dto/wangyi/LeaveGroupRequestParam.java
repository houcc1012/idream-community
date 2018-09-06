package com.idream.commons.lib.dto.wangyi;

import com.idream.commons.lib.dto.token.AuthUserInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/17 00:13
 * @Description:
 */
@ApiModel("主动退群")
public class LeaveGroupRequestParam {


    @ApiModelProperty("活动id")
    @NotNull(message = "活动id不能为空")
    private Integer activityId;

    @ApiModelProperty(value = "用户ID", hidden = true)
    private AuthUserInfo authUserInfo;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public AuthUserInfo getAuthUserInfo() {
        return authUserInfo;
    }

    public void setAuthUserInfo(AuthUserInfo authUserInfo) {
        this.authUserInfo = authUserInfo;
    }

}

