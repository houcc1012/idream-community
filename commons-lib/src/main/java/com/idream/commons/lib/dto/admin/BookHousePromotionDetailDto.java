package com.idream.commons.lib.dto.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Author: jeffery
 * @Date: 2018/8/6 13:25
 */
@ApiModel(value = "书屋推广 详细数据返回数据")
public class BookHousePromotionDetailDto {
    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "注册时间")
    private Date createTime;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
