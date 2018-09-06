package com.idream.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.wangyi.CreateGroupRequestParam;
import com.idream.commons.lib.dto.wangyi.CreateGroupResponseDto;
import com.idream.commons.lib.dto.wangyi.JoinGroupRequestParam;
import com.idream.commons.lib.dto.wangyi.LeaveGroupRequestParam;
import com.idream.commons.lib.dto.wangyi.UpdateGroupRequestParam;
import com.idream.feign.impl.FeignServiceUserImpl;

@FeignClient(value = "service-user", fallback = FeignServiceUserImpl.class)
public interface FeignServiceUser {

    @RequestMapping(value = "/app/userimgroup/createGroup", method = RequestMethod.POST)
    public JSONPublicDto<CreateGroupResponseDto> createGroup(@RequestBody CreateGroupRequestParam param);

    @RequestMapping(value = "/app/userimgroup/joinGroup", method = RequestMethod.POST)
    public JSONPublicDto joinGroup(@RequestBody JoinGroupRequestParam param);

    @RequestMapping(value = "/app/userimgroup/leaveGroup", method = RequestMethod.POST)
    public JSONPublicDto leaveGroup(@RequestBody LeaveGroupRequestParam param);

    @RequestMapping(value = "/app/userimgroup/updateGroupInformation", method = RequestMethod.POST)
    JSONPublicDto updateGroupInformation(@RequestBody JSONPublicParam<UpdateGroupRequestParam> param);
}
