package com.idream.commons.lib.dto.admin;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @Author: jeffery
 * @Date: 2018/8/24 13:17
 */
@ApiModel(value = "书屋列表 抽奖奖品设置请求参数")
public class IntegrationDetailParams extends PagesParam{
    @ApiModelProperty(value = "书屋id")
    @NotNull(message = "书屋id不能为空")
    private Integer bookId;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
}
