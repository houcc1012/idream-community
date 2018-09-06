package com.idream.commons.lib.dto.information;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("用户年龄")
public class InformationUserAgeDto {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "名称")
    private String value;

    @ApiModelProperty(value = "编码")
    private String code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
