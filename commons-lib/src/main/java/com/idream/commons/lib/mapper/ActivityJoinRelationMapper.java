package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.ActivityJoinRelation;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ActivityJoinRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityJoinRelation record);

    int insertSelective(ActivityJoinRelation record);

    ActivityJoinRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityJoinRelation record);

    int updateByPrimaryKey(ActivityJoinRelation record);

    @Delete("DELETE FROM activity_join_relation WHERE activity_id = #{activityId}")
    void deleteByActivityId(@Param("activityId") Integer activityId);

    @Select("select a.* from activity_join_relation a INNER JOIN activity_info b ON a.activity_id = b.id where a.activity_id = #{activityId} and now()< b.end_time")
    List<ActivityJoinRelation> selectByActivityId(@Param("activityId") Integer activityId);

    @Select("select * from activity_join_relation where join_id = #{regionId} and activity_id = #{activityId} and join_type = 40  and now()>start_time and now()<end_time")
    ActivityJoinRelation selectByActivityIdAndRegionId(@Param("regionId") Integer regionId, @Param("activityId") Integer activityId);

    @Select("select  a.join_id from activity_join_relation a INNER JOIN region_group_relation b ON a.join_id = b.group_id WHERE a.activity_id = #{activityId} AND a.join_type=60 AND b.region_id = #{regionId AND now()>a.start_time and now()<a.end_time")
    List<Integer> selectGroupIdByRegionIdAndActivityId(@Param("regionId") Integer regionId, @Param("activityId") Integer activityId);


}