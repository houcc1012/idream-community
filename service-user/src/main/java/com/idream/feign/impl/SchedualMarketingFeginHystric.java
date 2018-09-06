package com.idream.feign.impl;

import com.idream.feign.FeignMarketingService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author hejiang
 */
@Component
public class SchedualMarketingFeginHystric implements FeignMarketingService {
    @Override
    public void addUserScore(@RequestParam("score") int score,
                             @RequestParam("fromType") byte fromType,
                             @RequestParam("recordType") int recordType,
                             @RequestParam("authUserId") int authUserId) {

    }

    @Override
    public void addUserScore(@RequestParam("score") int score,
                             @RequestParam("fromType") byte fromType,
                             @RequestParam("recordType") int recordType,
                             @RequestParam("freeLottery") int freeLottery,
                             @RequestParam("authUserId") int authUserId) {

    }
}
