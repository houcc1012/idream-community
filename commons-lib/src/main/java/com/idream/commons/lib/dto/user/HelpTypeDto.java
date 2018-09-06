package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 帮助类型表
 */
@ApiModel("所有帮助类型")
public class HelpTypeDto {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "标题")
    private String typeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

}