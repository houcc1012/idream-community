package com.idream.controller.manager;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.activity.*;
import com.idream.commons.lib.dto.activity.Manager.ActivityListRequestDto;
import com.idream.commons.lib.dto.wangyi.CreateGroupRequestParam;
import com.idream.commons.lib.model.*;
import com.idream.service.ActivityAdminService;
import com.idream.service.ActivityManagerService;
import com.idream.service.ActivityTagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @Auther: penghekai
 * @Date: 2018/7/5 16:20
 * @Description:
 */
@RestController
@Api(tags = "活动相关接口(管理者平台)", description = "管理者平台")
@RequestMapping("/manager/admin")
public class ActivityManagerController {

    @Autowired
    private ActivityManagerService activityManagerService;
    @Autowired
    private ActivityAdminService activityAdminService;
    @Autowired
    private ActivityTagService activityTagService;


    @RequestMapping(value = "find/activity", method = RequestMethod.GET)
    @ApiOperation(value = "活动列表分页展示", notes = "活动列表分页展示", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<FindAllActivityResponseDto>> findAllActivity(@ApiIgnore @RequestParam("authUserId") Integer userId, ActivityListRequestDto param) {
        PagesDto<FindAllActivityResponseDto> pagesDto = activityManagerService.getActivityList(userId, param);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询活动列表成功", pagesDto);
    }

    //活动数据统计
    @RequestMapping(value = "count/statistics", method = RequestMethod.GET)
    @ApiOperation(value = "活动数据统计", notes = "活动数据展示", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<AdminActivityStatisticsResponseDto>> countActivityStatistics(@ApiParam("活动id") @RequestParam Integer activityId) {
        List<AdminActivityStatisticsResponseDto> dto = activityManagerService.getActivityStatisticsByActivityId(activityId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", dto);
    }

    @RequestMapping(value = "activity/type", method = RequestMethod.GET)
    @ApiOperation(value = "活动类型", notes = "活动类型", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<ActivityType>> findAllActivityType() {

        List<ActivityType> typeList = activityAdminService.getActivityTypeList();
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询活动类型成功", typeList);
    }

    @RequestMapping(value = "book/relate/district", method = RequestMethod.GET)
    @ApiOperation(value = "根据用户绑定的书屋查询所属的区域", notes = "根据用户关联的书屋查询区域", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<String> bookRelateDistrict(@ApiIgnore @RequestParam("authUserId") Integer userId) {
        String districtCode = activityManagerService.selectDistrictCodeByUserId(userId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", districtCode);
    }

    @RequestMapping(value = "book/relate/region", method = RequestMethod.GET)
    @ApiOperation(value = "根据用户绑定的书屋查询关联的社区", notes = "根据用户关联的书屋查询关联社区", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<RegionInfo>> bookRelateRegionList(@ApiIgnore @RequestParam("authUserId") Integer userId) {
        List<RegionInfo> list = activityManagerService.selectRegionListByUserId(userId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", list);
    }

    @RequestMapping(value = "select/region/group", method = RequestMethod.GET)
    @ApiOperation(value = "查询高级社区下的所有小区", notes = "查询高级社区下的所有小区", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<RegionGroupInfo>> selectRegionGroupByRegionId(@ApiParam(value = "高级社区id", required = true) @RequestParam Integer regionId) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "成功", activityAdminService.selectRegionGroupByRegionId(regionId));
    }

    @RequestMapping(value = "activity/information", method = RequestMethod.GET)
    @ApiOperation(value = "查询活动收集信息列表", notes = "查询活动收集信息列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<InformationRule>> information() {
        List<InformationRule> infos = activityAdminService.listInformations();
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", infos);
    }

    @RequestMapping(value = "secondTag", method = RequestMethod.GET)
    @ApiOperation(value = "查询活动类型关联的活动标签", notes = "查询活动活动标签", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<AppActivityTypeRelateTagResponseDto>> listByTypeId(@ApiParam(value = "typeId", required = true) @RequestParam("typeId") Integer typeId) {
        List<AppActivityTypeRelateTagResponseDto> list = activityTagService.listTagByTypeId(typeId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", list);
    }

    @RequestMapping(value = "add/activity", method = RequestMethod.POST)
    @ApiOperation(value = "创建活动", notes = "创建活动", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<String> addActivity(@ApiIgnore @RequestParam("authUserId") Integer createId, @RequestBody AdminActivityPublishDto aapd) {

        activityManagerService.addActivity(createId, aapd);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "创建活动成功", null);
    }

    @RequestMapping(value = "find/byId", method = RequestMethod.GET)
    @ApiOperation(value = "根据活动id查询活动信息", notes = "根据活动id查询活动信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<AdminActivityDisplayDto> getByActivityId(Integer activityId) {

        AdminActivityDisplayDto aadd = activityManagerService.getByActivityId(activityId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "成功", aadd);
    }

    @RequestMapping(value = "update/activity", method = RequestMethod.PUT)
    @ApiOperation(value = "编辑活动", notes = "编辑活动", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<String> updateActivity(@RequestBody JSONPublicParam<AdminActivityPublishDto> aapd) {

        activityManagerService.updateActivity(aapd);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "编辑成功", null);
    }

    @RequestMapping(value = "submit/{activityId}", method = RequestMethod.PUT)
    @ApiOperation(value = "提交审核", notes = "提交审核", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<String> submitActivity(@ApiParam(value = "活动id", required = true) @PathVariable Integer activityId) {

        activityManagerService.updateActivityStatusSubmit(activityId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "操作成功", null);
    }

    @RequestMapping(value = "cancel/activity", method = RequestMethod.POST)
    @ApiOperation(value = "临时取消", notes = "活动下架", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<String> cancelActivity(@ApiIgnore @RequestParam("authUserId") Integer userId, @ApiParam(value = "活动取消请求参数", required = true) @RequestBody AdminActivityOperateRecordRequestDto param) {
        param.setUserId(userId);
        activityManagerService.operateActivityCancel(param);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "取消活动成功", null);
    }

    @RequestMapping(value = "publish/{activityId}", method = RequestMethod.PUT)
    @ApiOperation(value = "发布活动(上架)", notes = "审核通过后发布活动", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<String> publishActivity(@ApiParam(value = "要发布的活动id", required = true) @PathVariable Integer activityId) {

        activityManagerService.updateActivityStatusPutWay(activityId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "操作成功", null);
    }

    //创建群聊
    @RequestMapping(value = "create/croup/chat", method = RequestMethod.POST)
    @ApiOperation(value = "创建群聊", notes = "手动创建群聊", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<String> createGroupChat(@ApiIgnore @RequestParam("authUserId") Integer userId, @ApiParam(value = "创建群聊请求参数", required = true) @RequestBody CreateGroupRequestParam param) {
        activityManagerService.addCreateGroupChat(userId, param);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "创建成功", null);
    }

    @RequestMapping(value = "delete/activity/{activityId}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除活动", notes = "删除活动", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<String> deleteActivity(@ApiParam(value = "活动id", required = true) @PathVariable Integer activityId) {

        activityManagerService.deleteActivity(activityId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "删除活动成功", null);
    }

    @RequestMapping(value = "find/type", method = RequestMethod.GET)
    @ApiOperation(value = "已发布成功的活动类型", notes = "已发布成功的活动类型", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<ActivityType>> findPublishedActivityType() {

        List<ActivityType> activityTypes = activityAdminService.findPublishedActivityType();
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询已发布活动类型成功", activityTypes);
    }

    @RequestMapping(value = "find/theme/{activityId}/{page}/{rows}", method = RequestMethod.GET)
    @ApiOperation(value = "活动主题列表展示", notes = "活动主题列表展示", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<FindThemeResponseDto>> findActivityTheme(@ApiParam(value = "活动id", required = true) @PathVariable Integer activityId,
                                                                           @ApiParam(value = "当前页码", required = true) @PathVariable Integer page,
                                                                           @ApiParam(value = "每页条数", required = true) @PathVariable Integer rows) {

        PagesDto<FindThemeResponseDto> pagesDto = activityAdminService.findActivityTheme(activityId, page, rows);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询主题列表成功", pagesDto);
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

    @RequestMapping(value = "find/community/life", method = RequestMethod.GET)
    @ApiOperation(value = "查看活动精彩", notes = "查看活动精彩", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<FindActivityMessageResponseDto>> findActivityMessage(ActivityAdminCommunityLifeRequestDto requestDto) {

        PagesDto<FindActivityMessageResponseDto> pagesDto = activityAdminService.findActivityCommunityLife(requestDto);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", pagesDto);
    }

    @RequestMapping(value = "find/publishedActivityDetail/{page}/{rows}", method = RequestMethod.GET)
    @ApiOperation(value = "查询累计发布的活动", notes = "查询发布活动明细", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<FindPublishedActivityDetailResponseDto>> findPublishedActivityDetail(@ApiIgnore @PathVariable("authUserId") Integer userId,
                                                                                                       @PathVariable(value = "当前页码", required = true) Integer page,
                                                                                                       @PathVariable(value = "每页条数", required = true) Integer rows) {
        PagesDto<FindPublishedActivityDetailResponseDto> pagesDto = activityManagerService.getPublishedActivityDetail(userId, page, rows);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", pagesDto);
    }

}

