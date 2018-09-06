package com.idream.controller.admin;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.marketing.*;
import com.idream.commons.lib.model.CouponCollection;
import com.idream.service.IntegrationService;
import com.idream.service.LotteryService;
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
@Api(tags = "开奖相关接口(管理端)", description = "LotteryController")
public class LotteryController {

    @Autowired
    private LotteryService lotteryService;
    @Autowired
    private IntegrationService integrationService;

    @RequestMapping(value = "/saveLottery", method = RequestMethod.POST)
    @ApiOperation(value = "新增开奖", notes = "新增开奖", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto saveLottery(@RequestBody JSONPublicParam<AddLotteryParams> param) {
        lotteryService.saveLottery(param.getRequestParam());
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "保存成功", null);
    }

    @RequestMapping(value = "/updateLotteryBean", method = RequestMethod.PUT)
    @ApiOperation(value = "修改开奖", notes = "修改开奖", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto updateLotteryBean(@RequestBody JSONPublicParam<AddLotteryParams> param) {
        lotteryService.updateLotteryBean(param.getRequestParam());
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "成功", null);
    }

    @RequestMapping(value = "/selectLotteryPoolList", method = RequestMethod.GET)
    @ApiOperation(value = "查询开奖列表", notes = "查询开奖列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public PagesDto<LotteryInfoDto> selectLotteryPoolList(LotterySearchParams params) {
        return lotteryService.selectLotteryPoolList(params);
    }

    @RequestMapping(value = "/selectLotteryListByUser", method = RequestMethod.GET)
    @ApiOperation(value = "根据用户查询开奖列表", notes = "根据用户查询开奖列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public PagesDto<LotteryInfoDto> selectLotteryListByUser(LotterySearchParams params, @ApiIgnore @RequestParam int authUserId) {
        params.setUserId(authUserId);
        return lotteryService.selectLotteryListByUser(params);
    }

    @RequestMapping(value = "/selectLotteryById", method = RequestMethod.GET)
    @ApiOperation(value = "查询开奖明细", notes = "查询开奖明细", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<LotteryInfoDto> selectLotteryById(@NotNull @ApiParam(value = "搜索条件(开奖ID)") @RequestParam Integer id) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "成功", lotteryService.selectLotteryById(id));
    }

    @RequestMapping(value = "/selectTimeInfoByLotteryId", method = RequestMethod.GET)
    @ApiOperation(value = "根据开奖ID查询开奖时间明细", notes = "根据开奖ID查询开奖时间明细", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<LotteryTimeInfoDto> selectTimeInfoByLotteryId(@ApiParam(value = "开奖信息ID)") @RequestParam Integer lotteryId) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "成功", lotteryService.selectTimeInfoByLotteryId(lotteryId));
    }

    @RequestMapping(value = "/saveLotteryPool", method = RequestMethod.POST)
    @ApiOperation(value = "新增开奖奖品", notes = "新增开奖奖品", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto saveLotteryPool(@RequestBody JSONPublicParam<LotteryPoolParams> param) {
        lotteryService.saveLotteryPool(param.getRequestParam());
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "保存成功", null);
    }

    @RequestMapping(value = "/selectPoolByLotteryId", method = RequestMethod.GET)
    @ApiOperation(value = "根据开奖查询开奖奖池", notes = "根据开奖查询开奖奖池", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<LotteryPoolDto>> selectPoolByLotteryId(@ApiParam(value = "开奖信息ID)") @RequestParam Integer lotteryId, @ApiParam(value = "奖品ID)") @RequestParam Integer poolId) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "成功", lotteryService.selectPoolByLotteryId(lotteryId, poolId));
    }

    @RequestMapping(value = "/updatePool", method = RequestMethod.PUT)
    @ApiOperation(value = "修改开奖奖池", notes = "修改开奖奖池", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto updatePool(@RequestBody JSONPublicParam<LotteryPoolParams> param) {
        lotteryService.updatePool(param.getRequestParam());
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "成功", null);
    }

    @RequestMapping(value = "/deleteLotteryById", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除奖品", notes = "删除奖品", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto deleteLotteryById(@NotNull @ApiParam(value = "奖品ID", required = true) @RequestParam(value = "id") int id) {
        lotteryService.deleteLotteryById(id);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "删除成功", null);
    }

    @RequestMapping(value = "/selectTime", method = RequestMethod.GET)
    @ApiOperation(value = "查询开奖时间", notes = "查询开奖时间", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<LotteryTimeDto>> selectTime(@NotNull @ApiParam(value = "lotteryId") @RequestParam Integer lotteryId) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "成功", lotteryService.selectTime(lotteryId));
    }

    @RequestMapping(value = "/selectLotteryList", method = RequestMethod.GET)
    @ApiOperation(value = "查询开奖记录", notes = "查询开奖记录", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public PagesDto<LotteryWinRecordDto> selectLotteryList(LotteryWinRecordSearchParams params) {
        return lotteryService.selectLotteryList(params);
    }

    @RequestMapping(value = "/selectCommunityByRegionId", method = RequestMethod.GET)
    @ApiOperation(value = "查询省市区下面的大社区或者模糊查询社区", notes = "查询省市区下面的社区或者模糊查询社区", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<CommunityDto>> selectCommunityList(CommunityInfoParams param) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "成功", lotteryService.selectCommunityList(param));
    }

    @RequestMapping(value = "/selectActivityListByCommunity", method = RequestMethod.GET)
    @ApiOperation(value = "查询社区里的活动", notes = "查询社区里的活动", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<ActivityInfoDto>> selectActivityList(@NotNull @ApiParam(value = "社区ID") @RequestParam(value = "communityid") Integer communityid) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "成功", lotteryService.selectActivityList(communityid));
    }

    @RequestMapping(value = "/selectDetailByCouponId", method = RequestMethod.GET)
    @ApiOperation(value = "查询查证信息", notes = "查询查证信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<CouponCollection>> selectDetailByCouponId(@NotNull @ApiParam(value = "券码ID", required = true) @RequestParam(value = "couponId") Integer couponId) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "成功", lotteryService.selectDetailByCouponId(couponId));
    }

    @RequestMapping(value = "/exchangeLottery", method = RequestMethod.PUT)
    @ApiOperation(value = "兑奖", notes = "兑奖/结果(\"0000\"成功, \"3002\"券码过期，\"3003\"已兑换过，\"3004\"不适用于此地区)", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto exchange(@NotNull @ApiParam(value = "券码", required = true) @RequestParam(value = "code") String code,
                                  @ApiIgnore @RequestParam int authUserId) {
        integrationService.updatePrizeStatus(code, authUserId);
        return JSONPublicDto.returnSuccessData("兑换成功!");
    }
}
