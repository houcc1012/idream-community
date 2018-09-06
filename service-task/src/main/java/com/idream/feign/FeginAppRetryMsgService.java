package com.idream.feign;

import com.idream.commons.lib.dto.JSONPublicDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/6/11 20:09
 * @Description:
 */
@FeignClient("service-activity")
public interface FeginAppRetryMsgService {

    @RequestMapping(value = "/app/retryMsg", method = RequestMethod.GET)
    JSONPublicDto retryMsg();

}
