package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.activity.ActivityByRegionAndCityCodeDto;
import com.idream.commons.lib.dto.activity.ActivityByRegionAndCityCodeResonDto;
import com.idream.commons.lib.model.ActivityDisplayRelation;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ActivityDisplayRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityDisplayRelation record);

    int insertSelective(ActivityDisplayRelation record);

    ActivityDisplayRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityDisplayRelation record);

    int updateByPrimaryKey(ActivityDisplayRelation record);

    //根据社区id获取活动id
    List<Integer> getActivityIdByRegionId(Integer regionId);

    //根据社区id和城市编码获取活动id
    // List<Integer> getActivityIdByRegionIdAndCityCode(Integer regionId);

    @Delete("DELETE FROM activity_display_relation WHERE activity_id = #{activityId}")
    void deleteByActivityId(@Param("activityId") Integer activityId);

    @Select("SELECT * FROM activity_display_relation WHERE activity_id = #{activityId} and from_region_id IS NOT NULL")
    List<ActivityDisplayRelation> selectByActivityId(@Param("activityId") Integer activityId);

    List<ActivityByRegionAndCityCodeResonDto> getActivityIdByRegionIdAndCityCode(ActivityByRegionAndCityCodeDto dto);

    @Select("SELECT display_type FROM activity_display_relation where activity_id=#{activityId} ORDER BY display_type ASC LIMIT 1")
    Byte selectDisplayTypeByActivityId(@Param("activityId") Integer activityId);
}