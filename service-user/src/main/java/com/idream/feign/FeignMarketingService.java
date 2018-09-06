package com.idream.feign;

import com.idream.feign.impl.SchedualMarketingFeginHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author hejiang
 */
@FeignClient(value = "service-marketing", fallback = SchedualMarketingFeginHystric.class)
public interface FeignMarketingService {

    @RequestMapping(value = "/user/score", method = RequestMethod.POST)
    void addUserScore(@RequestParam("score") int score,
                      @RequestParam("fromType") byte fromType,
                      @RequestParam("recordType") int recordType,
                      @RequestParam("authUserId") int authUserId);

    @RequestMapping(value = "/user/score", method = RequestMethod.POST)
    void addUserScore(@RequestParam("score") int score,
                      @RequestParam("fromType") byte fromType,
                      @RequestParam("recordType") int recordType,
                      @RequestParam("freeLottery") int freeLottery,
                      @RequestParam("authUserId") int authUserId);
}
