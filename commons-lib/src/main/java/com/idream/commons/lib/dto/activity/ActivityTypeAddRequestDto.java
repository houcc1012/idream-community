package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @Auther: penghekai
 * @Date: 2018/6/11 15:06
 * @Description:
 */
@ApiModel("新增活动类型请求参数")
public class ActivityTypeAddRequestDto {

    @NotNull(message = "属性不能为空")
    @ApiModelProperty(value = "活动类型属性,1 主类|2 辅类")
    private byte kind;

    @Length(max = 6, message = "不超过6个字符")
    @ApiModelProperty("类名")
    private String name;

    @ApiModelProperty("图标置灰")
    private String icon;

    @ApiModelProperty("图标高亮")
    private String iconLight;

    @ApiModelProperty("背景")
    private String background;

    @ApiModelProperty(value = "描述")
    private String description;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public byte getKind() {
        return kind;
    }

    public void setKind(byte kind) {
        this.kind = kind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconLight() {
        return iconLight;
    }

    public void setIconLight(String iconLight) {
        this.iconLight = iconLight;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

