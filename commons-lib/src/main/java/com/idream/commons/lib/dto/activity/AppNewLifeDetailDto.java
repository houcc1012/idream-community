package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Description :
 * @Created by xiaogang on 2018/5/2.
 */
@ApiModel(value = "其他动态详情")
public class AppNewLifeDetailDto {

    @ApiModelProperty("动态ID")
    private Integer id;

    @ApiModelProperty("用户Id")
    private Integer userId;

    @ApiModelProperty("用户头像")
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
