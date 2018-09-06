package com.idream.commons.lib.dto.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @Author: jeffery
 * @Date: 2018/6/26 16:10
 */
@ApiModel(value = "小程序发现页 热门城市跳转发现页")
public class HotCityRequestParams {

    @ApiModelProperty(value = "热门城市编码")
    @NotBlank(message = "城市编码不能为空")
    private String cityCode;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
