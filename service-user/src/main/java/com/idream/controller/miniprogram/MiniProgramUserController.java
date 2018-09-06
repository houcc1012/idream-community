package com.idream.controller.miniprogram;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.user.*;
import com.idream.commons.mvc.base.BaseController;
import com.idream.service.AdminPlatFromSettingService;
import com.idream.service.BannerImageService;
import com.idream.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;


@RestController
@Api(tags = "用户服务接口(小程序)", description = "UserController")
@RequestMapping("/user")
public class MiniProgramUserController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private BannerImageService bannerImageService;
    @Autowired
    private AdminPlatFromSettingService adminPlatFromSettingService;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户信息", notes = "查询用户信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<MiniUserInfoDto> getUserInfoById(@ApiIgnore @RequestParam int authUserId) {
        MiniUserInfoDto userInfo = userService.getUserInfo(authUserId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", userInfo);
    }

    @RequestMapping(value = "/info", method = RequestMethod.PUT)
    @ApiOperation(value = "编辑资料", notes = "编辑资料", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto updateUserInfo(@RequestBody JSONPublicParam<UpdateUserInfoParams> params) {
        userService.updateUserInfo(params);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "修改成功", null);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value = "微信小程序用户注册", notes = "微信小程序用户注册", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<UserRegisterDto> miniProgramUserRegister(@RequestBody UserRegisterParams params) {
        JSONPublicDto<UserRegisterDto> data = userService.doRegisterUserinfo(params, getRemoteIP());
        return data;
    }

    @RequestMapping(value = "/identify/code", method = RequestMethod.GET)
    @ApiOperation(value = "小程序获取手机验证码", notes = "小程序获取手机验证码", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto getIdentifyCode(@ApiIgnore @RequestParam("authUserId") int authUserId, @RequestParam("phone") String phone) {
        return userService.getIdentifyCode(authUserId, phone);
    }

    @RequestMapping(value = "/binding/phone", method = RequestMethod.GET)
    @ApiOperation(value = "用户绑定手机号,返回新的token", notes = "用户绑定手机号", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto userinflogin(@ApiIgnore @RequestParam("authUserId") int authUserId,
                                      @NotBlank(message = "手机号不能为空") @RequestParam("phone") String phone,
                                      @NotBlank(message = "验证码不能为空") @Pattern(regexp = "\\d{4}", message = "请输入有效的验证码") @RequestParam("receiveCode") String receiveCode) {
        String token = userService.doBindingPhone(authUserId, phone, receiveCode);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "绑定成功", token);
    }

    @RequestMapping(value = "/profession/list", method = RequestMethod.GET)
    @ApiOperation(value = "查询职业列表", notes = "查询职业列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<ProfessionInfoDto>> ProfessionInfo() {
        List<ProfessionInfoDto> data = userService.findProfessionList();
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }

    @RequestMapping(value = "/formId", method = RequestMethod.POST)
    @ApiOperation(value = "保存小程序用户FormId", notes = "保存小程序用户FormId", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public void addMiniProgramFormId(@RequestBody JSONPublicParam<List<MiniProgramUserFormIdParams>> params) {
        userService.addMiniProgramFormId(params);
    }

    @RequestMapping(value = "/template/send", method = RequestMethod.POST)
    @ApiOperation(value = "发送小程序模板消息", notes = "发送小程序模板消息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto sendMiniProgramTemplateMessage(@RequestBody JSONPublicParam<MiniProgramSendTemplateParams> params) {
        userService.sendMiniProgramTemplateMessage(params);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "发送成功", null);
    }

    @RequestMapping(value = "/banner/list", method = RequestMethod.GET)
    @ApiOperation(value = "小程序显示banner", notes = "小程序显示banner", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<AdminBannerUrlParams>> selectBannerList() {
        List<AdminBannerUrlParams> data = bannerImageService.selectWXBannerList();
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询banner数据成功", data);
    }

    @RequestMapping(value = "selectOpenCity", method = RequestMethod.GET)
    @ApiOperation(value = "查询开通城市", notes = "查询开通城市", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<MiniProgramOpenCityDto>> selectOpenCity() {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", adminPlatFromSettingService.selectMiniOpenCity());
    }

    @RequestMapping(value = "/defaultLocation",method = RequestMethod.GET)
    @ApiOperation("默认地址")
    public JSONPublicDto<DefaultUserLocationDto> getDefaultLocation(@ApiIgnore @RequestParam("authUserId") Integer userId) {
        DefaultUserLocationDto dto = userService.getUserLocation(userId, getRemoteIP());
        return JSONPublicDto.returnSuccessData(dto);
    }
}
