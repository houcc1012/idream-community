package com.idream.controller.app;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.marketing.*;
import com.idream.commons.lib.dto.region.UnityLotteryDto;
import com.idream.service.LotteryService;
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
 * @author hejiang
 */
@RestController
@RequestMapping(value = "/app/lottery")
@Api(tags = "现场开奖相关接口（APP）", description = "AppLotteryController")
public class AppLotteryController {

    @Autowired
    private LotteryService lotteryService;

    @RequestMapping(value = "/open/award", method = RequestMethod.POST)
    @ApiOperation(value = "现场开奖", notes = "现场开奖", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<OpenLotteryAwardDto> openLotteryAward(@RequestBody JSONPublicParam<OpenLotteryAwardParams> params) {
        OpenLotteryAwardDto dto = lotteryService.doOpenLotteryAward(params);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "", dto);
    }

    @RequestMapping(value = "/selectLotteryPoolList", method = RequestMethod.GET)
    @ApiOperation(value = "查询最近开奖列表", notes = "查询最近开奖列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<WeChatLotteryInfoDto>> selectLotteryPoolList(LocationParams params) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "成功", lotteryService.selectLotteryPoolListByNear(params));
    }

    @RequestMapping(value = "/selectActivityInfoByLotteryId", method = RequestMethod.GET)
    @ApiOperation(value = "根据抽奖活动id查询关联活动", notes = "根据抽奖活动id查询关联活动", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<LotteryActivityInfoDto> selectActivityInfoByLotteryId(@ApiIgnore @RequestParam("authUserId") Integer userId,
                                                                               @NotNull @ApiParam(value = "抽奖活动ID", required = true) @RequestParam(value = "lotteryId") Integer lotteryId) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "成功", lotteryService.selectActivityInfoByLotteryId(userId, lotteryId));
    }

    @RequestMapping(value = "/selectLotteryByCommunityId", method = RequestMethod.GET)
    @ApiOperation(value = "根据社区id查询开奖活动", notes = "根据社区id查询开奖活动", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<UnityLotteryDto>> selectLotteryByCommunityId(@NotNull @ApiParam(value = "社区ID", required = true) @RequestParam(value = "communityId") Integer communityId) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "成功", lotteryService.selectLotteryByCommunityId(communityId));
    }
}
