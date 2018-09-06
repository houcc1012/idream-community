package com.idream.commons.lib.dto;

import com.idream.commons.lib.annotation.SensitiveWordVaild;
import com.idream.commons.lib.dto.token.AuthUserInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;

/**
 * @author hejiang
 */
@ApiModel("公共参数")
public class JSONPublicParam<T> {

    //用户信息
    @ApiModelProperty(value = "用户信息", hidden = true)
    private AuthUserInfo authUserInfo;

    @ApiModelProperty(value = "业务请求参数", hidden = false)
    @Valid
    @SensitiveWordVaild
    private T requestParam;

    public T getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(T requestParam) {
        this.requestParam = requestParam;
    }

    public AuthUserInfo getAuthUserInfo() {
        return authUserInfo;
    }

    public void setAuthUserInfo(AuthUserInfo authUserInfo) {
        this.authUserInfo = authUserInfo;
    }
}
