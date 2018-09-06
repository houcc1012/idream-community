package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by DELL2018-005 on 2018/6/2.
 */
@ApiModel(value = "新增用户标签,返回所有一级标签")
public class AdminAllFirstUserTagDto {

    @ApiModelProperty(value = "一级标签id")
    private Integer id;

    @ApiModelProperty(value = "一级标签名")
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
