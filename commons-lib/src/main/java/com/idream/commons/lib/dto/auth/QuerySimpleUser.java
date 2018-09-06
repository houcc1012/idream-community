package com.idream.commons.lib.dto.auth;

import com.idream.commons.lib.dto.PagesParam;

public class QuerySimpleUser extends PagesParam {
    private String accountName;
    private Integer roleId;

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getAccountName() {
        return accountName;
    }

    public Integer getRoleId() {
        return roleId;
    }
}
