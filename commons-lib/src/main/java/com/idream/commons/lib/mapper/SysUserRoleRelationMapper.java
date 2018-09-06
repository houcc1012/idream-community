package com.idream.commons.lib.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.idream.commons.lib.model.SysUserRoleRelation;

public interface SysUserRoleRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUserRoleRelation record);

    int insertSelective(SysUserRoleRelation record);

    SysUserRoleRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUserRoleRelation record);

    int updateByPrimaryKey(SysUserRoleRelation record);

    void updateRoleIdByUserId(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    void deleteByUserIdAndRoleId(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    SysUserRoleRelation selectByUserId(Integer userId);

    @Select("select count(*) from sys_user_role_relation where user_id=#{userId}")
    int selectExistByUserIdAndRoleId(Integer userId);

    @Delete("delete from sys_user_role_relation where user_id=#{userId}")
    int deleteByUserId(@Param("userId") Integer userId);

    @Select("select count(*) from sys_user_role_relation where role_id=#{roleId}")
    int countByRoleId(@Param("roleId") Integer roleId);
}