package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "查看邀请信息返回参数")
public class FindInvitationResponseDto {

    @ApiModelProperty(value = "邀请人手机号")
    private String phone;
    @ApiModelProperty(value = "邀请人昵称")
    private String nickName;
    @ApiModelProperty(value = "被邀请人手机号")
    private String acceptPhone;
    @ApiModelProperty(value = "被邀请人昵称")
    private String acceptNickName;
    @ApiModelProperty(value = "被邀请人报名时间")
    private Date time;

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

    public String getAcceptPhone() {
        return acceptPhone;
    }

    public void setAcceptPhone(String acceptPhone) {
        this.acceptPhone = acceptPhone;
    }

    public String getAcceptNickName() {
        return acceptNickName;
    }

    public void setAcceptNickName(String acceptNickName) {
        this.acceptNickName = acceptNickName;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
