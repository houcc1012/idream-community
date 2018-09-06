package com.idream.commons.lib.dto.setting;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author hejiang
 * @date 2018/8/28
 */
@ApiModel("敏感字列表查询请求参数")
public class SearchSensitiveWordsParams extends PagesParam {

    @ApiModelProperty(value = "敏感字", required = false)
    private String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
