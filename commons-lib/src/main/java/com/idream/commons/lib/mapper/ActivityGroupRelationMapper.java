package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.activity.RegionGroupInfoExtention;
import com.idream.commons.lib.dto.admin.BookHouseListActivityDetailDto;
import com.idream.commons.lib.dto.admin.BookHouseListActivityDetailParams;
import com.idream.commons.lib.model.ActivityGroupRelation;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ActivityGroupRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityGroupRelation record);

    int insertSelective(ActivityGroupRelation record);

    ActivityGroupRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityGroupRelation record);

    int updateByPrimaryKey(ActivityGroupRelation record);

    //根据活动id和组织类型查询组织名称
    @Select("select group_name from activity_group_relation where activity_id = #{activityId} and group_type = 40")
    List<String> getRegionNameByActivityId(@Param("activityId") Integer activityId);

    //根据活动id和组织类型查询组织名称
    @Select("select group_name from activity_group_relation where activity_id = #{activityId} and group_type = 60")
    List<String> getGroupNameByActivityId(@Param("activityId") Integer activityId);

    //根据活动id和组织类型查询组织名称
    @Select("select group_name from activity_group_relation where activity_id = #{activityId} and group_type = 70")
    List<String> getBookNameByActivityId(@Param("activityId") Integer activityId);

    List<Integer> selectRegionIdsByActivityId(@Param("activityId") Integer activityId);

    @Select("SELECT DISTINCT b.id FROM activity_group_relation a INNER JOIN region_group_info b ON a.group_id = b.id WHERE a.activity_id = #{activityId} AND b.category=1 AND a.group_type=60")
    List<Integer> selectRegionGroupIdsByActivityId(@Param("activityId") Integer activityId);

    List<RegionGroupInfoExtention> getRegionGroupInfoByRegionId(@Param("regionId") Integer regionId);

    @Delete("DELETE FROM activity_group_relation WHERE activity_id = #{activityId}")
    void deleteByActivityId(@Param("activityId") Integer activityId);

    @Select("select * from activity_group_relation WHERE activity_id = #{activityId} AND group_type =40")
    List<ActivityGroupRelation> selectByActivityIdAndGroupType(@Param("activityId") Integer activityId);

    //后台管理 书屋列表 活动详情
    List<BookHouseListActivityDetailDto> getActivityInfoByBookId(BookHouseListActivityDetailParams bookHouseListActivityDetailParams);
}