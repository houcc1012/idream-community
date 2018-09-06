package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: penghekai
 * @Date: 2018/6/30 17:28
 * @Description:
 */
public class RegionGroupInfoExtention {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "名称")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

