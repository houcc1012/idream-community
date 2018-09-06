package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.ActivityExtension;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ActivityExtensionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityExtension record);

    int insertSelective(ActivityExtension record);

    ActivityExtension selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityExtension record);

    int updateByPrimaryKey(ActivityExtension record);

    @Select("SELECT * FROM activity_extension WHERE activity_id = #{activityId}")
    ActivityExtension selectByActivityId(@Param("activityId") Integer activityId);
}