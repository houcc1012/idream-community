package com.idream.commons.lib.dto.user;

import com.idream.commons.lib.dto.auth.MenuNode;
import com.idream.commons.lib.dto.auth.SimpleAuthMenu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author hejiang
 */
@ApiModel("管理端用户返回")
public class AdminUserLoginDto {

    @ApiModelProperty(value = "token")
    private String token;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "菜单权限")
    private List<SimpleAuthMenu> authMenus;


    public List<SimpleAuthMenu> getAuthMenus() {
        return authMenus;
    }

    public void setAuthMenus(List<SimpleAuthMenu> authMenus) {
        this.authMenus = authMenus;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }


}
