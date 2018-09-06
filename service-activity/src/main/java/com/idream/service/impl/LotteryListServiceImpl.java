package com.idream.service.impl;

import com.idream.commons.lib.dto.app.LotteryListResponseDto;
import com.idream.commons.lib.mapper.CommunityRegionRelationMapper;
import com.idream.commons.lib.mapper.LotteryInfoMapper;
import com.idream.commons.lib.mapper.RegionGroupRelationMapper;
import com.idream.service.LotteryListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JEFFERY
 */
@Service
public class LotteryListServiceImpl implements LotteryListService {

    @Autowired
    private LotteryInfoMapper lotteryInfoMapper;
    @Autowired
    private CommunityRegionRelationMapper communityRegionRelationMapper;
    @Autowired
    private RegionGroupRelationMapper regionGroupRelationMapper;


    @Override
    public List<LotteryListResponseDto> selectLotteryList(Integer regionId) {
        List<LotteryListResponseDto> list = new ArrayList<>();
        List<Integer> communityIdList = regionGroupRelationMapper.selectCommunityIdByRegionId(regionId);
        for (Integer communityId : communityIdList) {
            List<LotteryListResponseDto> lotteryList = lotteryInfoMapper.selectLotteryList(communityId);
            list.addAll(lotteryList);
        }
        return list;
    }
}
