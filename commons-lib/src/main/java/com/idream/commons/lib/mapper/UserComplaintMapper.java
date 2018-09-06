package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.adminuser.AdminComplaintDto;
import com.idream.commons.lib.dto.adminuser.AdminComplaintParams;
import com.idream.commons.lib.model.UserComplaint;

import java.util.List;

public interface UserComplaintMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserComplaint record);

    int insertSelective(UserComplaint record);

    UserComplaint selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserComplaint record);

    int updateByPrimaryKey(UserComplaint record);

    /**
     * 管理端查询用户举报
     *
     * @param query
     */
    List<AdminComplaintDto> selectAdminComplaintByQuery(AdminComplaintParams query);
}