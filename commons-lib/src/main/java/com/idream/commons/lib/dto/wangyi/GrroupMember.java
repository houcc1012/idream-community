package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/17 20:14
 * @Description:
 */
@ApiModel("群成员")
public class GrroupMember {

    @ApiModelProperty("userID")
    private Integer userId;
    @ApiModelProperty("性别")
    private Integer gender;
    @ApiModelProperty("头像")
    private String icon;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("网易账户accid")
    private String userAccid;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserAccid() {
        return userAccid;
    }

    public void setUserAccid(String userAccid) {
        this.userAccid = userAccid;
    }
}

