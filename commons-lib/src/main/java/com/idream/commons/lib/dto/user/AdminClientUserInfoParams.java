package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author houcc
 */
@ApiModel("修改账号和密码请求参数")
public class AdminClientUserInfoParams {

    @ApiModelProperty(value = "账号名称", required = true)
    @NotBlank(message = "accountName不能为空")
    @Length(max = 15, message = "账户最大不能超过15位")
    private String accountName;

    @ApiModelProperty(value = "密码", required = true)
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
