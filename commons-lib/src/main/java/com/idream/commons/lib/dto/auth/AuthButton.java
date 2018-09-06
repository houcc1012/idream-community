package com.idream.commons.lib.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author charles.wei
 */
@ApiModel("权限button类")
public class AuthButton {
    @ApiModelProperty(value = "主键")
    private int id;
    @ApiModelProperty(value = "权限名称")
    private String name;
    @ApiModelProperty(value = "菜单id")
    private int menuId;
    @ApiModelProperty(value = "是否选中")
    private boolean selected = false;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

}
