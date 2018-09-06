package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author hejiang
 * @date 2018/8/30
 */
@ApiModel("设置粉丝关注位值ID")
public class UpdateMyNewFansFlagParams {

    @ApiModelProperty("主键ID")
    public Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
