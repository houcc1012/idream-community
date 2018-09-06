package com.idream.commons.lib.dto.wangyi;

import com.idream.commons.lib.dto.token.AuthUserInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/16 13:17
 * @Description:
 */
@ApiModel("群组添加管理员请求参数封装")
public class AddGroupManagerRequestParam {

    //用户信息
    @ApiModelProperty(value = "用户信息", hidden = true)
    private AuthUserInfo authUserInfo;

    @ApiModelProperty(value = "群组id,必填")
    @NotBlank(message = "群组id不能为空")
    private String tid;

    @ApiModelProperty("群成员的id,必填")
    @NotBlank(message = "群组成员userId")
    private String memberUserId;

    public AddGroupManagerRequestParam() {
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

