package com.idream.controller.feign;

import com.idream.service.UserScoreService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author hejiang
 */
@RestController
@ApiIgnore
public class FeignUserScoreController {

    @Autowired
    private UserScoreService userScoreService;

    /**
     * 添加用户积分
     *
     * @param score      积分
     * @param fromType   积分获得 类型,1签到,2打卡,3抽奖,4兑换
     * @param recordType 用户积分记录类型 1获取,2使用
     * @param authUserId 用户ID
     */
    @RequestMapping(value = "/user/score", method = RequestMethod.POST)
    @ApiOperation(value = "添加用户积分", notes = "添加用户积分", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public void addUserScore(@RequestParam Integer score,
                             @RequestParam Byte fromType,
                             @RequestParam Byte recordType,
                             @RequestParam(required = false) Integer freeLottery,
                             @RequestParam(required = false) Integer businessId,
                             @RequestParam Integer authUserId) {
        userScoreService.updateUserScoreAndRecord(score, fromType, recordType, freeLottery, businessId, authUserId);
    }


}
