package com.idream.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.quartz.OperateQuartzJobParams;
import com.idream.commons.lib.dto.quartz.QueryQuartzJobData;
import com.idream.commons.lib.dto.quartz.QueryQuartzJobParams;
import com.idream.mapper.QuartzJobMapper;
import com.idream.service.QuartzJobService;
import com.idream.tool.QuartzTool;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author hejiang
 * @Description: quartz 任务服务相关
 * @Modified By:
 */
@Service
public class QuartzJobServiceImpl implements QuartzJobService {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private QuartzJobMapper quartzJobMapper;

    /**
     * 新增
     */
    @Override
    public void addJob(OperateQuartzJobParams params) {
        try {
            String jobClassName = params.getJobClassName();
            String jobGroupName = QuartzTool.getJobClassGroup(jobClassName, params.getJobGroupName());
            String cronExpression = params.getCronExpression();
            //构建job信息
            JobDetail jobDetail = JobBuilder.newJob(QuartzTool.getClass(jobClassName).getClass()).withIdentity(jobClassName, jobGroupName).build();
            //表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            //按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName).withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            logger.error("新增调度任务失败！params=" + JSON.toJSONString(params), e);
        }
    }

    /**
     * 暂停
     *
     * @param jobClassName job 全类名（必须要该类所在的包的全类名）
     * @param jobGroupName job 分组的名称
     */
    @Override
    public void pauseJob(String jobClassName, String jobGroupName) {
        try {
            jobGroupName = QuartzTool.getJobClassGroup(jobClassName, jobGroupName);
            scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
        } catch (Exception e) {
            logger.error("暂停调度任务失败! jobClassName=" + jobClassName + "jobGroupName=" + jobGroupName, e);
        }
    }

    /**
     * 恢复
     *
     * @param jobClassName job 全类名（必须要该类所在的包的全类名）
     * @param jobGroupName job 分组的名称
     */
    @Override
    public void resumeJob(String jobClassName, String jobGroupName) {
        try {
            jobGroupName = QuartzTool.getJobClassGroup(jobClassName, jobGroupName);
            scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
        } catch (Exception e) {
            logger.error("恢复调度任务失败! jobClassName=" + jobClassName + "jobGroupName=" + jobGroupName, e);
        }
    }

    /**
     * 修改
     */
    @Override
    public void updateJob(OperateQuartzJobParams params) {
        try {
            String jobClassName = params.getJobClassName();
            String jobGroupName = QuartzTool.getJobClassGroup(jobClassName, params.getJobGroupName());
            String cronExpression = params.getCronExpression();

            TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (Exception e) {
            logger.error("修改调度任务失败！params=" + JSON.toJSONString(params), e);
        }
    }

    /**
     * 删除
     *
     * @param jobClassName job 全类名（必须要该类所在的包的全类名）
     * @param jobGroupName job 分组的名称
     */
    @Override
    public void deleteJob(String jobClassName, String jobGroupName) {
        try {
            jobGroupName = QuartzTool.getJobClassGroup(jobClassName, jobGroupName);
            scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
            scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
            scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
        } catch (Exception e) {
            logger.error("删除调度任务失败! jobClassName=" + jobClassName + "jobGroupName=" + jobGroupName, e);
        }
    }

    /**
     * 查询定时任务列表
     *
     * @param params
     */
    @Override
    public PagesDto<QueryQuartzJobData> queryJob(QueryQuartzJobParams params) {
        //开启分页
        PageHelper.startPage(params.getPage(), params.getRows());
        List<QueryQuartzJobData> list = quartzJobMapper.selectQuartzJobList(params);
        PageInfo<QueryQuartzJobData> pageInfo = new PageInfo<QueryQuartzJobData>(list);
        return new PagesDto<QueryQuartzJobData>(list, pageInfo.getTotal(), params.getPage(), params.getRows());
    }
}
