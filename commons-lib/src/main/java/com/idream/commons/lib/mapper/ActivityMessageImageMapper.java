package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.ActivityMessageImage;

public interface ActivityMessageImageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityMessageImage record);

    int insertSelective(ActivityMessageImage record);

    ActivityMessageImage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityMessageImage record);

    int updateByPrimaryKey(ActivityMessageImage record);
}