package com.idream.service;

import java.util.List;

import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.app.MyNeighborCommunityResponseDto;
import com.idream.commons.lib.dto.app.NeighborCommunityListResponseDto;
import com.idream.commons.lib.dto.app.NeighborCommunityRequestDto;

public interface MyNeighborCommunityService {

    //我的社区和我附近的社区
    MyNeighborCommunityResponseDto selectMyNeighborCommunity(Integer userId, NeighborCommunityRequestDto neighborCommunityRequestDto);

    //附近的社区(社区位置)
    PagesDto<List<NeighborCommunityListResponseDto>> selectNeighborCommunity(NeighborCommunityRequestDto neighborCommunityRequestDto);
}
