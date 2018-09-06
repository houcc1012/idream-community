package com.idream.service;


public interface LotteryInfoService {

    /**
     * @Author: hejiang
     * @Description: 根据活动ID查询开奖活动Id
     * @Date: 23:13 2018/4/20
     */
    Boolean selectLotteryInfoByAcitvityId(Integer activityId);
}
