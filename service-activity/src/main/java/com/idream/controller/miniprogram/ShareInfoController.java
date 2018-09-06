/**
 *
 */
package com.idream.controller.miniprogram;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.activity.ActivityInfoDto;
import com.idream.commons.lib.dto.activity.PromotionRecordParams;
import com.idream.commons.lib.dto.activity.ShareInfoDto;
import com.idream.commons.lib.dto.activity.ShareRecordParams;
import com.idream.service.ActivityInfoService;
import com.idream.service.ActivityInvitationRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotNull;

/**
 * @author xiaogang
 */
@RestController
@RequestMapping(value = "shareInfoControl")
@Api(tags = "分享邀请及活动信息相关接口", description = "分享邀请及活动信息相关接口")
public class ShareInfoController {
    @Autowired
    private ActivityInfoService activityInfoService;
    @Autowired
    private ActivityInvitationRecordService activityInvitationRecordService;

    @RequestMapping(value = "/getActivityById", method = RequestMethod.GET)
    @ApiOperation(value = "查询活动信息", notes = "查询活动信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<ActivityInfoDto> getEntityById(@ApiIgnore @RequestParam int authUserId, @NotNull @ApiParam(value = "活动ID", required = true) @RequestParam(value = "id") Integer id) {
        ActivityInfoDto aid = activityInfoService.getEntityById(id);
        if (aid == null) {
            return JSONPublicDto.returnSuccessData(RetCodeConstants.UNKOWN_ERROR, "查询活动信息失败！", null);
        }
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询活动信息成功！", aid);
    }

    @RequestMapping(value = "/addInvitationRecord", method = RequestMethod.POST)
    @ApiOperation(value = "保存活动邀请记录", notes = "保存活动邀请记录", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto addInvitationRecord(@RequestBody JSONPublicParam<ShareInfoDto> param) throws Exception {
        activityInvitationRecordService.addInvitationRecord(param);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "保存活动邀请记录成功", null);
    }

    @RequestMapping(value = "/add/promotionRecord", method = RequestMethod.POST)
    @ApiOperation(value = "小程序推广扫二维码添加记录", notes = "小程序推广扫二维码添加记录", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto addPromotionRecord(@RequestBody JSONPublicParam<PromotionRecordParams> param) {
        int i = activityInfoService.addPromotionTeam(param);
        if (i == 0) {
            return JSONPublicDto.returnSuccessData(RetCodeConstants.UNKOWN_ERROR, "小程序推广扫二维码添加记录失败", null);
        } else {
            return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "小程序推广扫二维码添加记录成功", null);
        }
    }

    @RequestMapping(value = "/shareRecord",method = RequestMethod.POST)
    @ApiOperation("分享加积分")
    public JSONPublicDto<Integer> shareRecord(@RequestBody JSONPublicParam<ShareRecordParams> param) {
        return JSONPublicDto.returnSuccessData(activityInvitationRecordService.saveUserScore(param.getRequestParam().getActivityId(), param.getAuthUserInfo().getUserId()));
    }

}
