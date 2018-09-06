package com.idream.commons.lib.dto.user;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @Author: houcc
 * @Date: 2018/6/6
 */
@ApiModel("查询注册用户数据请求参数")
public class AdminUserStatisticsDetailParams extends PagesParam {
    @ApiModelProperty(value = "注册设备 1-ios ;2-andriod; 3-微信小程序；4-web 5 unkown")
    private int type;
    @ApiModelProperty(value = "开始时间 格式为20151211")
    @NotNull(message = "开始时间不能为空")
    private String startTime;
    @ApiModelProperty(value = "结束时间 格式为20151211")
    @NotNull(message = "结束时间不能为空")
    private String endTime;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
