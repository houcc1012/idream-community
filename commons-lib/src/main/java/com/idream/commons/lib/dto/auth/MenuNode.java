package com.idream.commons.lib.dto.auth;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("用户菜单")
public class MenuNode extends TreeNode {
    @ApiModelProperty(value = "icon")
    String icon;
    @ApiModelProperty(value = "菜单名")
    String title;
    @ApiModelProperty(value = "链接")
    String href;
    @ApiModelProperty(value = "是否有子集")
    boolean spread = false;
    @ApiModelProperty(value = "子菜单")
    List<MenuNode> children = new ArrayList<>();

    public List<MenuNode> getChildren() {
        return children;
    }

    public void setChildren(List<MenuNode> children) {
        this.children = children;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public boolean isSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }

}
