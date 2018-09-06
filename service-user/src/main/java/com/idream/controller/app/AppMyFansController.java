package com.idream.controller.app;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.activity.*;
import com.idream.service.AppMyFansService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Description : 粉丝相关接口
 * @Created by xiaogang on 2018/4/28.
 */
@RestController
@Api(tags = "粉丝相关接口（APP）", description = "AppMyFansController")
@RequestMapping(value = "/app/myNeighbor")
public class AppMyFansController {

    @Autowired
    private AppMyFansService appMyFansService;

    @RequestMapping(value = "/getWetherAuthenticated", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户是否认证过社区", notes = "查询用户是否认证过社区/(是为true，否为false)")
    public JSONPublicDto<Boolean> getWetherAuthenticated(@ApiIgnore @RequestParam int authUserId) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", appMyFansService.getWetherAuthenticated(authUserId));
    }

    @RequestMapping(value = "/addMyNotice", method = RequestMethod.POST)
    @ApiOperation(value = "新增关注", notes = "新增关注/(0000为成功,7005已关注过)", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto addMyNotice(@RequestBody JSONPublicParam<AppUserIdParam> param) {
        appMyFansService.addMyNotice(param);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "关注成功", null);
    }

    @RequestMapping(value = "/deleteMyNotice", method = RequestMethod.PUT)
    @ApiOperation(value = "取消关注", notes = "取消关注/(0000为成功,7006为未关注过该用户或者已取消关注)", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto deleteMyNotice(@RequestBody JSONPublicParam<AppUserIdParam> param) {
        appMyFansService.deleteMyNotice(param);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "取消关注成功", null);
    }

    @RequestMapping(value = "/getWhetherAttended", method = RequestMethod.GET)
    @ApiOperation(value = "查询当前用户是否关注过某个用户", notes = "查询当前用户是否关注过某个用户", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<Boolean> getWhetherAttended(@ApiIgnore @RequestParam int authUserId,
                                                     @NotNull(message = "用户ID") @RequestParam int userId) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", appMyFansService.getWhetherAttended(authUserId, userId));
    }

    @RequestMapping(value = "/getMyFansList", method = RequestMethod.GET)
    @ApiOperation(value = "我的粉丝列表", notes = "我的粉丝列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<AppFansInfoDto>> getMyFansList(AppSearchFansInfoParam param) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", appMyFansService.getMyFansList(param));
    }

    @RequestMapping(value = "/getMyNewFansList", method = RequestMethod.GET)
    @ApiOperation(value = "我的新进粉丝列表", notes = "我的新进粉丝列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<AppFansInfoDto>> getMyNewFansList(AppSearchFansInfoParam param) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", appMyFansService.getMyNewFansList(param));
    }

    @RequestMapping(value = "/updateMyNewFansList", method = RequestMethod.POST)
    @ApiOperation(value = "维护新进粉丝数据", notes = "维护新进粉丝数据", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<AppFansInfoDto>> updateMyNewFansList(@RequestBody JSONPublicParam<UpdateMyNewFansFlagParams> param) {
        appMyFansService.updateMyNewFansList(param);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "维护成功", null);
    }

    @RequestMapping(value = "/getMyAttendList", method = RequestMethod.GET)
    @ApiOperation(value = "我关注的用户列表", notes = "我关注的用户列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<AppFansInfoDto>> getMyAttendList(AppMyAttendParam param) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", appMyFansService.getMyAttendList(param));
    }

    @RequestMapping(value = "/getMyCommunity", method = RequestMethod.GET)
    @ApiOperation(value = "获取用户小社区列表", notes = "获取用户小社区列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<AppCommunityInfoDto>> getMyCommunity(@ApiIgnore @RequestParam int authUserId) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", appMyFansService.getMyCommunity(authUserId));
    }

}
