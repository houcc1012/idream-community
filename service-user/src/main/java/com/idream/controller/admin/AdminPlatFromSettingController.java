package com.idream.controller.admin;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.user.*;
import com.idream.service.AdminPlatFromSettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description : 帮助中心以及其他设置接口
 * @Created by xiaogang on 2018/7/19.
 */
@RestController
@RequestMapping(value = "admin/adminPlatFromSetting")
@Api(tags = "设置功能下帮助中心及其他设置接口(后台)", description = "AdminPlatFromSettingController")
public class AdminPlatFromSettingController {

    @Autowired
    private AdminPlatFromSettingService adminPlatFromSettingService;

    @RequestMapping(value = "/saveHelpInfo", method = RequestMethod.POST)
    @ApiOperation(value = "新增帮助中心数据", notes = "新增帮助中心数据", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto saveHelpInfo(@RequestBody HelpInfoParams helpInfoParams) {
        adminPlatFromSettingService.saveHelpInfo(helpInfoParams);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "保存成功", null);
    }

    @RequestMapping(value = "/updateHelpInfo", method = RequestMethod.PUT)
    @ApiOperation(value = "修改帮助中心数据", notes = "修改帮助中心数据", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto updateHelpInfo(@RequestBody HelpInfoParams helpInfoParams) {
        adminPlatFromSettingService.updateHelpInfo(helpInfoParams);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "保存成功", null);
    }

    @RequestMapping(value = "/deleteHelpInfoById", method = RequestMethod.DELETE)
    @ApiOperation(value = "根据ID删除帮助中心数据", notes = "根据ID删除帮助中心数据", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto deleteHelpInfoById(@ApiParam(value = "ID") @RequestParam(value = "id") int id) {
        adminPlatFromSettingService.deleteHelpInfoById(id);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "删除成功", null);
    }

    @RequestMapping(value = "selectHelpInfoList", method = RequestMethod.GET)
    @ApiOperation(value = "查询帮助中心列表", notes = "查询帮助中心列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<HelpInfoDto>> selectHelpInfoList(HelpInfoSearchParams params) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", adminPlatFromSettingService.selectHelpInfoList(params));
    }

    @RequestMapping(value = "selectHelpInfoById", method = RequestMethod.GET)
    @ApiOperation(value = "根据ID查询帮助中心数据", notes = "根据ID查询帮助中心数据", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<HelpInfoDto> selectHelpInfoById(@ApiParam(value = "ID") @RequestParam(value = "id") Integer id) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", adminPlatFromSettingService.selectHelpInfoById(id));
    }

    @RequestMapping(value = "selectHelpType", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有帮助类型", notes = "查询所有帮助类型", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<HelpTypeDto>> selectHelpType() {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", adminPlatFromSettingService.selectHelpType());
    }

    @RequestMapping(value = "selectOpenCity", method = RequestMethod.GET)
    @ApiOperation(value = "查询开通城市", notes = "查询开通城市", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<AdminOpenCityDto>> selectOpenCity() {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", adminPlatFromSettingService.selectOpenCity());
    }

    @RequestMapping(value = "/saveOpenCity", method = RequestMethod.POST)
    @ApiOperation(value = "添加开通城市", notes = "保存开通城市", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto saveOpenCity(@RequestBody AdminOpenCityParams adminOpenCityParams) {
        adminPlatFromSettingService.saveOpenCity(adminOpenCityParams);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "保存成功", null);
    }

    @RequestMapping(value = "/deleteHelpInfoByCityCode", method = RequestMethod.DELETE)
    @ApiOperation(value = "根据cityCode删除开通城市", notes = "根据cityCode删除开通城市", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto deleteHelpInfoByCityCode(@ApiParam(value = "cityCode") @RequestParam(value = "cityCode") String cityCode) {
        adminPlatFromSettingService.deleteOpenCityByCityCode(cityCode);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "删除成功", null);
    }
}
