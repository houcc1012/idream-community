package com.idream.commons.lib.dto.activity;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @author: ZhengGaosheng
 * @date: 2018/7/19 18:06
 * @description:
 */
public class MiniDisplayActivityByTypeRequestParam extends PagesParam {

    @ApiModelProperty("城市编码")
    @NotBlank(message = "城市编码不能为空")
    private String cityCode;

    @ApiModelProperty("类型id")
    @NotBlank(message = "活动类型id不能为空")
    private String typeId;

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
}

