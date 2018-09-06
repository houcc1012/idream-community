package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.ActivityCancelRecord;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ActivityCancelRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityCancelRecord record);

    int insertSelective(ActivityCancelRecord record);

    ActivityCancelRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityCancelRecord record);

    int updateByPrimaryKey(ActivityCancelRecord record);

    @Select("SELECT * FROM activity_cancel_record WHERE activity_id = #{activityId}")
    List<ActivityCancelRecord> getByActivityId(@Param("activityId") Integer activityId);

}