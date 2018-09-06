package com.idream.commons.lib.mapper;

import java.util.List;

import com.idream.commons.lib.dto.auth.PermissionInfo;
import com.idream.commons.lib.model.SysPermission;
import org.apache.ibatis.annotations.Param;

public interface SysPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    SysPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);

    /**
     * 根据用户id,查询所有的权限
     *
     * @param authUserId
     *
     * @return List<SysPermission>
     */
    List<SysPermission> selectPermissionListByUserId(@Param("authUserId") Integer authUserId);

    /**
     * 根据id和menuid查询权限
     *
     * @param authUserId
     * @param menuId
     *
     * @return List<PermissionInfo>
     */
    List<PermissionInfo> selectPermissionListByUserIdAndMenuId(@Param("authUserId") Integer authUserId, @Param("menuId") Integer menuId);

    /**
     * 查询所有权限
     *
     * @return List<SysPermission>
     */
    List<SysPermission> selectAll();

    /**
     * 当前用户的权限
     *
     * @param roleId
     *
     * @return List<SysPermission>
     */
    List<SysPermission> selectByRoleId(Integer roleId);

    /**
     * 查询所有的基础信息
     *
     * @return List<SysPermission>
     */
    List<SysPermission> selectBasis();

    /**
     * 通过菜单id,查找权限
     *
     * @param id
     */
    List<SysPermission> selectByMenuId(Integer id);
}