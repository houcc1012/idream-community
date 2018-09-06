package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/11 10:34
 * @Description:
 */
@ApiModel("网易用户注册返回封装")
public class CreateUserResponseDto {

    @ApiModelProperty("用户accid")
    private String accid;
    @ApiModelProperty("用户注册成功返回的token")
    private String token;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("头像")
    private String icon;
    @ApiModelProperty("性别")
    private String gender;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public CreateUserResponseDto() {
    }

    public CreateUserResponseDto(String accid, String token) {
        this.accid = accid;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}

