package com.idream.service;

import com.idream.commons.lib.dto.app.CommunityActivityRequestDto;
import com.idream.commons.lib.dto.app.CommunityNameListResponseDto;
import com.idream.commons.lib.dto.app.MyCommunityParams;

import java.util.List;

public interface MyCommunityService {

    //我的社区icon是否展示
    Boolean showIcon(Integer userId, CommunityActivityRequestDto communityActivityRequestDto);

    //我的社区列表查询
    List<CommunityNameListResponseDto> selectMyCommunityList(Integer userId, MyCommunityParams myCommunityParams);

}
