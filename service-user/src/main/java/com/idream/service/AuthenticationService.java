package com.idream.service;

import com.idream.commons.lib.dto.auth.*;

import java.util.List;

/**
 * @author charles.wei
 */
public interface AuthenticationService {
    /**
     * 通过用户id,获得所有的权限
     *
     * @param authUserId
     *
     * @return Stream<PermissionInfo>
     */
    List<PermissionInfo> listPermissionByUserId(Integer authUserId);

    /**
     * 返回所有的菜单和按钮权限
     *
     * @return List<AuthMenuNode>
     */
    List<AuthMenuNode> listAuthMenus();

    /**
     * 通过用户id,返回菜单
     *
     * @param authUserId
     *
     * @return List<MenuNode>
     */
    List<MenuNode> listMenuByUserId(Integer authUserId);

    /**
     * 根据用户id和菜单id获取,有关的权限
     *
     * @param authUserId
     * @param menuId
     *
     * @return List<PermissionInfo>
     */
    List<PermissionInfo> listPermissionByUserIdAndMenuId(Integer authUserId, Integer menuId);

    /**
     * 查询所有的角色
     *
     * @param query
     *
     * @return List<RoleInfo>
     */
    List<RoleInfo> listRoles(String query);

    /**
     * 保存新角色
     *
     * @param role void
     */
    void saveRole(RoleVO role);

    /**
     * 修改角色
     *
     * @param role void
     */
    void updateRole(RoleVO role);

    /**
     * 根据角色id,查询所有权限和菜单
     *
     * @param roleId
     *
     * @return RoleDto
     */
    RoleVO getRoleById(Integer roleId);

    /**
     * 添加用户与角色的关联
     *
     * @param userId
     * @param roleId void
     */
    void saveUserRoleRelation(Integer userId, Integer roleId);

    /**
     * 修改用户与角色的关系
     *
     * @param userId
     * @param roleId void
     */
    void updateUserRoleRelation(Integer userId, Integer roleId);

    /**
     * 删除用户与角色的关系
     *
     * @param userId
     * @param roleId void
     */
    void deleteUserRoleRelation(Integer userId, Integer roleId);

    /**
     * 返回当前用户的角色,以及所有角色
     *
     * @param userId
     *
     * @return UserRoleVO 用户与角色展示类
     */
    UserRoleVO listUserRoleByUserId(Integer userId);

    /**
     * 查询基础权限
     *
     * @return List<PermissionInfo>
     */
    List<PermissionInfo> listBasisPermission();

    /**
     * 获取所有权限,方便前端
     */
    List<SimpleAuthMenu> listSimpleAuthMenus();

    /**
     * 生成角色
     *
     * @param role
     */
    void createSimpleRole(SimpleRoleInfo role);

    /**
     * 通过roleId查询角色
     *
     * @param roleId
     */
    SimpleRoleInfo getSimpleRoleByRoleId(Integer roleId);

    /**
     * 修改权限
     *
     * @param role
     */
    void updateSimpleRole(SimpleRoleInfo role);

    /**
     * 用户全部权限
     *
     * @param userId
     */
    List<SimpleAuthMenu> listSimpleAuthMenusByUserId(Integer userId);

    /**
     * 删除角色
     *
     * @param roleId
     */
    void deleteUserRole(Integer roleId);
}
