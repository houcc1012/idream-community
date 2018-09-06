package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description : 是否
 * @Created by xiaogang on 2018/5/4.
 */
@ApiModel(value = "是否公用类")
public class AppWhetherDto {

    @ApiModelProperty(value = "是/否")
    private boolean whether;

    public boolean isWhether() {
        return whether;
    }

    public void setWhether(boolean whether) {
        this.whether = whether;
    }
}
