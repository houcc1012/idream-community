package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 生活类型
 */
@ApiModel("邻里生活类型")
public class AppLifeTypeDto {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "类别名")
    private String typeName;

    @ApiModelProperty(value = "图标")
    private String icon;
    @ApiModelProperty(value = "点亮图标")
    private String iconLight;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getIconLight() {
        return iconLight;
    }

    public void setIconLight(String iconLight) {
        this.iconLight = iconLight;
    }
}