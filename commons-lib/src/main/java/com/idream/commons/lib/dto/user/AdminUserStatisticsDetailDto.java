package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @Author: houcc
 * @Date: 2018/6/6
 */
@ApiModel("查询注销用户数据返回")
public class AdminUserStatisticsDetailDto {
    @ApiModelProperty(value = "注册日期")
    private String date;
    @ApiModelProperty(value = "设备类型")
    @NotNull(message = "设备类型不能为空")
    private Integer type;
    @ApiModelProperty(value = "注册ip")
    private String ip;
    @ApiModelProperty(value = "注册城市")
    private String city;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "昵称")
    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
