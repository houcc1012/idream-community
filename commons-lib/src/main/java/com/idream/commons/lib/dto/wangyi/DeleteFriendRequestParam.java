package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/18 23:22
 * @Description:
 */
@ApiModel("删除好友请求参数封装")
public class DeleteFriendRequestParam {

    @ApiModelProperty("用户id")
    @NotNull(message = "用户id不能为空")
    private Integer userId;
    @NotNull(message = "好友id不能为空")
    @ApiModelProperty("要删除的用户id")
    private Integer friendUserId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFriendUserId() {
        return friendUserId;
    }

    public void setFriendUserId(Integer friendUserId) {
        this.friendUserId = friendUserId;
    }
}

