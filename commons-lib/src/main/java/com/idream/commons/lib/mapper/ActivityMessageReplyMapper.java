package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.ActivityMessageReply;

public interface ActivityMessageReplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityMessageReply record);

    int insertSelective(ActivityMessageReply record);

    ActivityMessageReply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityMessageReply record);

    int updateByPrimaryKey(ActivityMessageReply record);
}