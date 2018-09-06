package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @author charles
 */
@ApiModel("社区认证操作参数")
public class AdminCommunityAuthOperParams {
    @ApiModelProperty("认证id")
    @NotNull
    private Integer authId;

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }
}
