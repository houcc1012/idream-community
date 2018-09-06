package com.idream.service;

import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.auth.AuthUserRole;
import com.idream.commons.lib.dto.auth.AuthUserVo;
import com.idream.commons.lib.dto.auth.QuerySimpleUser;

/**
 * @author hejiang
 */
public interface AdminUserService {


    /**
     * @param user
     */
    void addAdminUser(AuthUserRole user);

    /**
     * @param user
     */
    void updateAdminUser(AuthUserRole user);


    /**
     * 查询管理者列表
     *
     * @param query
     */
    PagesDto<AuthUserVo> listAuthUsers(QuerySimpleUser query);

    /**
     * 检查用户名是否重复
     *
     * @param authUserId
     * @param accountName
     */
    boolean checkAccountName(Integer authUserId, String accountName);

    /**
     * 删除账户
     *
     * @param authUserId
     * @param userId
     */
    void deleteAccount(Integer authUserId, Integer userId);
}
