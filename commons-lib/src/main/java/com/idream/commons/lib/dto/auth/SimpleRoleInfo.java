package com.idream.commons.lib.dto.auth;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class SimpleRoleInfo {
    private Integer roleId;
    @NotBlank
    @Size(max = 30)
    private String roleName;
    @Size(min = 1, message = "权限不能为空")
    private List<SimplePermissionInfo> permissions;
    private List<SimpleAuthMenu> authMenus;

    public List<SimpleAuthMenu> getAuthMenus() {
        return authMenus;
    }

    public void setAuthMenus(List<SimpleAuthMenu> authMenus) {
        this.authMenus = authMenus;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public List<SimplePermissionInfo> getPermissions() {
        return permissions;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setPermissions(List<SimplePermissionInfo> permissions) {
        this.permissions = permissions;
    }
}
