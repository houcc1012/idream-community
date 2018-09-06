package com.idream.feign;

import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.user.FindUserInfoDto;
import com.idream.feign.impl.FeignUserServiceHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author hejiang
 */
@FeignClient(value = "service-user", fallback = FeignUserServiceHystric.class)
public interface FeignUserService {

    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    JSONPublicDto<FindUserInfoDto> getUserInfoById(@RequestParam("authUserId") Integer authUserId);


    @RequestMapping(value = "/admin/client/user/updateUserStatistics", method = RequestMethod.PUT)
    JSONPublicDto updateUserStatistics();
}
