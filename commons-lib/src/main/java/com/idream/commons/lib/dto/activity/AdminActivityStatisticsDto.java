package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: houcc
 * @Date: 2018/6/6
 */
@ApiModel("查询活动相关数据返回")
public class AdminActivityStatisticsDto {
    @ApiModelProperty(value = "请求类型")
    private int type;
    @ApiModelProperty(value = "数量")
    private int count;
    @ApiModelProperty(value = "日期")
    private String date;
    @ApiModelProperty(value = "累计总量")
    private int total;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
