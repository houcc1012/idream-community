package com.idream.controller.admin;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.user.AdminUserStatisticsDetailDto;
import com.idream.commons.lib.dto.user.AdminUserStatisticsDetailParams;
import com.idream.commons.lib.dto.user.AdminUserStatisticsDto;
import com.idream.commons.lib.dto.user.AdminUserStatisticsParams;
import com.idream.commons.lib.enums.SystemEnum;
import com.idream.commons.lib.mapper.BookExtensionMapper;
import com.idream.service.AdminUserStatisticsService;
import com.idream.service.QrCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: houcc
 * @Date: 2018/6/6
 */
@RestController
@Api(description = "AdminUserStatisticsController", tags = "后台用户统计（管理端）")
@RequestMapping("/admin/userStatistics")
public class AdminUserStatisticsController {
    @Autowired
    private AdminUserStatisticsService adminUserStatisticsService;
    @Autowired
    private QrCodeService qrCodeService;

    @RequestMapping(value = "/getRegisterByDevice", method = RequestMethod.GET)
    @ApiOperation(value = "后台注册用户查询", notes = "后台注册用户查询", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<AdminUserStatisticsDto>> getRegisterUserList(AdminUserStatisticsParams params) {
        List<AdminUserStatisticsDto> registerUsers = adminUserStatisticsService.getRegisterUserByDevice(params);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", registerUsers);
    }

    @RequestMapping(value = "/getYesterdayAndToday", method = RequestMethod.GET)
    @ApiOperation(value = "查看今天昨天注册用户", notes = "查看今天昨天注册用户", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<List<AdminUserStatisticsDto>>> getYesterdayAndToday() {
        List<List<AdminUserStatisticsDto>> registerUsers = adminUserStatisticsService.getYesterdayAndToday();
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", registerUsers);
    }

    @RequestMapping(value = "/getRegisterDetail", method = RequestMethod.GET)
    @ApiOperation(value = "查看用户注册明细", notes = "查看用户注册明细", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<AdminUserStatisticsDetailDto>> getRegisterDetail(AdminUserStatisticsDetailParams params) {
        PagesDto<AdminUserStatisticsDetailDto> registerUsers = adminUserStatisticsService.getRegisterUserListByDevice(params);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", registerUsers);
    }

    @RequestMapping(value = "/qrcode/book", method = RequestMethod.GET)
    @ApiOperation("书屋小程序二维码")
    public JSONPublicDto<String> bookQrCode(Integer bookId) throws IOException {
        String qrcode = qrCodeService.getQrCode(bookId, SystemEnum.MiniQrCodeType.BOOK);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "成功", qrcode);
    }

    @RequestMapping(value = "/qrcode/team", method = RequestMethod.GET)
    @ApiOperation("地推小程序二维码")
    public JSONPublicDto<String> teamQrCode(Integer teamId) throws IOException {
        String qrcode = qrCodeService.getQrCode(teamId, SystemEnum.MiniQrCodeType.TEAM);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "成功", qrcode);
    }

}
