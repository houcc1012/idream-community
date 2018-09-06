package com.idream.controller.miniprogram;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.activity.UserJoinActivityRequestDto;
import com.idream.commons.lib.dto.admin.ActivityInfoDto;
import com.idream.commons.lib.dto.admin.CommunityActivityParams;
import com.idream.service.AppActivityService;
import com.idream.service.CommunityInfoService;
import com.idream.service.LotteryInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 用户活动列表信息查询
 *
 * @author DELL2018-005
 */
@RestController
@RequestMapping("/activityInfo")
@Api(tags = "活动列表", description = "用户活动列表方法")
public class ActivityInfoController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityInfoController.class);
    @Autowired
    private CommunityInfoService communityInfoService;

    @Autowired
    private LotteryInfoService lotteryInfoService;

    @Autowired
    private AppActivityService appActivityService;

    @RequestMapping(value = "/join/userJoinActivity", method = RequestMethod.POST)
    @ApiOperation(value = "用户报名参加活动", notes = "用户报名参加活动", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<Integer> userJoinActivity(@ApiParam(value = "用户报名参加活动", required = true) @RequestBody JSONPublicParam<UserJoinActivityRequestDto> params) {
        Integer activityId = appActivityService.addUserToActivity(params);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "报名参加活动成功", activityId);
    }

    @RequestMapping(value = "/search/lottery/award/{activityId}", method = RequestMethod.GET)
    @ApiOperation(value = "根据活动ID查询是否有开奖活动", notes = "根据活动ID查询是否有开奖活动", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto selectLotteryInfoByAcitvityId(@ApiParam("活动ID") @PathVariable Integer activityId) {
        Boolean result = lotteryInfoService.selectLotteryInfoByAcitvityId(activityId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", result);
    }
}
