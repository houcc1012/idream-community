package com.idream.feign.impl;

import com.idream.feign.FeginScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description :
 * @Created by xiaogang on 2018/7/23.
 */
@Component
public class FeginScoreServiceImpl implements FeginScoreService {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Override
    public void addUserScore(@RequestParam Integer score, @RequestParam Byte fromType, @RequestParam Byte recordType, @RequestParam(required = false) Integer freeLottery, @RequestParam(required = false) Integer businessId, @RequestParam Integer authUserId) {
        logger.error("调用新增用户积分失败! fromType:{}, score:{}, recordType:{}, businessId:{}, authUserId:{}", fromType, score, recordType, businessId, authUserId);
    }
}
