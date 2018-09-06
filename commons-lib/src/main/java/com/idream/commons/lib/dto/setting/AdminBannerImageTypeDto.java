package com.idream.commons.lib.dto.setting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author charles
 */
@ApiModel("banner图片类型")
public class AdminBannerImageTypeDto {
    @ApiModelProperty("类型id")
    private Byte typeId;
    @ApiModelProperty("类型名称")
    private String typeName;

    public Byte getTypeId() {
        return typeId;
    }

    public void setTypeId(Byte typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
