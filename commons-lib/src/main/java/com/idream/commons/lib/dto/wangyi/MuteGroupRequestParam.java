package com.idream.commons.lib.dto.wangyi;

import com.idream.commons.lib.dto.token.AuthUserInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/16 20:39
 * @Description:
 */
@ApiModel("禁言(解禁)群组")
public class MuteGroupRequestParam {

    //用户信息
    @ApiModelProperty(value = "用户信息", hidden = true)
    private AuthUserInfo authUserInfo;

    @ApiModelProperty("群组id , 必填")
    @NotNull(message = "群组id不能为空")
    private Integer tid;

    @ApiModelProperty("禁言类型 0:解除禁言，1:禁言普通成员 3:禁言整个群(包括群主) ")
    private Integer muteType;

    public MuteGroupRequestParam() {
    }

    public AuthUserInfo getAuthUserInfo() {
        return authUserInfo;
    }

    public void setAuthUserInfo(AuthUserInfo authUserInfo) {
        this.authUserInfo = authUserInfo;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getMuteType() {
        return muteType;
    }

    public void setMuteType(Integer muteType) {
        this.muteType = muteType;
    }
}

