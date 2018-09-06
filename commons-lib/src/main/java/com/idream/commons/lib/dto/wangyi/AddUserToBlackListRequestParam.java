package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/19 13:36
 * @Description:
 */
@ApiModel("将用户加入黑名单")
public class AddUserToBlackListRequestParam {

    @ApiModelProperty("用户id")
    @NotNull(message = "用户id不能为空")
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}

