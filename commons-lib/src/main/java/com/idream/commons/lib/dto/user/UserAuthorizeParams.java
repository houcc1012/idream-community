package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author hejiang
 */
@ApiModel("用户授权请求参数")
public class UserAuthorizeParams {

    @ApiModelProperty(value = "用户编号", required = true)
    private int userId;

    @ApiModelProperty(value = "姓名", required = true)
    private String realName;

    @ApiModelProperty(value = "身份证", required = true)
    private String identity;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}
