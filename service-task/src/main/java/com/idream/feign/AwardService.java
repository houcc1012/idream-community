package com.idream.feign;

import com.idream.commons.lib.dto.JSONPublicDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author charles
 */
@FeignClient("service-marketing")
public interface AwardService {

    @RequestMapping(value = "/admin/updateAwardOutDateStatus", method = RequestMethod.PUT)
    JSONPublicDto updateAwardOutDateStatus();

    @RequestMapping(value = "/admin/updateIntegrationOutDateStatus", method = RequestMethod.PUT)
    JSONPublicDto updateIntegrationOutDateStatus();
}
