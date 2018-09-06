package com.idream.commons.lib.dto.wangyi;

import com.idream.commons.lib.dto.token.AuthUserInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/16 14:20
 * @Description:
 */
@ApiModel("移除管理员")
public class RemoveManagerFromGroupRequestParam {

    //用户信息
    @ApiModelProperty(value = "用户信息", hidden = true)
    private AuthUserInfo authUserInfo;

    @ApiModelProperty("当前群组id. 必填")
    @NotBlank(message = "群组id不能为空")
    private String tid;

    @ApiModelProperty("要移除的管理员的userId. 必填")
    @NotBlank(message = "管理员userId不能为空")
    private String memberUserId;

    public RemoveManagerFromGroupRequestParam() {
    }

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

    public String getMemberUserId() {
        return memberUserId;
    }

    public void setMemberUserId(String memberUserId) {
        this.memberUserId = memberUserId;
    }
}

