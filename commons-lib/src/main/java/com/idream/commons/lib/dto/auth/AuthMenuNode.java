package com.idream.commons.lib.dto.auth;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("权限菜单")
public class AuthMenuNode extends TreeNode {
    @ApiModelProperty(value = "菜单名")
    private String text;
    @ApiModelProperty(value = "标签")
    private String icon;
    @ApiModelProperty(value = "子菜单")
    private List<AuthMenuNode> children = new ArrayList<>();
    @ApiModelProperty(value = "是否选中")
    private boolean selected = false;
    @ApiModelProperty(value = "菜单按钮")
    private List<AuthButton> buttons = new ArrayList<>();


    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public List<AuthButton> getButtons() {
        return buttons;
    }

    public void setButtons(List<AuthButton> buttons) {
        this.buttons = buttons;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<AuthMenuNode> getChildren() {
        return children;
    }

    public void setChildren(List<AuthMenuNode> children) {
        this.children = children;
    }


}
