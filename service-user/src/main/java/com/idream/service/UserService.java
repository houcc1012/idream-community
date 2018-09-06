package com.idream.service;

import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.activity.AdminUserAchievementDto;
import com.idream.commons.lib.dto.user.*;
import com.idream.commons.lib.model.UserInfo;

import java.util.List;

public interface UserService {

    /**
     * @Author: hejiang
     * @Description: 查询用户信息
     * @Date: 18:55 2018/3/30
     */
    MiniUserInfoDto getUserInfo(int userId);

    /**
     * @Author: hejiang
     * @Description: 用户注册
     * @Date: 18:55 2018/3/30
     */
    JSONPublicDto<UserRegisterDto> doRegisterUserinfo(UserRegisterParams userRegisterParams, String remoteIP);

    /**
     * @Author: hejiang
     * @Description: 获取用户手机验证码
     * @Date: 18:55 2018/3/30
     */
    JSONPublicDto getIdentifyCode(int authUserId, String phone);

    /**
     * 绑定用户手机号
     */
    String doBindingPhone(int authUserId, String phone, String receiveCode);

    /**
     * @Author: hejiang
     * @Description: 修改用户信息
     * @Date: 18:17 2018/3/30
     */
    void updateUserInfo(JSONPublicParam<UpdateUserInfoParams> params);

    /**
     * @Author: houcc
     * @Description: 修改用户信息
     * @Date: 2018/5/17
     */
    void updateAdminClientUserInfo(JSONPublicParam<AdminClientUserInfoParams> params);

    /**
     * @param
     */
    List<ProfessionInfoDto> findProfessionList();

    /**
     * @Author: hejiang
     * @Description: 管理端查询前台用户列表
     * @Date: 20:09 2018/4/14
     */
    PagesDto<AdminClientUserListDto> getClientUserList(AdminClientUserListParams params);

    /**
     * @param userId
     */
    UserDetailDto getUserDetail(Integer userId);

    /**
     * @Author: hejiang
     * @Description: 查询用户标签信息
     * @Date: 18:40 2018/4/15
     */
    List<UserTagDto> getUserTagList(Integer userId);

    /**
     * @Author: hejiang
     * @Description: 查询用户积分
     * @Date: 18:40 2018/4/15
     */
    PagesDto<UseIntergralDto> getUserIntergrals(Integer userId, Integer page, Integer rows);

    /**
     * @Author: hejiang
     * @Description: 查询管理者列表
     * @Date: 18:40 2018/4/15
     */
    PagesDto<AdminClientManageUserListDto> getClientManageUserList(AdminClientManageUserListParams params);

    /**
     * @Author: hejiang
     * @Description: 用户授权
     * @Date: 18:40 2018/4/15
     */
    int doUserAuthorize(JSONPublicParam<UserAuthorizeParams> params);

    /**
     * @Author: hejiang
     * @Description:新增管理者
     * @Date: 18:40 2018/4/15
     */
    void addUserManage(JSONPublicParam<OperateManageUserParams> params);

    /**
     * @Author: hejiang
     * @Description: 编辑管理者
     * @Date: 20:57 2018/4/15
     */
    void updateUserManage(JSONPublicParam<OperateManageUserParams> params);

    /**
     * @Author: hejiang
     * @Description: 取消授权
     * @Date: 20:57 2018/4/15
     */
    void doCancelAuthorize(Integer authUserId, Integer id);

    /**
     * @Author: hejiang
     * @Description: 查询管理员
     * @Date: 20:57 2018/4/15
     */
    UserManagerDto getUserManage(Integer authUserId, Integer id);

    /**
     * @Author: hejiang
     * @Description: 管理者列表编辑重新授权
     * @Date: 20:57 2018/4/15
     */
    void doAgainAuthorize(Integer authUserId, Integer id);

    /**
     * 返回管理员的用户信息
     *
     * @param authUserId
     *
     * @return ManagerInfoDto
     */
    ManagerInfoDto getManagerInfo(Integer authUserId);

    /**
     * 手机验证码
     *
     * @return ManagerInfoDto
     */
    void getPhoneVerifyCode(String phone);

    /**
     * 保存小程序用户formId
     *
     * @param params
     */
    void addMiniProgramFormId(JSONPublicParam<List<MiniProgramUserFormIdParams>> params);

    /**
     * 发送微信模板消息
     *
     * @param
     */
    void sendMiniProgramTemplateMessage(JSONPublicParam<MiniProgramSendTemplateParams> params);

    /**
     * 获取
     *
     * @param userId
     * @param remoteIP
     */
    DefaultUserLocationDto getUserLocation(Integer userId, String remoteIP);

    /**
     * 刷新用户统计相关数据
     */
    void updateUserStatistics();

    /**
     * @Author: houcc
     * @Description: 查询用户成就明细
     * @Date: 18:40 2018/4/15
     */
    PagesDto<AdminUserAchievementDto> getUserAchievements(Integer userId, Integer page, Integer rows);

    /**
     * 记录登录信息
     *
     * @param remoteIP
     * @param deviceType
     * @param cityName
     * @param userId
     */
    void insertUserLoginRecord(String remoteIP, Byte deviceType, String cityName, Integer userId);

    /**
     * 新增用户账号
     *
     * @param accountName
     * @param userId
     * @param accountType
     */
    boolean insertUserAccountRel(String accountName, Integer userId, Byte accountType);

    /**
     * 记录用户第三方关联表
     *
     * @param userId
     * @param paramCode
     * @param paramValue
     * @param type
     */
    void insertUserThirdInfoRel(Integer userId, String paramCode, String paramValue, Byte type);

    /**
     * 同步修改网易质料
     *
     * @param info
     */
    void updateIMUserInfo(UserInfo info);
}
