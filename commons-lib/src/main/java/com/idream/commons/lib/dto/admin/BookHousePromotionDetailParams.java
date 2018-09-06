package com.idream.commons.lib.dto.admin;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @Author: jeffery
 * @Date: 2018/8/6 13:14
 */
@ApiModel(value = "书屋推广 详细数据请求参数")
public class BookHousePromotionDetailParams extends PagesParam {
    @ApiModelProperty(value = "书屋id")
    @NotNull(message = "书屋id不能为空")
    private Integer bookId;

    @ApiModelProperty(value = "日期")
    private String registerDate;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }
}
