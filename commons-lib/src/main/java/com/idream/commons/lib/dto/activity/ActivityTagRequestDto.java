package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
/**
 * 活动标签库的请求参数
 * @author zhanfeifei
 * @param
 * @return
 * 2018年4月12日
 *
 */
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "活动标签库的请求参数")
public class ActivityTagRequestDto {

    @ApiModelProperty(value = "标签名")
    private String label;

    @ApiModelProperty(value = "标签属性 1 | 2")
    //一级标签 | 二级标签
    private Integer type;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
