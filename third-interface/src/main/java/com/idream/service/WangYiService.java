package com.idream.service;

import com.idream.commons.lib.dto.wangyi.*;

import java.util.List;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/10 17:33
 * @Description:
 */
public interface WangYiService {

    /**
     * 注册网易云用户
     *
     * @param createUserRequestDto
     */
    CreateUserResponseDto createIMUser(CreateUserRequestDto createUserRequestDto);

    /**
     * 更新用户信息
     *
     * @param dto
     */
    WangYiCommonResponseDto updateIMUserInfo(UpdateIMUserInfoRequestDto dto);

    /**
     * 添加好友
     *
     * @param dto
     */
    WangYiCommonResponseDto addFriend(AddFriendRequestDto dto);

    /**
     * 给好友加备注
     *
     * @param dto
     */
    WangYiCommonResponseDto updateFriendAlias(UpdateFriendRequestDto dto);

    /**
     * 删除好友
     *
     * @param dto
     */
    WangYiCommonResponseDto deleteFriend(DeleteFriendRequestDto dto);

    /**
     * 获取用户信息
     *
     * @param accids
     */
    List<IMUserInfoResponseDto> getIMUserInfo(String accids);

    /**
     * 将用户加入黑名单
     *
     * @param dto
     */
    WangYiCommonResponseDto addOrRemoveUserToBlackList(AddUserToBlackListRequestDto dto);


    /**
     * 网易用户刷新token
     *
     * @param accid 用户名
     */
    RefreshTokenResponseDto refreshToken(String accid);

    /**
     * 创建群组
     *
     * @param createGroupRequestDto
     */
    CreateGroupResponseDto createGroup(CreateGroupRequestDto createGroupRequestDto);

    /**
     * 往群组中添加用户
     */
    WangYiCommonResponseDto addUsersToGroup(AddUsersToGroupRequestDto dto);

    /**
     * 更新群组信息
     *
     * @param dto
     */
    WangYiCommonResponseDto updateGroupInformation(UpdateGroupRequestDto dto);

    /**
     * 群信息与成员列表查询
     *
     * @param dto
     */
    List<GroupAndMemberListResponseDto> queryGroupAndUsersList(QueryGroupAndUsersListRequestDto dto);

    /**
     * 从群组中踢出用户
     *
     * @param dto
     */
    WangYiCommonResponseDto kickOutUserFromGroup(KickOutUserFromGroupRequestDto dto);

    /**
     * 删除群组
     *
     * @param dto
     */
    WangYiCommonResponseDto deleteGroup(DeleteGroupRequestDto dto);

    /**
     * 添加群组管理员
     *
     * @param dto
     */
    WangYiCommonResponseDto addGroupManager(AddGroupManagerRequestDto dto);

    /**
     * 从群组中移除管理员
     *
     * @param dto
     */
    WangYiCommonResponseDto removeManagerFromGroup(RemoveManagerFromGroupRequestDto dto);

    /**
     * 获取群组详细信息
     *
     * @param tid
     */
    QueryGroupAndUsersDetailResponseDto queryGroupAndUsersDetail(String tid);

    /**
     * 获取用户参与的群组
     *
     * @param accid
     */
    JoinTeamsResponseDto getUserJoinTeams(String accid);

    /**
     * 移交群主
     *
     * @param dto
     */
    WangYiCommonResponseDto changeGroupOwner(ChangeGroupOwnerRequestDto dto);

    /**
     * 更改群组内用户的昵称
     *
     * @param dto
     */
    WangYiCommonResponseDto updateTeamNick(UpdateGroupUserNickNameRequestDto dto);

    /**
     * 修改消息提醒开关
     *
     * @param dto
     */
    WangYiCommonResponseDto muteTeam(MuteTeamRequestDto dto);

    /**
     * 群组禁言群成员
     *
     * @param dto
     */
    WangYiCommonResponseDto muteTlist(MuteUserRequestDto dto);

    /**
     * 群组禁言
     *
     * @param dto
     */
    WangYiCommonResponseDto muteTlistAll(MuteGroupRequestDto dto);

    /**
     * 主动退群
     *
     * @param dto
     */
    WangYiCommonResponseDto leaveGroup(LeaveGroupRequestDto dto);

    /**
     * 用户加入群组通知
     *
     * @param dto
     */
    WangYiCommonResponseDto groupAdvice(GroupAdviceRequestDto dto);
}
