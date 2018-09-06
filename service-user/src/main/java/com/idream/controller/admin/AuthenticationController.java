package com.idream.controller.admin;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.auth.*;
import com.idream.service.AuthenticationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author charles.wei
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "后端权限管理")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @ApiOperation(value = "权限菜单")
    @RequestMapping(value = "/authMenu", method = RequestMethod.GET)
    public JSONPublicDto<List<SimpleAuthMenu>> listAuthMenu() {
        //先隐藏 List<AuthMenuNode> tree = authenticationService.listAuthMenus();
        List<SimpleAuthMenu> list = authenticationService.listSimpleAuthMenus();
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "Ok", list);
    }

    @ApiOperation(value = "所有角色")
    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public JSONPublicDto<List<RoleInfo>> role(String query) {
        List<RoleInfo> infos = authenticationService.listRoles(query);
        return JSONPublicDto.returnSuccessData(infos);
    }


    @RequestMapping(value = "/role/{roleId}", method = RequestMethod.GET)
    public JSONPublicDto<RoleVO> roleById(@PathVariable Integer roleId) {
        RoleVO role = authenticationService.getRoleById(roleId);
        return JSONPublicDto.returnSuccessData(role);
    }


    @RequestMapping(value = "/role", method = RequestMethod.POST)
    public JSONPublicDto<String> addRole(RoleVO role) {
        authenticationService.saveRole(role);
        return JSONPublicDto.returnSuccessData("success");
    }


    @RequestMapping(value = "/role", method = RequestMethod.PUT)
    public JSONPublicDto<String> updateRole(RoleVO role) {
        authenticationService.updateRole(role);
        return JSONPublicDto.returnSuccessData("success");
    }


    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public JSONPublicDto<List<MenuNode>> menu(@RequestParam Integer authUserId) {
        List<MenuNode> menus = authenticationService.listMenuByUserId(authUserId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "Ok", menus);
    }


    @RequestMapping(value = "user/role", method = RequestMethod.POST)
    public JSONPublicDto<String> saveUserRole(Integer userId, Integer roleId) {
        authenticationService.saveUserRoleRelation(userId, roleId);
        return JSONPublicDto.returnSuccessData("OK");
    }

    @RequestMapping(value = "user/role", method = RequestMethod.PUT)
    public JSONPublicDto<String> updateUserRole(Integer userId, Integer roleId) {
        authenticationService.updateUserRoleRelation(userId, roleId);
        return JSONPublicDto.returnSuccessData("OK");
    }


    @RequestMapping(value = "user/role", method = RequestMethod.DELETE)
    public JSONPublicDto<String> deleteUserRole(Integer userId, Integer roleId) {
        authenticationService.deleteUserRoleRelation(userId, roleId);
        return JSONPublicDto.returnSuccessData("OK");
    }


    @RequestMapping(value = "user/role", method = RequestMethod.GET)
    public JSONPublicDto<UserRoleVO> listUserRole(Integer userId) {
        UserRoleVO vo = authenticationService.listUserRoleByUserId(userId);
        return JSONPublicDto.returnSuccessData(vo);
    }

    @RequestMapping(value = "menuPermission", method = RequestMethod.GET)
    public JSONPublicDto<List<PermissionInfo>> menuPermission(@RequestParam Integer authUserId, @NotNull Integer menuId) {
        List<PermissionInfo> infos = authenticationService.listPermissionByUserIdAndMenuId(authUserId, menuId);
        return JSONPublicDto.returnSuccessData(infos);
    }

    @ApiOperation(value = "创建角色")
    @RequestMapping(value = "simpleRole", method = RequestMethod.POST)
    public JSONPublicDto<String> createRole(@RequestBody SimpleRoleInfo role) {
        authenticationService.createSimpleRole(role);
        return JSONPublicDto.returnSuccessData("创建成功");
    }

    @ApiOperation(value = "查询角色权限")
    @RequestMapping(value = "simpleRole", method = RequestMethod.GET)
    public JSONPublicDto<SimpleRoleInfo> getRole(@RequestParam("roleId") Integer roleId) {
        SimpleRoleInfo role = authenticationService.getSimpleRoleByRoleId(roleId);
        return JSONPublicDto.returnSuccessData(role);
    }

    @ApiOperation(value = "修改角色权限")
    @RequestMapping(value = "simpleRole", method = RequestMethod.PUT)
    public JSONPublicDto updateRole(@RequestBody SimpleRoleInfo role) {
        authenticationService.updateSimpleRole(role);
        return JSONPublicDto.returnSuccessData("修改成功");
    }

    @ApiOperation(value = "删除角色")
    @RequestMapping(value = "simpleRole", method = RequestMethod.DELETE)
    public JSONPublicDto deleteRole(Integer roleId) {
        authenticationService.deleteUserRole(roleId);
        return JSONPublicDto.returnSuccessData("删除成功");
    }

}
