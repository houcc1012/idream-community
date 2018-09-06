package com.idream.service;

import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.activity.UpdateGroupInfoRequestParam;
import com.idream.commons.lib.dto.wangyi.*;

import java.util.List;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/15 14:31
 * @Description:
 */
public interface UserIMGroupService {

    /**
     * 创建群组.
     *
     * @param param
     */
    CreateGroupResponseDto createIMGroup(CreateGroupRequestParam param);

    /**
     * 加入群组
     *
     * @param param
     */
    int doJoinGroup(JoinGroupRequestParam param);

    /**
     * 获取群组详情
     *
     * @param tid
     */
    QueryGroupAndUsersDetailResponseDto queryGroupAndUsersDetail(String tid);

    /**
     * 更新群组信息
     *
     * @param param
     */
    int updateGroupInformation(JSONPublicParam<UpdateGroupRequestParam> param);

    /**
     * 任命群组管理员
     *
     * @param param
     */
    int addGroupManager(AddGroupManagerRequestParam param);

    /**
     * 移除管理员
     *
     * @param param
     */
    int removeManagerFromGroup(RemoveManagerFromGroupRequestParam param);

    /**
     * 修改群组内用户的昵称
     *
     * @param param
     */
    int updateGroupMemberNickName(UpdateGroupUserNickNameRequestParam param);

    /**
     * 修改消息提醒开关
     *
     * @param param
     */
    WangYiCommonResponseDto muteTeam(MuteTeamRequestParam param);

    /**
     * 群组内禁言用户
     *
     * @param param
     */
    int doMuteTlist(MuteUserRequestParam param);

    /**
     * 群组禁言,解禁
     *
     * @param param
     */
    int doMuteTlistAll(MuteGroupRequestParam param);

    /**
     * 从群组中踢出用户
     *
     * @param param
     */
    int kickOutUserFromGroup(KickOutUserFromGroupRequestParam param);

    /**
     * 主动退群
     *
     * @param param
     */
    int doLeaveGroup(LeaveGroupRequestParam param);

    /**
     * 解散群组
     *
     * @param param
     */
    WangYiCommonResponseDto deleteGroup(DeleteGroupRequestParam param);

    /**
     * 查询群详情
     *
     * @param param
     */
    ActivityGroupInfoResponseDto activityGroupInfo(ActivityGroupInfoRequestParam param);

    /**
     * 群组黑名单类别
     *
     * @param tid
     */
    List<GroupMuteListResponseDto> groupMuteList(Integer tid);

    /**
     * 获取用户参与的群组列表
     *
     * @param userId
     */
    List<JoinGroupListResposeDto> queryJoinGroupList(Integer userId);


    /**
     * 根据活动id查询tid
     *
     * @param activityId
     */
    QueryGroupByActivityId queryGroupByActivityId(Integer activityId);

    /**
     * 根据群组id查询活动id
     *
     * @param tid
     */
    QueryActivityIdByTidResponseDto queryActivityIdByTid(String tid);

    /**
     * 测试用户创建100个群.... 测试用的
     *
     * @param param
     */
    CreateGroupResponseDto createGroupDemo(CreateGroupRequestParam param);

    /**
     * 更新网易群组名称,头像,公告,描述
     *
     * @param param
     */
    int updateGroupInfo(UpdateGroupInfoRequestParam param);

    /**
     * 加入群组
     *
     * @param param
     */
    int doJoinGroupByTid(JoinGroupByTidRequestParam param);
}
