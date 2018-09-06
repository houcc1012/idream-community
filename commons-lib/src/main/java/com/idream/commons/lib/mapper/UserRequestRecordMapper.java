package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.UserRequestRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRequestRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRequestRecord record);

    int insertSelective(UserRequestRecord record);

    UserRequestRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRequestRecord record);

    int updateByPrimaryKey(UserRequestRecord record);

    int insertBatchRecord(@Param("records") List<UserRequestRecord> records);
}