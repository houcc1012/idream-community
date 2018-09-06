package com.idream.controller.app;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
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
import com.idream.commons.lib.dto.user.AppComplaintParams;
import com.idream.commons.lib.dto.user.AppComplaintTypeDto;
import com.idream.commons.lib.dto.user.AppHelpInfoDto;
import com.idream.commons.lib.dto.user.AppPhoneLoginDto;
import com.idream.commons.lib.dto.user.AppRegisterRecordDto;
import com.idream.commons.lib.dto.user.AppSimpleUserInfoDto;
import com.idream.commons.lib.dto.user.AppUserBlackDto;
import com.idream.commons.lib.dto.user.AppUserCollectionDto;
import com.idream.commons.lib.dto.user.AppUserInfoDto;
import com.idream.commons.lib.dto.user.AppUserInfoParams;
import com.idream.commons.lib.dto.user.AppUserRegisterDto;
import com.idream.commons.lib.dto.user.AppUserRegisterParams;
import com.idream.commons.lib.dto.user.AppUserSearchDto;
import com.idream.commons.lib.dto.user.AppUserSearchParams;
import com.idream.commons.lib.dto.user.AppUserUnreadNoticeDto;
import com.idream.commons.lib.dto.user.AppWeiChatLoginDto;
import com.idream.commons.lib.dto.user.AppWeiChatLoginParams;
import com.idream.commons.lib.dto.user.DefaultUserLocationDto;
import com.idream.commons.lib.dto.user.HelpTypeDto;
import com.idream.commons.lib.dto.user.PhoneLoginParams;
import com.idream.commons.lib.dto.user.UpdateUserFeedBackRequestDto;
import com.idream.commons.lib.dto.user.UserFeedBackRequestDto;
import com.idream.commons.lib.dto.user.UserProfessionDto;
import com.idream.commons.mvc.base.BaseController;
import com.idream.service.AdminPlatFromSettingService;
import com.idream.service.AppUserService;
import com.idream.service.UserComplaintService;
import com.idream.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author hejiang
 */
