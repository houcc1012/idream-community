package com.idream.commons.lib.dto.user;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author hejiang
 */
@ApiModel("查询后台用户列表请求参数")
public class AdminUserListParams extends PagesParam {

    @ApiModelProperty(value = "账号名称", required = false)
    private String accountName;

    @ApiModelProperty(value = "权限ID", required = false)
    private String roleId;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
