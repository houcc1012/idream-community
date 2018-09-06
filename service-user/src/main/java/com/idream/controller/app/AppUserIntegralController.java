package com.idream.controller.app;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.integration.FindUserIntergrationInfoDto;
import com.idream.commons.lib.dto.integration.OldIntegrationConfigDto;
import com.idream.commons.lib.model.IntegrationConfig;
import com.idream.service.UserIntegralService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author hejiang
 */
@RestController
@Api(description = "AppUserIntegralController", tags = "用户签到积分相关(APP)")
@RequestMapping("/app/user")
public class AppUserIntegralController {

    @Autowired
    private UserIntegralService userIntegralService;

    @RequestMapping(value = "/sign", method = RequestMethod.GET)
    @ApiOperation(value = "app用户签到", notes = "app用户签到", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto addSignCofig(@ApiIgnore @RequestParam int authUserId) {
        userIntegralService.doSignGetIntegration(authUserId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "签到成功", null);
    }

    @RequestMapping(value = "/sign/integration/info", method = RequestMethod.GET)
    @ApiOperation(value = "用户签到积分详情", notes = "用户签到积分详情", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<FindUserIntergrationInfoDto> getUserIntegrationInfo(@ApiIgnore @RequestParam int authUserId) {
        return userIntegralService.getUserIntegrationInfo(authUserId);
    }

    @RequestMapping(value = "/sign/integration/config", method = RequestMethod.GET)
    @ApiOperation(value = "查询签到积分配置", notes = "查询签到积分配置", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<OldIntegrationConfigDto> getSignCofig() {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "", userIntegralService.getSignCofig());
    }

}
