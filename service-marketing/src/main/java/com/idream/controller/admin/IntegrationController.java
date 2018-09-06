package com.idream.controller.admin;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.marketing.*;
import com.idream.commons.lib.model.InformationRule;
import com.idream.service.IntegrationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Description :抽奖相关接口
 * Created by 肖刚 on 2018/4/11.
 */
@RestController
@RequestMapping(value = "admin")
@Api(tags = "抽奖相关接口(管理端)", description = "IntegrationController")
public class IntegrationController {

    @Autowired
    private IntegrationService integrationService;

    @RequestMapping(value = "/saveIntegrationBean", method = RequestMethod.POST)
    @ApiOperation(value = "新增抽奖奖品", notes = "新增抽奖奖品", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto saveIntegrationBean(@RequestBody JSONPublicParam<IntegrationPoolParams> param) {
        param.getRequestParam().setUserId(param.getAuthUserInfo().getUserId());
        integrationService.saveIntegrationPool(param.getRequestParam());
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "保存成功", null);
    }

    @RequestMapping(value = "/updateIntegrationBean", method = RequestMethod.PUT)
    @ApiOperation(value = "修改抽奖奖品", notes = "修改抽奖奖品", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto updateIntegrationBean(@RequestBody JSONPublicParam<IntegrationPoolParams> param) {
        param.getRequestParam().setUserId(param.getAuthUserInfo().getUserId());
        integrationService.updateIntegrationPool(param.getRequestParam());
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "修改成功", null);
    }

    @RequestMapping(value = "/updateIntegrationBean/byBookId", method = RequestMethod.PUT)
    @ApiOperation(value = "后台管理系统上/修改抽奖奖品", notes = "后台管理系统上/修改抽奖奖品", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto updateIntegrationBeanByBookId(@RequestBody JSONPublicParam<IntegrationPoolParams> param) {
        param.getRequestParam().setUserId(param.getAuthUserInfo().getUserId());
        integrationService.updateIntegrationPoolByBookId(param.getRequestParam());
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "修改成功", null);
    }

