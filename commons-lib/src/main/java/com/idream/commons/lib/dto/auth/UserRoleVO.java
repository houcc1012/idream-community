package com.idream.commons.lib.dto.auth;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author charles.wei
 */
@ApiModel("用户角色模型")
public class UserRoleVO {
    @ApiModelProperty(value = "用户id")
    private Integer userId;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "角色id")
    private Integer roleId;
    @ApiModelProperty(value = "橘色列表")
    private List<RoleInfo> roles;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<RoleInfo> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleInfo> roles) {
        this.roles = roles;
    }


}
