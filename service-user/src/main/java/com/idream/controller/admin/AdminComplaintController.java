package com.idream.controller.admin;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.base.RetMessageConstans;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.adminuser.*;
import com.idream.commons.lib.enums.UserEnum;
import com.idream.service.UserComplaintService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/complaint")
@Api(tags = "后台处理用户举报")
public class AdminComplaintController {

    @Autowired
    private UserComplaintService userComplaintService;

    @ApiOperation(value = "查询举报的信息")
    @RequestMapping(method = RequestMethod.GET)
    public JSONPublicDto<PagesDto<AdminComplaintDto>> getComplaint(AdminComplaintParams query) {
        PagesDto<AdminComplaintDto> page = userComplaintService.getUserComplaintByQuery(query);
        return JSONPublicDto.returnSuccessData(page);
    }

    @ApiOperation(value = "举报失败")
    @RequestMapping(value = "/handle/fail", method = RequestMethod.PUT)
    public JSONPublicDto fail(@RequestBody JSONPublicParam<AdminComplaintStatusParams> param) {
        userComplaintService.updateComplaintStatus(param.getRequestParam().getComplaintId(), UserEnum.UserComplaintStatus.REJECT.getCode());
        return JSONPublicDto.returnSuccessData("举报失败");
    }

    @ApiOperation(value = "举报成功")
    @RequestMapping(value = "/handle/success", method = RequestMethod.PUT)
    public JSONPublicDto success(@RequestBody JSONPublicParam<AdminComplaintStatusParams> param) {
        userComplaintService.updateComplaintStatus(param.getRequestParam().getComplaintId(), UserEnum.UserComplaintStatus.PASS.getCode());
        return JSONPublicDto.returnSuccessData("举报成功");
    }

    @ApiOperation(value = "禁言处理")
    @RequestMapping(value = "/handle/ban", method = RequestMethod.POST)
    public JSONPublicDto<Integer> handleBan(@RequestBody JSONPublicParam<AdminComplaintHandleBanParams> param) {
        Integer handleId = userComplaintService.addHandleBan(param);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "禁言成功", handleId);
    }

    @ApiOperation(value = "处理的状态")
    @RequestMapping(value = "/handle", method = RequestMethod.GET)
    public JSONPublicDto<AdminComplaintHandleStatusDto> handleStatus(Integer handleId) {
        AdminComplaintHandleStatusDto handle = userComplaintService.getHandleStatus(handleId);
        return JSONPublicDto.returnSuccessData(handle);
    }

    @ApiOperation(value = "取消处理")
    @RequestMapping(value = "/handle", method = RequestMethod.PUT)
    public JSONPublicDto cancelHandle(@RequestBody JSONPublicParam<AdminComplaintHandleCancelParams> param) {
        userComplaintService.updateHandleStatus(param.getRequestParam().getHandleId(), UserEnum.UserComplaintHandleStatus.CANCEL.getCode());
        return JSONPublicDto.returnSuccessData("取消成功");
    }
}
