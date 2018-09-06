package com.idream.commons.lib.dto.token;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 后台管理系统登录
 *
 * @param
 *
 * @author zhanfeifei
 */
@ApiModel("用户信息")
@ApiIgnore
public class AuthAdminInfo {

    //用户主键
    @ApiModelProperty(value = "用户主键", required = true)
    private int authUserId;

    //昵称
    @ApiModelProperty(value = "用户名", required = true)
    private String userName;

    //姓名
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    //电话
    @ApiModelProperty(value = "状态", required = true)
    private Integer status;

    public int getauthUserId() {
        return authUserId;
    }

    public void setauthUserId(int authUserId) {
        this.authUserId = authUserId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


}
