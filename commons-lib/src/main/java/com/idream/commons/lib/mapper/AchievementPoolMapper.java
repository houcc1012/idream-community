package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.AchievementPool;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AchievementPoolMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AchievementPool record);

    int insertSelective(AchievementPool record);

    AchievementPool selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AchievementPool record);

    int updateByPrimaryKey(AchievementPool record);

    @Select("select * from achievement_pool where achieve_id=#{achievementId}")
    List<AchievementPool> selectByAchieveId(@Param("achievementId") Integer achievementId);

    @Delete("delete from achievement_pool where achieve_id=#{achievementId} and type=#{type}")
    void deleteByAchieveIdAndType(@Param("achievementId") Integer achievementId, @Param("type") Byte type);
}