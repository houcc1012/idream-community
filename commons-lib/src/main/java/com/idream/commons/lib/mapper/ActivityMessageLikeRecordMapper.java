package com.idream.commons.lib.mapper;

import org.apache.ibatis.annotations.Param;

import com.idream.commons.lib.model.ActivityMessageLikeRecord;

public interface ActivityMessageLikeRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityMessageLikeRecord record);

    int insertSelective(ActivityMessageLikeRecord record);

    ActivityMessageLikeRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityMessageLikeRecord record);

    int updateByPrimaryKey(ActivityMessageLikeRecord record);

    int countActivityMessageLikeByUserId(Integer userId);

    Integer deleteByUserIdAndMessageId(@Param("userId") Integer userId, @Param("messageId") Integer messageId);

}