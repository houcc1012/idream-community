package com.idream.commons.lib.dto.activity;

import com.idream.commons.lib.dto.token.AuthUserInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @author: ZhengGaosheng
 * @date: 2018/7/17 13:50
 * @description:
 */
@ApiModel("用户搜藏活动请求参数")
public class ActivityCollectionRequestParam {

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

