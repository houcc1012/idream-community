package com.idream.controller.app;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.marketing.*;
import com.idream.commons.lib.model.AwardPool;
import com.idream.service.AwardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author hejiang
 */
@RestController
@RequestMapping(value = "/app/award")
@Api(tags = "兑奖相关接口（APP）", description = "AppAwardController")
public class AppAwardController {

    @Autowired
    private AwardService awardService;

    @RequestMapping(value = "/convert/coupon", method = RequestMethod.POST)
    @ApiOperation(value = "奖券兑换", notes = "奖券兑换", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<Integer> convertCoupon(@RequestBody JSONPublicParam<ConvertCouponParams> params) {
        Integer couponId = awardService.doConvertCoupon(params);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "兑换成功", couponId);
    }

    @RequestMapping(value = "/selectAwardList", method = RequestMethod.GET)
    @ApiOperation(value = "查询兑奖奖券", notes = "查询兑奖奖券", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<AwardPoolDto>> selectAwardList(DrawAwardPoolParams params) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", awardService.selectAwardList(params));
    }

    @RequestMapping(value = "/selectAwardById", method = RequestMethod.GET)
    @ApiOperation(value = "查询兑奖明细", notes = "查询兑奖明细", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<AdminAwardPoolDto> selectAwardById(@NotNull @ApiParam(value = "", required = true) @RequestParam(value = "id") int id, @ApiIgnore @RequestParam int authUserId) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", awardService.selectAwardByIdAndUserId(id, authUserId));
    }

    @RequestMapping(value = "/selectRecommendedAwardList", method = RequestMethod.GET)
    @ApiOperation(value = "查询推荐兑奖奖券", notes = "查询推荐兑奖奖券", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<AwardPoolDto>> selectRecommendedAwardList(DrawAwardPoolParams params) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", awardService.selectRecommendedAwardList(params));
    }

    @RequestMapping(value = "/selectNearAwardList", method = RequestMethod.GET)
    @ApiOperation(value = "查询离我最近的兑奖奖券", notes = "查询离我最近的兑奖奖券", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<AwardPoolDto>> selectNearAwardList(DrawAwardPoolParams params) {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", awardService.selectNearAwardList(params));
    }

}
