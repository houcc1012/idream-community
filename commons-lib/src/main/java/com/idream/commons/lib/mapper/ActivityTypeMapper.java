package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.activity.ActivityTagResponseDto;
import com.idream.commons.lib.dto.activity.ActivityTypeLibraryResponseDto;
import com.idream.commons.lib.dto.activity.AppLifeTypeDto;
import com.idream.commons.lib.dto.appactivity.AppActivityTypeResponseDto;
import com.idream.commons.lib.dto.appactivity.AppActivityUserLikeTypeResponseDto;
import com.idream.commons.lib.model.ActivityType;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ActivityTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityType record);

    int insertSelective(ActivityType record);

    ActivityType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityType record);

    int updateByPrimaryKey(ActivityType record);

    List<ActivityType> queryActivityType();

    /*
     * 根据活动id查询活动类型名
     */
    @Select("SELECT `at`.`name` FROM activity_type at INNER JOIN activity_info ai on `at`.id = ai.type_id WHERE ai.id = #{activityId}")
    String getActivityTypeByActivityId(Integer activityId);

    //通过前端传来的参数查询活动标签库中的一级标签
    List<ActivityTagResponseDto> selectActivityTypeListByActivityTag(@Param("label") String label);

    List<ActivityType> getActivityTypeByUserId(@Param("userId") Integer userId);

    @Select("SELECT * FROM activity_type WHERE NAME = #{name}")
    ActivityType getActivityTypeByTypeName(@Param("name") String name);

    @Select("SELECT id, name as typeName, icon,icon_light FROM activity_type WHERE kind = 1 and status = 1")
    List<AppLifeTypeDto> selectLifeType();

    List<ActivityTypeLibraryResponseDto> getActivityTypeLibrary();

    List<AppActivityUserLikeTypeResponseDto> getAllActivityType();

    //小程序根据城市编码查询当前城市的有活动的活动类型
    List<AppActivityTypeResponseDto> getMiniActivityTypeByCityCode(@Param("cityCode") String cityCode);

    //管理端 书屋列表 累计发布活动 活动类型查询查询
    @Select("SELECT * FROM activity_type WHERE `status` = 1")
    List<ActivityType> getActivityType();
}