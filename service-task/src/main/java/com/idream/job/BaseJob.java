package com.idream.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.Serializable;


/**
 * hejiang
 * job 基础类接口
 */
public interface BaseJob extends Job, Serializable {
    @Override
    void execute(JobExecutionContext context) throws JobExecutionException;
}
