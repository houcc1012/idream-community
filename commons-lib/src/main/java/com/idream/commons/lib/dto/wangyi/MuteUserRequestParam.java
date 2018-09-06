package com.idream.commons.lib.dto.wangyi;

import com.idream.commons.lib.dto.token.AuthUserInfo;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/16 16:44
 * @Description:
 */
public class MuteUserRequestParam {

    //用户信息
    @ApiModelProperty(value = "用户信息", hidden = true)
    private AuthUserInfo authUserInfo;

    @ApiModelProperty("群组id. 必填")
    @NotBlank(message = "群组id不能为空")
    private String tid;

    @ApiModelProperty("要禁言用户的userId/")
    private String userId;

    @ApiModelProperty("1-禁言, 0-解禁 . 必填")
    @NotNull(message = "mute不能为空")
    private Integer mute;


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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getMute() {
        return mute;
    }

    public void setMute(Integer mute) {
        this.mute = mute;
    }
}

