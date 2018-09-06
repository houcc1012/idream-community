package com.idream.controller.app;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.base.StatisticalType;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.activity.*;
import com.idream.commons.lib.dto.app.*;
import com.idream.commons.lib.dto.appactivity.*;
import com.idream.commons.lib.dto.information.AppInformationRuleDetail;
import com.idream.commons.lib.dto.information.InformationActivityParams;
import com.idream.commons.lib.dto.information.InformationRuleDto;
import com.idream.commons.lib.dto.information.InformationUserRecordDto;
import com.idream.commons.lib.dto.user.ParticipateActivityDto;
import com.idream.commons.mvc.annotation.StatisticalData;
import com.idream.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author penghekai
 */
@RestController
@Api(tags = "活动相关接口(APP)", description = "AppActivityController")
@RequestMapping("/app/activity")
public class AppActivityController {

    @Autowired
    private AppActivityService appActivityService;
    @Autowired
    private ActivityInvitationRecordService activityInvitationRecordService;
    @Autowired
    private ActivityDisplayService activityDisplayService;
    @Autowired
    private InformationRuleService informationRuleService;
    @Autowired
    private ActivityInfoService activityInfoService;

    //根据选择的活动类型(或者不选择类型)列表展示最新活动(分页)
    @RequestMapping(value = "/list/nearby", method = RequestMethod.GET)
    @ApiOperation(value = "条件分页查询活动列表", notes = "条件分页查询活动列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<AppActivityListDto>> getNearbyActivityByTypeIdOrLocation(@ApiIgnore @RequestParam("authUserId") Integer userId, AppActivityListRequestDto params) {

        PagesDto<AppActivityListDto> pagesDto = appActivityService.getNearbyActivityByTypeIdOrLocation(userId, params);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询活动信息成功", pagesDto);
    }

