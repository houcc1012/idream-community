package com.idream.service;

import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.PagesParam;
import com.idream.commons.lib.dto.achievement.AppAchievementAwardParams;
import com.idream.commons.lib.dto.achievement.UserAchieveDetailDto;
import com.idream.commons.lib.dto.activity.AppHomePageDto;
import com.idream.commons.lib.dto.activity.AppJoinActivityDto;
import com.idream.commons.lib.dto.activity.AppPublishCommunityLifeDto;
import com.idream.commons.lib.dto.app.AppUserAchievementCategoryDto;
import com.idream.commons.lib.dto.token.AuthUserInfo;
import com.idream.commons.lib.dto.user.*;

import java.util.List;

/**
 * @author hejiang
 */
public interface AppUserService {

    /**
     * @Author: hejiang
     * @Description: app游客注册
     * @Date: 18:41 2018/4/26
     */
    AppUserRegisterDto doAppRegisterUserinfo(AppUserRegisterParams params, String remoteIP, Byte deviceType);

    /**
     * @Author: hejiang
     * @Description: app微信登录
     * @Date: 18:41 2018/4/26
     */
    AppWeiChatLoginDto doAppWeichatLogin(AppWeiChatLoginParams params, String remoteIP, Byte deviceType);

    /**
     * @Author: hejiang
     * @Description: 手机验证码登录
     * @Date: 18:41 2018/4/26
     */
    AppPhoneLoginDto doPhoneVerifyCode(PhoneLoginParams params, String remoteIP, Byte deviceType);

    /**
     * 同过用户id,获取用户信息
     *
     * @param authUserId
     */
    AppUserInfoDto getAppUserInfo(Integer authUserId);

    /**
     * 修改用户信息
     *
     * @param requestParam
     * @param userId
     */
    void updateUserInfo(AppUserInfoParams requestParam, Integer userId);

    /**
     * app用户退出登录
     *
     * @param authUserId
     */
    void logout(Integer authUserId);

    /**
     * 根据用户查找参加的活动
     *
     * @param userId
     * @param param
     */
    PagesDto<AppJoinActivityDto> getJoinActivityByUserId(Integer userId, PagesParam param);

    /**
     * 职业列表
     */
    List<UserProfessionDto> listProfessions();

    /**
     * 用户发布的活动
     *
     * @param userId
     * @param param
     */
    PagesDto<AppPublishCommunityLifeDto> getPublishLifeByUserId(Integer userId, PagesParam param);

    /**
     * 保存用户反馈
     *
     * @param userFeedback
     */
    void userFeedback(JSONPublicParam<UserFeedBackRequestDto> userFeedback);

    /**
     * 更新用户反馈
     *
     * @param dto
     */
    void updateUserFeedback(UpdateUserFeedBackRequestDto dto);

    /**
     * app登录后绑定微信号
     *
     * @param params
     */
    void doAppBingingWeichat(JSONPublicParam<AppWeiChatLoginParams> params);

    /**
     * app登录后解绑微信号
     *
     * @param authUserId
     */
    void doAppUnBingingWeichat(Integer authUserId);

    /**
     * 查询对方的主页
     *
     * @param authUserId
     * @param userId
     */
    AppHomePageDto getTargetHomePage(Integer authUserId, Integer userId);

    /**
     * 通过usrId,查询头像
     *
     * @param userId
     */
    AppSimpleUserInfoDto getSimpleUserInfo(Integer userId);

    /**
     * 获得注册时间
     *
     * @param userId
     */
    AppRegisterRecordDto getUserRegistertTime(Integer userId);

    /**
     * 获取用户成就详情
     *
     * @param userId
     * @param achievementId
     */
    UserAchieveDetailDto getAchievementDetail(Integer userId, Integer achievementId);

    /**
     * 用户拉黑信息
     *
     * @param authUserId
     * @param userId
     */
    AppUserBlackDto getUserBlack(Integer authUserId, Integer userId);

    /**
     * 通过昵称查找用户
     *
     * @param userId
     * @param params
     */
    PagesDto<AppUserSearchDto> findUserByNickName(Integer userId, AppUserSearchParams params);

    /**
     * 重置token到期时间
     *
     * @param authUserInfo
     */
    void doAccessToken(AuthUserInfo authUserInfo);

    /**
     * 我的收藏
     */
    PagesDto<AppUserCollectionDto> getUserCollection(Integer userId, PagesParam param);

    /**
     * 用户未读消息
     *
     * @param userId
     */
    AppUserUnreadNoticeDto getUserUnreadNotice(Integer userId);

    /**
     * 同步用户消息
     *
     * @param userId
     */
    void addUserNoticeInfo(Integer userId);

    /**
     * 用户成就详情显示
     *
     * @param userId
     */
    List<AppUserAchievementCategoryDto> listAchievement(Integer userId);

    /**
     * 用户成就奖励提取
     *
     * @param requestParam
     */
    Integer addAchievementAward(AppAchievementAwardParams requestParam);

    /**
     * @param userId
     */

    String saveBackgroundImage(Integer userId);
}
