package com.idream.service.impl;

import com.alibaba.fastjson.JSON;
import com.idream.commons.db.redis.RedisCache;
import com.idream.commons.db.redis.RedisKeyConstants;
import com.idream.commons.db.token.JWTTokenService;
import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.adminuser.AdminUserLoginParams;
import com.idream.commons.lib.dto.auth.PermissionInfo;
import com.idream.commons.lib.dto.token.AuthUserInfo;
import com.idream.commons.lib.dto.user.AdminUserLoginDto;
import com.idream.commons.lib.enums.UserEnum;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.mapper.AdminAccountMapper;
import com.idream.commons.lib.mapper.UserAccountMapper;
import com.idream.commons.lib.mapper.UserInfoMapper;
import com.idream.commons.lib.mapper.UserLoginRecordMapper;
import com.idream.commons.lib.model.AdminAccount;
import com.idream.commons.lib.model.UserAccount;
import com.idream.commons.lib.model.UserInfo;
import com.idream.commons.lib.util.AESClientUtils;
import com.idream.service.AuthenticationService;
import com.idream.service.UserLoginService;
import com.idream.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hejiang
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {
    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    //账号锁定判断次数
    @Value("${admin.account.lock.no}")
    private int lockNos;

    //账号锁定时长
    @Value("${admin.account.lock.expire}")
    private int lockExpire;

    @Resource
    private AdminAccountMapper adminAccountMapper;

    @Resource
    private RedisCache redisCache;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private JWTTokenService jwtTokenService;

    @Autowired
    private AuthenticationService authenticationService;

    @Resource
    private UserLoginRecordMapper userLoginRecordMapper;

    @Resource
    private UserAccountMapper userAccountMapper;

    @Resource
    private UserService userService;

    @Override
    public AdminUserLoginDto adminUserLogin(AdminUserLoginParams params) {
        //根据账号名获取账号信息
        AdminAccount account = adminAccountMapper.selectByAccountName(params.getAccountName());
        if (account == null) {
            logger.error("账号不存在，params=" + JSON.toJSONString(params));
            throw new BusinessException(RetCodeConstants.ACCOUNT_NAME_NOT_EXIST, "账号不存在！");
        }
        //查询用户
        UserInfo user = userInfoMapper.selectByPrimaryKey(account.getUserId());
        if (user == null || !UserEnum.UserType.MANAGE_WEB_USER.getCode().equals(user.getUserType())) {
            logger.error("用户信息不存在，params=" + JSON.toJSONString(params));
            throw new BusinessException(RetCodeConstants.ACCOUNTNAME_OR_PASSEORD_ERROR, "用户账号不存在！");
        }

        String lockKey = account.getId() + "";
        //判断账号是否锁定状态
       /* if(UserEnum.AdminAccountStatus.FORZEN.getCode().equals(account.getStatus())){
            //账号锁定，需要判断是否已经到锁定时间
            if (isLockTime(lockKey, RedisKeyConstants.ADMIN_ACCOUNT_LOCK)) {
                throw new BusinessException(RetCodeConstants.LOGIN_FAIL_ACCOUNT_LOCK, "登陆失败，账号已被锁定！");
            } else {
                //已经超过锁定时间，解锁
                releaseLock(lockKey, RedisKeyConstants.ADMIN_ACCOUNT_LOCK);
                //修改账号状态
                adminAccountMapper.updateAccountStatusById(account.getId(), UserEnum.AdminAccountStatus.NORMAL.getCode());
            }
        }*/
        //校验账号状态
        if (UserEnum.AdminAccountStatus.NOT_OPEN.getCode().equals(account.getStatus())) {
            logger.error("账号状态异常，params=" + JSON.toJSONString(params));
            throw new BusinessException(RetCodeConstants.ACCOUNT_STATUS_EXCEPTION, "账号状态异常！");
        }
        //校验密码
        String decrptPwd;
        try {
            decrptPwd = AESClientUtils.aesDecrypt(account.getPassword());
        } catch (Exception e) {
            logger.error("密码解密失败", e);
            throw new BusinessException(RetCodeConstants.ACCOUNTNAME_OR_PASSEORD_ERROR, "登陆失败，请稍后重试！");
        }
        if (!decrptPwd.equals(params.getPassword())) {
            logger.error("密码错误，params=" + JSON.toJSONString(params));
            //密码校验失败，需要记录失败次数
            if (needLockAccount(lockKey, RedisKeyConstants.ADMIN_ACCOUNT_LOCK)) {
                //修改账号状态
                adminAccountMapper.updateAccountStatusById(account.getId(), UserEnum.AdminAccountStatus.FORZEN.getCode());
                throw new BusinessException(RetCodeConstants.ACCOUNT_STATUS_EXCEPTION, "账号或密码错误，您的账号已被锁定！");
            } else {
                String no = redisCache.getString(lockKey, RedisKeyConstants.ADMIN_ACCOUNT_LOCK);
                String message = "账号或密码错误, 再输入错误" + (lockNos - Integer.parseInt(no)) + "次账号将被锁定！";
                throw new BusinessException(RetCodeConstants.ACCOUNTNAME_OR_PASSEORD_ERROR, message);
            }
        }
        //密码验证通过，删除redis中的锁定次数
        releaseLock(lockKey, RedisKeyConstants.ADMIN_ACCOUNT_LOCK);

        //存储权限菜单信息
        storePermission(user.getId());
        //返回数据
        AdminUserLoginDto dto = new AdminUserLoginDto();
        //组装登陆成功返回需要的token
        AuthUserInfo userInfo = getAuthUserInfo(user);
        dto.setToken(jwtTokenService.generateToken(userInfo));
        dto.setNickName(user.getNickName());
        dto.setAuthMenus(authenticationService.listSimpleAuthMenusByUserId(user.getId()));
        return dto;
    }

    private AuthUserInfo getAuthUserInfo(UserInfo user) {
        AuthUserInfo userInfo = new AuthUserInfo();
        userInfo.setNickName(user.getNickName());
        userInfo.setGender(user.getGender() + "");
        userInfo.setUserId(user.getId());
        userInfo.setPhone(user.getPhone());
        userInfo.setUserRole(user.getUserRole());
        userInfo.setUserType(UserEnum.UserType.MANAGE_WEB_USER.getCode());
        return userInfo;
    }


    @Override
    public AdminUserLoginDto userLogin(AdminUserLoginParams params, String remoteIP, Byte deviceType) {
        //根据账号名获取账号信息
        UserAccount account = userAccountMapper.selectByAccountName(params.getAccountName());
        if (account == null) {
            logger.error("账号不存在，params=" + JSON.toJSONString(params));
            throw new BusinessException(RetCodeConstants.ACCOUNT_NAME_NOT_EXIST, "账号不存在！");
        }
        UserInfo user = userInfoMapper.selectByPrimaryKey(account.getUserId());
        if (user == null || !UserEnum.UserType.MOBILE_USER.getCode().equals(user.getUserType())) {
            logger.error("用户信息不存在，params=" + JSON.toJSONString(params));
            throw new BusinessException(RetCodeConstants.ACCOUNTNAME_OR_PASSEORD_ERROR, "用户账号不存在！");
        }

        String lockKey = account.getId() + "";
        //判断账号是否锁定状态
        if (UserEnum.AdminAccountStatus.FORZEN.getCode().equals(account.getStatus())) {
            //账号锁定，需要判断是否已经到锁定时间
            if (isLockTime(lockKey, RedisKeyConstants.ADMIN_ACCOUNT_LOCK)) {
                throw new BusinessException(RetCodeConstants.LOGIN_FAIL_ACCOUNT_LOCK, "登陆失败，账号已被锁定！");
            } else {
                //已经超过锁定时间，解锁
                releaseLock(lockKey, RedisKeyConstants.ADMIN_ACCOUNT_LOCK);
                //修改账号状态
                userAccountMapper.updateAccountStatusById(account.getId(), UserEnum.AdminAccountStatus.NORMAL.getCode());
            }
        }
        //校验账号状态
        if (UserEnum.AdminAccountStatus.NOT_OPEN.getCode().equals(account.getStatus())) {
            logger.error("账号状态异常，params=" + JSON.toJSONString(params));
            throw new BusinessException(RetCodeConstants.ACCOUNT_STATUS_EXCEPTION, "账号状态异常！");
        }
        //校验密码
        String decrptPwd;
        try {
            decrptPwd = AESClientUtils.aesDecrypt(account.getPassword());
        } catch (Exception e) {
            logger.error("密码解密失败", e);
            throw new BusinessException(RetCodeConstants.ACCOUNTNAME_OR_PASSEORD_ERROR, "登陆失败，请稍后重试！");
        }
        if (!decrptPwd.equals(params.getPassword())) {
            logger.error("密码错误，params=" + JSON.toJSONString(params));
            //密码校验失败，需要记录失败次数
            if (needLockAccount(lockKey, RedisKeyConstants.USER_ACCOUNT_LOCK)) {
                //修改账号状态
                userAccountMapper.updateAccountStatusById(account.getId(), UserEnum.AdminAccountStatus.FORZEN.getCode());
                throw new BusinessException(RetCodeConstants.ACCOUNT_STATUS_EXCEPTION, "账号或密码错误，您的账号已被锁定！");
            } else {
                String no = redisCache.getString(lockKey, RedisKeyConstants.USER_ACCOUNT_LOCK);
                String message = "账号或密码错误, 再输入错误" + (lockNos - Integer.parseInt(no)) + "次账号将被锁定！";
                throw new BusinessException(RetCodeConstants.ACCOUNTNAME_OR_PASSEORD_ERROR, message);
            }
        }
        //密码验证通过，删除redis中的锁定次数
        releaseLock(lockKey, RedisKeyConstants.USER_ACCOUNT_LOCK);

        //记录登录信息
        userService.insertUserLoginRecord(remoteIP, deviceType, null, user.getId());

        //存储权限菜单信息
        storePermission(user.getId());

        //组装登陆成功返回需要的token
        AuthUserInfo userInfo = UserServiceImpl.getAuthUserInfo(user);
        //返回数据
        AdminUserLoginDto dto = new AdminUserLoginDto();
        dto.setToken(jwtTokenService.generateToken(userInfo));
        dto.setNickName(user.getNickName());
        return dto;
    }


    private void storePermission(Integer userId) {
        List<PermissionInfo> list = authenticationService.listPermissionByUserId(userId);
        List<PermissionInfo> basis = authenticationService.listBasisPermission();
        list.addAll(basis);
        redisCache.setObject(userId.toString(), list, RedisKeyConstants.AUTH_PERMISSION);
    }

    /**
     * 判断账号是否在锁定期内
     *
     * @param lockKey
     */
    private boolean isLockTime(String lockKey, String type) {
        if (redisCache.exists(lockKey, type)) {
            int no = Integer.parseInt(redisCache.getString(lockKey, type));
            if (no >= lockNos) {
                return true;
            }
        }
        return false;
    }

    /**
     * 释放锁定的计数
     *
     * @param lockKey
     */
    private void releaseLock(String lockKey, String type) {
        redisCache.del(lockKey, type);
    }

    /**
     * 判断是否要锁定账号
     *
     * @param lockKey
     */
    private boolean needLockAccount(String lockKey, String type) {
        long number = 1;
        if (!redisCache.exists(lockKey, type)) {
            redisCache.setString(lockKey, number + "", type, lockExpire);
        } else {
            number = redisCache.incr(lockKey, type);
        }
        if (number >= lockNos) {
            return true;
        }
        return false;
    }

    @Override
    public void adminUserLogout(Integer authUserId) {
        String token = redisCache.hget(RedisKeyConstants.AUTH_TOKEN_REL, authUserId + "-" + UserEnum.UserType.MANAGE_WEB_USER.getCode());
        redisCache.del(token, RedisKeyConstants.AUTH_TOKEN);
    }

    @Override
    public void userLogout(Integer authUserId) {
        String token = redisCache.hget(RedisKeyConstants.AUTH_TOKEN_REL, authUserId + "-" + UserEnum.UserType.MOBILE_USER.getCode());
        redisCache.del(token, RedisKeyConstants.AUTH_TOKEN);
    }
}
