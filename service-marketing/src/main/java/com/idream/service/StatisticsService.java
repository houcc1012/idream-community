package com.idream.service;

import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.marketing.*;

import java.util.List;

/**
 * @Author: houcc
 * @Date: 2018/6/6
 */
public interface StatisticsService {
    List<MarketStaticsDto> getMarketData(MarketStaticsParams params);

    List<List<MarketStaticsDto>> getYesterdayAndToday();

    PagesDto<MarketIntegrationStaticsDto> getIntegrationDetail(MarketDetailStaticsParams params);

    PagesDto<MarketAwardStaticsDto> getAwardDetail(MarketDetailStaticsParams params);

    PagesDto<MarketLotteryStaticsDto> getLotteryDetail(MarketDetailStaticsParams params);

}
