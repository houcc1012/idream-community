package com.idream.commons.lib.mapper;

import java.util.List;

import com.idream.commons.lib.model.SysRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    /**
     * 查询所有的角色
     *
     * @return List<SysRole>
     */
    List<SysRole> selectAll();

    /**
     * 通过角色名,查询角色
     *
     * @param query
     */
    List<SysRole> selectByRoleName(@Param("query") String query);

    @Select("select * from sys_role where name=#{roleName}")
    SysRole selectByExactRoleName(@Param("roleName") String roleName);
}