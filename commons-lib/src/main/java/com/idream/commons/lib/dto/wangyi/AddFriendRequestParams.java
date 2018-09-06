package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.naming.MalformedLinkException;
import javax.validation.constraints.NotNull;

import static javafx.scene.input.KeyCode.M;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/14 22:55
 * @Description:
 */
@ApiModel("添加好友")
public class AddFriendRequestParams {

    @ApiModelProperty("当前用户id")
    @NotNull(message = "用户id不能为空")
    private Integer userId;
    @ApiModelProperty("好友id")
    @NotNull(message = "好友用户id不能为空...")
    private Integer friendUserId;

    public Integer getFriendUserId() {
        return friendUserId;
    }

    public void setFriendUserId(Integer friendUserId) {
        this.friendUserId = friendUserId;
    }

    public AddFriendRequestParams() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}

