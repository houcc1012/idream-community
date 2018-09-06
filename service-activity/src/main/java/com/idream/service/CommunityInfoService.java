package com.idream.service;

import com.github.pagehelper.PageInfo;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.activity.*;
import com.idream.commons.lib.dto.admin.CommunityActivityParams;
import com.idream.commons.lib.dto.app.CommunityInfoResponseDto;
import com.idream.commons.lib.dto.app.CommunityListSearchRequestDto;
import com.idream.commons.lib.model.CommunityInfo;


public interface CommunityInfoService {

    //app端 搜索社区(社区列表)
    PagesDto<CommunityInfoResponseDto> selectCommunityInfoListByCommunityName(Integer userId, CommunityListSearchRequestDto communityListSearchRequestDto);

    /**
     * 获取认证的用户信息
     *
     * @param params
     */
    PagesDto<AdminCommunityAuthDto> getAuthUser(AdminCommunityAuthParams params);

    /**
     * 修改认证状态
     *
     * @param adminUserId
     * @param authId
     * @param status
     */
    void updateAuthUser(Integer adminUserId, Integer authId, Byte status);


    void updateAuthUserCommunitySuccess(Integer userId, Integer authId);

    void updateAuthUserCommunityFail(Integer userId, Integer authId);
}
