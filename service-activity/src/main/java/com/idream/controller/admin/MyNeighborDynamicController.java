package com.idream.controller.admin;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.activity.*;
import com.idream.commons.lib.dto.marketing.*;
import com.idream.commons.lib.dto.marketing.CommunityDto;
import com.idream.service.AppMyNeighborService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.message.Message;
import org.apache.poi.ss.formula.functions.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Description : 邻里相关接口
 * @Created by xiaogang on 2018/5/9.
 */
@RestController
@Api(tags = "后端管理系统邻里相关接口", description = "MyNeighborDynamicController")
@RequestMapping(value = "admin/myNeighborDynamic")
public class MyNeighborDynamicController {

    @Autowired
    private AppMyNeighborService appMyNeighborService;

    @RequestMapping(value = "/getMyDynamicList", method = RequestMethod.GET)
    @ApiOperation(value = "生活明细", notes = "生活明细", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<AdminMyDynamicDto>> getMyDynamicByUserId(@NotNull(message = "用户ID") @RequestParam int userId,
                                                                           @NotNull @ApiParam(value = "第几页", required = true) @RequestParam(value = "page") int page,
                                                                           @NotNull @ApiParam(value = "多少行", required = true) @RequestParam(value = "rows") int rows) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "成功", appMyNeighborService.getMyDynamicByUserId(userId, page, rows));
    }

    @RequestMapping(value = "/getAllNeighborDynamicList", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户社区/所有动态列表", notes = "查询用户社区/所有动态列表")
    public JSONPublicDto<PagesDto<AdminMyNeighborDynamicDto>> getAllNeighborDynamicList(AdminMyNeighborDynamicParam param, @ApiIgnore @RequestParam int authUserId) {
        param.setUserId(authUserId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "成功", appMyNeighborService.getAllNeighborDynamicList(param));
    }

    @RequestMapping(value = "/getThumbUpDetailList", method = RequestMethod.GET)
    @ApiOperation(value = "点赞明细", notes = "点赞明细", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<AdminThumbUpDetailDto>> getThumbUpDetailList(@NotNull(message = "动态ID") @RequestParam int id,
                                                                               @NotNull @ApiParam(value = "第几页", required = true) @RequestParam(value = "page") int page,
                                                                               @NotNull @ApiParam(value = "多少行", required = true) @RequestParam(value = "rows") int rows) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "成功", appMyNeighborService.getThumbUpDetailList(id, page, rows));
    }

    @RequestMapping(value = "/updateStatus", method = RequestMethod.PUT)
    @ApiOperation(value = "屏蔽动态", notes = "屏蔽动态", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto updateStatus(@NotNull(message = "动态ID") @RequestParam int id,
                                      @NotNull(message = "状态(2为取消屏蔽，3为屏蔽)") @RequestParam byte status) {
        appMyNeighborService.updateStatus(id, status);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "成功", null);
    }


    @RequestMapping(value = "/selectSmallCommunityByRegionId", method = RequestMethod.GET)
    @ApiOperation(value = "查询省市区下面的小区或者模糊查询小区", notes = "查询省市区下面的小区或者模糊查询小区", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<CommunityDto>> selectSmallCommunityByRegionId(CommunityInfoParams param) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "成功", appMyNeighborService.selectSmallCommunityByRegionId(param));
    }
}
