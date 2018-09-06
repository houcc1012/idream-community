package com.idream.job.impl;

import com.idream.feign.FeginAppCommunityService;
import com.idream.job.BaseJob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

/**
 * @Author: jeffery
 * @Date: 2018/7/6 11:27
 */
@DisallowConcurrentExecution
public class AppCommunityServiceTaskJob implements BaseJob {

    private static final Logger logger = LoggerFactory.getLogger(AppCommunityServiceTaskJob.class);

    @Autowired
    private FeginAppCommunityService feginAppCommunityService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("进入热门社区表数据更新" + LocalDateTime.now());
        feginAppCommunityService.updateHotRegion();
    }
}
