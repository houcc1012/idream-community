package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Author: houcc
 * @Date: 2018/6/6
 */
@ApiModel("查询注销用户数据返回")
public class AdminUserStatisticsDto extends AdminUserStatisticsTemplateDto {
    @ApiModelProperty(value = "注册设备 1-ios ;2-andriod; 3-微信小程序；4-web 5 unkown")
    private int type;
    @ApiModelProperty(value = "累计注册")
    private int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
