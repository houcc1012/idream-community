package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: houcc
 * @Date: 2018/6/6
 */
@ApiModel("查询注销用户数据返回")
public class AdminUserStatisticsTemplateDto {
    @ApiModelProperty(value = "注册用户数量")
    private int count;
    @ApiModelProperty(value = "注册日期")
    private String date;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
