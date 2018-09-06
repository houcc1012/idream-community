package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author hejiang
 */
@ApiModel("用户登录返回参数")
public class UserRegisterDto {

    @ApiModelProperty(value = "unionId", required = true)
    private String unionId;

    @ApiModelProperty(value = "openId", required = true)
    private String openId;

    @ApiModelProperty(value = "token", required = true)
    private String token;

    @ApiModelProperty(value = "用户是否绑定过手机号 0-否；1-是", required = true)
    private Byte bindingPhone;

    public Byte getBindingPhone() {
        return bindingPhone;
    }

    public void setBindingPhone(Byte bindingPhone) {
        this.bindingPhone = bindingPhone;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
