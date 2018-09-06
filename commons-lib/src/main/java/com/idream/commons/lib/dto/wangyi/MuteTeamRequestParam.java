package com.idream.commons.lib.dto.wangyi;

import com.idream.commons.lib.dto.token.AuthUserInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/16 15:53
 * @Description:
 */
@ApiModel("修改群组消息提醒开关")
public class MuteTeamRequestParam {

    //用户信息
    @ApiModelProperty(value = "用户信息", hidden = true)
    private AuthUserInfo authUserInfo;

    @ApiModelProperty("群组id")
    @NotBlank(message = "群组id不能为空")
    private String tid;


    @ApiModelProperty("1：关闭消息提醒，2：打开消息提醒，其他值无效 ")
    @NotNull(message = "提醒标识不能为空")
    private Integer ope;

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

    public Integer getOpe() {
        return ope;
    }

    public void setOpe(Integer ope) {
        this.ope = ope;
    }
}

