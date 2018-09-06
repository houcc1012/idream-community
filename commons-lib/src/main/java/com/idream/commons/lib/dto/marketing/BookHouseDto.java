package com.idream.commons.lib.dto.marketing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description :
 * @Created by xiaogang on 2018/6/26.
 */
@ApiModel("书屋信息")
public class BookHouseDto {

    @ApiModelProperty(value = "书屋id")
    private Integer bookId;

    @ApiModelProperty(value = "书屋名")
    private String bookName;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
