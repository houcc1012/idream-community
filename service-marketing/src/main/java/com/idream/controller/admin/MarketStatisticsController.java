package com.idream.controller.admin;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.marketing.*;
import com.idream.service.StatisticsService;
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
@RequestMapping(value = "admin/marketStatistics")
@Api(tags = "营销数据统计相关接口(管理端)", description = "MarketStatisticsController")
public class MarketStatisticsController {
    @Autowired
    private StatisticsService statisticsService;

    @RequestMapping(value = "/getMarketData", method = RequestMethod.GET)
    @ApiOperation(value = "查询营销数据", notes = "查询营销数据", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<MarketStaticsDto>> selectLotteryPoolList(MarketStaticsParams params) {
        List<MarketStaticsDto> lists = statisticsService.getMarketData(params);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", lists);
    }

    @RequestMapping(value = "/getYesterdayAndToday", method = RequestMethod.GET)
    @ApiOperation(value = "查询今日昨日数据", notes = "查询今日昨日数据", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<List<MarketStaticsDto>>> selectTodayTomorrow() {
        List<List<MarketStaticsDto>> lists = statisticsService.getYesterdayAndToday();
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", lists);
    }

    @RequestMapping(value = "/getIntegrationDetail", method = RequestMethod.GET)
    @ApiOperation(value = "查询抽奖数据", notes = "查询抽奖数据", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<MarketIntegrationStaticsDto>> getIntegrationDetail(MarketDetailStaticsParams params) {
        PagesDto<MarketIntegrationStaticsDto> lists = statisticsService.getIntegrationDetail(params);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", lists);
    }

    @RequestMapping(value = "/getAwardDetail", method = RequestMethod.GET)
    @ApiOperation(value = "查询兑奖数据", notes = "查询兑奖数据", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<MarketAwardStaticsDto>> getAwardDetail(MarketDetailStaticsParams params) {
        PagesDto<MarketAwardStaticsDto> lists = statisticsService.getAwardDetail(params);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", lists);
    }

    @RequestMapping(value = "/getLotteryDetail", method = RequestMethod.GET)
    @ApiOperation(value = "查询开奖数据", notes = "查询抽奖数据", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<MarketLotteryStaticsDto>> getLotteryDetail(MarketDetailStaticsParams params) {
        PagesDto<MarketLotteryStaticsDto> lists = statisticsService.getLotteryDetail(params);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", lists);
    }
}
