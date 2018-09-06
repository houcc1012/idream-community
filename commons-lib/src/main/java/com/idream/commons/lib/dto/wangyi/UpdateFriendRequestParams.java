package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/14 23:41
 * @Description:
 */
@ApiModel("给好友修改备注")
public class UpdateFriendRequestParams {

    @ApiModelProperty("好友的userId. 必填")
    @NotBlank(message = "好友userId不能为空")
    private String friendUserId;
    @ApiModelProperty("给好友的备注. 必填")
    @NotBlank(message = "给好友的备注不能为空")
    private String alias;

    public UpdateFriendRequestParams() {
    }

    public String getFriendUserId() {
        return friendUserId;
    }

    public void setFriendUserId(String friendUserId) {
        this.friendUserId = friendUserId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}

