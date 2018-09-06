package com.idream.service.impl;

import com.idream.commons.lib.mapper.LotteryInfoMapper;
import com.idream.service.LotteryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LotteryInfoServiceImpl implements LotteryInfoService {

    @Autowired
    private LotteryInfoMapper lotteryInfoMapper;

    @Override
    public Boolean selectLotteryInfoByAcitvityId(Integer activityId) {
        Integer count = lotteryInfoMapper.selectByActivity(activityId);
        return count > 0 ? true : false;
    }
}
