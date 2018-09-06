package com.idream.job.impl;

import com.idream.feign.ActivityService;
import com.idream.job.BaseJob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

@DisallowConcurrentExecution
public class CreateTodayTaskJob implements BaseJob {
    @Autowired
    private ActivityService activityService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        activityService.createNextDayTask();
    }
}
