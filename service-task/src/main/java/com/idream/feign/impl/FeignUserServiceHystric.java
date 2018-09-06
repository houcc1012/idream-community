package com.idream.feign.impl;

import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.user.FindUserInfoDto;
import com.idream.feign.FeignUserService;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 调用service-user服务接口发生异常时处理
 *
 * @author hejiang
 */
public class FeignUserServiceHystric implements FeignUserService {

    @Override
    public JSONPublicDto<FindUserInfoDto> getUserInfoById(@RequestParam("authUserId") Integer authUserId) {
        return null;
    }

    @Override
    public JSONPublicDto updateUserStatistics() {
        return null;
    }
}
