package com.idream.controller.admin;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.adminuser.AdminUserLoginParams;
import com.idream.commons.lib.dto.auth.AuthUserRole;
import com.idream.commons.lib.dto.auth.AuthUserVo;
import com.idream.commons.lib.dto.auth.QuerySimpleUser;
import com.idream.commons.lib.dto.user.AdminUserLoginDto;
import com.idream.service.AdminUserService;
import com.idream.service.UserComplaintService;
import com.idream.service.UserLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author hejiang
 */
@RestController
@Api(description = "AdminUserController", tags = "管理端后台用户（管理端）")
@RequestMapping("/admin/user")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private UserComplaintService userComplaintService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "管理端用户登陆", notes = "管理端用户登陆", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<AdminUserLoginDto> adminUserLogin(@RequestBody AdminUserLoginParams params) {
        AdminUserLoginDto dto = userLoginService.adminUserLogin(params);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "登录成功", dto);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ApiOperation(value = "管理端用户退出", notes = "管理端用户退出", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto adminUserLogout(@ApiIgnore Integer authUserId) {
        userLoginService.adminUserLogout(authUserId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "退出成功", null);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "新增后台管理员账号", notes = "新增后台管理员账号", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto addAdminUser(@RequestBody AuthUserRole user) {
        adminUserService.addAdminUser(user);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "新增后台管理员账号成功", null);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation(value = "修改后台管理员账号", notes = "修改后台管理员账号", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto updateAdminUser(@RequestBody AuthUserRole user) {
        adminUserService.updateAdminUser(user);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "编辑后台管理员账号成功", null);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "后台管理员账号列表查询", notes = "后台管理员账号列表查询", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<AuthUserVo>> getAdminUserList(QuerySimpleUser query) {
        PagesDto<AuthUserVo> adminUsers = adminUserService.listAuthUsers(query);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "", adminUsers);
    }

    @ApiOperation(value = "检查账户名")
    @RequestMapping(value = "/checkAccountName", method = RequestMethod.GET)
    public JSONPublicDto<Boolean> checkAccountName(Integer userId, String accountName) {
        boolean flag = adminUserService.checkAccountName(userId, accountName);
        return JSONPublicDto.returnSuccessData(flag);
    }

    @ApiOperation(value = "删除管理端账户")
    @RequestMapping(value = "/account", method = RequestMethod.DELETE)
    public JSONPublicDto deleteAccount(@ApiIgnore Integer authUserId, Integer userId) {
        adminUserService.deleteAccount(authUserId, userId);
        return JSONPublicDto.returnSuccessData("成功");
    }
}
