package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/11 13:24
 * @Description:
 */
@ApiModel("网易用户获取新token")
public class RefreshTokenResponseDto {

    @ApiModelProperty("返回的token")
    private String token;


    public RefreshTokenResponseDto() {
    }

    public RefreshTokenResponseDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

