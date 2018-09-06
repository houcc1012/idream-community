package com.idream.commons.lib.dto.activity;

import com.idream.commons.lib.dto.token.AuthUserInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author: ZhengGaosheng
 * @date: 2018/6/30 09:40
 * @description:
 */
@ApiModel("用户参加活动请求参数封装")
public class UserJoinActivityRequestDto {


    @ApiModelProperty("城市编码")
    @NotNull(message = "城市编码不能为空")
    private Integer cityCode;

    @ApiModelProperty("活动id")
    @NotNull(message = "活动id不能为空")
    private Integer activityId;

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }
}

