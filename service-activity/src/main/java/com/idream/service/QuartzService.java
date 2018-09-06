package com.idream.service;

import java.util.List;

import com.idream.commons.lib.model.ActivityTask;
import com.idream.commons.lib.model.ActivityTimeRule;


public interface QuartzService {
    /**
     * @return List<ActivityTimeRule>
     */
    List<ActivityTimeRule> listIntervalActivities();

    /**
     * @return List<ActivityTask>
     */
    List<ActivityTask> listTodayTask();

    /**
     * @param taskId
     * @param status void
     */
    void updateTaskStatus(Integer taskId, Integer status);

    /**
     * @param activityId void
     */
    void addNextTask(Integer activityId);

    /**
     * 生成今天的打卡任务
     */
    void createTodayTask();
}
