package com.idream.controller.app;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.marketing.*;
import com.idream.commons.lib.model.IntegrationPool;
import com.idream.service.IntegrationService;
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
@RequestMapping(value = "/app/integration")
@Api(tags = "抽奖相关接口（APP）", description = "AppIntegrationController")
public class AppIntegrationController {

    @Autowired
    private IntegrationService integrationService;

    @RequestMapping(value = "/draw", method = RequestMethod.POST)
    @ApiOperation(value = "积分抽奖", notes = "积分抽奖", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<IntegrationDrawDto> integrationDraw(@RequestBody JSONPublicParam<IntegrationDrawParams> params) {
        IntegrationDrawDto drawDto = integrationService.doIntegrationDraw(params);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "兑换成功", drawDto);
    }

    @RequestMapping(value = "/winning/record", method = RequestMethod.GET)
    @ApiOperation(value = "查询24小时的中奖纪录", notes = "查询24小时的中奖纪录", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<String>> get24HoursWinningRecord() {
        List<String> data = integrationService.get24HoursWinningRecord();
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }

    @RequestMapping(value = "/selectCouponRecord", method = RequestMethod.GET)
    @ApiOperation(value = "查询中奖记录列表", notes = "查询中奖记录列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<CouponRecordDto>> selectCouponRecord() {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", integrationService.selectCouponRecord());
    }

    @RequestMapping(value = "/selectIntegrationList", method = RequestMethod.GET)
    @ApiOperation(value = "查询抽奖奖池", notes = "查询抽奖奖池", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<IntegrationPool>> selectIntegrationPoolList(@NotNull @ApiParam(value = "第几页", required = true) @RequestParam(value = "page") int page,
                                                                              @NotNull @ApiParam(value = "一页多少行", required = true) @RequestParam(value = "rows") int rows) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", integrationService.selectMiniIntegrationPoolList(page, rows));
    }

    @RequestMapping(value = "/selectIntegrationById", method = RequestMethod.GET)
    @ApiOperation(value = "查询抽奖明细", notes = "查询抽奖明细", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<IntegrationPoolDto> selectIntegrationById(@NotNull @ApiParam(value = "奖池ID", required = true) @RequestParam(value = "id") int id) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", integrationService.selectIntegrationById(id));
    }

    @RequestMapping(value = "/selectPool", method = RequestMethod.GET)
    @ApiOperation(value = "查询抽奖书屋奖池", notes = "查询抽奖社区奖池", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<Integer> selectDrawRegion(RegionPollParams param) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", integrationService.getDrawBookId(param));
    }

    @RequestMapping(value = "/selectWinningCount", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户挖到藏宝次数", notes = "查询用户挖到藏宝次数", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<Integer> selectIntegrationCount(@ApiIgnore @RequestParam int authUserId) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", integrationService.selectIntegrationCountByUser(authUserId));
    }

    @RequestMapping(value = "/selectAwardCostScore", method = RequestMethod.GET)
    @ApiOperation(value = "查询抽奖消耗积分", notes = "查询抽奖消耗积分", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<Integer> selectAwardCostScore() {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", integrationService.selectAwardCostScore());
    }

    @RequestMapping(value = "/selectIntegrationImage", method = RequestMethod.GET)
    @ApiOperation(value = "查询抽奖背景图", notes = "查询抽奖背景图", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<String>> selectIntegrationImage() {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", integrationService.selectIntegrationImage());
    }
}
