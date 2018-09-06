package com.idream.job.impl;

import com.idream.feign.FeginAppRetryMsgService;
import com.idream.job.BaseJob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: ZhengGaosheng
 * @date: 2018/6/11 20:24
 * @description:
 */
@DisallowConcurrentExecution
public class RetryMsgServiceTaskJob implements BaseJob {

    @Autowired
    private FeginAppRetryMsgService feginAppRetryMsgService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        feginAppRetryMsgService.retryMsg();
    }

}

