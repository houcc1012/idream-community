package com.idream.controller.admin;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.activity.*;
import com.idream.commons.lib.dto.admin.ActivitySearchBookByExampleRequestDto;
import com.idream.commons.lib.dto.wangyi.CreateGroupRequestParam;
import com.idream.commons.lib.enums.SystemEnum;
import com.idream.commons.lib.model.ActivityType;
import com.idream.commons.lib.model.InformationRule;
import com.idream.commons.lib.model.RegionGroupInfo;
import com.idream.commons.lib.model.RegionInfo;
import com.idream.service.ActivityAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author penghekai
 */
@RestController
@Api(tags = "活动相关接口(后台管理端)", description = "平台后台")
@RequestMapping("/admin")
public class ActivityAdminController {

    @Autowired
    private ActivityAdminService activityAdminService;

    //活动列表按照书屋输入式查询
    @RequestMapping(value = "search/book", method = RequestMethod.GET)
    @ApiOperation(value = "输入式查询书屋", notes = "查询书屋名", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<RegionGroupInfoExtention>> searchBookByQuery(ActivitySearchBookByExampleRequestDto param) {

        List<RegionGroupInfoExtention> dtoList = activityAdminService.getBookNameByExample(param);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", dtoList);
    }

    //活动列表分页查询
    @RequestMapping(value = "find/activity", method = RequestMethod.GET)
    @ApiOperation(value = "活动列表分页展示", notes = "所有活动相关信息列表分页展示", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<FindAllActivityResponseDto>> findAllActivity(QueryActivityPage query) {
        PagesDto<FindAllActivityResponseDto> pagesDto = activityAdminService.getActivityCount(query);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", pagesDto);
    }

    //活动数据统计
    @RequestMapping(value = "count/statistics", method = RequestMethod.GET)
    @ApiOperation(value = "活动数据统计", notes = "活动相关信息统计", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<AdminActivityStatisticsResponseDto>> countActivityStatistics(@ApiParam("活动id") @RequestParam Integer activityId) {
        List<AdminActivityStatisticsResponseDto> dto = activityAdminService.getActivityStatisticsByActivityId(activityId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", dto);
    }

    @RequestMapping(value = "find/type", method = RequestMethod.GET)
    @ApiOperation(value = "已发布成功的活动类型", notes = "已发布成功的活动类型", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<ActivityType>> findPublishedActivityType() {

        List<ActivityType> activityTypes = activityAdminService.findPublishedActivityType();
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询已发布活动类型成功", activityTypes);
    }

    @RequestMapping(value = "find/byId", method = RequestMethod.GET)
    @ApiOperation(value = "根据活动id查询活动信息", notes = "根据活动id查询活动信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<AdminActivityDisplayDto> getByActivityId(Integer activityId) {

        AdminActivityDisplayDto aadd = activityAdminService.getByActivityId(activityId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "成功", aadd);
    }

    @RequestMapping(value = "select/region/info", method = RequestMethod.GET)
    @ApiOperation(value = "查询省市区下的高级社区", notes = "查询省市区下高级社区", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<RegionInfo>> selectActivityRegionList(@ApiParam(value = "省市区编码", required = true) AdminActivityCommunityListRequestDto param) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "成功", activityAdminService.selectRegionInfoList(param));
    }

    @RequestMapping(value = "select/region/group", method = RequestMethod.GET)
    @ApiOperation(value = "查询高级社区下的所有小区", notes = "查询高级社区下的所有小区", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<RegionGroupInfo>> selectRegionGroupByRegionId(@ApiParam(value = "高级社区id", required = true) @RequestParam Integer regionId) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "成功", activityAdminService.selectRegionGroupByRegionId(regionId));
    }

    @RequestMapping(value = "activity/manager", method = RequestMethod.POST)
    @ApiOperation(value = "根据社区id查询管理者", notes = "根据社区id查询管理者", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<ActivityManagerDto>> activityManager(@ApiParam(value = "社区id") @RequestBody ActivityAdminManagerRequestDto param) {

        List<ActivityManagerDto> list = activityAdminService.listActivityManagerByRegionId(param.getRegionIds());
        return JSONPublicDto.returnSuccessData(list);
    }

    @RequestMapping(value = "activity/type", method = RequestMethod.GET)
    @ApiOperation(value = "活动类型", notes = "活动类型", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<ActivityType>> findAllActivityType() {

        List<ActivityType> typeList = activityAdminService.getActivityTypeList();
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询活动类型成功", typeList);
    }

    @RequestMapping(value = "activity/information", method = RequestMethod.GET)
    @ApiOperation(value = "查询活动收集信息列表", notes = "查询活动收集信息列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<InformationRule>> information() {
        List<InformationRule> infos = activityAdminService.listInformations();
        return JSONPublicDto.returnSuccessData(infos);
    }

    @RequestMapping(value = "add/activity", method = RequestMethod.POST)
    @ApiOperation(value = "创建活动", notes = "创建活动", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<String> addActivity(@ApiIgnore @RequestParam("authUserId") Integer createId, @RequestBody AdminActivityPublishDto aapd) {

        activityAdminService.addActivity(createId, aapd, SystemEnum.ClientChannelEnum.ADMIN_WEB.getCode());
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "创建成功", null);
    }

    @RequestMapping(value = "update/activity", method = RequestMethod.PUT)
    @ApiOperation(value = "编辑活动", notes = "编辑活动", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<String> updateActivity(@RequestBody JSONPublicParam<AdminActivityPublishDto> aapd) {

        activityAdminService.updateActivity(aapd, SystemEnum.ClientChannelEnum.ADMIN_WEB.getCode());
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "编辑活动详情成功", null);
    }

    @RequestMapping(value = "delete/activity/{activityId}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除活动", notes = "删除活动", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<String> deleteActivity(@ApiParam(value = "活动id", required = true) @PathVariable Integer activityId) {

        activityAdminService.deleteActivity(activityId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "删除活动成功", null);
    }

    @RequestMapping(value = "check/activity/list", method = RequestMethod.GET)
    @ApiOperation(value = "待审核活动信息列表分页展示", notes = "活动审核页面列表展示", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<ActivityListCheckResponseDto>> checkActivityList(@ApiParam(value = "活动审核页列表展示请求参数", required = true) ActivityListCheckRequestDto param) {
        PagesDto<ActivityListCheckResponseDto> pagesDto = activityAdminService.getCheckActivityList(param);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询所有活动详情成功", pagesDto);
    }

    @RequestMapping(value = "check/activity/pass", method = RequestMethod.PUT)
    @ApiOperation(value = "审核成功", notes = "活动审核成功", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<String> checkActivityPass(@ApiIgnore @RequestParam("authUserId") Integer userId, @ApiParam(value = "活动id", required = true) @RequestParam Integer activityId) {

        activityAdminService.checkActivityPassByActivityId(activityId, userId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "操作成功", null);
    }

    @RequestMapping(value = "check/activity/fail", method = RequestMethod.PUT)
    @ApiOperation(value = "审核失败", notes = "活动审核失败", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<String> checkActivityFail(@ApiIgnore @RequestParam("authUserId") Integer userId, @ApiParam(value = "活动审核请求参数", required = true) @RequestBody AdminActivityOperateRecordRequestDto param) {
        param.setUserId(userId);
        activityAdminService.checkActivityFailByActivityId(param);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "操作成功", null);
    }

    @RequestMapping(value = "cancel/activity", method = RequestMethod.POST)
    @ApiOperation(value = "活动下架(临时取消)", notes = "活动下架", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<String> cancelActivity(@ApiIgnore @RequestParam("authUserId") Integer userId, @ApiParam(value = "活动取消请求参数", required = true) @RequestBody AdminActivityOperateRecordRequestDto param) {
        param.setUserId(userId);
        activityAdminService.operateActivityCancel(param);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "取消活动成功", null);
    }

    @RequestMapping(value = "publish/{activityId}", method = RequestMethod.PUT)
    @ApiOperation(value = "发布活动(上架)", notes = "修改活动状态为发布", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<String> publishActivity(@ApiParam(value = "要发布的活动id", required = true) @PathVariable Integer activityId) {

        activityAdminService.updateActivityStatusPutWay(activityId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "操作成功", null);
    }

    //创建群聊
    @RequestMapping(value = "create/group/chat", method = RequestMethod.POST)
    @ApiOperation(value = "创建群聊", notes = "手动创建群聊", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<String> createGroupChat(@ApiIgnore @RequestParam("authUserId") Integer userId, @ApiParam(value = "创建群聊请求参数", required = true) @RequestBody CreateGroupRequestParam param) {
        activityAdminService.addCreateGroupChat(userId, param);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "创建成功", null);
    }

    //回显群聊信息
    @RequestMapping(value = "show/group/name", method = RequestMethod.GET)
    @ApiOperation(value = "回显群聊信息", notes = "修改群聊时回显群聊信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<UpdateGroupNameRequestParam> showGroupChatName(@ApiParam(value = "回显群聊请求参数", required = true) @RequestParam Integer activityId) {

        UpdateGroupNameRequestParam dto = activityAdminService.getGroupInfoByActivityId(activityId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", dto);
    }

    //编辑趣聊名称
    @RequestMapping(value = "update/group/name", method = RequestMethod.PUT)
    @ApiOperation(value = "编辑群聊名", notes = "修改趣聊名字", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<String> updateGroupName(@ApiParam(value = "群组id", required = true) @RequestBody UpdateGroupNameRequestParam param) {

        activityAdminService.updateGroupNameByGroupId(param);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "操作成功", null);
    }

    @RequestMapping(value = "find/theme/{activityId}/{page}/{rows}", method = RequestMethod.GET)
    @ApiOperation(value = "活动主题列表展示", notes = "活动主题列表展示", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<FindThemeResponseDto>> findActivityTheme(@ApiParam(value = "活动id", required = true) @PathVariable Integer activityId,
                                                                           @ApiParam(value = "当前页码", required = true) @PathVariable Integer page,
                                                                           @ApiParam(value = "每页条数", required = true) @PathVariable Integer rows) {

        PagesDto<FindThemeResponseDto> pagesDto = activityAdminService.findActivityTheme(activityId, page, rows);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询主题列表成功", pagesDto);
    }

    @RequestMapping(value = "find/message/{themeId}/{page}/{rows}", method = RequestMethod.GET)
    @ApiOperation(value = "查看主题留言明细", notes = "查看主题留言明细", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<FindThemeMessageResponseDto>> findThemeMessage(@ApiParam(value = "主题id", required = true) @PathVariable Integer themeId,
                                                                                 @ApiParam(value = "当前页码", required = true) @PathVariable Integer page,
                                                                                 @ApiParam(value = "每页条数", required = true) @PathVariable Integer rows) {

        PagesDto<FindThemeMessageResponseDto> pagesDto = activityAdminService.findThemeMessage(themeId, page, rows);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询主题留言成功", pagesDto);
    }

    @RequestMapping(value = "find/community/life", method = RequestMethod.GET)
    @ApiOperation(value = "查看活动精彩", notes = "查看活动精彩", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<FindActivityMessageResponseDto>> activityCommunityLifeMessage(ActivityAdminCommunityLifeRequestDto requestDto) {

        PagesDto<FindActivityMessageResponseDto> pagesDto = activityAdminService.findActivityCommunityLife(requestDto);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", pagesDto);
    }

    @RequestMapping(value = "/shield/communitylife", method = RequestMethod.PUT)
    @ApiOperation(value = "屏蔽活动动态", notes = "屏蔽活动动态", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PreCreateThemeDto> shieldCommunityLife(@NotNull(message = "动态ID") @RequestParam Integer lifeId) {
        activityAdminService.updateCommunityLifeStatusShield(lifeId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "操作成功", null);
    }

    @RequestMapping(value = "/cancel/shield/communitylife", method = RequestMethod.PUT)
    @ApiOperation(value = "取消屏蔽活动动态", notes = "取消屏蔽活动动态", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PreCreateThemeDto> cacelShieldCommunityLife(@NotNull(message = "动态ID") @RequestParam Integer lifeId) {
        activityAdminService.updateCommunityLifeStatusCancelShield(lifeId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "操作成功", null);
    }

    @RequestMapping(value = "theme/activityInfo", method = RequestMethod.GET)
    @ApiOperation(value = "创建主题信息回显活动信息", notes = "创建主题信息回显", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PreCreateThemeDto> preCreateTheme(@ApiParam(value = "活动id") @RequestParam Integer activityId) {
        PreCreateThemeDto dto = activityAdminService.getPreCreateThemeDtoByActivityId(activityId);
        return JSONPublicDto.returnSuccessData(dto);
    }

    @RequestMapping(value = "add/theme", method = RequestMethod.POST)
    @ApiOperation(value = "创建主题", notes = "创建主题", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<String> addTheme(@RequestBody JSONPublicParam<AddThemeRequestDto> params) {

        activityAdminService.addTheme(params);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "创建主题成功", null);
    }

    @RequestMapping(value = "display/theme", method = RequestMethod.GET)
    @ApiOperation(value = "编辑主题回显主题信息", notes = "根据主题id回显主题信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<FindThemeDetailResponseDto> getByThemeId(@ApiParam(value = "主题id", required = true) @RequestParam("themeId") Integer themeId) {

        FindThemeDetailResponseDto atrd = activityAdminService.getByThemeId(themeId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "成功", atrd);
    }

    @RequestMapping(value = "update/theme", method = RequestMethod.PUT)
    @ApiOperation(value = "编辑主题", notes = "编辑主题", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<String> updateTheme(@RequestBody JSONPublicParam<FindThemeDetailResponseDto> param) {
        activityAdminService.updateTheme(param);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "编辑主题成功", null);
    }

    @RequestMapping(value = "find/info/{activityId}/{page}/{rows}", method = RequestMethod.GET)
    @ApiOperation(value = "查看报名明细", notes = "查看报名明细", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<FindRegisterResponseDto>> findInfo(@ApiParam(value = "活动id", required = true) @PathVariable Integer activityId,
                                                                     @ApiParam(value = "当前页码", required = true) @PathVariable Integer page,
                                                                     @ApiParam(value = "每页条数", required = true) @PathVariable Integer rows) {

        PagesDto<FindRegisterResponseDto> pagesDto = activityAdminService.getRegistInfo(activityId, page, rows);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询报名明细成功", pagesDto);
    }

    @RequestMapping(value = "find/invitationInfo/{activityId}/{page}/{rows}", method = RequestMethod.GET)
    @ApiOperation(value = "查看邀请明细", notes = "查看邀请明细", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<FindInvitationResponseDto>> findInvitationInfo(@ApiParam(value = "活动id", required = true) @PathVariable Integer activityId,
                                                                                 @ApiParam(value = "当前页码", required = true) @PathVariable Integer page,
                                                                                 @ApiParam(value = "每页条数", required = true) @PathVariable Integer rows) {

        PagesDto<FindInvitationResponseDto> pagesDto = activityAdminService.getInvitationInfo(activityId, page, rows);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询邀请明细成功", pagesDto);
    }

    @RequestMapping(value = "add/taskRreditRule", method = RequestMethod.POST)
    @ApiOperation(value = "活动打卡积分设置", notes = "活动打卡积分设置", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<String> addTaskCreditRule(@RequestBody TaskRecordRequestDto trrd) {
        //打卡一次增加一个积分
        activityAdminService.updateTaskScore(trrd.getTaskRecord());
        return JSONPublicDto.returnSuccessData("设置打卡积分成功");
    }

    @RequestMapping(value = "taskScore", method = RequestMethod.GET)
    @ApiOperation(value = "打卡积分回显", notes = "活动打卡积分回显", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<Integer> taskScore() {
        return JSONPublicDto.returnSuccessData(activityAdminService.getTaskScore());
    }


    @RequestMapping(value = "find/publishedActivityDetail", method = RequestMethod.GET)
    @ApiOperation(value = "查询发布活动明细", notes = "查询发布活动明细", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<FindPublishedActivityDetailResponseDto>> findPublishedActivityDetail(@ApiParam(value = "用户id") UserPublishActivityRequestDto param) {
        PagesDto<FindPublishedActivityDetailResponseDto> pagesDto = activityAdminService.getPublishedActivityDetail(param);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询发布的活动信息成功", pagesDto);
    }

    @RequestMapping(value = "find/participateActivityDetail/{userId}/{page}/{rows}", method = RequestMethod.GET)
    @ApiOperation(value = "查询参与活动明细", notes = "查询参与活动明细", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<FindParticipateActivityDetailResponseDto>> findparticipateActivityDetail(
            @ApiParam(value = "要查询的用户id", required = true) @PathVariable Integer userId,
            @ApiParam(value = "当前页码", required = true) @PathVariable Integer page,
            @ApiParam(value = "每页条数", required = true) @PathVariable Integer rows) {
        PagesDto<FindParticipateActivityDetailResponseDto> pagesDto = activityAdminService.getParticipateActivityDetail(userId, page, rows);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询参与的活动信息成功", pagesDto);
    }

}
