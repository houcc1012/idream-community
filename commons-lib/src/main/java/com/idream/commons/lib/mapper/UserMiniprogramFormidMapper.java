package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.user.MiniProgramUserFormIdParams;
import com.idream.commons.lib.model.UserMiniprogramFormid;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMiniprogramFormidMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserMiniprogramFormid record);

    int insertSelective(UserMiniprogramFormid record);

    UserMiniprogramFormid selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserMiniprogramFormid record);

    int updateByPrimaryKey(UserMiniprogramFormid record);

    // 批量保存用户的formId
    int addBatchFormId(@Param("userId") Integer userId, @Param("formIdParams") List<MiniProgramUserFormIdParams> formIdParams);

    List<UserMiniprogramFormid> selectByUserId(@Param("userId") Integer userId);
}