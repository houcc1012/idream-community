package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.achievement.AdminAchievementUserDto;
import com.idream.commons.lib.dto.achievement.AdminAchievementUserParams;
import com.idream.commons.lib.dto.achievement.UserAchieveDetailDto;
import com.idream.commons.lib.dto.activity.AdminUserAchievementDto;
import com.idream.commons.lib.dto.activity.AppUserAchievementDto;
import com.idream.commons.lib.dto.user.UserTagDto;
import com.idream.commons.lib.model.AchievementUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface AchievementUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AchievementUser record);

    int insertSelective(AchievementUser record);

    AchievementUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AchievementUser record);

    int updateByPrimaryKey(AchievementUser record);

    List<AppUserAchievementDto> selectAchievementInfoListByUserId(@Param("userId") Integer userId);

    List<AdminUserAchievementDto> selectCompletedAchievement(@Param("userId") Integer userId);

    AchievementUser selectByUserIdAndAchieveId(@Param("userId") Integer userId, @Param("achieveId") Integer achieveId);

    void updateCompletedByUserIdAndAchieveId(@Param("userId") Integer userId, @Param("achieveId") Integer achieveId, @Param("createTime") Date date);


    UserAchieveDetailDto selectDetailByUserIdAndAchievementId(@Param("userId") Integer userId, @Param("achievementId") Integer achievementId);

    List<AchievementUser> selectUncompleteByEventIdAndUserId(@Param("eventId") Integer eventId, @Param("userId") Integer userId);

    @Select("select count(*) from achievement_user where achieve_id=#{achieveId}")
    int selectCountByAchieveId(@Param("achieveId") Integer achieveId);

    List<AdminAchievementUserDto> selectCompletedUserByAchievementId(AdminAchievementUserParams params);

    //根据用户id查询用户标签信息
    @Select("select a.achieve_id as id, a.achieve_name as label from achievement_user a where a.user_id = #{userId} order by a.id desc")
    List<UserTagDto> selectAchievementByUserId(@Param("userId") Integer userId);
}