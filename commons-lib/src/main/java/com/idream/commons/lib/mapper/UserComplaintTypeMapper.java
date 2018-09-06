package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.user.AppComplaintTypeDto;
import com.idream.commons.lib.model.UserComplaintType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserComplaintTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserComplaintType record);

    int insertSelective(UserComplaintType record);

    UserComplaintType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserComplaintType record);

    int updateByPrimaryKey(UserComplaintType record);

    List<AppComplaintTypeDto> selectComplaintTypeDtoList(@Param("typeId") Integer typeId);
}