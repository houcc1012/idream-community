package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Description :
 * @Created by xiaogang on 2018/5/2.
 */
@ApiModel(value = "APP用户标签信息")
public class AppUserLabelDto {

    @ApiModelProperty("标签Id")
    private Integer id;

    @ApiModelProperty("标签名")
    private String label;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
