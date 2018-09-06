package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/14 18:26
 * @Description:
 */
public class UpdateIMUserInfoParams {

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("昵称")
    private String name;

    @ApiModelProperty("图片路径")
    private String icon;

    @ApiModelProperty("性别")
    private String gender;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public UpdateIMUserInfoParams() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

