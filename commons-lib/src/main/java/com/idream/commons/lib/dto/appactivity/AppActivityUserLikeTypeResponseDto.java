package com.idream.commons.lib.dto.appactivity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: penghekai
 * @Date: 2018/7/17 18:49
 * @Description:
 */
@ApiModel("用户感兴趣的类型")
public class AppActivityUserLikeTypeResponseDto {

    @ApiModelProperty("类型id")
    private Integer typeId;
    @ApiModelProperty("类型名称")
    private String typeName;
    @ApiModelProperty("图标")
    private String icon;
    @ApiModelProperty("高亮图片")
    private String iconLight;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconLight() {
        return iconLight;
    }

    public void setIconLight(String iconLight) {
        this.iconLight = iconLight;
    }
}

