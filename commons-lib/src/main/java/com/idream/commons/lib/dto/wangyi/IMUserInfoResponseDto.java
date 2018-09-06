package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/14 15:20
 * @Description:
 */
@ApiModel("获取用户信息返回封装")
public class IMUserInfoResponseDto {

    @ApiModelProperty("个人签名")
    private String sign;

    @ApiModelProperty("头像路径")
    private String icon;

    @ApiModelProperty("生日")
    private Date birth;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("用户accid")
    private String accid;

    @ApiModelProperty("昵称")
    private String name;

    @ApiModelProperty("性别")
    private int gender;

    @ApiModelProperty("手机号")
    private String mobile;

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Date getBirth() {
        return birth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public String getAccid() {
        return accid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getGender() {
        return gender;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

}

