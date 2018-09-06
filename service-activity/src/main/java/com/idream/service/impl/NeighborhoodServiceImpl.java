package com.idream.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idream.commons.lib.dto.app.NeighborCommunityResponseDto;
import com.idream.commons.lib.mapper.CommunityLifeLocationRecordMapper;
import com.idream.service.NeighborhoodService;

@Service
public class NeighborhoodServiceImpl implements NeighborhoodService {

    @Autowired
    private CommunityLifeLocationRecordMapper communityLifeLocationRecordMapper;

    @Override
    public List<NeighborCommunityResponseDto> selectNeighborhood(Integer communityId) {
        List<NeighborCommunityResponseDto> neighborhoodAll = communityLifeLocationRecordMapper.selectNeighborhoodAll(communityId);
        return neighborhoodAll;
    }

}
