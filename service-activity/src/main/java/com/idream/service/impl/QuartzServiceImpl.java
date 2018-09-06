package com.idream.service.impl;

import com.idream.commons.lib.dto.quartz.IntervalActivityDto;
import com.idream.commons.lib.enums.ActivityEnum;
import com.idream.commons.lib.mapper.QuartzTaskMapper;
import com.idream.commons.lib.model.ActivityTask;
import com.idream.commons.lib.model.ActivityTimeRule;
import com.idream.commons.lib.model.ActivityTimeRuleDetail;
import com.idream.commons.lib.util.DateUtils;
import com.idream.service.QuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@Service
public class QuartzServiceImpl implements QuartzService {
    @Autowired
    private QuartzTaskMapper taskMapper;

    @Override
    public List<ActivityTimeRule> listIntervalActivities() {
        return null;
    }

    @Override
    public List<ActivityTask> listTodayTask() {
        return taskMapper.listTodayTask();
    }

    @Override
    public void updateTaskStatus(Integer taskId, Integer status) {
        taskMapper.updateTaskStatus(taskId, status);
    }

    @Override
    public void addNextTask(Integer activityId) {
        ActivityTimeRule rule = taskMapper.getActivityRuleByActivityId(activityId);
        //多次任务,插入打卡任务
        if (rule.getType() == 2) {
            List<ActivityTimeRuleDetail> details = taskMapper.listNextActivityTimeDetailByActivityId(activityId);
            Optional<ActivityTimeRuleDetail> first = details.stream().sorted(comparing(ActivityTimeRuleDetail::getStartTime)).findFirst();
            if (first.isPresent()) {
                ActivityTimeRuleDetail detail = first.get();
                int count = taskMapper.findTaskExistByActivityIdAndStartTime(activityId, detail.getStartTime());
                System.out.println("是否存在" + count);
                if (count == 0) {
                    Date date = new Date();
                    ActivityTask task = new ActivityTask();
                    task.setActivityId(detail.getActivityId());
                    task.setCreateTime(date);
                    task.setEndTime(detail.getEndTime());
                    task.setStartTime(detail.getStartTime());
                    task.setStatus(1);
                    task.setUpdateTime(date);
                    Integer themeId = taskMapper.getDefaultThemeIdByActivityId(detail.getActivityId());
                    task.setThemeId(themeId);
                    taskMapper.addActivityTask(task);
                    taskMapper.updateActivityTimeDetailStatus(detail.getId(), 2);
                }
            }
        }
        //周期任务,插入规则
        if (rule.getType() == 3) {
            Date date = new Date();
            Date nextDate = DateUtils.listIntervalWeekDates(date, rule.getWeekDay(), 1).get(0);
            int count = taskMapper.findTaskExistByActivityIdAndStartTime(activityId, nextDate);
            if (count == 0) {
                ActivityTimeRuleDetail detail = taskMapper.getIntervalTimeDetail(activityId);
                Date startTime = DateUtils.mergeDateTime(nextDate, detail.getStartTime());
                Date endTime = DateUtils.mergeDateTime(nextDate, detail.getEndTime());

                ActivityTask task = new ActivityTask();
                task.setActivityId(rule.getActivityId());
                task.setCreateTime(date);
                task.setEndTime(endTime);
                task.setStartTime(startTime);
                task.setStatus(1);
                task.setUpdateTime(date);
                Integer themeId = taskMapper.getDefaultThemeIdByActivityId(rule.getActivityId());
                task.setThemeId(themeId);
                taskMapper.addActivityTask(task);
            }

        }
    }

    @Override
    public void createTodayTask() {

        List<ActivityTimeRuleDetail> times = taskMapper.listTodayTime();
        Date date = new Date();
        times.forEach(l -> {
            int count = taskMapper.findTaskExistByActivityIdAndStartTime(l.getActivityId(), l.getStartTime());
            if (count == 0) {
                ActivityTask task = new ActivityTask();
                task.setActivityId(l.getActivityId());
                task.setCreateTime(date);
                task.setUpdateTime(date);
                task.setEndTime(DateUtils.mergeDateTime(date, l.getEndTime()));
                task.setStartTime(DateUtils.mergeDateTime(date, l.getStartTime()));
                task.setStatus(1);
                Integer themeId = taskMapper.getDefaultThemeIdByActivityId(l.getActivityId());
                task.setThemeId(themeId);
                taskMapper.addActivityTask(task);
            }
            addNextTask(l.getActivityId());
        });
        List<IntervalActivityDto> intervalActivityDtos = taskMapper.listIntervalActivity();

        intervalActivityDtos.stream().filter(i -> DateUtils.checkWeekDate(i.getWeekDay())).forEach(l -> {
            int count = taskMapper.findTaskExistByActivityIdAndStartTime(l.getActivityId(), date);
            if (count == 0) {
                ActivityTask task = new ActivityTask();
                task.setActivityId(l.getActivityId());
                task.setCreateTime(date);
                task.setUpdateTime(date);
                task.setEndTime(DateUtils.mergeDateTime(date, l.getEndTime()));
                task.setStartTime(DateUtils.mergeDateTime(date, l.getStartTime()));
                task.setStatus(1);
                Integer themeId = taskMapper.getDefaultThemeIdByActivityId(l.getActivityId());
                task.setThemeId(themeId);
                taskMapper.addActivityTask(task);
            }
            addNextTask(l.getActivityId());
        });

    }
}
