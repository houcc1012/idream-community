package com.idream.commons.lib.dto.auth;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author charles
 */
public class AuthUserRole {
    private Integer userId;
    @NotBlank(message = "昵称不能为空")
    @Size(max = 15, message = "昵称不能超过15个字符")
    private String nickName;
    @NotBlank(message = "账户不能为空")
    @Size(max = 15, message = "账户不能超过15个字符")
    private String accountName;
    @NotBlank(message = "密码不能为空")
    private String password;
    private Integer roleId;

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }


    public String getPassword() {
        return password;
    }

    public Integer getRoleId() {
        return roleId;
    }
}
