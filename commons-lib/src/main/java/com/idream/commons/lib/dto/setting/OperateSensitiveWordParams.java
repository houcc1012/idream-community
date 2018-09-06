package com.idream.commons.lib.dto.setting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @author hejiang
 * @date 2018/8/28
 */
@ApiModel("操作敏感字列表查询请求参数")
public class OperateSensitiveWordParams {

    @ApiModelProperty(value = "主键编号， 新增时不传", required = true)
    private Integer id;

    @ApiModelProperty(value = "敏感字", required = true)
    @NotNull(message = "敏感字不能为空")
    private String word;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
