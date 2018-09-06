package com.idream.commons.lib.dto.wangyi;

import com.idream.commons.lib.dto.token.AuthUserInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/17 18:14
 * @Description:
 */
@ApiModel("用户参加群组")
public class JoinGroupRequestParam {

    @ApiModelProperty(value = "用户ID", hidden = true)
    private Integer userId;
    @ApiModelProperty("当前活动id")
    @NotNull(message = "活动id不能为空")
    private Integer activityId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }
}

