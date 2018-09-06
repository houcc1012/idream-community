package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.activity.AdminActivityInviteStatisticsDto;
import com.idream.commons.lib.dto.activity.AdminActivityInviteStatisticsParams;
import com.idream.commons.lib.dto.activity.AdminActivityStatisticsDto;
import com.idream.commons.lib.dto.activity.FindInvitationResponseDto;
import com.idream.commons.lib.dto.appactivity.AppActivityInvitationRankResponseDto;
import com.idream.commons.lib.model.ActivityInvitationRecord;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface ActivityInvitationRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityInvitationRecord record);

    int insertSelective(ActivityInvitationRecord record);

    ActivityInvitationRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityInvitationRecord record);

    int updateByPrimaryKey(ActivityInvitationRecord record);

    Integer getUserIdByUnionId(@Param(value = "unionId") String unionId);

    //查询活动邀请人数
    @Select("SELECT count(1) FROM activity_invitation_record WHERE activity_id = #{activityId} AND `status` = 1")
    Integer countInvitation(@Param("activityId") Integer activityId);

    //查询邀请信息
    List<FindInvitationResponseDto> getInvitationInfoByActivityId(@Param("activityId") Integer activityId);

    @Select("SELECT * FROM activity_invitation_record WHERE activity_id = #{activityId} AND invitation_id=#{inviteId} AND accept_id=#{acceptId} AND `status`=1")
    ActivityInvitationRecord getRecordByInviteIdAndAcceptId(@Param("activityId") Integer activityId, @Param("inviteId") Integer inviteId, @Param("acceptId") Integer acceptId);

    List<AdminActivityStatisticsDto> selectInvitesByDate(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    Integer selectTotalInvites();

    // 查询
    ActivityInvitationRecord getIdByOtherId(@Param("userId") Integer userId, @Param("activityId") Integer activityId, @Param("sharedUserId") Integer sharedUserId);

    List<AdminActivityInviteStatisticsDto> selectActivityInviteDetail(AdminActivityInviteStatisticsParams params);
}