package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 书屋列表
 *
 * @param
 *
 * @author zhanfeifei
 */
@ApiModel(value = "书屋列表")
public class BookHouseListDto {

    @ApiModelProperty(value = "书屋名")
    private String value;

    @ApiModelProperty(value = "书屋id")
    private Integer bookHouseId;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getBookHouseId() {
        return bookHouseId;
    }

    public void setBookHouseId(Integer bookHouseId) {
        this.bookHouseId = bookHouseId;
    }


}
