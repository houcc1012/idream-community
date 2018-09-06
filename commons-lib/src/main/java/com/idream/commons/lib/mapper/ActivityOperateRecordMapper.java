package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.admin.ActivityOperateRecordResponseDto;
import com.idream.commons.lib.model.ActivityOperateRecord;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ActivityOperateRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityOperateRecord record);

    int insertSelective(ActivityOperateRecord record);

    ActivityOperateRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityOperateRecord record);

    int updateByPrimaryKey(ActivityOperateRecord record);

    List<ActivityOperateRecordResponseDto> getCancelRecordByActivityId(@Param("activityId") Integer activityId);

    List<ActivityOperateRecordResponseDto> getCheckRecordByActivityId(@Param("activityId") Integer activityId);

    @Select("SELECT count(*) FROM activity_operate_record WHERE activity_id=#{activityId} AND category=#{category} AND type=#{type}")
    Integer selectCountOperateRecordByActivityId(@Param("activityId") Integer activityId, @Param("category") byte category, @Param("type") byte type);
}