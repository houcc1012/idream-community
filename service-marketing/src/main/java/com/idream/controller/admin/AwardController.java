package com.idream.controller.admin;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.marketing.AdminAwardPoolDto;
import com.idream.commons.lib.dto.marketing.AwardPoolParams;
import com.idream.commons.lib.dto.marketing.MyAwardRecordDto;
import com.idream.commons.lib.dto.marketing.MyCouponInfoParam;
import com.idream.service.IntegrationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotNull;

/**
 * Description :兑奖相关接口
 * Created by 肖刚 on 2018/4/12.
 */
@RestController
@RequestMapping(value = "admin/")
@Api(tags = "兑奖相关接口(管理端)", description = "AwardController")
public class AwardController {

    @Autowired
    private com.idream.service.AwardService awardService;
    @Autowired
    private IntegrationService integrationService;

    @RequestMapping(value = "/saveAwardBean", method = RequestMethod.POST)
    @ApiOperation(value = "新增兑奖奖品", notes = "新增兑奖奖品", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto saveAwardBean(@RequestBody JSONPublicParam<AwardPoolParams> param) {
        param.getRequestParam().setUserId(param.getAuthUserInfo().getUserId());
        awardService.saveAwardPool(param.getRequestParam());
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "保存成功", null);
    }

    @RequestMapping(value = "/updateAwardBean", method = RequestMethod.PUT)
    @ApiOperation(value = "修改兑奖奖品", notes = "修改兑奖奖品", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto updateAwardBean(@RequestBody JSONPublicParam<AwardPoolParams> param) {
        awardService.updateAwardBean(param.getRequestParam(), param.getAuthUserInfo().getUserId());
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "修改成功", null);
    }

    @RequestMapping(value = "/deleteAwardById", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除奖品", notes = "删除奖品", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto deleteAwardById(@NotNull @ApiParam(value = "奖品ID", required = true) @RequestParam(value = "id") int id) {
        awardService.deleteAwardById(id);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "删除成功", null);
    }

    @RequestMapping(value = "/updateAwardStatus", method = RequestMethod.PUT)
    @ApiOperation(value = "上/下架奖品", notes = "上/下架奖品(\"0000\"成功,\"9999\"失败,)", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto updateStatus(@NotNull @ApiParam(value = "奖品ID", required = true) @RequestParam(value = "id") Integer id,
                                      @NotNull @ApiParam(value = "奖品状态(1为上2为下)", required = true) @RequestParam(value = "status") Integer status) {
        awardService.updateStatus(id, status);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "成功", null);
    }

    @RequestMapping(value = "/updateAwardOutDateStatus", method = RequestMethod.PUT)
    @ApiOperation(value = "定时下架奖品", notes = "定时下架奖品", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto updateOutDateStatus() {
        awardService.updateOutDateStatus();
        return JSONPublicDto.returnSuccessData("修改状态成功");
    }

    @RequestMapping(value = "/updateAwardPrizeStatus", method = RequestMethod.PUT)
    @ApiOperation(value = "兑奖", notes = "奖券兑换", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto updatePrizeStatus(@NotNull @ApiParam(value = "券码", required = true) @RequestParam(value = "code") String code,
                                           @ApiIgnore @RequestParam int authUserId) {
        integrationService.updatePrizeStatus(code, authUserId);
        return JSONPublicDto.returnSuccessData("兑换成功");
    }

    @RequestMapping(value = "/selectAwardList", method = RequestMethod.GET)
    @ApiOperation(value = "查询兑奖奖池", notes = "查询兑奖奖池", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public PagesDto<AdminAwardPoolDto> selectAwardList(@ApiIgnore @RequestParam int authUserId,
                                                       MyCouponInfoParam myCouponInfoParam) {
        myCouponInfoParam.setAuthUserId(authUserId);
        return awardService.selectAwardList(myCouponInfoParam);
    }

    @RequestMapping(value = "/selectAwardById", method = RequestMethod.GET)
    @ApiOperation(value = "查询兑奖明细", notes = "查询兑奖明细", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<AdminAwardPoolDto> selectAwardById(@NotNull @ApiParam(value = "奖品ID", required = true) @RequestParam(value = "id") int id) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", awardService.selectAwardById(id));
    }

    @RequestMapping(value = "/selectMyRecord", method = RequestMethod.GET)
    @ApiOperation(value = "查询我的抽奖/我的兑奖列表", notes = "查询我的抽奖/我的兑奖列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public PagesDto<MyAwardRecordDto> selectMyRecord(@ApiIgnore @RequestParam int authUserId,
                                                     @NotNull @ApiParam(value = "类型(1为抽奖，2为兑奖)", required = true) @RequestParam(value = "type") Integer type,
                                                     @ApiParam(value = "券码") @RequestParam(value = "couponCode", required = false) String couponCode,
                                                     @NotNull @ApiParam(value = "第几页", required = true) @RequestParam(value = "page") int page,
                                                     @NotNull @ApiParam(value = "一页多少行", required = true) @RequestParam(value = "rows") int rows) {
        return awardService.selectMyRecord(authUserId, type, couponCode, page, rows);
    }

    @RequestMapping(value = "/updateAwardPrizeStatusByCode", method = RequestMethod.PUT)
    @ApiOperation(value = "根据券码兑奖", notes = "根据券码兑奖(\"0000\"成功, \"3002\"券码过期，\"3003\"已兑换过，\"3004\"不适用于此地区)", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto updateAwardPrizeStatusByCode(@NotNull @ApiParam(value = "券码", required = true) @RequestParam(value = "code") String code,
                                                      @NotNull @ApiParam(value = "类型(1为抽奖，2为兑奖)", required = true) @RequestParam(value = "type") Integer type,
                                                      @ApiIgnore @RequestParam int authUserId) {
        return integrationService.updatePrizeStatusByCode(code, type, authUserId);
    }

}
