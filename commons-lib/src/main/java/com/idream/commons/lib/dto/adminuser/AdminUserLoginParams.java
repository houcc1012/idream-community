package com.idream.commons.lib.dto.adminuser;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @author hejiang
 */
@ApiModel("管理端用户登陆请求参数")
public class AdminUserLoginParams {

    @ApiModelProperty(value = "账号")
    @NotBlank(message = "账号不能为空")
    private String accountName;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
