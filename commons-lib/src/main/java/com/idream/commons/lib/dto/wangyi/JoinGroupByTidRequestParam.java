package com.idream.commons.lib.dto.wangyi;

import com.idream.commons.lib.dto.token.AuthUserInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @Author: jeffery
 * @Date: 2018/7/26 13:55
 */
@ApiModel("用户参加群组(通过tid)")
public class JoinGroupByTidRequestParam {
    @ApiModelProperty(value = "用户ID", hidden = true)
    private AuthUserInfo authUserInfo;
    @ApiModelProperty("当前趣聊群id")
    @NotBlank(message = "趣聊群id不能为空")
    private String tid;

    public AuthUserInfo getAuthUserInfo() {
        return authUserInfo;
    }

    public void setAuthUserInfo(AuthUserInfo authUserInfo) {
        this.authUserInfo = authUserInfo;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }
}
