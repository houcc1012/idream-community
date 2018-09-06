package com.idream.commons.lib.dto.auth;

import java.util.Date;

public class AuthUserVo {
    private String accountName;
    private Integer userId;
    private String nickName;
    private String roleName;
    private Integer roleId;
    private String phone;
    private String password;
    private Date createTime;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public Long getCreateTime() {
        return createTime.getTime();
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getAccountName() {
        return accountName;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getNickName() {
        return nickName;
    }

    public String getRoleName() {
        return roleName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public String getPhone() {
        return phone;
    }


}
