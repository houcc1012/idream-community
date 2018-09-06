package com.idream.controller.admin;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.integration.FindUserSignRankingListDto;
import com.idream.commons.lib.dto.integration.OldIntegrationConfigDto;
import com.idream.commons.lib.model.IntegrationConfig;
import com.idream.service.UserIntegralService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@Api(description = "AdminSignIntegralController", tags = "签到积分相关(管理端)")
@RequestMapping("/admin/sign")
public class AdminSignIntegralController {

    @Autowired
    private UserIntegralService signIntegralService;

    @RequestMapping(value = "/config", method = RequestMethod.PUT)
    @ApiOperation(value = "修改签到积分配置", notes = "修改签到积分配置", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto updateSignCofig(@RequestBody JSONPublicParam<OldIntegrationConfigDto> param) {
        signIntegralService.updateSignCofig(param);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "修改成功", null);
    }

    @RequestMapping(value = "/config", method = RequestMethod.GET)
    @ApiOperation(value = "查询签到积分配置", notes = "查询签到积分配置", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<OldIntegrationConfigDto> getSignCofig() {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "", signIntegralService.getSignCofig());
    }

    @RequestMapping(value = "/ranking/list", method = RequestMethod.GET)
    @ApiOperation(value = "管理端用户签到排行榜列表查询", notes = "管理端用户签到排行榜列表查询", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<FindUserSignRankingListDto>> getUserSignRankingList(
            @NotNull(message = "页码不能为空") @ApiParam("页码") @RequestParam int page,
            @NotNull(message = "行数不能为空") @ApiParam("行数") @RequestParam int rows) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "", signIntegralService.getUserSignRankingList(page, rows));
    }
}
