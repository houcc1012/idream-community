package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.UserComplaintHandleRecord;
import org.apache.ibatis.annotations.Param;

public interface UserComplaintHandleRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserComplaintHandleRecord record);

    int insertSelective(UserComplaintHandleRecord record);

    UserComplaintHandleRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserComplaintHandleRecord record);

    int updateByPrimaryKey(UserComplaintHandleRecord record);

    UserComplaintHandleRecord queryUserBanStatus(@Param("userId") Integer userId);

}