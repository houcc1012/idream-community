package com.idream.controller.app;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.basic.AppVersionDto;
import com.idream.commons.mvc.base.BaseController;
import com.idream.service.AppBasicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @author hejiang
 */
@RestController
@Api(tags = "基础信息服务接口(APP)", description = "AppBasicController")
@RequestMapping("/app/basic")
public class AppBasicController extends BaseController {

    @Resource
    private AppBasicService appBasicService;

    @RequestMapping(value = "/version", method = RequestMethod.GET)
    @ApiOperation(value = "获取app版本号", notes = "获取app版本号", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<AppVersionDto> appUserRegister() {
        AppVersionDto data = appBasicService.getAppVersion(getDeviceType(), getAppVersion());
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "", data);
    }

}
