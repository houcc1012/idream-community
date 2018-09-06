package com.idream.commons.lib.dto.appactivity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author hejiang
 */
@ApiModel("用户感兴趣的类型请求参数")
public class AppActivityUserLikeTypeRequestDto {
    @ApiModelProperty("类型id")
    private Integer typeId;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}
