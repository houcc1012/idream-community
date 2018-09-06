package com.idream.service;

import com.idream.commons.lib.dto.adminuser.AdminUserLoginParams;
import com.idream.commons.lib.dto.user.AdminUserLoginDto;

/**
 * @author hejiang
 */
public interface UserLoginService {

    /**
     * 后台用户登录
     *
     * @param params
     */
    AdminUserLoginDto adminUserLogin(AdminUserLoginParams params);

    /**
     * @Author: hejiang
     * @Description: 管理端用户退出
     * @Date: 10:33 2018/4/13
     */
    void adminUserLogout(Integer authUserId);

    /**
     * 中台用户登录
     *
     * @param params
     * @param remoteIP
     * @param deviceType
     */
    AdminUserLoginDto userLogin(AdminUserLoginParams params, String remoteIP, Byte deviceType);

    /**
     * 中台用户退出登录
     *
     * @param authUserId
     */
    void userLogout(Integer authUserId);
}
