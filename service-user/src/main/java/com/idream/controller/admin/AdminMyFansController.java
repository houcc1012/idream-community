package com.idream.controller.admin;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.activity.*;
import com.idream.service.AppMyFansService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Description : 粉丝相关接口
 * @Created by xiaogang on 2018/4/28.
 */
@RestController
@Api(tags = "粉丝相关接口（后台）", description = "AdminMyFansController")
@RequestMapping(value = "/admin/myNeighbor")
public class AdminMyFansController {

    @Autowired
    private AppMyFansService appMyFansService;


    @RequestMapping(value = "/getMyFansDetailList", method = RequestMethod.GET)
    @ApiOperation(value = "管理系统粉丝明细", notes = "管理系统粉丝明细", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<AdminFansInfoDto>> getFansList(AdminSearchFansInfoParam adminSearchFansInfoParam) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "成功", appMyFansService.getFansList(adminSearchFansInfoParam));
    }
}
