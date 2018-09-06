package com.idream.controller.miniprogram;

import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.marketing.CouponInfoDto;
import com.idream.service.IntegrationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description : 小程序卡券相关接口
 * @Created by houcc on 2018/8/29.
 */
@RestController
@RequestMapping(value = "/miniProgram")
@Api(tags = "奖券相关接口（小程序）", description = "MiniCouponController")
public class MiniCouponController {

    @Autowired
    private IntegrationService integrationService;

    @RequestMapping(value = "/deleteCouponById", method = RequestMethod.DELETE)
    @ApiOperation(value = "用户删除礼品券", notes = "用户删除礼品券", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<CouponInfoDto>> selectCouponByUserId(@ApiParam(value = "券码id", required = true) @RequestParam(value = "couponId") Integer couponId) {
        integrationService.deleteCouponById(couponId);
        return JSONPublicDto.returnSuccessData("删除成功");
    }
}
