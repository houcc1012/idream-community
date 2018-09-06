package com.idream.controller.admin;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.activity.AdminUserAchievementDto;
import com.idream.commons.lib.dto.adminuser.AdminUserLoginParams;
import com.idream.commons.lib.dto.user.AdminClientManageUserListDto;
import com.idream.commons.lib.dto.user.AdminClientManageUserListParams;
import com.idream.commons.lib.dto.user.AdminClientUserInfoParams;
import com.idream.commons.lib.dto.user.AdminClientUserListDto;
import com.idream.commons.lib.dto.user.AdminClientUserListParams;
import com.idream.commons.lib.dto.user.AdminUserLoginDto;
import com.idream.commons.lib.dto.user.ManagerInfoDto;
import com.idream.commons.lib.dto.user.OperateManageUserParams;
import com.idream.commons.lib.dto.user.UseIntergralDto;
import com.idream.commons.lib.dto.user.UserAuthorizeParams;
import com.idream.commons.lib.dto.user.UserDetailDto;
import com.idream.commons.lib.dto.user.UserManagerDto;
import com.idream.commons.lib.dto.user.UserTagDto;
import com.idream.commons.mvc.base.BaseController;
import com.idream.service.UserLoginService;
import com.idream.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@Api(tags = "管理端前台用户相关接口（管理端）", description = "AdminClientUserController")
@RequestMapping("/admin/client/user")
public class AdminClientUserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserLoginService userLoginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "管理端用户登陆", notes = "管理端用户登陆", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<AdminUserLoginDto> userLogin(@RequestBody AdminUserLoginParams params) {
        AdminUserLoginDto dto = userLoginService.userLogin(params, getRemoteIP(), getDeviceType());
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "登录成功", dto);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ApiOperation(value = "管理端用户退出", notes = "管理端用户退出", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto adminUserLogout(@ApiIgnore Integer authUserId) {
        userLoginService.userLogout(authUserId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "退出成功", null);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "用户列表查询", notes = "用户列表查询", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<AdminClientUserListDto>> getClientUserList(AdminClientUserListParams params) {
        PagesDto<AdminClientUserListDto> resultUserList = userService.getClientUserList(params);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "", resultUserList);
    }

    @RequestMapping(value = "/detail/{userId}", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户详细资料", notes = "查询用户详细资料", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<UserDetailDto> getUserDetail(@ApiParam("用户id") @PathVariable Integer userId) {
        UserDetailDto detail = userService.getUserDetail(userId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "", detail);
    }

    @RequestMapping(value = "/tags/{userId}", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户标签信息", notes = "查询用户标签信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<UserTagDto>> getUserTagList(@ApiParam("用户id") @PathVariable Integer userId) {
        List<UserTagDto> userTags = userService.getUserTagList(userId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "", userTags);
    }

    @RequestMapping(value = "/intergral/{userId}/{page}/{rows}", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户积分明细", notes = "查询用户积分明细", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<UseIntergralDto>> getUserIntergrals(@ApiParam("用户id") @PathVariable Integer userId,
                                                                      @ApiParam("页码") @PathVariable Integer page,
                                                                      @ApiParam("行数") @PathVariable Integer rows) {
        PagesDto<UseIntergralDto> userIntergrals = userService.getUserIntergrals(userId, page, rows);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "", userIntergrals);
    }

    @RequestMapping(value = "/authorize", method = RequestMethod.POST)
    @ApiOperation(value = "用户列表授权", notes = "用户列表授权", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto userAuthorize(@RequestBody JSONPublicParam<UserAuthorizeParams> params) {
        userService.doUserAuthorize(params);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "授权成功", null);
    }

    @RequestMapping(value = "/manage/list", method = RequestMethod.GET)
    @ApiOperation(value = "管理者列表查询", notes = "管理者列表查询", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<AdminClientManageUserListDto>> getClientManageUserList(AdminClientManageUserListParams params) {
        PagesDto<AdminClientManageUserListDto> resultUserList = userService.getClientManageUserList(params);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", resultUserList);
    }

    @RequestMapping(value = "/manage", method = RequestMethod.POST)
    @ApiOperation(value = "新增管理者", notes = "新增管理者", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto addUserManage(@RequestBody JSONPublicParam<OperateManageUserParams> params) {
        userService.addUserManage(params);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "新增成功", null);
    }

    @RequestMapping(value = "/manage/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询管理者", notes = "查询管理者", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<UserManagerDto> getUserManage(@ApiIgnore @RequestParam Integer authUserId, @ApiParam("主键ID") @PathVariable Integer id) {
        UserManagerDto manager = userService.getUserManage(authUserId, id);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", manager);
    }

    @RequestMapping(value = "/manage", method = RequestMethod.PUT)
    @ApiOperation(value = "编辑管理者", notes = "编辑管理者", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto updateUserManage(@RequestBody JSONPublicParam<OperateManageUserParams> params) {
        userService.updateUserManage(params);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "编辑成功", null);
    }

    @RequestMapping(value = "/cancel/authorize/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "管理者编辑取消授权", notes = "管理者编辑取消授权", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto cancelAuthorize(@ApiIgnore @RequestParam Integer authUserId, @ApiParam("主键ID") @PathVariable Integer id) {
        userService.doCancelAuthorize(authUserId, id);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "取消授权成功", null);
    }

    @RequestMapping(value = "/again/authorize/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "管理者编辑重新授权", notes = "管理者编辑重新授权", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto againAuthorize(@ApiIgnore @RequestParam Integer authUserId, @ApiParam("主键ID") @PathVariable Integer id) {
        userService.doAgainAuthorize(authUserId, id);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "授权成功", null);
    }

    @ApiOperation(value = "管理员信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/managerInfo", method = RequestMethod.GET)
    public JSONPublicDto<ManagerInfoDto> getManagerInfo(@ApiIgnore @RequestParam("authUserId") Integer authUserId) {
        ManagerInfoDto dto = userService.getManagerInfo(authUserId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "获取管理员信息成功", dto);
    }

    @RequestMapping(value = "/modifyInfo", method = RequestMethod.PUT)
    @ApiOperation(value = "管理者修改账号和密码", notes = "管理者修改账号和密码", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto modifyInfo(@RequestBody JSONPublicParam<AdminClientUserInfoParams> param) {
        userService.updateAdminClientUserInfo(param);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "修改成功", null);
    }

    @RequestMapping(value = "/updateUserStatistics", method = RequestMethod.PUT)
    @ApiIgnore
    @ApiOperation(value = "提供对service-quartz服务的调用", notes = "更新用户相关统计数据", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto updateUserStatistics() {
        userService.updateUserStatistics();
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "更新成功", null);
    }

    @RequestMapping(value = "/achievement", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户成就明细", notes = "查询成就积分明细", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<AdminUserAchievementDto>> getUserAchievement(@ApiParam("用户id") @RequestParam Integer userId,
                                                                               @ApiParam("页码") @RequestParam Integer page,
                                                                               @ApiParam("行数") @RequestParam Integer rows) {
        PagesDto<AdminUserAchievementDto> achievements = userService.getUserAchievements(userId, page, rows);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "", achievements);
    }

}
