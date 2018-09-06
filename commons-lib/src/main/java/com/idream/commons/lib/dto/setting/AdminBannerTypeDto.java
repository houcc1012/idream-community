package com.idream.commons.lib.dto.setting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author charles
 */
@ApiModel("banner类型")
public class AdminBannerTypeDto {
    @ApiModelProperty("类型id")
    private Integer typeId;
    @ApiModelProperty("类型名称")
    private String typeName;

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
}
