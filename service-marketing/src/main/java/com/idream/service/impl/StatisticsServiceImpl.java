package com.idream.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.activity.AdminActivityStatisticsDto;
import com.idream.commons.lib.dto.marketing.*;
import com.idream.commons.lib.enums.ActivityStatisticsEnum;
import com.idream.commons.lib.enums.MarketStatisticsEnum;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.mapper.CouponInfoMapper;
import com.idream.commons.lib.util.DateUtils;
import com.idream.service.StatisticsService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.krb5.internal.PAData;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author: houcc
 * @Date: 2018/6/6
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {
    private Logger logger = LoggerFactory.getLogger(getClass().getName());
    @Autowired
    private CouponInfoMapper couponInfoMapper;

    @Override
    public List<MarketStaticsDto> getMarketData(MarketStaticsParams params) {
        try {
            if (params.getStartTime().length() != 8 || params.getEndTime().length() != 8) {
                throw new BusinessException(RetCodeConstants.PARAMS_ERROR, "日期格式错误!");
            }
            Date startTime = DateUtils.format(params.getStartTime(), DateUtils.YYYYMMDD);
            Date endTime = DateUtils.format(params.getEndTime(), DateUtils.YYYYMMDD);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(endTime);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            endTime = calendar.getTime();
            List<MarketStaticsDto> dtos = null;
            if (params.getType() == MarketStatisticsEnum.LOTTERY.getCode()) {
                dtos = couponInfoMapper.selectLotteryByDate(startTime, endTime);
            } else if (params.getType() == MarketStatisticsEnum.COUPON.getCode()) {
                dtos = couponInfoMapper.selectCouponsByDate(startTime, endTime);
            } else if (params.getType() == MarketStatisticsEnum.OPEN.getCode()) {
                dtos = couponInfoMapper.selectOpenByDate(startTime, endTime);
            } else {
                dtos = couponInfoMapper.selectAllByDate(startTime, endTime);
            }
            for (MarketStaticsDto dto : dtos) {
                dto.setType(params.getType());
            }
            return dtos;
        } catch (ParseException e) {
            logger.error("日期格式不正确");
            throw new BusinessException(RetCodeConstants.PARAMS_ERROR, "日期格式错误!");
        }
    }

    @Override
    public List<List<MarketStaticsDto>> getYesterdayAndToday() {
        String todayStr = DateUtils.format(new Date(), DateUtils.YYYYMMDD);
        try {
            Date todayDate = DateUtils.format(todayStr, DateUtils.YYYYMMDD);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(todayDate);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            Date yesterday = calendar.getTime();
            calendar.add(Calendar.DAY_OF_MONTH, 2);
            todayDate = calendar.getTime();
            String yesterdayStr = DateUtils.format(yesterday, DateUtils.YYYYMMDD);
            List<MarketStaticsDto> dtos = null;
            List<List<MarketStaticsDto>> lists = new ArrayList<>();
            dtos = couponInfoMapper.selectLotteryByDate(yesterday, todayDate);
            dtos = setStatisticsData(dtos, yesterdayStr, todayStr);
            MarketStaticsDto total = couponInfoMapper.selectTotalCouponByType(MarketStatisticsEnum.LOTTERY.getCode());
            for (MarketStaticsDto dto : dtos) {
                dto.setType(MarketStatisticsEnum.LOTTERY.getCode());
                if (total!=null){
                    dto.setTotalUserCount(total.getTotalUserCount());
                    dto.setTotalMarketCount(total.getTotalMarketCount());
                }
            }
            lists.add(dtos);
            dtos = couponInfoMapper.selectCouponsByDate(yesterday, todayDate);
            dtos = setStatisticsData(dtos, yesterdayStr, todayStr);
            total = couponInfoMapper.selectTotalCouponByType(MarketStatisticsEnum.COUPON.getCode());
            for (MarketStaticsDto dto : dtos) {
                dto.setType(MarketStatisticsEnum.COUPON.getCode());
                if (total!=null){
                    dto.setTotalUserCount(total.getTotalUserCount());
                    dto.setTotalMarketCount(total.getTotalMarketCount());
                }
            }
            lists.add(dtos);
            dtos = couponInfoMapper.selectOpenByDate(yesterday, todayDate);
            dtos = setStatisticsData(dtos, yesterdayStr, todayStr);
            total = couponInfoMapper.selectTotalCouponByType(MarketStatisticsEnum.OPEN.getCode());
            for (MarketStaticsDto dto : dtos) {
                dto.setType(MarketStatisticsEnum.OPEN.getCode());
                if (total!=null){
                    dto.setTotalUserCount(total.getTotalUserCount());
                    dto.setTotalMarketCount(total.getTotalMarketCount());
                }
            }
            lists.add(dtos);
            return lists;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public PagesDto<MarketIntegrationStaticsDto> getIntegrationDetail(MarketDetailStaticsParams params) {
        if (params.getStartTime().length() != 8 || params.getEndTime().length() != 8) {
            throw new BusinessException(RetCodeConstants.PARAMS_ERROR, "日期格式错误!");
        }
        PageHelper.startPage(params.getPage(),params.getRows());
        List<MarketIntegrationStaticsDto> list = couponInfoMapper.selectIntegrationDetail(params);
        PageInfo info = new PageInfo(list);
        return new PagesDto<>(info.getList(), info.getTotal(), params.getPage(),params.getRows());
    }

    @Override
    public PagesDto<MarketAwardStaticsDto> getAwardDetail(MarketDetailStaticsParams params) {
        if (params.getStartTime().length() != 8 || params.getEndTime().length() != 8) {
            throw new BusinessException(RetCodeConstants.PARAMS_ERROR, "日期格式错误!");
        }
        PageHelper.startPage(params.getPage(),params.getRows());
        List<MarketAwardStaticsDto> list = couponInfoMapper.selectAwardDetail(params);
        PageInfo info = new PageInfo(list);
        return new PagesDto<>(info.getList(), info.getTotal(), params.getPage(),params.getRows());
    }

    @Override
    public PagesDto<MarketLotteryStaticsDto> getLotteryDetail(MarketDetailStaticsParams params) {
        if (params.getStartTime().length() != 8 || params.getEndTime().length() != 8) {
            throw new BusinessException(RetCodeConstants.PARAMS_ERROR, "日期格式错误!");
        }
        PageHelper.startPage(params.getPage(),params.getRows());
        List<MarketLotteryStaticsDto> list = couponInfoMapper.selectLotteryDetail(params);
        PageInfo info = new PageInfo(list);
        return new PagesDto<>(info.getList(), info.getTotal(), params.getPage(),params.getRows());
    }

    private List<MarketStaticsDto> setStatisticsData(List<MarketStaticsDto> dtos, String yesterdayStr, String todayStr) {
        if (CollectionUtils.isEmpty(dtos)) {
            dtos = new ArrayList<>();
            MarketStaticsDto yesterdayStaticsDto = new MarketStaticsDto();
            yesterdayStaticsDto.setDate(yesterdayStr);
            dtos.add(yesterdayStaticsDto);
            MarketStaticsDto todayStaticsDto = new MarketStaticsDto();
            todayStaticsDto.setDate(todayStr);
            dtos.add(todayStaticsDto);
        } else if (dtos.size() == 1) {
            MarketStaticsDto staticsDto = new MarketStaticsDto();
            if (dtos.get(0).getDate().equalsIgnoreCase(todayStr)) {
                staticsDto.setDate(yesterdayStr);
                dtos.add(0,staticsDto);
            } else {
                staticsDto.setDate(todayStr);
                dtos.add(staticsDto);
            }
        }
        return dtos;
    }
}
