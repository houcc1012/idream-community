package com.idream.commons.lib.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author charles.wei
 */
@ApiModel("权限信息")
public class PermissionInfo {
    @ApiModelProperty(value = "编码")
    private String code;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "链接")
    private String uri;
    @ApiModelProperty(value = "菜单id")
    private String menuId;
    @ApiModelProperty(value = "链接类型")
    private String method;
    @ApiModelProperty(value = "权限类型,menu或button")
    private String type;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
