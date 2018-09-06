package com.idream.controller.app;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.activity.UpdateGroupInfoRequestParam;
import com.idream.commons.lib.dto.wangyi.*;
import com.idream.service.UserIMGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author: ZhengGaosheng
 * @date: 2018/5/15 14:29
 * @description:
 */
@RestController
@Api(description = "APPUserIMGroupController", tags = "用户IM群组(APP)")
@RequestMapping("/app/userimgroup")
public class APPUserIMGroupController {

    @Autowired
    private UserIMGroupService userIMGroupService;

    @RequestMapping(value = "/createGroup", method = RequestMethod.POST)
    @ApiOperation(value = "创建群组", notes = "创建群组", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<CreateGroupResponseDto> createGroup(@RequestBody CreateGroupRequestParam param) {
        CreateGroupResponseDto imGroup = userIMGroupService.createIMGroup(param);
        String code = RetCodeConstants.SUCESS;
        String msg = "创建成功";
        if (imGroup == null) {
            code = RetCodeConstants.WANGYI_CREATEGROUP_FAILED;
            msg = "创建失败";
        }
        return JSONPublicDto.returnSuccessData(code, msg, imGroup);
    }

    //用户加入群组
    @RequestMapping(value = "/joinGroup", method = RequestMethod.POST)
    @ApiOperation(value = "用户加入群组", notes = "用户加入群组", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto joinGroup(@RequestBody JSONPublicParam<JoinGroupRequestParam> param) {
        param.getRequestParam().setUserId(param.getAuthUserInfo().getUserId());
        int result = userIMGroupService.doJoinGroup(param.getRequestParam());
        String code = RetCodeConstants.SUCESS;
        String msg = "加入成功";
        if (result == 0) {
            code = RetCodeConstants.WANGYI_ADDUSERSTOGROUP_FAILED;
            msg = "加入失败";
        }
        return JSONPublicDto.returnSuccessData(code, msg, null);
    }

    //获取群组详细信息
    @RequestMapping(value = "/queryGroupAndUsersDetail", method = RequestMethod.GET)
    @ApiOperation(value = "获取群组详细信息", notes = "获取群组详细信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<QueryGroupAndUsersDetailResponseDto> queryGroupAndUsersDetail(@NotBlank(message = "群组tid不能为空") @RequestParam("tid") String tid) {
        QueryGroupAndUsersDetailResponseDto responseDto = userIMGroupService.queryGroupAndUsersDetail(tid);
        String code = RetCodeConstants.SUCESS;
        String msg = "查询成功";
        if (responseDto == null) {
            code = RetCodeConstants.WANGYI_QUERYGROUPANDUSERSLIST_FAILED;
            msg = "查询失败";
        }
        return JSONPublicDto.returnSuccessData(code, msg, responseDto);
    }

    //更新群组信息
    @RequestMapping(value = "/updateGroupInfo", method = RequestMethod.PUT)
    @ApiOperation(value = "更新群组(群组名称,头像,公告,描述)", notes = "更新群组(群组名称,头像,公告,描述)", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto updateGroupInfo(@RequestBody UpdateGroupInfoRequestParam param) {
        int result = userIMGroupService.updateGroupInfo(param);
        String code = RetCodeConstants.SUCESS;
        String msg = "修改成功";
        if (result == 0) {
            code = RetCodeConstants.WANGYI_UPDATEGROUP_FAILED;
            msg = "修改失败";
        }
        return JSONPublicDto.returnSuccessData(code, msg, null);
    }


    //更新群组信息
    @ApiIgnore
    @RequestMapping(value = "/updateGroupInformation", method = RequestMethod.PUT)
    @ApiOperation(value = "更新群组信息", notes = "更新群组信息", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto updateGroupInformation(@RequestBody JSONPublicParam<UpdateGroupRequestParam> param) {
        int result = userIMGroupService.updateGroupInformation(param);
        String code = RetCodeConstants.SUCESS;
        String msg = "修改成功";
        if (result == 0) {
            code = RetCodeConstants.WANGYI_UPDATEGROUP_FAILED;
            msg = "修改失败";
        }
        return JSONPublicDto.returnSuccessData(code, msg, null);
    }

    //添加群组管理员
    @ApiIgnore
    @RequestMapping(value = "/addGroupManager", method = RequestMethod.POST)
    @ApiOperation(value = "添加群组管理员", notes = "添加群组管理员", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto addGroupManager(@RequestBody AddGroupManagerRequestParam param) {
        int result = userIMGroupService.addGroupManager(param);
        String code = RetCodeConstants.SUCESS;
        String msg = "添加群组管理员成功";
        if (result == 0) {
            code = RetCodeConstants.WANGYI_ADDGROUPMANAGER_FAILED;
            msg = "添加群组管理员失败";
        }
        return JSONPublicDto.returnSuccessData(code, msg, null);
    }

    //从群组中移除管理员
    @ApiIgnore
    @RequestMapping(value = "/removeManagerFromGroup", method = RequestMethod.PUT)
    @ApiOperation(value = "从群组中移除管理员", notes = "从群组中移除管理员", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto removeManagerFromGroup(@RequestBody RemoveManagerFromGroupRequestParam param) {
        int result = userIMGroupService.removeManagerFromGroup(param);
        String code = RetCodeConstants.SUCESS;
        String msg = "群组中移除管理员成功";
        if (result == 0) {
            code = RetCodeConstants.WANGYI_REMOVEMANAGER_FAILED;
            msg = "群组中移除管理员失败";
        }
        return JSONPublicDto.returnSuccessData(code, msg, null);
    }

    //更改群组内用户的昵称
    @ApiIgnore
    @RequestMapping(value = "/updateGroupMemberNickName", method = RequestMethod.POST)
    @ApiOperation(value = "更改群组内用户的昵称", notes = "更改群组内用户的昵称", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto updateGroupMemberNickName(@RequestBody UpdateGroupUserNickNameRequestParam param) {
        int result = userIMGroupService.updateGroupMemberNickName(param);
        String code = RetCodeConstants.SUCESS;
        String msg = "更改群组内用户的昵称成功";
        if (result == 0) {
            code = RetCodeConstants.WANGYI_UPDATETEAMNICK_FAILED;
            msg = "更改群组内用户的昵称失败";
        }
        return JSONPublicDto.returnSuccessData(code, msg, null);
    }

    //修改消息提醒开关
    @ApiIgnore
    @RequestMapping(value = "/muteTeam", method = RequestMethod.POST)
    @ApiOperation(value = "修改消息提醒开关", notes = "修改消息提醒开关", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<WangYiCommonResponseDto> muteTeam(@RequestBody MuteTeamRequestParam param) {
        WangYiCommonResponseDto responseDto = userIMGroupService.muteTeam(param);
        String code = RetCodeConstants.SUCESS;
        String msg = "修改消息提醒开关成功";
        if (responseDto == null) {
            code = RetCodeConstants.WANGYI_MUTETEAM_FAILED;
            msg = "修改消息提醒开关失败";
        }
        return JSONPublicDto.returnSuccessData(code, msg, null);
    }

    //禁言群组用户
    @ApiIgnore
    @RequestMapping(value = "/muteTlist", method = RequestMethod.POST)
    @ApiOperation(value = "禁言群组成员", notes = "禁言群组成员", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto muteTlist(@RequestBody MuteUserRequestParam param) {
        int result = userIMGroupService.doMuteTlist(param);
        String code = RetCodeConstants.SUCESS;
        String msg = "禁言成功";
        if (result == 0) {
            code = RetCodeConstants.WANGYI_MUTEUSER_FAILED;
            msg = "禁言失败";
        }
        return JSONPublicDto.returnSuccessData(code, msg, null);
    }

    //群组禁言,解禁
    @ApiIgnore
    @RequestMapping(value = "/muteTlistAll", method = RequestMethod.POST)
    @ApiOperation(value = "群组禁言,解禁", notes = "群组禁言,解禁", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto muteTlistAll(@RequestBody MuteGroupRequestParam param) {
        int result = userIMGroupService.doMuteTlistAll(param);
        String code = RetCodeConstants.SUCESS;
        String msg = "群组禁言成功";
        if (result == 0) {
            code = RetCodeConstants.WANGYI_MUTEGROUP_FAILED;
            msg = "群组禁言失败";
        }
        return JSONPublicDto.returnSuccessData(code, msg, null);
    }

    //从群组中踢出用户
    @ApiIgnore
    @RequestMapping(value = "/kickOutUserFromGroup", method = RequestMethod.POST)
    @ApiOperation(value = "从群组中踢出用户", notes = "从群组中踢出用户", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto kickOutUserFromGroup(@RequestBody KickOutUserFromGroupRequestParam param) {
        int result = userIMGroupService.kickOutUserFromGroup(param);
        String code = RetCodeConstants.SUCESS;
        String msg = "踢出成功";
        if (result == 0) {
            code = RetCodeConstants.WANGYI_KICKOUTUSERFROMGROUP_FAILED;
            msg = "踢出失败";
        }
        return JSONPublicDto.returnSuccessData(code, msg, null);
    }

    //主动退群
    @RequestMapping(value = "/leaveGroup", method = RequestMethod.POST)
    @ApiOperation(value = "主动退群", notes = "主动退群", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto leaveGroup(@RequestBody LeaveGroupRequestParam param) {
        int result = userIMGroupService.doLeaveGroup(param);
        String code = RetCodeConstants.SUCESS;
        String msg = "主动退群成功";
        if (result == 0) {
            code = RetCodeConstants.WANGYI_LEAVEGROUP_FAILED;
            msg = "主动退群失败";
        }
        return JSONPublicDto.returnSuccessData(code, msg, null);
    }

    //解散群组
    @ApiIgnore
    @RequestMapping(value = "/deleteGroup", method = RequestMethod.POST)
    @ApiOperation(value = "解散群组", notes = "解散群组", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<WangYiCommonResponseDto> deleteGroup(@RequestBody DeleteGroupRequestParam param) {
        WangYiCommonResponseDto wangYiCommonResponseDto = userIMGroupService.deleteGroup(param);
        String code = RetCodeConstants.SUCESS;
        String msg = "解散群组成功";
        if (wangYiCommonResponseDto == null) {
            code = RetCodeConstants.WANGYI_DELETEGROUP_FAILED;
            msg = "解散群组失败";
        }
        return JSONPublicDto.returnSuccessData(code, msg, null);
    }

    //查询活动群组
    @RequestMapping(value = "/activityGroupInfo", method = RequestMethod.GET)
    @ApiOperation(value = "查询活动群组", notes = "查询活动群组", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<ActivityGroupInfoResponseDto> activityGroupInfo(ActivityGroupInfoRequestParam param) {
        ActivityGroupInfoResponseDto activityGroupInfoResponseDto = userIMGroupService.activityGroupInfo(param);
        String code = RetCodeConstants.SUCESS;
        String msg = "查询成功";
        if (activityGroupInfoResponseDto == null) {
            code = RetCodeConstants.WANGYI_MUTEGROUP_FAILED;
            msg = "查询失败,没有该群组";
        }
        return JSONPublicDto.returnSuccessData(code, msg, activityGroupInfoResponseDto);
    }

    //查询群组禁言列表
    @ApiIgnore
    @RequestMapping(value = "/groupMuteList", method = RequestMethod.GET)
    @ApiOperation(value = "查询群组禁言列表", notes = "查询群组禁言列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<GroupMuteListResponseDto>> groupMuteList(@RequestParam(value = "tid") Integer tid) {
        List<GroupMuteListResponseDto> groupMuteListResponseDto = userIMGroupService.groupMuteList(tid);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", groupMuteListResponseDto);
    }

    //获取用户参与的群组列表
    @RequestMapping(value = "/joinGroupList", method = RequestMethod.GET)
    @ApiOperation(value = "获取用户参与的群组列表", notes = "获取用户参与的群组列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<JoinGroupListResposeDto>> joinGroupList(@ApiIgnore @RequestParam Integer authUserId) {
        List<JoinGroupListResposeDto> joinGroupListResposeDtos = userIMGroupService.queryJoinGroupList(authUserId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", joinGroupListResposeDtos);
    }

    //获取活动id查询群组
    @RequestMapping(value = "/queryGroupByActivityId", method = RequestMethod.GET)
    @ApiOperation(value = "获取活动id查询群组", notes = "获取活动id查询群组", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<QueryGroupByActivityId> queryGroupByActivityId(@RequestParam(value = "activityId") Integer activityId) {
        QueryGroupByActivityId queryGroupByActivityId = userIMGroupService.queryGroupByActivityId(activityId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", queryGroupByActivityId);
    }

    //根据群组id获取活动id
    @RequestMapping(value = "/queryActivityIdByTid", method = RequestMethod.GET)
    @ApiOperation(value = "根据群组id获取活动id", notes = "根据群组id获取活动id", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<QueryActivityIdByTidResponseDto> queryActivityIdByTid(@RequestParam(value = "tid") String tid) {
        QueryActivityIdByTidResponseDto queryActivityIdByTidResponseDto = userIMGroupService.queryActivityIdByTid(tid);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", queryActivityIdByTidResponseDto);
    }

    //用户加入群组(通过tid)
    @RequestMapping(value = "/joinGroupByTid", method = RequestMethod.POST)
    @ApiOperation(value = "用户加入群组(通过tid)", notes = "用户加入群组(通过tid)", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto joinGroupByTid(@RequestBody JoinGroupByTidRequestParam param) {
        int result = userIMGroupService.doJoinGroupByTid(param);
        String code = RetCodeConstants.SUCESS;
        String msg = "加入成功";
        if (result == 0) {
            code = RetCodeConstants.WANGYI_ADDUSERSTOGROUP_FAILED;
            msg = "加入失败";
        }
        return JSONPublicDto.returnSuccessData(code, msg, null);
    }
}

