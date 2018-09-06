package com.idream.commons.lib.dto.appactivity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("活动类型查询返回参数")
public class AppActivityTypeResponseDto {

    @ApiModelProperty("主键id")
    private Integer typeId;
    @ApiModelProperty("类别名字")
    private String typeName;
    @ApiModelProperty("图标")
    private String icon;

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

}
