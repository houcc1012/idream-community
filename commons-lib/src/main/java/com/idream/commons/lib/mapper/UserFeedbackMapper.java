package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.UserFeedback;

public interface UserFeedbackMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserFeedback record);

    int insertSelective(UserFeedback record);

    UserFeedback selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserFeedback record);

    int updateByPrimaryKey(UserFeedback record);

}