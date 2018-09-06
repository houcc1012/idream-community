package com.idream.commons.lib.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @Author: jeffery
 * @Date: 2018/7/17 11:03
 */
@ApiModel(value = "附近活动趣聊 请求参数")
public class NeighborChatParams {

    @ApiModelProperty(value = "城市编码")
    @NotBlank(message = "请前往系统设置允许风和邻里在使用应用期间内访问您的位置信息，有助于您更好的使用风和邻里")
    private String cityCode;

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

}
