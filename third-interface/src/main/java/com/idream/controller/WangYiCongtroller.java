package com.idream.controller;

import com.idream.commons.lib.dto.wangyi.*;
import com.idream.service.WangYiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/10 20:23
 * @Description:
 */
@RestController
@RequestMapping("/wangyi")
@Api(tags = "网易云即时聊天", description = "网易云即时聊天")
public class WangYiCongtroller {

    @Autowired
    private WangYiService wangYiService;

    //网易用户注册
    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    @ApiOperation(value = "网易用户注册", notes = "网易用户注册", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public CreateUserResponseDto createIMUser(@RequestBody CreateUserRequestDto dto) {
        return wangYiService.createIMUser(dto);
    }

    //更新用户信息
    @RequestMapping(value = "/updateIMUserInfo", method = RequestMethod.POST)
    @ApiOperation(value = "更新用户信息", notes = "更新用户信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public WangYiCommonResponseDto updateIMUserInfo(@RequestBody UpdateIMUserInfoRequestDto dto) {
        return wangYiService.updateIMUserInfo(dto);
    }

    //添加好友
    @RequestMapping(value = "/addFriend", method = RequestMethod.POST)
    @ApiOperation(value = "添加好友", notes = "添加好友", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public WangYiCommonResponseDto addFriend(@RequestBody AddFriendRequestDto dto) {
        return wangYiService.addFriend(dto);
    }

    //给好友加备注
    @RequestMapping(value = "/updateFriendAlias", method = RequestMethod.POST)
    @ApiOperation(value = "给好友加备注", notes = "给好友加备注", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public WangYiCommonResponseDto updateFriendAlias(@RequestBody UpdateFriendRequestDto dto) {
        return wangYiService.updateFriendAlias(dto);
    }

    //删除好友
    @RequestMapping(value = "/deleteFriend", method = RequestMethod.POST)
    @ApiOperation(value = "删除好友", notes = "删除好友", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public WangYiCommonResponseDto deleteFriend(@RequestBody DeleteFriendRequestDto dto) {
        return wangYiService.deleteFriend(dto);
    }

    //获取用户信息
    @RequestMapping(value = "/getIMUserInfo/{accids}", method = RequestMethod.GET)
    @ApiOperation(value = "获取用户信息", notes = "获取用户信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<IMUserInfoResponseDto> getIMUserInfo(
            @NotBlank(message = "网易用户名不能为空") @ApiParam(value = "网易用户名,多个用逗号隔开", required = true) @PathVariable(value = "accids") String accids) {
        return wangYiService.getIMUserInfo(accids);
    }

    //将用户加入黑名单
    @RequestMapping(value = "/addOrRemoveUserToBlackList", method = RequestMethod.POST)
    @ApiOperation(value = "将用户加入黑名单", notes = "将用户加入黑名单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public WangYiCommonResponseDto addOrRemoveUserToBlackList(@RequestBody AddUserToBlackListRequestDto dto) {
        return wangYiService.addOrRemoveUserToBlackList(dto);
    }

    //更新并获取新token
    @RequestMapping(value = "/refreshToken/{accid}", method = RequestMethod.GET)
    @ApiOperation(value = "网易用户获取新token", notes = "网易用户获取新token", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public RefreshTokenResponseDto refreshToken(
            @NotBlank(message = "网易用户名") @ApiParam(value = "网易用户名", required = true) @PathVariable(value = "accid") String accid) {
        return wangYiService.refreshToken(accid);
    }

    //创建群组
    @RequestMapping(value = "/createGroup", method = RequestMethod.POST)
    @ApiOperation(value = "创建群组", notes = "创建群组", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public CreateGroupResponseDto createGroup(@RequestBody CreateGroupRequestDto dto) {
        return wangYiService.createGroup(dto);
    }

    //更新群组信息
    @RequestMapping(value = "/updateGroupInformation", method = RequestMethod.POST)
    @ApiOperation(value = "更新群组信息", notes = "更新群组信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public WangYiCommonResponseDto updateGroupInformation(@RequestBody UpdateGroupRequestDto dto) {
        return wangYiService.updateGroupInformation(dto);
    }

    //往群组中添加用户
    @RequestMapping(value = "/addUsersToGroup", method = RequestMethod.POST)
    @ApiOperation(value = "往群组中添加用户", notes = "往群组中添加用户", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public WangYiCommonResponseDto addUsersToGroup(@RequestBody AddUsersToGroupRequestDto dto) {
        return wangYiService.addUsersToGroup(dto);
    }

    //群信息与成员列表查询
    @RequestMapping(value = "/queryGroupAndUsersList", method = RequestMethod.POST)
    @ApiOperation(value = "群信息与成员列表查询", notes = "群信息与成员列表查询", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GroupAndMemberListResponseDto> queryGroupAndUsersList(@RequestBody QueryGroupAndUsersListRequestDto dto) {
        return wangYiService.queryGroupAndUsersList(dto);
    }

    //从群组中踢出用户
    @RequestMapping(value = "/kickOutUserFromGroup", method = RequestMethod.POST)
    @ApiOperation(value = "从群组中踢出用户", notes = "从群组中踢出用户", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public WangYiCommonResponseDto kickOutUserFromGroup(@RequestBody KickOutUserFromGroupRequestDto dto) {
        return wangYiService.kickOutUserFromGroup(dto);
    }

    //删除群组
    @RequestMapping(value = "/deleteGroup", method = RequestMethod.POST)
    @ApiOperation(value = "删除群组", notes = "删除群组", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public WangYiCommonResponseDto deleteGroup(@RequestBody DeleteGroupRequestDto dto) {
        return wangYiService.deleteGroup(dto);
    }

    //添加群组管理员
    @RequestMapping(value = "/addGroupManager", method = RequestMethod.POST)
    @ApiOperation(value = "添加群组管理员", notes = "添加群组管理员", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public WangYiCommonResponseDto addGroupManager(@RequestBody AddGroupManagerRequestDto dto) {
        return wangYiService.addGroupManager(dto);
    }

    //从群组中移除管理员
    @RequestMapping(value = "/removeManagerFromGroup", method = RequestMethod.POST)
    @ApiOperation(value = "从群组中移除管理员", notes = "从群组中移除管理员", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public WangYiCommonResponseDto removeManagerFromGroup(@RequestBody RemoveManagerFromGroupRequestDto dto) {
        return wangYiService.removeManagerFromGroup(dto);
    }

    //获取群组详细信息
    @RequestMapping(value = "/queryGroupAndUsersDetail/{tid}", method = RequestMethod.GET)
    @ApiOperation(value = "获取群组详细信息", notes = "获取群组详细信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public QueryGroupAndUsersDetailResponseDto queryGroupAndUsersDetail(
            @NotBlank(message = "群组id") @ApiParam(value = "群组id", required = true) @PathVariable(value = "tid") String tid) {
        return wangYiService.queryGroupAndUsersDetail(tid);
    }

    //获取用户参与的群组
    @RequestMapping(value = "/getUserJoinTeams/{accid}", method = RequestMethod.GET)
    @ApiOperation(value = "获取用户参与的群组", notes = "获取用户参与的群组", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JoinTeamsResponseDto getUserJoinTeams(
            @NotBlank(message = "群组id") @ApiParam(value = "群组id", required = true) @PathVariable(value = "accid") String accid) {
        return wangYiService.getUserJoinTeams(accid);
    }

    //移交群主
    @RequestMapping(value = "/changeGroupOwner", method = RequestMethod.POST)
    @ApiOperation(value = "移交群主", notes = "移交群主", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public WangYiCommonResponseDto changeGroupOwner(@RequestBody ChangeGroupOwnerRequestDto dto) {
        return wangYiService.changeGroupOwner(dto);
    }

    //更改群组内用户的昵称
    @RequestMapping(value = "/updateTeamNick", method = RequestMethod.POST)
    @ApiOperation(value = "更改群组内用户的昵称", notes = "更改群组内用户的昵称", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public WangYiCommonResponseDto updateTeamNick(@RequestBody UpdateGroupUserNickNameRequestDto dto) {
        return wangYiService.updateTeamNick(dto);
    }

    //修改消息提醒开关
    @RequestMapping(value = "/muteTeam", method = RequestMethod.POST)
    @ApiOperation(value = "修改消息提醒开关", notes = "修改消息提醒开关", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public WangYiCommonResponseDto muteTeam(@RequestBody MuteTeamRequestDto dto) {
        return wangYiService.muteTeam(dto);
    }

    //禁言群组用户
    @RequestMapping(value = "/muteTlist", method = RequestMethod.POST)
    @ApiOperation(value = "禁言群组用户", notes = "禁言群组用户", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public WangYiCommonResponseDto muteTlist(@RequestBody MuteUserRequestDto dto) {
        return wangYiService.muteTlist(dto);
    }

    //禁言整个群组
    @RequestMapping(value = "/muteTlistAll", method = RequestMethod.POST)
    @ApiOperation(value = "禁言整个群组", notes = "禁言整个群组", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public WangYiCommonResponseDto muteTlistAll(@RequestBody MuteGroupRequestDto dto) {
        return wangYiService.muteTlistAll(dto);
    }

    //主动退群
    @RequestMapping(value = "/leaveGroup", method = RequestMethod.POST)
    @ApiOperation(value = "主动退群", notes = "主动退群", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public WangYiCommonResponseDto leaveGroup(@RequestBody LeaveGroupRequestDto dto) {
        return wangYiService.leaveGroup(dto);
    }

    //群组通知
    @RequestMapping(value = "/groupAdvice", method = RequestMethod.POST)
    @ApiOperation(value = "群组通知(用户加群,退群)", notes = "群组通知(用户加群,退群)", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public WangYiCommonResponseDto groupAdvice(@RequestBody GroupAdviceRequestDto dto) {
        return wangYiService.groupAdvice(dto);
    }

}



