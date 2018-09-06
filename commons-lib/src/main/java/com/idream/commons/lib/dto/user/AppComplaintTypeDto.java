package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("投诉类型")
public class AppComplaintTypeDto {
    @ApiModelProperty("类型id")
    private Integer typeId;
    @ApiModelProperty("标题")
    private String title;

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public String getTitle() {
        return title;
    }
}
