package com.idream.commons.lib.dto.admin;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @Author: jeffery
 * @Date: 2018/8/23 17:27
 */
@ApiModel(value = "后台管理 书屋列表 活动详情请求参数")
public class BookHouseListActivityDetailParams extends PagesParam{
    @ApiModelProperty(value = "书屋id")
    @NotNull(message = "书屋id不能为空")
    private Integer bookId;

    @ApiModelProperty(value = "活动主题")
    private String title;

    @ApiModelProperty(value = "活动类型")
    private Integer activityType;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getActivityType() {
        return activityType;
    }

    public void setActivityType(Integer activityType) {
        this.activityType = activityType;
    }
}
