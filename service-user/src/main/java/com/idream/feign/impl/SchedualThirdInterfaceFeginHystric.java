package com.idream.feign.impl;

import com.idream.commons.lib.dto.getui.GetuiResponseDto;
import com.idream.commons.lib.dto.getui.PushMessageRequestDto;
import com.idream.commons.lib.dto.wangyi.*;
import com.idream.feign.FeignThirdInterfaceService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author hejiang
 */
@Component
public class SchedualThirdInterfaceFeginHystric implements FeignThirdInterfaceService {

    @Override
    public GetuiResponseDto pushMessageToAllAndroid(@RequestBody PushMessageRequestDto pushMessageRequestDto) {
        return GetuiResponseDto.returnErrorData("错误啦!");
    }

    @Override
    public GetuiResponseDto pushMessageToAllIOS(@RequestBody PushMessageRequestDto pushMessageRequestDto) {
        return GetuiResponseDto.returnErrorData("错误啦!");
    }

    @Override
    public CreateUserResponseDto createUser(@RequestBody CreateUserRequestDto dto) {
        return null;
    }

    @Override
    public List<IMUserInfoResponseDto> getIMUserInfo(String accids) {
        return null;
    }

    @Override
    public WangYiCommonResponseDto updateIMUserInfo(@RequestBody UpdateIMUserInfoRequestDto dto) {
        return null;
    }

    @Override
    public WangYiCommonResponseDto addFriend(@RequestBody AddFriendRequestDto dto) {
        return null;
    }

    @Override
    public WangYiCommonResponseDto updateFriendAlias(@RequestBody UpdateFriendRequestDto dto) {
        return null;
    }

    @Override
    public WangYiCommonResponseDto deleteFriend(@RequestBody DeleteFriendRequestDto dto) {
        return null;
    }

    @Override
    public WangYiCommonResponseDto addOrRemoveUserToBlackList(@RequestBody AddUserToBlackListRequestDto dto) {
        return null;
    }

    @Override
    public CreateGroupResponseDto createGroup(@RequestBody CreateGroupRequestDto dto) {
        return null;
    }

    @Override
    public WangYiCommonResponseDto addUsersToGroup(@RequestBody AddUsersToGroupRequestDto dto) {
        return WangYiCommonResponseDto.returnErrorData("错误啦!");
    }

    @Override
    public QueryGroupAndUsersDetailResponseDto queryGroupAndUsersDetail(@PathVariable(value = "tid") String tid) {
        return null;
    }

    @Override
    public WangYiCommonResponseDto updateGroupInformation(@RequestBody UpdateGroupRequestDto dto) {
        return null;
    }

    @Override
    public WangYiCommonResponseDto addGroupManager(@RequestBody AddGroupManagerRequestDto dto) {
        return null;
    }

    @Override
    public WangYiCommonResponseDto removeManagerFromGroup(@RequestBody RemoveManagerFromGroupRequestDto dto) {
        return null;
    }

    @Override
    public WangYiCommonResponseDto updateTeamNick(@RequestBody UpdateGroupUserNickNameRequestDto dto) {
        return null;
    }

    @Override
    public WangYiCommonResponseDto muteTeam(@RequestBody MuteTeamRequestDto dto) {
        return null;
    }

    @Override
    public WangYiCommonResponseDto muteTlist(@RequestBody MuteUserRequestDto dto) {
        return null;
    }

    @Override
    public WangYiCommonResponseDto muteTlistAll(@RequestBody MuteGroupRequestDto dto) {
        return null;
    }

    @Override
    public WangYiCommonResponseDto kickOutUserFromGroup(@RequestBody KickOutUserFromGroupRequestDto dto) {
        return null;
    }

    @Override
    public WangYiCommonResponseDto leaveGroup(@RequestBody LeaveGroupRequestDto dto) {
        return null;
    }

    @Override
    public WangYiCommonResponseDto deleteGroup(@RequestBody DeleteGroupRequestDto dto) {
        return null;
    }

    @Override
    public WangYiCommonResponseDto groupAdvice(@RequestBody GroupAdviceRequestDto dto) {
        return null;
    }
}
