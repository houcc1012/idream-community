package com.idream.job.impl;

import com.idream.feign.FeignUserService;
import com.idream.job.BaseJob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Date;

@DisallowConcurrentExecution
public class UpdateUserStatisticsJob implements BaseJob {
    private static final Logger logger = LoggerFactory.getLogger(UpdateUserStatisticsJob.class);
    @Autowired
    private FeignUserService feignUserService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("定时统计用户相关信息:" + LocalDateTime.now());
        feignUserService.updateUserStatistics();
    }
}
