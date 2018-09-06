package com.idream.feign;

import com.idream.feign.impl.FeginScoreServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description :
 * @Created by xiaogang on 2018/7/23.
 */
@FeignClient(value = "service-marketing", fallback = FeginScoreServiceImpl.class)
public interface FeginScoreService {

    @RequestMapping(value = "/user/score", method = RequestMethod.POST)
    void addUserScore(@RequestParam(value = "score") Integer score,
                      @RequestParam(value = "fromType") Byte fromType,
                      @RequestParam(value = "recordType") Byte recordType,
                      @RequestParam(value = "freeLottery", required = false) Integer freeLottery,
                      @RequestParam(value = "businessId", required = false) Integer businessId,
                      @RequestParam(value = "authUserId") Integer authUserId);
}
