package com.idream.controller;

import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.quartz.OperateQuartzJobParams;
import com.idream.commons.lib.dto.quartz.QueryQuartzJobData;
import com.idream.commons.lib.dto.quartz.QueryQuartzJobParams;
import com.idream.service.QuartzJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * hejiang
 * 定时任务Controller
 */
@RestController
@Api(tags = "定时任务相关", description = "QuartzJobController")
public class QuartzJobController {

    private static Logger logger = LoggerFactory.getLogger(QuartzJobController.class);

    @Resource
    private QuartzJobService quartzJobService;

    @RequestMapping(value = "/add/quartz/job", method = RequestMethod.POST)
    @ApiOperation(value = "新增Quartz任务", notes = "新增Quartz任务", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public void addjob(@RequestBody OperateQuartzJobParams params) {
        quartzJobService.addJob(params);
    }


    /**
     * 暂停任务
     *
     * @param jobClassName
     * @param jobGroupName
     */
    @RequestMapping(value = "/pause/quartz/job", method = RequestMethod.POST)
    @ApiOperation(value = "暂停任务", notes = "暂停任务", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public void pausejob(@ApiParam(value = "job类名", required = true) @RequestParam String jobClassName,
                         @ApiParam(value = "job分组名称") @RequestParam(required = false) String jobGroupName) {
        quartzJobService.pauseJob(jobClassName, jobGroupName);
    }

    @RequestMapping(value = "/resume/quartz/job", method = RequestMethod.POST)
    @ApiOperation(value = "恢复任务", notes = "恢复任务", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public void resumejob(@ApiParam(value = "job类名", required = true) @RequestParam String jobClassName,
                          @ApiParam(value = "job分组名称") @RequestParam(required = false) String jobGroupName) {
        quartzJobService.resumeJob(jobClassName, jobGroupName);
    }

    @RequestMapping(value = "/update/quartz/job", method = RequestMethod.POST)
    @ApiOperation(value = "修改任务", notes = "修改任务", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateJob(@RequestBody OperateQuartzJobParams params) {
        quartzJobService.updateJob(params);
    }

    @RequestMapping(value = "/delete/quartz/job", method = RequestMethod.POST)
    @ApiOperation(value = "删除任务", notes = "删除任务", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deletejob(@ApiParam(value = "job类名", required = true) @RequestParam String jobClassName,
                          @ApiParam(value = "job分组名称") @RequestParam(required = false) String jobGroupName) {
        quartzJobService.deleteJob(jobClassName, jobGroupName);
    }


    @RequestMapping(value = "/query/quartz/job", method = RequestMethod.GET)
    @ApiOperation(value = "查询任务", notes = "查询任务", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public PagesDto<QueryQuartzJobData> queryJob(QueryQuartzJobParams params) {
        return quartzJobService.queryJob(params);
    }
}
