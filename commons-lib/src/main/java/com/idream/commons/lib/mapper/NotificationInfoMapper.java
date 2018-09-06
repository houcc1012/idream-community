package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.NotificationInfo;

public interface NotificationInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NotificationInfo record);

    int insertSelective(NotificationInfo record);

    NotificationInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NotificationInfo record);

    int updateByPrimaryKey(NotificationInfo record);
}