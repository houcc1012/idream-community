package com.idream.controller.admin;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.activity.*;
import com.idream.service.AdminActivityStatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: houcc
 * @Date: 2018/6/6
 */
@RestController
@Api(description = "ActivityStatisticsController", tags = "后台活动统计（管理端）")
@RequestMapping("/admin/activityStatistics")
public class ActivityStatisticsController {
    @Autowired
    private AdminActivityStatisticsService adminActivityStatisticsService;

    @RequestMapping(value = "/getActivityData", method = RequestMethod.GET)
    @ApiOperation(value = "后台活动信息查询", notes = "后台活动信息查询", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<AdminActivityStatisticsDto>> getRegisterUserList(AdminActivityStatisticsParams params) {
        List<AdminActivityStatisticsDto> activityDtos = adminActivityStatisticsService.getActivityData(params);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", activityDtos);
    }

    @RequestMapping(value = "/getYesterdayAndToday", method = RequestMethod.GET)
    @ApiOperation(value = "今天昨天活动信息查询", notes = "后台活动信息查询", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<List<AdminActivityStatisticsDto>>> selectYesterdayAndToday() {
        List<List<AdminActivityStatisticsDto>> activityDtos = adminActivityStatisticsService.getYesterdayAndToday();
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", activityDtos);
    }
    @RequestMapping(value = "/getActivitySignDetail", method = RequestMethod.GET)
    @ApiOperation(value = "活动报名信息查询", notes = "后台活动信息查询", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<AdminActivitySignStatisticsDto>> getActivitySignDetail(AdminActivitySignStatisticsParams params) {
        PagesDto<AdminActivitySignStatisticsDto> activityDtos = adminActivityStatisticsService.getActivitySignDetail(params);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", activityDtos);
    }

    @RequestMapping(value = "/getActivityTaskDetail", method = RequestMethod.GET)
    @ApiOperation(value = "活动打卡信息查询", notes = "后台活动信息查询", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<AdminActivityTaskStatisticsDto>> getActivityTaskDetail(AdminActivityTaskStatisticsParams params) {
        PagesDto<AdminActivityTaskStatisticsDto> activityDtos = adminActivityStatisticsService.getActivityTaskDetail(params);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", activityDtos);
    }

    @RequestMapping(value = "/getActivityInviteDetail", method = RequestMethod.GET)
    @ApiOperation(value = "活动邀请信息查询", notes = "后台活动信息查询", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<AdminActivityInviteStatisticsDto>> getActivityInviteDetail(AdminActivityInviteStatisticsParams params) {
        PagesDto<AdminActivityInviteStatisticsDto> activityDtos = adminActivityStatisticsService.getActivityInviteDetail(params);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", activityDtos);
    }
}
