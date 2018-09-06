package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Description :
 * @Created by xiaogang on 2018/5/2.
 */
@ApiModel(value = "用户的信息")
public class AppNeighborInfoDto {

    @ApiModelProperty("用户Id")
    private Integer userId;

    @ApiModelProperty("用户昵称")
    private String nickName;

    @ApiModelProperty("用户头像")
    private String image;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
