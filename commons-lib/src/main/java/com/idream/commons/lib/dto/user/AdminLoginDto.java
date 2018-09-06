package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 后台管理系统admin登录
 *
 * @param
 *
 * @author zhanfeifei
 */
@ApiModel("用户登录返回参数")
public class AdminLoginDto {

    @ApiModelProperty(value = "token", required = true)
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