@RestController
@Api(tags = "用户服务接口(APP)", description = "AppUserController")
@RequestMapping("/app/user")
public class AppUserController extends BaseController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private UserService userService;
    @Autowired
    private UserComplaintService userComplaintService;
    @Autowired
    private AdminPlatFromSettingService adminPlatFromSettingService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value = "app游客注册", notes = "app游客注册", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<AppUserRegisterDto> appUserRegister(@RequestBody AppUserRegisterParams params) {
        AppUserRegisterDto data = appUserService.doAppRegisterUserinfo(params, getRemoteIP(), getDeviceType());
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "注册成功", data);
    }

    @RequestMapping(value = "/weichat/login", method = RequestMethod.POST)
    @ApiOperation(value = "app微信登录", notes = "app微信登录", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<AppWeiChatLoginDto> appWeichatLogin(@RequestBody AppWeiChatLoginParams params) {
        AppWeiChatLoginDto data = appUserService.doAppWeichatLogin(params, getRemoteIP(), getDeviceType());
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "登录成功", data);
    }

    @RequestMapping(value = "/verifycode/login", method = RequestMethod.POST)
    @ApiOperation(value = "app手机验证码登录", notes = "app手机验证码登录", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<AppPhoneLoginDto> doPhoneVerifyCode(@RequestBody PhoneLoginParams params) {
        AppPhoneLoginDto data = appUserService.doPhoneVerifyCode(params, getRemoteIP(), getDeviceType());
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "登录成功", data);
    }

    @RequestMapping(value = "/verify/code", method = RequestMethod.GET)
    @ApiOperation(value = "获取手机验证码", notes = "获取手机验证码", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto getVerifyCode(@ApiParam("手机号码") @RequestParam String phone) {
        userService.getPhoneVerifyCode(phone);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "发送成功", null);
    }

    @RequestMapping(value = "/weichat/binding/phone", method = RequestMethod.GET)
    @ApiOperation(value = "app微信登录后绑定手机号", notes = "app微信登录后绑定手机号", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<AppPhoneLoginDto> appWeichatBindingPhone(@ApiIgnore @RequestParam("authUserId") int authUserId,
                                                                  @NotBlank(message = "手机号不能为空") @RequestParam("phone") String phone,
                                                                  @NotBlank(message = "验证码不能为空") @RequestParam("receiveCode") String receiveCode) {
        String token = userService.doBindingPhone(authUserId, phone, receiveCode);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "绑定手机号成功", new AppPhoneLoginDto(token));
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    @ApiOperation(value = "用户详情", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<AppUserInfoDto> getUserInfo(@ApiIgnore @RequestParam Integer authUserId) {
        AppUserInfoDto userInfo = appUserService.getAppUserInfo(authUserId);
        if (StringUtils.isEmpty(userInfo.getBackgroundImage())) {
            String image = appUserService.saveBackgroundImage(authUserId);
            userInfo.setBackgroundImage(image);
        }
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "用户信息", userInfo);
    }

    @ApiOperation(value = "修改用户详情", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/userInfo", method = RequestMethod.PUT)
    public JSONPublicDto<String> updateUserInfo(@RequestBody JSONPublicParam<AppUserInfoParams> params) {
        AuthUserInfo authUserInfo = params.getAuthUserInfo();
        appUserService.updateUserInfo(params.getRequestParam(), authUserInfo.getUserId());
        return JSONPublicDto.returnSuccessData("修改成功");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ApiOperation(value = "App用户退出登录", notes = "App用户退出登录", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto adminUserLogout(@ApiIgnore Integer authUserId) {
        appUserService.logout(authUserId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "退出成功", null);
    }

    @ApiOperation(value = "我参加的活动", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/myJoinActivity", method = RequestMethod.GET)
    public JSONPublicDto<PagesDto<AppJoinActivityDto>> myJoinActivity(@ApiIgnore @RequestParam Integer authUserId, PagesParam param) {
        PagesDto<AppJoinActivityDto> page = appUserService.getJoinActivityByUserId(authUserId, param);
        return JSONPublicDto.returnSuccessData(page);
    }

    @ApiOperation(value = "我发布的动态", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/myPublishLife", method = RequestMethod.GET)
    public JSONPublicDto<PagesDto<AppPublishCommunityLifeDto>> myPublishLife(@ApiIgnore @RequestParam("authUserId") Integer userId, PagesParam param) {
        PagesDto<AppPublishCommunityLifeDto> page = appUserService.getPublishLifeByUserId(userId, param);
        return JSONPublicDto.returnSuccessData(page);
    }

    @ApiOperation(value = "所有职业列表", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/profession", method = RequestMethod.GET)
    public JSONPublicDto<List<UserProfessionDto>> listUserProfession() {
        List<UserProfessionDto> list = appUserService.listProfessions();
        return JSONPublicDto.returnSuccessData(list);
    }

    //用户反馈
    @ApiOperation(value = "用户反馈", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/userFeedback", method = RequestMethod.POST)
    public JSONPublicDto<String> userFeedback(@RequestBody JSONPublicParam<UserFeedBackRequestDto> userFeedback) {
        appUserService.userFeedback(userFeedback);
        return JSONPublicDto.returnSuccessData("添加成功");
    }

    //修改用户反馈状态
    @ApiOperation(value = "修改用户反馈的状态", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/updateUserFeedback", method = RequestMethod.POST)
    public JSONPublicDto updateUserFeedback(@RequestBody UpdateUserFeedBackRequestDto dto) {
        appUserService.updateUserFeedback(dto);
        return JSONPublicDto.returnSuccessData("修改成功");
    }

    @RequestMapping(value = "/binding/weichat", method = RequestMethod.POST)
    @ApiOperation(value = "app手机登陆后绑定微信号", notes = "app手机登陆后绑定微信号", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto appBingingWeichat(@RequestBody JSONPublicParam<AppWeiChatLoginParams> params) {
        appUserService.doAppBingingWeichat(params);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "绑定微信号成功", null);
    }

    @RequestMapping(value = "/unbinding/weichat", method = RequestMethod.POST)
    @ApiOperation(value = "app手机登陆后解绑微信号", notes = "app手机登陆后解绑微信号", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<AppPhoneLoginDto> appUnBingingWeichat(@ApiIgnore @RequestParam Integer authUserId) {
        appUserService.doAppUnBingingWeichat(authUserId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "解绑微信号成功", null);
    }

    @ApiOperation(value = "他人主页")
    @RequestMapping(value = "/targetHomePage", method = RequestMethod.GET)
    public JSONPublicDto<AppHomePageDto> targetHomePage(@ApiIgnore Integer authUserId, Integer userId) {
        AppHomePageDto a = appUserService.getTargetHomePage(authUserId, userId);
        return JSONPublicDto.returnSuccessData(a);
    }

    @ApiOperation(value = "投诉类型")
    @RequestMapping(value = "/complaintType", method = RequestMethod.GET)
    public JSONPublicDto<List<AppComplaintTypeDto>> complaintType(@RequestParam(defaultValue = "1") Integer typeId) {
        List<AppComplaintTypeDto> list = userComplaintService.listComplaintType(typeId);
        return JSONPublicDto.returnSuccessData(list);
    }

    @ApiOperation(value = "保存投诉")
    @RequestMapping(value = "/complaint", method = RequestMethod.POST)
    public JSONPublicDto<String> complaint(@RequestBody JSONPublicParam<AppComplaintParams> params) {
        userComplaintService.saveComplaint(params);
        return JSONPublicDto.returnSuccessData("您已提交投诉,正在审核中");
    }

    @ApiOperation(value = "获取用户头像信息")
    @RequestMapping(value = "/simpleInfo", method = RequestMethod.GET)
    public JSONPublicDto<AppSimpleUserInfoDto> getSimpleUserInfo(@ApiIgnore @RequestParam("authUserId") Integer userId) {
        AppSimpleUserInfoDto info = appUserService.getSimpleUserInfo(userId);
        return JSONPublicDto.returnSuccessData(info);
    }

    @ApiOperation(value = "默认地址信息")
    @RequestMapping(value = "/defaultLocation", method = RequestMethod.GET)
    public JSONPublicDto<DefaultUserLocationDto> getDefaultLocation(@ApiIgnore @RequestParam("authUserId") Integer userId) {
        DefaultUserLocationDto dto = userService.getUserLocation(userId, getRemoteIP());
        return JSONPublicDto.returnSuccessData(dto);
    }

    @ApiOperation(value = "加入时间")
    @RequestMapping(value = "registerTime", method = RequestMethod.GET)
    public JSONPublicDto<AppRegisterRecordDto> getRegisterTime(@ApiIgnore @RequestParam("authUserId") Integer userId) {
        AppRegisterRecordDto date = appUserService.getUserRegistertTime(userId);
        return JSONPublicDto.returnSuccessData(date);
    }

    @ApiOperation(value = "成就详情")
    @RequestMapping(value = "achievementDetail", method = RequestMethod.GET)
    public JSONPublicDto<UserAchieveDetailDto> getAchievement(@RequestParam Integer userId, @RequestParam Integer achievementId) {
        UserAchieveDetailDto dto = appUserService.getAchievementDetail(userId, achievementId);
        return JSONPublicDto.returnSuccessData(dto);
    }

    @ApiOperation(value = "用户拉黑信息")
    @RequestMapping(value = "userBlack", method = RequestMethod.GET)
    public JSONPublicDto<AppUserBlackDto> getUserBlack(@ApiIgnore @RequestParam("authUserId") Integer authUserId, Integer userId) {
        AppUserBlackDto dto = appUserService.getUserBlack(authUserId, userId);
        return JSONPublicDto.returnSuccessData(dto);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ApiOperation("搜索用户")
    public JSONPublicDto<PagesDto<AppUserSearchDto>> searchUser(@ApiIgnore @RequestParam("authUserId") Integer userId, AppUserSearchParams params) {
        PagesDto<AppUserSearchDto> page = appUserService.findUserByNickName(userId, params);
        return JSONPublicDto.returnSuccessData(page);
    }

    @RequestMapping(value = "/accessToken", method = RequestMethod.PUT)
    @ApiOperation("重置token失效时间")
    public JSONPublicDto accessToken(@RequestBody JSONPublicParam param) {
        appUserService.doAccessToken(param.getAuthUserInfo());
        return JSONPublicDto.returnSuccessData("成功");
    }

    @RequestMapping(value = "/collection", method = RequestMethod.GET)
    @ApiOperation("我的收藏记录")
    public JSONPublicDto<PagesDto<AppUserCollectionDto>> getCollection(@ApiIgnore @RequestParam Integer authUserId, PagesParam param) {
        PagesDto<AppUserCollectionDto> data = appUserService.getUserCollection(authUserId, param);
        return JSONPublicDto.returnSuccessData(data);
    }

    @RequestMapping(value = "selectHelpType", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有帮助类型", notes = "查询所有帮助类型", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<HelpTypeDto>> selectHelpType() {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", adminPlatFromSettingService.selectHelpType());
    }

    @RequestMapping(value = "selectHelpTitleByTypeId", method = RequestMethod.GET)
    @ApiOperation(value = "根据帮助类型ID查询问题标题", notes = "根据帮助类型ID查询问题标题", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<AppHelpInfoDto>> selectHelpTitleByTypeId(@ApiParam(value = "typeId") @RequestParam(value = "typeId") Integer typeId) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", adminPlatFromSettingService.selectHelpTitleByTypeId(typeId));
    }

    @RequestMapping(value = "selectContentByHelpTitle", method = RequestMethod.GET)
    @ApiOperation(value = "根据问题ID查询问题内容", notes = "根据问题ID查询问题内容", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<String> selectContentByHelpTitle(@ApiParam(value = "id") @RequestParam(value = "id") Integer id) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", adminPlatFromSettingService.selectContentByHelpTitle(id));
    }

    @RequestMapping(value = "/unreadNotice", method = RequestMethod.GET)
    @ApiOperation("用户未读消息")
    public JSONPublicDto<AppUserUnreadNoticeDto> unreadNotice(@ApiIgnore @RequestParam("authUserId") Integer userId) {
        AppUserUnreadNoticeDto dto = appUserService.getUserUnreadNotice(userId);
        return JSONPublicDto.returnSuccessData(dto);
    }

    @RequestMapping(value = "/notice/info", method = RequestMethod.POST)
    @ApiOperation(value = "同步用户通知消息", notes = "同步用户通知消息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto addUserNoticeInfo(@ApiIgnore @RequestParam("authUserId") Integer userId) {
        appUserService.addUserNoticeInfo(userId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "同步成功", null);
    }

    @RequestMapping(value = "/achievement", method = RequestMethod.GET)
    @ApiOperation("用户成就展示")
    public JSONPublicDto<List<AppUserAchievementCategoryDto>> achievement(@ApiIgnore @RequestParam Integer authUserId) {
        List<AppUserAchievementCategoryDto> list = appUserService.listAchievement(authUserId);
        return JSONPublicDto.returnSuccessData(list);
    }

    @RequestMapping(value = "/achievement/award", method = RequestMethod.POST)
    @ApiOperation("获取成就奖励")
    public JSONPublicDto<Integer> achievementAward(@RequestBody JSONPublicParam<AppAchievementAwardParams> param) {
        AppAchievementAwardParams params = param.getRequestParam();
        params.setAuthUserId(param.getAuthUserInfo().getUserId());
        Integer score = appUserService.addAchievementAward(params);
        return JSONPublicDto.returnSuccessData(score);
    }

}