    @RequestMapping(value = "/deleteIntegrationById", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除奖品", notes = "删除奖品", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto deleteIntegrationById(@NotNull @ApiParam(value = "奖品ID", required = true) @RequestParam(value = "id") int id) {
        integrationService.deleteIntegrationById(id);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "删除成功", null);
    }

    @RequestMapping(value = "/updateIntegrationStatus", method = RequestMethod.PUT)
    @ApiOperation(value = "上/下架奖品", notes = "上/下架奖品(\"0000\"成功,\"9999\"失败,)", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto updateStatus(@ApiIgnore @RequestParam int authUserId,
                                      @NotNull @ApiParam(value = "奖品id", required = true) @RequestParam(value = "id") Integer id,
                                      @NotNull @ApiParam(value = "奖品状态（1为上2为下）", required = true) @RequestParam(value = "status") Integer status) {
        integrationService.updateStatus(authUserId, id, status);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "成功", null);
    }

    @RequestMapping(value = "/updateIntegrationStatus/byBookId", method = RequestMethod.POST)
    @ApiOperation(value = "后台管理系统上/下架书屋奖品", notes = "后台管理系统上/下架书屋奖品", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto updateStatusByBookId(@ApiIgnore @RequestParam int authUserId,
                                      @NotNull @ApiParam(value = "奖品id", required = true) @RequestParam(value = "id") Integer id,
                                      @NotNull @ApiParam(value = "奖品状态（1为上2为下）", required = true) @RequestParam(value = "status") Byte status,
                                      @NotNull @ApiParam(value = "书屋id", required = true) @RequestParam Integer bookId) {
        integrationService.updateStatusByBookId(authUserId, id, status, bookId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "上架成功", null);
    }


    @RequestMapping(value = "/updateIntegrationOutDateStatus", method = RequestMethod.PUT)
    @ApiOperation(value = "定时下架奖品", notes = "定时下架奖品", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto updateIntegrationOutDateStatus() {
        integrationService.updateOutDateStatus();
        return JSONPublicDto.returnSuccessData("修改状态成功");
    }

    @RequestMapping(value = "/updateIntegrationPrizeStatus", method = RequestMethod.PUT)
    @ApiOperation(value = "兑奖", notes = "兑奖/结果(\"0000\"成功, \"3002\"券码过期，\"3003\"已兑换过，\"3004\"不适用于此地区)", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto updatePrizeStatus(@NotNull @ApiParam(value = "券码", required = true) @RequestParam(value = "code") String code,
                                           @ApiIgnore @RequestParam int authUserId) {
        integrationService.updatePrizeStatus(code, authUserId);
        return JSONPublicDto.returnSuccessData("兑换成功!");
    }

    @RequestMapping(value = "/selectPrizeStatus", method = RequestMethod.GET)
    @ApiOperation(value = "券码状态", notes = "券码状态(\"3006\"未兑换, \"3002\"券码过期，\"3003\"已兑换)", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto selectPrizeStatus(@NotNull @ApiParam(value = "券码", required = true) @RequestParam(value = "code") String code,
                                           @ApiIgnore @RequestParam int authUserId) {
        return JSONPublicDto.returnSuccessData(integrationService.selectPrizeStatus(code), "", null);
    }

    @RequestMapping(value = "/selectAllList", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有奖券记录", notes = "查询所有奖券记录", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public PagesDto<CouponInfoDto> selectIntegrationList(CouponInfoParam couponInfoParam) {
        return integrationService.selectCouponList(couponInfoParam);
    }

    @RequestMapping(value = "/selectCouponByUserId", method = RequestMethod.GET)
    @ApiOperation(value = "用户礼品券明细", notes = "用户礼品券明细", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<CouponInfoDto>> selectCouponByUserId(@NotNull @ApiParam(value = "第几页", required = true) @RequestParam(value = "page") int page,
                                                                       @NotNull @ApiParam(value = "一页多少行", required = true) @RequestParam(value = "rows") int rows,
                                                                       @NotNull @ApiParam(value = "用户Id", required = true) @RequestParam(value = "userId") int userId) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "", integrationService.selectCouponByUserId(page, rows, userId));
    }

    @RequestMapping(value = "/selectIntegrationList", method = RequestMethod.GET)
    @ApiOperation(value = "查询抽奖奖池", notes = "查询抽奖奖池", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public PagesDto<IntegrationPoolDto> selectIntegrationPoolList(@NotNull @ApiParam(value = "第几页", required = true) @RequestParam(value = "page") int page,
                                                                  @NotNull @ApiParam(value = "一页多少行", required = true) @RequestParam(value = "rows") int rows,
                                                                  @ApiIgnore @RequestParam int authUserId,
                                                                  @NotNull @ApiParam(value = "查询类型（0所有,1通用，2区域）") @RequestParam(value = "type") Integer type) {
        return integrationService.selectIntegrationPoolList(page, rows, authUserId, type);
    }

    @RequestMapping(value = "/selectIntegrationById", method = RequestMethod.GET)
    @ApiOperation(value = "查询抽奖明细", notes = "查询抽奖明细", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<IntegrationPoolDto> selectIntegrationById(@NotNull @ApiParam(value = "奖池ID", required = true) @RequestParam(value = "id") int id) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", integrationService.selectIntegrationById(id));
    }

    @RequestMapping(value = "/selectTag", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有选填信息", notes = "查询所有选填信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<InformationRule>> selectTag() {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", integrationService.getAwardInformationRules());
    }

    @RequestMapping(value = "/updateNeedScore", method = RequestMethod.PUT)
    @ApiOperation(value = "抽奖积分设置", notes = "抽奖积分设置", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto updateNeedScore(@NotNull @ApiParam(value = "抽一次所耗积分", required = true) @RequestParam(value = "score") Integer score) {
        integrationService.updateNeedScore(score);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "设置成功", null);
    }

    @RequestMapping(value = "/selectScore", method = RequestMethod.GET)
    @ApiOperation(value = "查询抽奖积分", notes = "查询抽奖积分", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<Integer> selectScore() {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", integrationService.selectScore());
    }

    @RequestMapping(value = "/selectBookHouse", method = RequestMethod.GET)
    @ApiOperation(value = "查询管理者书屋信息", notes = "查询管理者书屋信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<BookHouseDto>> selectBookHouse(BookHouseParams param) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", integrationService.selectBookHouse(param));

    }
}
