package com.idream.job.impl;

import com.idream.job.BaseJob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hejiang
 */
@DisallowConcurrentExecution//防止任务并行执行
public class TestJob implements BaseJob {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("test job start ...");
    }
}
