package com.idream.commons.lib.mapper;

import java.util.List;

import com.idream.commons.lib.model.SysMenu;

public interface SysMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);

    List<SysMenu> selectAll();

    List<SysMenu> listMenuByUserId(Integer authUserId);

    List<SysMenu> selectByRoleId(Integer roleId);

    List<SysMenu> selectByPid(Integer menuId);
}