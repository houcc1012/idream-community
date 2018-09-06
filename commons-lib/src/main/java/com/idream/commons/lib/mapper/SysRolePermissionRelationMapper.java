package com.idream.commons.lib.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.idream.commons.lib.dto.auth.RoleVO;
import com.idream.commons.lib.model.SysRolePermissionRelation;

public interface SysRolePermissionRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRolePermissionRelation record);

    int insertSelective(SysRolePermissionRelation record);

    SysRolePermissionRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRolePermissionRelation record);

    int updateByPrimaryKey(SysRolePermissionRelation record);

    /**
     * 批量插入
     *
     * @param id
     * @param Ids
     * @param string void
     */
    void batchInsert(@Param("id") Integer id, @Param("ids") List<Integer> Ids, @Param("type") String type);

    void batchDeleteByRoleId(Integer roleId);
}