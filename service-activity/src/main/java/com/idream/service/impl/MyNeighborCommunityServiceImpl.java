package com.idream.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.app.*;
import com.idream.commons.lib.mapper.*;
import com.idream.service.MyNeighborCommunityService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyNeighborCommunityServiceImpl implements MyNeighborCommunityService {

    @Autowired
    private UserCommunityRelationMapper userCommunityRelationMapper;
    @Autowired
    private CommunityRegionInfoMapper communityRegionInfoMapper;
    @Autowired
    private CommunityInfoMapper communityInfoMapper;
    @Autowired
    private RegionGroupInfoMapper regionGroupInfoMapper;
    @Autowired
    private RegionInfoMapper regionInfoMapper;

    @Override
    public MyNeighborCommunityResponseDto selectMyNeighborCommunity(Integer userId, NeighborCommunityRequestDto neighborCommunityRequestDto) {
        int page = neighborCommunityRequestDto.getPage();
        int rows = neighborCommunityRequestDto.getRows();
        //communityId和regionId是当前在哪个小区或者社区 ,是否是当前社区或者小区
        Integer communityId = neighborCommunityRequestDto.getCommunityId();
        Integer regionId = neighborCommunityRequestDto.getRegionId();
        PageHelper.startPage(page, rows);
        PageInfo pageInfo = null;
        PagesDto<List<NeighborCommunityRegionResponseDto>> pagesDto = new PagesDto<>();
        //附近的社区
        List<NeighborCommunityRegionResponseDto> neighborCommunityRegionList = regionInfoMapper.selectNeighborCommunityRegion(neighborCommunityRequestDto.getLongitude(), neighborCommunityRequestDto.getLatitude());
        if (CollectionUtils.isNotEmpty(neighborCommunityRegionList)) {
            for (NeighborCommunityRegionResponseDto n : neighborCommunityRegionList) {
                if (regionId.equals(n.getId())) {
                    //是当前大社区
                    n.setFlag(1);
                }
            }
        }
        if (neighborCommunityRegionList.size() != 0 && neighborCommunityRegionList != null) {
            pageInfo = new PageInfo<>(neighborCommunityRegionList);
            pagesDto = new PagesDto(neighborCommunityRegionList, pageInfo.getTotal(), page, rows);
        } else {
            pagesDto = new PagesDto(neighborCommunityRegionList, 0, page, rows);
        }
        //我的社区
        PagesDto<List<CommunityNameListResponseDto>> pageDto = new PagesDto<>();
        List<CommunityNameListResponseDto> myCommunityList = userCommunityRelationMapper.selectMyCommunityList(userId, neighborCommunityRequestDto.getLongitude(), neighborCommunityRequestDto.getLatitude());
        if (CollectionUtils.isNotEmpty(myCommunityList)) {
            for (CommunityNameListResponseDto c : myCommunityList) {
                if (communityId.equals(c.getCommunityId())) {
                    //是当前小区
                    c.setFlag(1);
                }
            }
        }
        if (myCommunityList.size() != 0 && myCommunityList != null) {
            pageInfo = new PageInfo<>(myCommunityList);
            pageDto = new PagesDto(myCommunityList, pageInfo.getTotal(), page, rows);
        } else {
            pageDto = new PagesDto(myCommunityList, 0, page, rows);
        }
        MyNeighborCommunityResponseDto myNeighborCommunityResponseDto = new MyNeighborCommunityResponseDto();
        myNeighborCommunityResponseDto.setCommunityNameListResponseDtoList(pageDto);
        myNeighborCommunityResponseDto.setNeighborCommunityRegionResponseDtoList(pagesDto);
        return myNeighborCommunityResponseDto;
    }

    @Override
    public PagesDto<List<NeighborCommunityListResponseDto>> selectNeighborCommunity(NeighborCommunityRequestDto neighborCommunityRequestDto) {
        int page = neighborCommunityRequestDto.getPage();
        int rows = neighborCommunityRequestDto.getRows();
        PageInfo pageInfo = null;
        PageHelper.startPage(page, rows);
        List<NeighborCommunityListResponseDto> neighborCommunityList = regionGroupInfoMapper.getNeighborCommunity(neighborCommunityRequestDto.getCityCode(), neighborCommunityRequestDto.getLongitude(), neighborCommunityRequestDto.getLatitude());
        if (neighborCommunityList.size() != 0 && neighborCommunityList != null) {
            pageInfo = new PageInfo<>(neighborCommunityList);
        } else {
            return new PagesDto(neighborCommunityList, 0, page, rows);
        }
        return new PagesDto(neighborCommunityList, pageInfo.getTotal(), page, rows);
    }

}
