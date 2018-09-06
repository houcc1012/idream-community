package com.idream.feign;

import com.idream.commons.lib.dto.getui.GetuiResponseDto;
import com.idream.commons.lib.dto.getui.PushMessageRequestDto;
import com.idream.commons.lib.dto.wangyi.AddFriendRequestDto;
import com.idream.commons.lib.dto.wangyi.AddGroupManagerRequestDto;
import com.idream.commons.lib.dto.wangyi.AddUserToBlackListRequestDto;
import com.idream.commons.lib.dto.wangyi.AddUsersToGroupRequestDto;
import com.idream.commons.lib.dto.wangyi.CreateGroupRequestDto;
import com.idream.commons.lib.dto.wangyi.CreateGroupResponseDto;
import com.idream.commons.lib.dto.wangyi.CreateUserRequestDto;
import com.idream.commons.lib.dto.wangyi.CreateUserResponseDto;
import com.idream.commons.lib.dto.wangyi.DeleteFriendRequestDto;
import com.idream.commons.lib.dto.wangyi.DeleteGroupRequestDto;
import com.idream.commons.lib.dto.wangyi.GroupAdviceRequestDto;
import com.idream.commons.lib.dto.wangyi.IMUserInfoResponseDto;
import com.idream.commons.lib.dto.wangyi.KickOutUserFromGroupRequestDto;
import com.idream.commons.lib.dto.wangyi.LeaveGroupRequestDto;
import com.idream.commons.lib.dto.wangyi.MuteGroupRequestDto;
import com.idream.commons.lib.dto.wangyi.MuteTeamRequestDto;
import com.idream.commons.lib.dto.wangyi.MuteUserRequestDto;
import com.idream.commons.lib.dto.wangyi.QueryGroupAndUsersDetailResponseDto;
import com.idream.commons.lib.dto.wangyi.RemoveManagerFromGroupRequestDto;
import com.idream.commons.lib.dto.wangyi.UpdateFriendRequestDto;
import com.idream.commons.lib.dto.wangyi.UpdateGroupRequestDto;
import com.idream.commons.lib.dto.wangyi.UpdateGroupUserNickNameRequestDto;
import com.idream.commons.lib.dto.wangyi.UpdateIMUserInfoRequestDto;
import com.idream.commons.lib.dto.wangyi.WangYiCommonResponseDto;
import com.idream.feign.impl.SchedualThirdInterfaceFeginHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author hejiang
 */
@FeignClient(value = "third-interface", fallback = SchedualThirdInterfaceFeginHystric.class)
public interface FeignThirdInterfaceService {

    @RequestMapping(value = "/getui/pushMessageToAllAndroid", method = RequestMethod.POST, consumes = "application/json")
    GetuiResponseDto pushMessageToAllAndroid(@RequestBody PushMessageRequestDto pushMessageRequestDto);

    @RequestMapping(value = "/getui/pushMessageToAllIOS", method = RequestMethod.POST, consumes = "application/json")
    GetuiResponseDto pushMessageToAllIOS(@RequestBody PushMessageRequestDto pushMessageRequestDto);

    //网易注册用户
    @RequestMapping(value = "/wangyi/createUser", method = RequestMethod.POST, consumes = "application/json")
    CreateUserResponseDto createUser(@RequestBody CreateUserRequestDto dto);

    //获取用户信息
    @RequestMapping(value = "/wangyi/getIMUserInfo/{accids}", method = RequestMethod.GET, consumes = "application/json")
    List<IMUserInfoResponseDto> getIMUserInfo(@PathVariable(value = "accids") String accids);

    //更新用户信息
    @RequestMapping(value = "/wangyi/updateIMUserInfo", method = RequestMethod.POST, consumes = "application/json")
    WangYiCommonResponseDto updateIMUserInfo(@RequestBody UpdateIMUserInfoRequestDto dto);

    //添加好友
    @RequestMapping(value = "/wangyi/addFriend", method = RequestMethod.POST)
    WangYiCommonResponseDto addFriend(@RequestBody AddFriendRequestDto dto);

