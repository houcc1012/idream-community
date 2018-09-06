package com.idream.commons.lib.dto.admin;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @Author: jeffery
 * @Date: 2018/8/6 15:05
 */
@ApiModel(value = "其他推广 详细数据请求参数")
public class OtherPromotionDetailParams extends PagesParam {
    @ApiModelProperty(value = "地推id")
    @NotNull(message = "地推id不能为空")
    private Integer id;

    @ApiModelProperty(value = "日期")
    private String registerDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

}
