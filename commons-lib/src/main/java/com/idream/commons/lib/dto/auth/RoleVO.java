package com.idream.commons.lib.dto.auth;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author charles.wei
 */
@Api("角色信息")
public class RoleVO {
    @ApiModelProperty(value = "roleId")
    private Integer id;
    @ApiModelProperty(value = "角色名")
    private String name;
    @ApiModelProperty(value = "权限菜单")
    private List<AuthMenuNode> authMenus;

    public List<AuthMenuNode> getAuthMenus() {
        return authMenus;
    }

    public void setAuthMenus(List<AuthMenuNode> authMenus) {
        this.authMenus = authMenus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
