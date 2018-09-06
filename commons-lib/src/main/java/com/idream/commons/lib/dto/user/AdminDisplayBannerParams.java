package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @Author: jeffery
 * @Date: 2018/6/9 9:59
 */
@ApiModel(value = "banner展示状态修改请求参数")
public class AdminDisplayBannerParams {

    @ApiModelProperty(value = "banner主键id")
    @NotNull(message = "bannerId不能为空")
    private Integer bannerId;

    public Integer getBannerId() {
        return bannerId;
    }

    public void setBannerId(Integer bannerId) {
        this.bannerId = bannerId;
    }

}
