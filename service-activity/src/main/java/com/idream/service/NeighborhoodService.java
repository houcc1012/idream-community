package com.idream.service;

import java.util.List;

import com.idream.commons.lib.dto.app.NeighborCommunityResponseDto;

public interface NeighborhoodService {

    //查看邻居
    List<NeighborCommunityResponseDto> selectNeighborhood(Integer communityId);
}
