package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author hejiang
 */
@ApiModel("app微信登录请求参数")
public class AppWeiChatLoginParams {

    @ApiModelProperty(value = "微信登录Code", required = true)
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
