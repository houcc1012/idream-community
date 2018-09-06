package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.achievement.AchievementPageDto;
import com.idream.commons.lib.dto.achievement.AdminAchievementPageParams;
import com.idream.commons.lib.model.AchievementInfo;
import com.idream.commons.lib.model.AchievementUserRecord;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AchievementInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AchievementInfo record);

    int insertSelective(AchievementInfo record);

    AchievementInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AchievementInfo record);

    int updateByPrimaryKey(AchievementInfo record);

    List<AchievementInfo> selectUncompletedByUserIdAndCategoryAndKind(@Param("userId") Integer userId, @Param("category") Byte category, @Param("kind") Byte kind);

    List<AchievementPageDto> selectAdminPageList(AdminAchievementPageParams param);

    @Update("update achievement_info set status=#{status} where id=#{achievementId}")
    void updateStatusByAchievementId(Integer achievementId, byte status);
}