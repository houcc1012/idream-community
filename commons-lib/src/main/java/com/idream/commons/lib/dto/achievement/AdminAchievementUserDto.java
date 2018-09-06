package com.idream.commons.lib.dto.achievement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("用户成就详情返回模型")
public class AdminAchievementUserDto {
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("昵称")
    private String nickName;

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

    @ApiModelProperty("获取时间")
    private Date createTime;
}
