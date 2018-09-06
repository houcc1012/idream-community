package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.activity.ActivityCommunityDto;
import com.idream.commons.lib.dto.activity.ActivityDto;
import com.idream.commons.lib.dto.activity.ActivityTaskDto;
import com.idream.commons.lib.dto.activity.MiniActivityAssociationTask;
import com.idream.commons.lib.dto.admin.ActivityRecentOneDto;
import com.idream.commons.lib.model.ActivityTask;
import com.idream.commons.lib.model.ActivityTheme;
import com.idream.commons.lib.model.CommunityInfo;
import com.idream.commons.lib.model.UserInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public interface ActivityTaskMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityTask record);

    int insertSelective(ActivityTask record);

    ActivityTask selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityTask record);

    int updateByPrimaryKey(ActivityTask record);


    /**
     * 查询指定活动地址
     */
    ActivityCommunityDto getactivityCommunity(Integer activityId);

    /**
     * 通过activity_id查询活动打卡表中的信息
     *
     * @param activityId
     */
    List<ActivityTask> getActivityTaskByActivityId(Integer activityId);

    /**
     * 获取活动id
     */
    LinkedList<ActivityDto> getactivitytaskId(Integer activityId);


    /**
     * 通过start_time和end_time查询theme_id
     *
     * @param startTime
     * @param endTime
     */
    List<ActivityTask> getThemeIdByTime(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /**
     * 修改主题
     *
     * @param activityTask
     */
    @Update("update activity_task set activity_id = #{activityId},theme_id = #{themeId},start_time = #{startTime},end_time = #{endTime},update_time = #{updateTime},status =#{status} where user_id=#{userId} and theme_id = #{themeId}")
    void updateActivityTaskByThemeId(ActivityTask activityTask, @Param("userId") int userId);

    /**
     * @Title: addTheme @Description: TODO @param @param theme @return
     * void @throws
     */
    void addTheme(ActivityTheme theme);


    /**
     * 获取当前用户UniondId
     *
     * @param userId
     */
    UserInfo userCurrent(Integer userId);


    /**
     * 判断用户是否是活动发起人
     */
    Integer getCountByUserIdAndActivityId(@Param("activityId") Integer activityId, @Param("userId") Integer userId);

    /**
     * 判断用户是否参加过活动
     */
    Integer countByUserIdAndActivityId(@Param("activityId") Integer activityId, @Param("userId") Integer userId);

    /*
     *判断用户是否退出过活动
     */
    Integer isExisted(@Param("activityId") Integer activityId, @Param("userId") Integer userId);

    /*
     *修改用户参加活动状态
     */
    void updateStatusById(Integer id);


    /**
     * 查询用户是否注册
     */
    @Select("SELECT phone FROM user_info WHERE id = #{userId} ")
    String isRegisted(@Param("userId") Integer userId);

    /**
     * 查询活动是否开启 : 根据状态判断
     *
     * @param activityId
     */
    @Select("SELECT a.id FROM activity_task a WHERE a.activity_id = #{activityId} AND a.`status` = 2")
    Integer findActivitTaskTecordtaskId(@Param("activityId") int activityId);

    /**
     * 查询活动是否开启  根据开始时间判断
     *
     * @param activityId
     */
    @Select("SELECT a.id FROM activity_task a WHERE a.start_time < NOW() AND a.activity_id = #{activityId}")
    Integer findActivitTaskTecordtaskId2(@Param("activityId") int activityId);

    /**
     * 当前用户今天是否打卡
     *
     * @param taskId
     * @param authUserId
     */
    @Select("SELECT b.user_id FROM activity_task_record b WHERE b.task_id = #{taskId} AND b.user_id = #{authUserId}")
    Integer findActivitTaskTecordUserInfo(@Param("taskId") Integer taskId, @Param("authUserId") Integer authUserId);

    /**
     * 查询未开始活动
     *
     * @param activityId
     */
    @Select("SELECT t.id,t.start_time as startTime, t.end_time as endTime, b.title, b.type FROM activity_task t LEFT JOIN activity_theme b ON t.theme_id = b.id WHERE t.start_time > NOW() AND t.activity_id = #{activityId}")
    ActivityTaskDto findStartActivity(@Param("activityId") Integer activityId);

    /**
     * Copyright: Copyright (c) 2018
     * Company: www.idream.com
     *
     * @param
     */
    @Select("SELECT t.id,t.start_time as startTime, t.end_time as endTime, b.title, b.type FROM activity_task t LEFT JOIN activity_theme b ON t.theme_id = b.id WHERE t.end_time > NOW() AND t.activity_id = #{activityId}")
    ActivityTaskDto findStartActivityEnd(@Param("activityId") Integer activityId);

    /**
     * 查询打卡详情
     * <p>
     * Copyright: Copyright (c) 2018
     * Company: www.idream.com
     *
     * @param
     */
    @Select("SELECT a.id, a.start_time AS startTime, a.end_time AS endTime, b.title, c.subtitle, b.content contents,b.type FROM activity_task a LEFT JOIN activity_theme b ON a.theme_id = b.id LEFT JOIN activity_info c ON a.activity_id = c.id WHERE a.activity_id = #{activityId} AND NOW() < a.end_time ORDER BY a.end_time LIMIT 1")
    ActivityTaskDto findTaskDetail(@Param("activityId") Integer activityId);

    /**
     * 判断是不是发起者
     *
     * @param authUserId
     * @param activityId
     */
    @Select("SELECT a.user_id FROM `activity_info` a WHERE a.user_id = #{authUserId} and a.id = #{activityId}")
    Integer getactivityInfoUserById(@Param("authUserId") Integer authUserId, @Param("activityId") int activityId);

    @Delete("DELETE FROM `activity_task` where activity_id = #{activityId}")
    void deleteActivityTaskByActivityId(@Param("activityId") Integer activityId);

    void updateActivityTask(ActivityTaskDto bean);

    /*
     * 根据主题id查询打卡记录数
     */
    @Select("SELECT id FROM activity_task WHERE theme_id = #{themeId}")
    List<Integer> getTaskIdsByThemeId(Integer themeId);

    @Select("select count(*) from activity_task where activity_id=#{activityId} and DATE(start_time)=DATE(#{startDate})")
    int selectExistByActivityIdAndDate(@Param("activityId") Integer activityId, @Param("startDate") Date startDate);

    void updateByActivityIdAndStartTime(ActivityTask record);

    CommunityInfo getCommunityInfoByActivityId(@Param("activityId") Integer activityId);

    //小程序发现页 活动为多次时 返回离当前时间最近的一次
    ActivityRecentOneDto getActivityRecentOne(@Param("activityId") Integer activityId);

    List<Date> selectTaskTimeByActivityId(@Param("activityId") Integer activityId);

    //根据活动id,查询社团相关信息
    MiniActivityAssociationTask selectActiviyAssociationByActiviyId(@Param("activityId") Integer activityId);

    //小程序用户已经参加的活动
    ActivityRecentOneDto getMiniActivityRecentOne(Integer activityId);
}