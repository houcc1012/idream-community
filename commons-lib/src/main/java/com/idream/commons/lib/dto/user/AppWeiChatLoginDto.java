package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author hejiang
 */
@ApiModel("app微信登录响应参数")
public class AppWeiChatLoginDto {

    @ApiModelProperty(value = "token", required = true)
    private String token;

    @ApiModelProperty(value = "是否绑定手机号 1-是;0-否", required = true)
    private Byte bindingPhone;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Byte getBindingPhone() {
        return bindingPhone;
    }

    public void setBindingPhone(Byte bindingPhone) {
        this.bindingPhone = bindingPhone;
    }
}
