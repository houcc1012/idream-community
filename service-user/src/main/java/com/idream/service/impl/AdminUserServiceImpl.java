package com.idream.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.auth.AuthUserRole;
import com.idream.commons.lib.dto.auth.AuthUserVo;
import com.idream.commons.lib.dto.auth.QuerySimpleUser;
import com.idream.commons.lib.enums.UserEnum;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.mapper.AdminAccountMapper;
import com.idream.commons.lib.mapper.SysUserRoleRelationMapper;
import com.idream.commons.lib.mapper.UserInfoMapper;
import com.idream.commons.lib.model.AdminAccount;
import com.idream.commons.lib.model.SysUserRoleRelation;
import com.idream.commons.lib.model.UserInfo;
import com.idream.commons.lib.util.AESClientUtils;
import com.idream.service.AdminUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static org.bouncycastle.asn1.ua.DSTU4145NamedCurves.params;

/**
 * @author hejiang
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {

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
    private UserInfoMapper userInfoMapper;

    @Resource
    private SysUserRoleRelationMapper sysUserRoleRelationMapper;


    /**
     * @param user
     */
    @Override
    public void addAdminUser(AuthUserRole user) {
        //新增用户
        UserInfo userInfo = new UserInfo();
        userInfo.setNickName(user.getNickName());
        Date date = new Date();
        userInfo.setUpdateTime(date);
        userInfo.setCreateTime(date);
        userInfo.setUserType(UserEnum.UserType.MANAGE_WEB_USER.getCode());
        userInfo.setUserRole(UserEnum.UserRoleEnum.MANAGEER.getCode());
        userInfoMapper.insertSelective(userInfo);

        //新增权限关联
        SysUserRoleRelation roleRelation = new SysUserRoleRelation();
        roleRelation.setUpdateTime(date);
        roleRelation.setUserId(userInfo.getId());
        roleRelation.setCreateTime(date);
        roleRelation.setRoleId(user.getRoleId());
        sysUserRoleRelationMapper.insertSelective(roleRelation);

        //新增账号
        AdminAccount account = adminAccountMapper.selectByAccountName(user.getAccountName());
        if (null != account) {
            logger.error("账号已存在！params=" + JSON.toJSONString(user));
            throw new BusinessException(RetCodeConstants.ERROR_NO_USER, "账号已存在！");
        }
        account = new AdminAccount();
        account.setCreateTime(date);

        checkPassword(user.getPassword());
        String passwordStr;
        try {
            passwordStr = AESClientUtils.aesEncrypt(user.getPassword());
        } catch (Exception e) {
            logger.error("密码加密失败！params=" + JSON.toJSONString(user));
            throw new BusinessException(RetCodeConstants.ERROR_NO_USER, "新增管理员失败，请稍后重试！");
        }
        account.setPassword(passwordStr);
        account.setUserId(userInfo.getId());
        account.setStatus(UserEnum.AdminAccountStatus.NORMAL.getCode());
        account.setAccountName(user.getAccountName());
        account.setUpdateTime(date);
        adminAccountMapper.insertSelective(account);
    }

    private void checkPassword(String password) {
        String regex = "^[a-zA-Z0-9_]{6,20}$";
        boolean matches = password.matches(regex);
        if (!matches) {
            throw new BusinessException(RetCodeConstants.USER_PASSWORD_ILLEGAL, "密码非法.密码长度6-20位,数字,字母下划线");
        }
    }

    @Override
    public void updateAdminUser(AuthUserRole user) {
        boolean b = checkAccountName(user.getUserId(), user.getAccountName());
        if (!b) {
            throw new BusinessException("账户名已存在,请修改");
        }
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(user.getUserId());
        userInfo.setNickName(user.getNickName());
        userInfoMapper.updateByPrimaryKeySelective(userInfo);
        AdminAccount account = adminAccountMapper.selectByUserId(user.getUserId());
        account.setAccountName(user.getAccountName());
        checkPassword(user.getPassword());
        String passwordStr = "";
        try {
            passwordStr = AESClientUtils.aesEncrypt(user.getPassword());
        } catch (Exception e) {
            logger.error("密码加密失败！params=" + JSON.toJSONString(params));
            throw new BusinessException(RetCodeConstants.ERROR_NO_USER, "新增管理员失败，请稍后重试！");
        }
        account.setPassword(passwordStr);
        adminAccountMapper.updateByPrimaryKeySelective(account);
        sysUserRoleRelationMapper.updateRoleIdByUserId(user.getUserId(), user.getRoleId());
    }

    @Override
    public PagesDto<AuthUserVo> listAuthUsers(QuerySimpleUser query) {
        PageHelper.startPage(query.getPage(), query.getRows());
        List<AuthUserVo> users = adminAccountMapper.selectByQuerySimpleUser(query);
        users.forEach(u -> {
            try {
                u.setPassword(AESClientUtils.aesDecrypt(u.getPassword()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        PageInfo info = new PageInfo(users);

        return new PagesDto(users, info.getTotal(), query.getPage(), query.getRows());
    }

    @Override
    public boolean checkAccountName(Integer userId, String accountName) {
        AdminAccount account = adminAccountMapper.selectByAccountName(accountName);
        if (account == null || account.getUserId().equals(userId)) {
            return true;
        }
        return false;
    }

    @Override
    public void deleteAccount(Integer authUserId, Integer userId) {
        if (userId == 1) {
            throw new BusinessException("不能删除admin账户");
        }

        adminAccountMapper.deleteByUserId(userId);
        sysUserRoleRelationMapper.deleteByUserId(userId);
    }


}
