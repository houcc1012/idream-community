package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/19 13:42
 * @Description:
 */
@ApiModel("将好友移除黑名单")
public class RemoveUserFromBlackListRequestParam {

    @ApiModelProperty("好友id")
    @NotNull(message = "好友id不能为空")
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}