    //给好友加备注
    @RequestMapping(value = "/wangyi/updateFriendAlias", method = RequestMethod.POST)
    WangYiCommonResponseDto updateFriendAlias(@RequestBody UpdateFriendRequestDto dto);

    //删除好友
    @RequestMapping(value = "/wangyi/deleteFriend", method = RequestMethod.POST)
    WangYiCommonResponseDto deleteFriend(@RequestBody DeleteFriendRequestDto dto);

    //将用户加入黑名单或者取消黑名单
    @RequestMapping(value = "/wangyi/addOrRemoveUserToBlackList", method = RequestMethod.POST)
    WangYiCommonResponseDto addOrRemoveUserToBlackList(@RequestBody AddUserToBlackListRequestDto dto);

    //创建群组
    @RequestMapping(value = "/wangyi/createGroup", method = RequestMethod.POST)
    CreateGroupResponseDto createGroup(@RequestBody CreateGroupRequestDto dto);

    //用户加入群组
    @RequestMapping(value = "/wangyi/addUsersToGroup", method = RequestMethod.POST)
    WangYiCommonResponseDto addUsersToGroup(@RequestBody AddUsersToGroupRequestDto dto);

    //获取群组详细信息
    @RequestMapping(value = "/wangyi/queryGroupAndUsersDetail/{tid}", method = RequestMethod.GET)
    QueryGroupAndUsersDetailResponseDto queryGroupAndUsersDetail(@PathVariable(value = "tid") String tid);

    //更新群组信息
    @RequestMapping(value = "/wangyi/updateGroupInformation", method = RequestMethod.POST)
    WangYiCommonResponseDto updateGroupInformation(@RequestBody UpdateGroupRequestDto dto);

    //添加群组管理员
    @RequestMapping(value = "/wangyi/addGroupManager", method = RequestMethod.POST)
    WangYiCommonResponseDto addGroupManager(@RequestBody AddGroupManagerRequestDto dto);

    //移除群组管理员
    @RequestMapping(value = "/wangyi/removeManagerFromGroup", method = RequestMethod.POST)
    WangYiCommonResponseDto removeManagerFromGroup(@RequestBody RemoveManagerFromGroupRequestDto dto);

    //更改群内昵称
    @RequestMapping(value = "/wangyi/updateTeamNick", method = RequestMethod.POST)
    WangYiCommonResponseDto updateTeamNick(@RequestBody UpdateGroupUserNickNameRequestDto dto);

    //修改消息提醒开关
    @RequestMapping(value = "/wangyi/muteTeam", method = RequestMethod.POST)
    WangYiCommonResponseDto muteTeam(@RequestBody MuteTeamRequestDto dto);

    //群组禁言用户
    @RequestMapping(value = "/wangyi/muteTlist", method = RequestMethod.POST)
    WangYiCommonResponseDto muteTlist(@RequestBody MuteUserRequestDto dto);

    //群组禁言(解禁)
    @RequestMapping(value = "/wangyi/muteTlistAll", method = RequestMethod.POST)
    WangYiCommonResponseDto muteTlistAll(@RequestBody MuteGroupRequestDto dto);

    //从群众踢出用户
    @RequestMapping(value = "/wangyi/kickOutUserFromGroup", method = RequestMethod.POST)
    WangYiCommonResponseDto kickOutUserFromGroup(@RequestBody KickOutUserFromGroupRequestDto dto);

    //主动退群
    @RequestMapping(value = "/wangyi/leaveGroup", method = RequestMethod.POST)
    WangYiCommonResponseDto leaveGroup(@RequestBody LeaveGroupRequestDto dto);

    //删除群组
    @RequestMapping(value = "/wangyi/deleteGroup", method = RequestMethod.POST)
    WangYiCommonResponseDto deleteGroup(@RequestBody DeleteGroupRequestDto dto);

    //群组通知
    @RequestMapping(value = "/wangyi/groupAdvice", method = RequestMethod.POST)
    WangYiCommonResponseDto groupAdvice(@RequestBody GroupAdviceRequestDto dto);
}

