package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.AdminComplaintHandleType;

public interface AdminComplaintHandleTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminComplaintHandleType record);

    int insertSelective(AdminComplaintHandleType record);

    AdminComplaintHandleType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminComplaintHandleType record);

    int updateByPrimaryKey(AdminComplaintHandleType record);
}