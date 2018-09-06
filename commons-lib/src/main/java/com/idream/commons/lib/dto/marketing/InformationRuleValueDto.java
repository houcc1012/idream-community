package com.idream.commons.lib.dto.marketing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel("选填信息项最新值")
public class InformationRuleValueDto {

    @ApiModelProperty(value = "code")
    private String infoCode;

    @ApiModelProperty(value = "itemValue")
    private String itemValue;

    public String getInfoCode() {
        return infoCode;
    }

    public void setInfoCode(String infoCode) {
        this.infoCode = infoCode;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }
}