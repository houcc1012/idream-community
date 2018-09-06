package com.idream.controller.app;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.service.RetryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ZhengGaosheng
 * @date: 2018/6/11 19:46
 * @description:查询处理失败信息,重新处理
 */
@RestController
@Api(tags = "消息处理失败重试(APP)", description = "AppRetryController")
@RequestMapping("/app")
public class AppRetryMsgController {

    @Autowired
    private RetryService retryService;


    @RequestMapping(value = "/retryMsg", method = RequestMethod.GET)
    @ApiOperation(value = "重发消息", notes = "重发消息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto retryMsg() {
        retryService.retryMsg();
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "发送成功", null);
    }


}

