package com.idream.controller.miniprogram;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.information.InformationCouponParams;
import com.idream.commons.lib.dto.information.InformationRuleDetail;
import com.idream.commons.lib.dto.information.InformationUserRecordDto;
import com.idream.commons.lib.dto.marketing.*;
import com.idream.commons.lib.model.TagInfoTree;
import com.idream.service.InformationMarketService;
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


@RestController
@RequestMapping(value = "/miniProgram/lottery")
@Api(tags = "开奖相关接口（小程序）", description = "miniLotteryController")
public class MiniLotteryController {

    @Autowired
    private LotteryService lotteryService;
    @Autowired
    private InformationMarketService informationMarketService;

    @RequestMapping(value = "/wetherNeedWriteInfo", method = RequestMethod.GET)
    @ApiOperation(value = "根据奖池ID和类型判断是否需要填写信息/(1是0否)", notes = "根据奖池ID和类型判断是否需要填写信息/(1是0否)", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto wetherNeedWriteInfo(@NotNull @ApiParam(value = "奖池ID", required = true) @RequestParam(value = "poolId") Integer poolId,
                                             @NotNull @ApiParam(value = "类型(1-抽奖,2-兑奖,3-现场开奖)", required = true) @RequestParam(value = "type") Integer type) {

        return JSONPublicDto.returnSuccessData(lotteryService.wetherNeedWriteInfo(poolId, type));
    }

    @RequestMapping(value = "/selectLotteryPoolList", method = RequestMethod.GET)
    @ApiOperation(value = "查询最近开奖列表", notes = "查询最近开奖列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<WeChatLotteryInfoDto>> selectLotteryPoolList(LocationParams params) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "成功", lotteryService.selectLotteryPoolListByNear(params));
    }

    @RequestMapping(value = "/open/award", method = RequestMethod.POST)
    @ApiOperation(value = "现场开奖", notes = "现场开奖", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<OpenLotteryAwardDto> openLotteryAward(@RequestBody JSONPublicParam<OpenLotteryAwardParams> params) {
        OpenLotteryAwardDto dto = lotteryService.doOpenLotteryAward(params);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "", dto);
    }

    @RequestMapping(value = "/selectActivityInfoByLotteryId", method = RequestMethod.GET)
    @ApiOperation(value = "根据抽奖活动id查询关联活动", notes = "根据抽奖活动id查询关联活动", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<LotteryActivityInfoDto> selectActivityInfoByLotteryId(@ApiIgnore @RequestParam("authUserId") Integer userId,
                                                                               @NotNull @ApiParam(value = "抽奖活动ID", required = true) @RequestParam(value = "lotteryId") Integer lotteryId) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "成功", lotteryService.selectActivityInfoByLotteryId(userId, lotteryId));
    }

    @RequestMapping(value = "information", method = RequestMethod.GET)
    @ApiOperation("活动信息规则返回")
    public JSONPublicDto<List<com.idream.commons.lib.dto.information.InformationRuleDto>> getInformation(Integer couponId) {
        List<com.idream.commons.lib.dto.information.InformationRuleDto> list = informationMarketService.listInformationRulesByCouponId(couponId);
        return JSONPublicDto.returnSuccessData(list);
    }

    @RequestMapping(value = "information", method = RequestMethod.POST)
    @ApiOperation("录入信息")
    public JSONPublicDto<String> saveInformation(@RequestBody JSONPublicParam<InformationCouponParams> couponInformations) {
        Integer userId = couponInformations.getAuthUserInfo().getUserId();
        InformationCouponParams requestParam = couponInformations.getRequestParam();
        informationMarketService.saveCouponInformations(userId, requestParam);
        return JSONPublicDto.returnSuccessData("录入成功");
    }

    @RequestMapping(value = "informationUser", method = RequestMethod.GET)
    @ApiOperation("用户最新录入信息")
    public JSONPublicDto<List<InformationUserRecordDto>> informationUserRecord(@ApiIgnore @RequestParam("authUserId") Integer userId) {
        List<InformationUserRecordDto> list = informationMarketService.getLastUserRecord(userId);
        return JSONPublicDto.returnSuccessData(list);
    }

}
