package com.idream.commons.lib.dto.app;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: jeffery
 * @Date: 2018/7/17 14:33
 */
@ApiModel(value = "app端 添加朋友 输入昵称或手机号模糊查询 请求参数")
public class AddFriendParams extends PagesParam {
    @ApiModelProperty(value = "用户昵称或者手机号")
    private String nickNameOrPhone;

    public String getNickNameOrPhone() {
        return nickNameOrPhone;
    }

    public void setNickNameOrPhone(String nickNameOrPhone) {
        this.nickNameOrPhone = nickNameOrPhone;
    }
}
