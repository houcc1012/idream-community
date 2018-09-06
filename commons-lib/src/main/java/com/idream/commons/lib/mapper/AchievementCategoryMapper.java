package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.AchievementCategory;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AchievementCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AchievementCategory record);

    int insertSelective(AchievementCategory record);

    AchievementCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AchievementCategory record);

    int updateByPrimaryKey(AchievementCategory record);

    @Select("select * from achievement_category")
    List<AchievementCategory> selectAll();
}