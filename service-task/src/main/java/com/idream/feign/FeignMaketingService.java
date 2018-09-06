package com.idream.feign;

import com.idream.feign.impl.FeignMarketingServiceHystric;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author hejiang
 */
@FeignClient(value = "service-marketing", fallback = FeignMarketingServiceHystric.class)
public interface FeignMaketingService {


}
