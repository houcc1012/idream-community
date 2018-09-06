package com.idream.commons.lib.mapper;

import org.apache.ibatis.annotations.Select;

import com.idream.commons.lib.model.ActivityThemeExtension;

public interface ActivityThemeExtensionMapper {
    int deleteByPrimaryKey(Integer themeId);

    int insert(ActivityThemeExtension record);

    int insertSelective(ActivityThemeExtension record);

    ActivityThemeExtension selectByPrimaryKey(Integer themeId);

    int updateByPrimaryKeySelective(ActivityThemeExtension record);

    int updateByPrimaryKey(ActivityThemeExtension record);

    //根据主题id查询浏览量
    @Select("SELECT visit_count FROM activity_theme_extension WHERE theme_id = #{themeId}")
    Integer countAllVisit(Integer themeId);
}