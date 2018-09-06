package com.idream.job.impl;

import com.idream.feign.AwardService;
import com.idream.job.BaseJob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@DisallowConcurrentExecution
public class UpdatePoolStatusJob implements BaseJob {
    private static final Logger logger = LoggerFactory.getLogger(UpdatePoolStatusJob.class);
    @Autowired
    private AwardService awardService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("进入兑奖奖池状态修改" + LocalDateTime.now());
        awardService.updateAwardOutDateStatus();
    }


}
