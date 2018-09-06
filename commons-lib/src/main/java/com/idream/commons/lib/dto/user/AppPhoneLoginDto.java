package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author hejiang
 */
@ApiModel("app手机登录响应参数")
public class AppPhoneLoginDto {

    @ApiModelProperty(value = "token", required = true)
    private String token;

    public AppPhoneLoginDto() {
    }

    public AppPhoneLoginDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
