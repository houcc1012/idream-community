package com.idream.controller.miniprogram;

import com.idream.commons.lib.model.ActivityTask;
import com.idream.rabbitmq.RabbitSendService;
import com.idream.service.QuartzService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author charles.wei
 */
@RestController
@RequestMapping("/quartz")
@ApiIgnore
//@Api(tags = "定时任务")
public class QuartzController {
    @Autowired
    private QuartzService quartzService;

    @RequestMapping(value = "todayTasks", method = RequestMethod.GET)
    @ApiOperation(value = "查询今天要开始的任务", notes = "", httpMethod = "GET")
    public List<ActivityTask> todayTasks() {
        return quartzService.listTodayTask();
    }

    @ApiOperation(value = "修改打卡状态", notes = "", httpMethod = "PUT")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "任务id", required = true, dataType = "Integre"),
            @ApiImplicitParam(name = "status", value = "任务状态", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "taskStatus", method = RequestMethod.PUT)
    public void taskStatus(Integer taskId, Integer status) {
        quartzService.updateTaskStatus(taskId, status);
    }

    @RequestMapping(value = "createNextTask", method = RequestMethod.GET)
    @ApiOperation(value = "生成下次活动任务", notes = "", httpMethod = "GET")
    @ApiImplicitParam(name = "activityId", value = "活动id", required = true, dataType = "Integer")
    public void createNextTask(Integer activityId) {
        quartzService.addNextTask(activityId);
    }

    @RequestMapping(value = "/nextDayTasks", method = RequestMethod.POST)
    @ApiOperation("生成下一天的打卡")
    public void createNextDayTask() {
        quartzService.createTodayTask();
    }

}
