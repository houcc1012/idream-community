package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.activity.ActivityThemesDto;
import com.idream.commons.lib.dto.activity.FindActivityMessageResponseDto;
import com.idream.commons.lib.dto.activity.FindThemeDetailResponseDto;
import com.idream.commons.lib.dto.activity.ThemePublisherDto;
import com.idream.commons.lib.model.ActivityTheme;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

public interface ActivityThemeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityTheme record);

    int insertSelective(ActivityTheme record);

    ActivityTheme selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityTheme record);

    int updateByPrimaryKey(ActivityTheme record);

    //修改主题数据回显
    List<ActivityThemesDto> getActivityThemeByThemeId(Integer themeId);

    //修改主题
    @Update("update activity_theme set activity_id = #{activityId},title = #{title},content = #{content},type = #{type},update_time = #{updateTime} where user_id=#{userId} and id = #{id}")
    void updateActivityThemeByThemeId(ActivityTheme activityTheme, @Param("userId") int userId);

    @Delete("delete from activity_theme  where activity_id = #{activityId}")
    int deleteActivityThemeByActivityId(@Param("activityId") Integer activityId);

    List<ActivityThemesDto> getActivityThemeByActivityId(Integer activityId);

    ThemePublisherDto getTitleAndPublisherByThemeId(Integer themeId);

    @Select("SELECT count(1) FROM activity_theme WHERE activity_id = #{activityId}")
    Integer countTheme(@Param("activityId") Integer activityId);

    //根据活动id查询所有的主题id
    @Select("SELECT id FROM activity_theme WHERE activity_id = #{activityId}")
    List<Integer> getThemeIdByActivityId(Integer activityId);

    //根据主题id回显主题信息
    @Select("SELECT DISTINCT a.title themeTitle,a.image image,b.enabled isTask,a.content content from activity_theme a LEFT JOIN activity_task b ON a.id=b.theme_id WHERE a.id = #{themeId}")
    FindThemeDetailResponseDto getThemeDetailByThemeId(Integer themeId);

    @Select("SELECT b.start_time taskTime from activity_theme a INNER JOIN activity_task b ON a.id=b.theme_id WHERE a.id = #{themeId}")
    List<Date> getThemeTaskTimeByThemeId(Integer themeId);

    List<FindActivityMessageResponseDto> getActivityMessageByActivityId(Integer activityId);

    List<ActivityTheme> getThemesByActivityId(@Param("activityId") Integer activityId);

}