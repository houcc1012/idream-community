package com.idream.commons.lib.dto.admin;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @Author: jeffery
 * @Date: 2018/6/26 16:23
 */
@ApiModel(value = "小程序发现页 热门城市跳发现页")
public class HotCityParams extends PagesParam {

    @ApiModelProperty(value = "热门城市编码")
    @NotBlank(message = "城市编码不能为空")
    private String cityCode;

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

}
