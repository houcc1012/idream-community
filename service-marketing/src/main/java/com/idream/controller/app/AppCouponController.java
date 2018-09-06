package com.idream.controller.app;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.information.AppInformationRuleDetail;
import com.idream.commons.lib.dto.information.InformationCouponParams;
import com.idream.commons.lib.dto.information.InformationRuleDetail;
import com.idream.commons.lib.dto.information.InformationUserRecordDto;
import com.idream.commons.lib.dto.marketing.CouponCollectionParams;
import com.idream.commons.lib.dto.marketing.CouponInfoDto;
import com.idream.commons.lib.dto.marketing.InformationRuleDto;
import com.idream.commons.lib.dto.marketing.InformationRuleValueDto;
import com.idream.service.InformationMarketService;
import com.idream.service.IntegrationService;
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
 * @Description : APP卡券相关接口
 * @Created by xiaogang on 2018/5/2.
 */
@RestController
@RequestMapping(value = "/app")
@Api(tags = "奖券相关接口（APP）", description = "AppCouponController")
public class AppCouponController {

    @Autowired
    private IntegrationService integrationService;

    @Autowired
    private LotteryService lotteryService;
    @Autowired
    private InformationMarketService informationMarketService;

    @RequestMapping(value = "/selectCouponByUserId", method = RequestMethod.GET)
    @ApiOperation(value = "用户礼品券列表", notes = "用户礼品券列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<CouponInfoDto>> selectCouponByUserId(@NotNull @ApiParam(value = "第几页", required = true) @RequestParam(value = "page") int page,
                                                                       @NotNull @ApiParam(value = "一页多少行", required = true) @RequestParam(value = "rows") int rows,
                                                                       @ApiIgnore @RequestParam int authUserId) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", integrationService.selectNotDeletedCouponByUserId(page, rows, authUserId));
    }

    @RequestMapping(value = "/selectOneByCouponId", method = RequestMethod.GET)
    @ApiOperation(value = "用户礼品券详情", notes = "用户礼品券详情", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<CouponInfoDto> selectOneByCouponId(@NotNull @ApiParam(value = "券码ID", required = true) @RequestParam(value = "couponId") int couponId) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", integrationService.selectOneByCouponId(couponId));
    }

    @RequestMapping(value = "/wetherNeedWriteInfo", method = RequestMethod.GET)
    @ApiOperation(value = "根据奖池ID和类型判断是否需要填写信息/(1是0否)", notes = "根据奖池ID和类型判断是否需要填写信息/(1是0否)", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto wetherNeedWriteInfo(@NotNull @ApiParam(value = "奖池ID", required = true) @RequestParam(value = "poolId") Integer poolId,
                                             @NotNull @ApiParam(value = "类型(1-抽奖,2-兑奖,3-现场开奖)", required = true) @RequestParam(value = "type") Integer type) {

        return JSONPublicDto.returnSuccessData(lotteryService.wetherNeedWriteInfo(poolId, type));
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

    @RequestMapping(value = "informationRule", method = RequestMethod.GET)
    @ApiOperation("信息规则")
    public JSONPublicDto informationRule() {
        AppInformationRuleDetail informationRuleDetail = informationMarketService.getAppInformationRuleDetail();
        return JSONPublicDto.returnSuccessData(informationRuleDetail);
    }

    @RequestMapping(value = "/deleteCouponById", method = RequestMethod.DELETE)
    @ApiOperation(value = "用户删除礼品券", notes = "用户删除礼品券", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<CouponInfoDto>> selectCouponByUserId(@ApiParam(value = "券码id", required = true) @RequestParam(value = "couponId") Integer couponId) {
        integrationService.deleteCouponById(couponId);
        return JSONPublicDto.returnSuccessData("删除成功");
    }


}
