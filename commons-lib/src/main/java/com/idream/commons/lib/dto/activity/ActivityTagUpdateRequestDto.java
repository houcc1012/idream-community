package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

/**
 * @Auther: penghekai
 * @Date: 2018/6/20 13:57
 * @Description:
 */
@ApiModel("活动标签修改请求参数")
public class ActivityTagUpdateRequestDto {

    @ApiModelProperty("id")
    private Integer id;

    @Length(max = 6, message = "标签名不超过6个字符")
    @ApiModelProperty("标签名")
    private String label;

    @ApiModelProperty("标签级别(1 一级，2二级，3三级)")
    private Integer level;

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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}

