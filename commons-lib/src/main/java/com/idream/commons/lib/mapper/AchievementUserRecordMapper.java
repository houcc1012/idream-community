package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.AchievementUserRecord;
import org.apache.ibatis.annotations.Param;

public interface AchievementUserRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AchievementUserRecord record);

    int insertSelective(AchievementUserRecord record);

    AchievementUserRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AchievementUserRecord record);

    int updateByPrimaryKey(AchievementUserRecord record);

    /**
     * 通过用户id,category,kind
     *
     * @param userId
     * @param category
     * @param kind
     */
    AchievementUserRecord selectByUserIdAndCategoryAndKind(@Param("userId") Integer userId, @Param("category") Byte category, @Param("kind") Byte kind);

    void updateCountByPrimaryKey(@Param("id") Integer id, @Param("countValue") Integer countValue, @Param("cycleValue") Integer cycleValue);

    AchievementUserRecord selectByUserIdAndEventId(@Param("userId") Integer userId, @Param("eventId") Integer eventId);
}