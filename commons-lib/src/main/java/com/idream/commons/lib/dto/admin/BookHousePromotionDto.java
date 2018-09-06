package com.idream.commons.lib.dto.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: jeffery
 * @Date: 2018/8/6 10:32
 */
@ApiModel(value = "书屋推广 返回参数")
public class BookHousePromotionDto {
    @ApiModelProperty(value = "书屋id")
    private Integer bookId;

    @ApiModelProperty(value = "书屋名称")
    private String bookHouseName;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "今日新增")
    private Integer nowAddCount;

    @ApiModelProperty(value = "累计扫码注册")
    private Integer registerCount;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookHouseName() {
        return bookHouseName;
    }

    public void setBookHouseName(String bookHouseName) {
        this.bookHouseName = bookHouseName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getNowAddCount() {
        return nowAddCount;
    }

    public void setNowAddCount(Integer nowAddCount) {
        this.nowAddCount = nowAddCount;
    }

    public Integer getRegisterCount() {
        return registerCount;
    }

    public void setRegisterCount(Integer registerCount) {
        this.registerCount = registerCount;
    }
}
