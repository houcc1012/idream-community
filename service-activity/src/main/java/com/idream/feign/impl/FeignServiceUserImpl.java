package com.idream.feign.impl;

import org.springframework.stereotype.Component;

import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.wangyi.CreateGroupRequestParam;
import com.idream.commons.lib.dto.wangyi.CreateGroupResponseDto;
import com.idream.commons.lib.dto.wangyi.JoinGroupRequestParam;
import com.idream.commons.lib.dto.wangyi.LeaveGroupRequestParam;
import com.idream.commons.lib.dto.wangyi.UpdateGroupRequestParam;
import com.idream.feign.FeignServiceUser;

@Component
public class FeignServiceUserImpl implements FeignServiceUser {

    @Override
    public JSONPublicDto<CreateGroupResponseDto> createGroup(CreateGroupRequestParam param) {
        return null;
    }

    @Override
    public JSONPublicDto joinGroup(JoinGroupRequestParam param) {
        return null;
    }

    @Override
    public JSONPublicDto leaveGroup(LeaveGroupRequestParam param) {

        return null;
    }

    @Override
    public JSONPublicDto updateGroupInformation(JSONPublicParam<UpdateGroupRequestParam> param) {

        return null;
    }


}
