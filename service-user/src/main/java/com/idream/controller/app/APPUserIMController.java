package com.idream.controller.app;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.wangyi.*;
import com.idream.service.UserIMService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @author: ZhengGaosheng
 * @date: 2018/5/14 12:06
 * @description:
 */
@RestController
@Api(description = "UserIMController", tags = "用户IM聊天(APP)")
@RequestMapping("/app/userim")
public class APPUserIMController {

    @Autowired
    private UserIMService userIMService;

    /*
    //获取网易IM用户
    @RequestMapping(value = "/getIMUser", method = RequestMethod.POST)
    @ApiOperation(value = "获取网易IM用户", notes = "获取网易IM用户", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<WangYiUserInfo> getIMUserPost(@ApiIgnore @RequestParam("authUserId") int authUserId){
        WangYiUserInfo imUser = userIMService.doGetIMUser(authUserId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS,"创建用户返回信息",imUser);
    }

*/
    //获取网易IM用户
    @RequestMapping(value = "/getIMUser", method = RequestMethod.GET)
    @ApiOperation(value = "获取网易IM用户", notes = "获取网易IM用户", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<WangYiUserInfo> getIMUser(@ApiIgnore @RequestParam("authUserId") int authUserId) {
        WangYiUserInfo imUser = userIMService.doGetIMUser(authUserId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "创建用户返回信息", imUser);
    }

    //获取用户信息
    @RequestMapping(value = "/getIMUserInfo", method = RequestMethod.GET)
    @ApiOperation(value = "获取用户信息", notes = "获取用户信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<IMUserInfoResponseDto>> getIMUserInfo(@RequestParam("userId") int userId) {
        List<IMUserInfoResponseDto> imUserInfo = userIMService.getIMUserInfo(userId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "获取用户信息", imUserInfo);
    }

    //更新用户信息
    @RequestMapping(value = "/updateIMUserInfo", method = RequestMethod.PUT)
    @ApiOperation(value = "更新用户信息", notes = "更新用户信息", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<WangYiCommonResponseDto> updateIMUserInfo(@RequestBody UpdateIMUserInfoParams param) {
        WangYiCommonResponseDto wangYiCommonResponseDto = userIMService.updateIMUserInfo(param);
        String code = RetCodeConstants.SUCESS;
        String msg = "更新用户信息成功";
        if (wangYiCommonResponseDto == null) {
            code = RetCodeConstants.WANGYI_UPDATEUSERINFO_FAILED;
            msg = "更新用户信息失败";
        }
        return JSONPublicDto.returnSuccessData(code, msg, null);
    }

    //添加好友
    @RequestMapping(value = "/addIMFriend", method = RequestMethod.POST)
    @ApiOperation(value = "添加好友", notes = "添加好友", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto addIMFriend(@RequestBody AddFriendRequestParams param) {
        int i = userIMService.addIMFriend(param);
        String code = RetCodeConstants.SUCESS;
        String msg = "添加好友成功";
        if (i == 0) {
            code = RetCodeConstants.WANGYI_UPDATEFRIENDALIAS_FAILED;
            msg = "添加好友失败";
        }
        return JSONPublicDto.returnSuccessData(code, msg, null);
    }

    //修改备注
    @ApiIgnore
    @RequestMapping(value = "/updateFriendAlias", method = RequestMethod.POST)
    @ApiOperation(value = "修改好友备注", notes = "修改好友备注", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto updateFriendAlias(@RequestBody JSONPublicParam<UpdateFriendRequestParams> param) {
        int i = userIMService.updateFriendAlias(param);
        String code = RetCodeConstants.SUCESS;
        String msg = "修改好友备注成功";
        if (i == 0) {
            code = RetCodeConstants.WANGYI_UPDATEFRIENDALIAS_FAILED;
            msg = "修改好友备注失败";
        }
        return JSONPublicDto.returnSuccessData(code, msg, null);
    }

    //删除好友
    @RequestMapping(value = "/deleteFriend", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除好友", notes = "删除好友", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto deleteFriend(@RequestBody DeleteFriendRequestParam param) {
        int i = userIMService.deleteFriend(param);
        String code = RetCodeConstants.SUCESS;
        String msg = "删除好友成功";
        if (i == 0) {
            code = RetCodeConstants.WANGYI_DELETEFRIEND_FAILED;
            msg = "删除好友失败";
        }
        return JSONPublicDto.returnSuccessData(code, msg, null);
    }

    //将好友加入黑名单
    @RequestMapping(value = "/addUserToBlackList", method = RequestMethod.POST)
    @ApiOperation(value = "将好友加入黑名单", notes = "将好友加入黑名单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto addUserToBlackList(@RequestBody JSONPublicParam<AddUserToBlackListRequestParam> param) {
        int i = userIMService.addUserToBlackList(param);
        String code = RetCodeConstants.SUCESS;
        String msg = "加入黑名单成功";
        if (i == 0) {
            code = RetCodeConstants.WANGYI_ADDUSERTOBLACKLIST_FAILED;
            msg = "加入黑名单失败";
        }
        return JSONPublicDto.returnSuccessData(code, msg, null);
    }

    //将好友移除黑名单
    @RequestMapping(value = "/removeUserFromBlackList", method = RequestMethod.POST)
    @ApiOperation(value = "将好友移除黑名单", notes = "将好友移除黑名单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto removeUserFromBlackList(@RequestBody JSONPublicParam<RemoveUserFromBlackListRequestParam> param) {
        int i = userIMService.removeUserFromBlackList(param);
        String code = RetCodeConstants.SUCESS;
        String msg = "移除成功";
        if (i == 0) {
            code = RetCodeConstants.WANGYI_REMOVEUSERFROMBLACKLIST_FAILED;
            msg = "移除失败";
        }
        return JSONPublicDto.returnSuccessData(code, msg, null);
    }

    //查看用户的黑名单列表
    @ApiIgnore
    @RequestMapping(value = "/queryBlackListByUserId", method = RequestMethod.GET)
    @ApiOperation(value = "查看用户的黑名单列表", notes = "查看用户的黑名单列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<QueryBlackListResponseParams>> queryBlackListByUserId(@ApiIgnore @RequestParam int authUserId) {
        List<QueryBlackListResponseParams> queryBlackListResponseParamses = userIMService.queryBlackListByUserId(authUserId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "黑名单列表", queryBlackListResponseParamses);
    }

    //根据userid查询accid
    @RequestMapping(value = "/queryAccidByUserId", method = RequestMethod.GET)
    @ApiOperation(value = "根据userid查询accid", notes = "根据userid查询accid", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<QueryAccidByUserIdResponseDto> queryAccidByUserId(@RequestParam(value = "userId") Integer userId) {
        QueryAccidByUserIdResponseDto responseDto = userIMService.queryAccidByUserId(userId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", responseDto);
    }


}

