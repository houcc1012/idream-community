package com.idream.controller.miniprogram;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.base.StatisticalType;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.activity.*;
import com.idream.commons.lib.dto.appactivity.AppActivityGroupRequestDto;
import com.idream.commons.lib.dto.appactivity.AppActivityTypeResponseDto;
import com.idream.commons.lib.dto.information.InformationActivityParams;
import com.idream.commons.lib.dto.information.InformationRuleDetail;
import com.idream.commons.lib.dto.information.InformationRuleDto;
import com.idream.commons.lib.dto.information.InformationUserRecordDto;
import com.idream.commons.lib.dto.user.ParticipateActivityDto;
import com.idream.commons.mvc.annotation.StatisticalData;
import com.idream.service.ActivityDisplayService;
import com.idream.service.ActivityInfoService;
import com.idream.service.AppActivityService;
import com.idream.service.InformationRuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

import static org.bouncycastle.asn1.ua.DSTU4145NamedCurves.params;

/**
 * @author: ZhengGaosheng
 * @date: 2018/7/2 16:47
 * @description:
 */
@RestController
@Api(tags = "小程序活动展示相关接口", description = "小程序活动展示相关接口")
@RequestMapping("/activityDisplay")
public class ActivityDisplayController {

    @Autowired
    private ActivityDisplayService activityDisplayService;

    @Autowired
    private ActivityInfoService activityInfoService;

    @Autowired
    private AppActivityService appActivityService;
    @Autowired
    private InformationRuleService informationRuleService;

    @RequestMapping(value = "/displayActivity", method = RequestMethod.GET)
    @ApiOperation(value = "展示活动", notes = "展示活动", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @StatisticalData(type = StatisticalType.ACTIVITY)
    public JSONPublicDto<DisplayActivityResponseDto> displayActivity(@ApiIgnore @RequestParam("authUserId") Integer authUserId,
                                                                     @NotNull @ApiParam(value = "活动id", required = true) @RequestParam Integer activityId) {
        DisplayActivityResponseDto displayActivityDto = activityDisplayService.displayActivity(activityId, authUserId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "回显活动成功", displayActivityDto);
    }

    //小程序查询用户参加的活动
    @RequestMapping(value = "/show/join/userActivityPartake", method = RequestMethod.GET)
    @ApiOperation(value = "小程序查询用户参加的活动", notes = "小程序查询用户参加的活动", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<ParticipateActivityDto>> showJoinActivity(@ApiIgnore @RequestParam int authUserId,
                                                                            @ApiParam(value = "页码", required = true) @RequestParam int page,
                                                                            @ApiParam(value = "行数", required = true) @RequestParam int rows) {

        PagesDto<ParticipateActivityDto> data = activityDisplayService.getUserActivityPartake(authUserId, page, rows);
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

    @RequestMapping(value = "information", method = RequestMethod.GET)
    @ApiOperation("活动信息规则返回")
    public JSONPublicDto<List<InformationRuleDto>> getInformation(Integer activityId) {
        List<InformationRuleDto> list = informationRuleService.listInformationRules(activityId);
        return JSONPublicDto.returnSuccessData(list);
    }

    @RequestMapping(value = "information", method = RequestMethod.POST)
    @ApiOperation("录入信息")
    public JSONPublicDto<String> saveInformation(@RequestBody JSONPublicParam<InformationActivityParams> activityInformations) {
        Integer userId = activityInformations.getAuthUserInfo().getUserId();
        InformationActivityParams requestParam = activityInformations.getRequestParam();
        informationRuleService.saveActivityInformation(userId, requestParam);
        return JSONPublicDto.returnSuccessData("录入信息成功");
    }

    @RequestMapping(value = "informationRule", method = RequestMethod.GET)
    @ApiOperation("活动规则相关")
    public JSONPublicDto<InformationRuleDetail> ruleDetail() {
        InformationRuleDetail detail = informationRuleService.getInformationRuleDetail();
        return JSONPublicDto.returnSuccessData(detail);
    }

    @RequestMapping(value = "informationUser", method = RequestMethod.GET)
    @ApiOperation("用户最新录入信息")
    public JSONPublicDto<List<InformationUserRecordDto>> informationUserRecord(@ApiIgnore @RequestParam("authUserId") Integer userId) {
        List<InformationUserRecordDto> list = informationRuleService.getLastUserRecord(userId);
        return JSONPublicDto.returnSuccessData(list);
    }

    @RequestMapping(value = "especiallyActivity", method = RequestMethod.GET)
    @ApiOperation(value = "特色活动", notes = "特色活动", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<EspeciallyActivityResponseDto>> especiallyActivity(@ApiIgnore @RequestParam("authUserId") Integer authUserId, @NotBlank(message = "活动id不能为空") @ApiParam(value = "活动id,多个活动id用英文逗号隔开", required = true) @RequestParam("activityIds") String activityIds) {
        List<EspeciallyActivityResponseDto> list = activityDisplayService.especiallyActivity(authUserId, activityIds);
        return JSONPublicDto.returnSuccessData(list);
    }

    //发现页所有的活动类型
    @RequestMapping(value = "/miniActivitytype", method = RequestMethod.GET)
    @ApiOperation(value = "当前城市所有活动类型", notes = "当前城市有活动的活动类型", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<AppActivityTypeResponseDto>> getMiniActivityTypeByCityCode(@NotBlank(message = "城市编码不能为空") @RequestParam("cityCode") String cityCode) {
        List<AppActivityTypeResponseDto> list = activityDisplayService.getMiniActivityTypeByCityCode(cityCode);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询活动类目成功", list);
    }

    @RequestMapping(value = "/miniDiscoverypage", method = RequestMethod.GET)
    @ApiOperation(value = "发现页(小程序)", notes = "活动列表(小程序)", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<MiniActivityDiscoverypageResponseDto>> miniDiscoverypage(DiscoveryPageRequestParams params) {
        PagesDto<MiniActivityDiscoverypageResponseDto> data = activityDisplayService.miniDiscoverypage(params);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }

    @RequestMapping(value = "/miniActivityByType", method = RequestMethod.GET)
    @ApiOperation(value = "根据活动类型查询活动列表(小程序)", notes = "根据活动类型查询活动列表(小程序)", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<MiniActivityDiscoverypageResponseDto>> miniActivityByType(DiscoveryPageRequestParams params) {
        PagesDto<MiniActivityDiscoverypageResponseDto> data = activityDisplayService.miniActivityByType(params);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }

    @RequestMapping(value = "/group/interaction", method = RequestMethod.GET)
    @ApiOperation(value = "活动圈互动排行榜", notes = "活动圈互动排行榜", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<AppCommunityLifeDto>> getActivityGroupInteraction(AppActivityGroupRequestDto requestDto) {

        PagesDto<AppCommunityLifeDto> list = appActivityService.getGroupInteractionList(requestDto);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "活动圈互动查询成功", list);
    }


}

