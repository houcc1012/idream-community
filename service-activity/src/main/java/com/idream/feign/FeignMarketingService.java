package com.idream.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-marketing")
public interface FeignMarketingService {
    @RequestMapping(value = "/user/score", method = RequestMethod.POST)
    void addUserScore(@RequestParam("score") int score,
                      @RequestParam("fromType") byte fromType,
                      @RequestParam("recordType") byte recordType,
                      @RequestParam("businessId") int businessId,
                      @RequestParam("authUserId") int authUserId);
}
