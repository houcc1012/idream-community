package com.idream.commons.lib.dto.activity;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @Description :
 * @Created by xiaogang on 2018/5/2.
 */
@ApiModel(value = "动态列表查询参数")
public class AppDynamicParam extends PagesParam {

    @ApiModelProperty("1为邻友，2为关注")
    @NotNull(message = "查询类型不能为空")
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
