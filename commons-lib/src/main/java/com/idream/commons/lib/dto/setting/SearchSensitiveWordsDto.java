package com.idream.commons.lib.dto.setting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author hejiang
 * @date 2018/8/28
 */
@ApiModel("敏感字列表查询返回参数")
public class SearchSensitiveWordsDto {

    @ApiModelProperty(value = "主键编号", required = true)
    private Integer id;

    @ApiModelProperty(value = "敏感字", required = true)
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
