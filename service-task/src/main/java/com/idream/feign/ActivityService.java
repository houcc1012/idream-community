package com.idream.feign;

import com.idream.commons.lib.model.ActivityTask;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * @author charles.wei
 */
@FeignClient("service-activity")
public interface ActivityService {
    /**
     * 查询所当天要发生的任务
     *
     * @return List<ActivityTask>
     */
    @RequestMapping(value = "/quartz/todayTasks", method = RequestMethod.GET)
    List<ActivityTask> listTodayTask();

    /**
     * 修改打卡的状态
     *
     * @param taskId
     * @param status void
     */
    @RequestMapping(value = "/quartz/taskStatus", method = RequestMethod.PUT)
    void updateTaskStatus(@RequestParam("taskId") Integer taskId, @RequestParam("status") Integer status);

    /**
     * 生成下一次打卡
     *
     * @param activityId void
     */
    @RequestMapping(value = "/quartz/createNextTask", method = RequestMethod.GET)
    void createNextTask(@RequestParam("activityId") Integer activityId);

    /**
     * 生成下一次的打卡
     *
     * @return List<ActivityTask>
     */
    @RequestMapping(value = "/quartz/nextDayTasks", method = RequestMethod.POST)
    List<ActivityTask> createNextDayTask();

}
