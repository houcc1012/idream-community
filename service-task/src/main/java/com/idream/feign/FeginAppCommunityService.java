package com.idream.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: jeffery
 * @Date: 2018/7/6 11:24
 */
@FeignClient("service-activity")
public interface FeginAppCommunityService {

    @RequestMapping(value = "/app/community/hot/region", method = RequestMethod.PUT)
    void updateHotRegion();
}
