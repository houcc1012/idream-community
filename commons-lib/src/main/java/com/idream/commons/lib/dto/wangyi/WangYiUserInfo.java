package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/15 17:12
 * @Description:
 */
public class WangYiUserInfo {


    @ApiModelProperty("用户accid")
    private String accid;
    @ApiModelProperty("用户注册成功返回的token")
    private String token;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("头像")
    private String icon;
    @ApiModelProperty("该用户是否被禁言. true:禁言. false:正常")
    private boolean banFlag;

    @ApiModelProperty("性别")
    private String gender;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public WangYiUserInfo() {
    }

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public boolean isBanFlag() {
        return banFlag;
    }

    public void setBanFlag(boolean banFlag) {
        this.banFlag = banFlag;
    }
}

