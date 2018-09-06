package com.idream.service;

import com.idream.commons.lib.dto.rabbitmq.IntegrationAwardAsyncInsertDto;
import com.idream.commons.lib.dto.rabbitmq.OpenLotteryAwardAsyncInsertDto;

/**
 * @Author: houcc
 * @Date: 2018/6/30
 * 异步执行任务
 */
public interface AsyncTaskService {
    /**
     * 记录积分抽奖用户中奖信息
     */
    void doRecordIntegrationDraw(int userId, int integrationPoolId);

    void doRecordIntegrationDraw(IntegrationAwardAsyncInsertDto awardSyncInsertDto);

    /**
     * 保存现场开奖用户奖券信息
     *
     * @param lotteryAwardId
     * @param userId
     * @param redisKey
     */
    void doRecordOpenLotteryAward(int lotteryAwardId, int userId, String redisKey);

    void doRecordOpenLotteryAward(OpenLotteryAwardAsyncInsertDto awardAsyncInsertDto);
}