    //根据活动id查看详细信息和是否已经参与活动
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ApiOperation(value = "根据活动id查询活动详情", notes = "根据活动id查询活动详情", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @StatisticalData(type = StatisticalType.ACTIVITY)
    public JSONPublicDto<DisplayActivityResponseDto> getActivityDetailById(@ApiIgnore @RequestParam("authUserId") Integer userId,
                                                                           @NotNull(message = "活动id不能为空") @ApiParam(value = "活动id", required = true) @RequestParam Integer activityId) {

        //AppActivityInfoResponseDto dto = appActivityService.getActivityInfoAndUserRelation(userId,activityId);
        DisplayActivityResponseDto displayActivityDto = activityDisplayService.displayActivity(activityId, userId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询活动详情成功", displayActivityDto);

    }

    @RequestMapping(value = "/join/userJoinActivity", method = RequestMethod.POST)
    @ApiOperation(value = "用户报名参加活动", notes = "用户报名参加活动", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<String> userJoinActivity(@ApiParam(value = "用户报名参加活动", required = true) @RequestBody JSONPublicParam<UserJoinActivityRequestDto> params) {
        appActivityService.addUserToActivity(params);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "报名参加活动成功", null);
    }

    @RequestMapping(value = "information", method = RequestMethod.POST)
    @ApiOperation("录入信息")
    public JSONPublicDto<String> saveInformation(@RequestBody JSONPublicParam<InformationActivityParams> activityInformations) {
        Integer userId = activityInformations.getAuthUserInfo().getUserId();
        InformationActivityParams requestParam = activityInformations.getRequestParam();
        informationRuleService.saveActivityInformation(userId, requestParam);
        return JSONPublicDto.returnSuccessData("录入信息成功");
    }

    //分享活动
    @RequestMapping(value = "/share", method = RequestMethod.POST)
    @ApiOperation(value = "活动分享邀请", notes = "活动分享邀请", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<String> shareActivity(@ApiParam(value = "活动ID，邀请人unionid，受邀人unionid", required = true) @RequestBody JSONPublicParam<ShareInfoDto> param) {

        try {
            activityInvitationRecordService.addInvitationRecord(param);
            return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "分享成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return JSONPublicDto.returnSuccessData(RetCodeConstants.UNKOWN_ERROR, "分享失败", null);
        }
    }

    //分享活动显示本人信息和活动信息
    @RequestMapping(value = "/share/info", method = RequestMethod.GET)
    @ApiOperation(value = "分享页本人信息和活动信息", notes = "分享人信息和活动信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<AppShareInfoResponseDto> getUserInfoAndActivityInfo(@ApiIgnore @RequestParam("authUserId") Integer userId, @NotNull(message = "活动id不能为空") @ApiParam(value = "活动id", required = true) @RequestParam Integer activityId) {

        AppShareInfoResponseDto dto = appActivityService.getUserInfoAndActivityInfo(userId, activityId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", dto);
    }

    @RequestMapping(value = "/image/info", method = RequestMethod.GET)
    @ApiOperation(value = "活动精彩页活动封面信息", notes = "活动精彩页活动封面信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<AppActivityImageResponseDto> getActivityById(@ApiIgnore @RequestParam("authUserId") Integer userId, @NotNull(message = "活动id不能为空") @ApiParam(value = "活动id", required = true) @RequestParam Integer activityId) {

        AppActivityImageResponseDto dto = appActivityService.getImageInfoByActivityId(userId, activityId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", dto);

    }

    @RequestMapping(value = "/image/info/byLifeId", method = RequestMethod.GET)
    @ApiOperation(value = "通过活动动态id获取活动封面信息", notes = "活动精彩页活动封面信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<AppActivityImageResponseDto> imageInfoByLifeId(@ApiIgnore @RequestParam("authUserId") Integer userId, @NotNull(message = "活动动态id不能为空") @ApiParam(value = "活动动态id", required = true) @RequestParam Integer lifeId) {

        AppActivityImageResponseDto dto = appActivityService.getImageInfoByLifeId(userId, lifeId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", dto);

    }

    //活动圈排行榜展示
    @RequestMapping(value = "/group/interaction", method = RequestMethod.GET)
    @ApiOperation(value = "活动圈互动排行榜", notes = "活动圈互动排行榜", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<AppCommunityLifeDto>> getActivityGroupInteraction(AppActivityGroupRequestDto requestDto) {

        PagesDto<AppCommunityLifeDto> list = appActivityService.getGroupInteractionList(requestDto);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "活动圈互动查询成功", list);
    }

    //根据活动id查看未读消息
    @RequestMapping(value = "/unread/info", method = RequestMethod.GET)
    @ApiOperation(value = "根据活动id查询未读消息", notes = "根据活动id查询未读消息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<CommunityLifeLikeCountDto> getUnreadInfo(@ApiIgnore @RequestParam("authUserId") Integer userId, @NotNull(message = "活动id不能为空") @ApiParam(value = "活动id", required = true) @RequestParam Integer activityId) {

        CommunityLifeLikeCountDto dto = appActivityService.getUnreadInfo(userId, activityId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", dto);
    }

    @RequestMapping(value = "information", method = RequestMethod.GET)
    @ApiOperation("活动信息规则返回")
    public JSONPublicDto<List<InformationRuleDto>> getInformation(Integer activityId) {
        List<InformationRuleDto> list = informationRuleService.listInformationRules(activityId);
        return JSONPublicDto.returnSuccessData(list);
    }

    @RequestMapping(value = "informationRule", method = RequestMethod.GET)
    @ApiOperation("活动规则相关")
    public JSONPublicDto<AppInformationRuleDetail> ruleDetail() {
        AppInformationRuleDetail detail = informationRuleService.getAppInformationRuleDetail();
        return JSONPublicDto.returnSuccessData(detail);
    }

    @RequestMapping(value = "informationUser", method = RequestMethod.GET)
    @ApiOperation("用户最新录入信息")
    public JSONPublicDto<List<InformationUserRecordDto>> informationUserRecord(@ApiIgnore @RequestParam("authUserId") Integer userId) {
        List<InformationUserRecordDto> list = informationRuleService.getLastUserRecord(userId);
        return JSONPublicDto.returnSuccessData(list);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ApiOperation("活动搜索")
    public JSONPublicDto<PagesDto<AppActivitySearchDto>> searchActivity(@ApiIgnore @RequestParam("authUserId") Integer userId, AppActivitySearchParams params) {
        PagesDto<AppActivitySearchDto> page = activityInfoService.getActivityByActivityTitle(userId, params);
        return JSONPublicDto.returnSuccessData(page);
    }

    @RequestMapping(value = "addActivityCollection", method = RequestMethod.POST)
    @ApiOperation(value = "添加活动收藏", notes = "添加活动收藏", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto addActivityCollection(@RequestBody JSONPublicParam<ActivityCollectionRequestParam> params) {

        int i = appActivityService.addActivityCollection(params);
        String code = RetCodeConstants.SUCESS;
        String msg = "添加活动收藏成功";
        if (i == 0) {
            code = RetCodeConstants.ACTIVITY_COLLECTION_FAILED;
            msg = "添加活动收藏失败";
        }
        return JSONPublicDto.returnSuccessData(code, msg, null);
    }

    @RequestMapping(value = "removeActivityCollection", method = RequestMethod.POST)
    @ApiOperation(value = "取消活动收藏", notes = "取消活动收藏", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto removeActivityCollection(@RequestBody JSONPublicParam<ActivityCollectionRequestParam> params) {
        int i = appActivityService.removeActivityCollection(params);
        String code = RetCodeConstants.SUCESS;
        String msg = "取消活动收藏成功";
        if (i == 0) {
            code = RetCodeConstants.ACTIVITY_REMOVE_COLLECTION_FAILED;
            msg = "取消活动收藏失败";
        }
        return JSONPublicDto.returnSuccessData(code, msg, null);
    }

    @RequestMapping(value = "activityDetailBottom", method = RequestMethod.GET)
    @ApiOperation(value = "活动详情底部(趣聊,收藏,参加)", notes = "活动详情底部(趣聊,收藏,参加)", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<ActivityDetailBottomResponseDto> activityDetailBottom(@ApiIgnore @RequestParam("authUserId") Integer authUserId, @NotNull(message = "活动id不能为空") @RequestParam("activityId") Integer activityId) {
        ActivityDetailBottomResponseDto activityDetailBottomResponseDto = appActivityService.activityDetailBottom(authUserId, activityId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", activityDetailBottomResponseDto);
    }

    //用户进入App后，判断用户身份,是否跳转到类型选择页面
    @RequestMapping(value = "jump/type/page", method = RequestMethod.GET)
    @ApiOperation(value = "登陆后判断用户身份，进行页面跳转", notes = "用户身份确认", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto jumpToTypePage(@ApiIgnore @RequestParam("authUserId") Integer authUserId) {

        boolean flag = appActivityService.getPageOfActivityType(authUserId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", flag);
    }

    //发现页顶部活动类型展示
    @RequestMapping(value = "top/bar/activity/type", method = RequestMethod.GET)
    @ApiOperation(value = "发现页顶部展示的活动类型", notes = "展示活动类型", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<AppActivityUserLikeTypeResponseDto>> topBarActivityTypeList(@ApiIgnore @RequestParam("authUserId") Integer authUserId) {

        List<AppActivityUserLikeTypeResponseDto> list = appActivityService.getTopBarActivityTypeList(authUserId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", list);
    }

    //App登录页，返回所有的类型
    @RequestMapping(value = "all/activity/type", method = RequestMethod.GET)
    @ApiOperation(value = "app登陆后展示所有活动类型", notes = "展示所有活动类型", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<AppActivityUserLikeTypeResponseDto>> getAllActivityType() {

        List<AppActivityUserLikeTypeResponseDto> list = appActivityService.getAllActivityType();
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", list);
    }

    //新增默认的六种类型
    @RequestMapping(value = "/add/default/type", method = RequestMethod.POST)
    @ApiOperation(value = "点击跳过,保存提交默认的活动类型", notes = "提交默认的六种类型", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<String> addDefaultActivityType(@ApiIgnore @RequestParam("authUserId") Integer userId) {

        appActivityService.addUserDefaultActivityType(userId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "操作成功", null);
    }

    //发现页活动类型选择
    @RequestMapping(value = "/different/type/list", method = RequestMethod.GET)
    @ApiOperation(value = "发现页活动类型编辑前展示", notes = "编辑活动偏好类型前列表展示", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<AppMyActivityTypeResponseDto> getDifferentActivityType(@ApiIgnore @RequestParam("authUserId") Integer userId) {

        AppMyActivityTypeResponseDto dto = appActivityService.getDifferentActivityTypeFromAll(userId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", dto);
    }

    //发现页感兴趣的活动类型编辑
    @RequestMapping(value = "/update/interest/type", method = RequestMethod.PUT)
    @ApiOperation(value = "编辑感兴趣的类型", notes = "添加或删除感兴趣的类型", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<String> updateInterestActivityType(@RequestBody JSONPublicParam<AppSaveTypeDto> param) {

        appActivityService.updateMyInterestActivityType(param);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "操作成功", null);
    }

    @RequestMapping(value = "/discover/life/dynamic", method = RequestMethod.GET)
    @ApiOperation(value = "发现页点击(生活)动态/跳转该类型的动态列表", notes = "查询相关类型动态列表", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<AppCommunityLifeDto>> discoverLifeDynamicList(AppExploreDynamicRequestDto param) {

        PagesDto<AppCommunityLifeDto> dto = appActivityService.getDiscoverLifeDynamicList(param);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", dto);
    }

    @RequestMapping(value = "/discover/activity/dynamic", method = RequestMethod.GET)
    @ApiOperation(value = "发现页点击(活动)动态/跳转该类型的动态列表", notes = "查询相关类型动态列表", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<AppCommunityLifeDto>> discoverActivityDynamicList(AppActivityDynamicRequestDto param) {

        PagesDto<AppCommunityLifeDto> dto = appActivityService.getDiscoverActivityDynamicList(param);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", dto);
    }

    @RequestMapping(value = "/dislike", method = RequestMethod.POST)
    @ApiOperation("不喜欢")
    public JSONPublicDto<String> dislike(@RequestBody JSONPublicParam<AppDislikeParams> param) {
        appActivityService.addDisLike(param.getAuthUserInfo().getUserId(), param.getRequestParam());
        return JSONPublicDto.returnSuccessData("添加不喜欢成功");
    }

    @RequestMapping(value = "/discover", method = RequestMethod.GET)
    @ApiOperation("发现页")
    public JSONPublicDto<AppDiscoveryPageDto<AppDiscoveryDto>> discover(AppDiscoveryParams params) {
        AppDiscoveryPageDto<AppDiscoveryDto> page = appActivityService.getDiscoverPage(params);
        return JSONPublicDto.returnSuccessData(page);
    }

    @RequestMapping(value = "/typeInfo", method = RequestMethod.GET)
    @ApiOperation("根据typeId,返回类型的信息")
    public JSONPublicDto<AppActivityUserLikeTypeResponseDto> getType(@NotNull @RequestParam Integer typeId) {
        AppActivityUserLikeTypeResponseDto dto = appActivityService.getActivityTypeByTypeId(typeId);
        return JSONPublicDto.returnSuccessData(dto);
    }

    //APP查询用户参加的活动
    @RequestMapping(value = "/userJoinedActivityList", method = RequestMethod.GET)
    @ApiOperation(value = "APP查询用户参加的活动", notes = "APP查询用户参加的活动", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<ParticipateActivityDto>> userJoinedActivityList(@ApiIgnore @RequestParam int authUserId,
                                                                                  @ApiParam(value = "页码", required = true) @RequestParam int page,
                                                                                  @ApiParam(value = "行数", required = true) @RequestParam int rows) {
        PagesDto<ParticipateActivityDto> data = activityDisplayService.getUserActivityPartake(authUserId, page, rows);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }

    //APP查询别人参加的活动
    @RequestMapping(value = "/otherPeopleJoinedActivityList", method = RequestMethod.GET)
    @ApiOperation(value = "APP查询别人参加的活动", notes = "APP查询别人参加的活动", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<ParticipateActivityDto>> otherPeopleJoinedActivityList(@ApiParam(value = "用户id", required = true) @RequestParam(value = "userId") int userId,
                                                                                         @ApiParam(value = "页码", required = true) @RequestParam int page,
                                                                                         @ApiParam(value = "行数", required = true) @RequestParam int rows) {
        PagesDto<ParticipateActivityDto> data = activityDisplayService.getUserActivityPartake(userId, page, rows);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }

    //活动社群显示
    @RequestMapping(value = "/activityAssociation", method = RequestMethod.GET)
    @ApiOperation(value = "活动社群显示", notes = "活动社群显示", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<MiniActivityAssociationDto> activityAssociation(@ApiIgnore @RequestParam("authUserId") Integer authUserId,
                                                                         @NotNull @ApiParam(value = "活动id", required = true) @RequestParam Integer activityId) {
        MiniActivityAssociationDto miniActivityAssociationDto = activityDisplayService.activityAssociation(authUserId, activityId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "报名收集规则查询成功", miniActivityAssociationDto);
    }

    @RequestMapping(value = "/activityTask/userTask", method = RequestMethod.POST)
    @ApiOperation(value = "500米内打卡传递参数经纬度活动Id", notes = "500米内打卡传递参数经纬度活动Id", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto activityCard(@RequestBody JSONPublicParam<ActivityCardDto> dto) {
        JSONPublicDto jsonPublicDto = activityInfoService.addUserTask(dto);
        return jsonPublicDto;
    }

    @RequestMapping(value = "/shareRecord",method = RequestMethod.POST)
    @ApiOperation("分享加积分")
    public JSONPublicDto<Integer> shareRecord(@RequestBody JSONPublicParam<ShareRecordParams> param) {
        return JSONPublicDto.returnSuccessData(activityInvitationRecordService.saveUserScore(param.getRequestParam().getActivityId(), param.getAuthUserInfo().getUserId()));
    }


}
