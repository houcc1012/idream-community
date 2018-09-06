package com.idream.service.impl;

import com.idream.commons.lib.dto.app.CommunityActivityRequestDto;
import com.idream.commons.lib.dto.app.CommunityNameListResponseDto;
import com.idream.commons.lib.dto.app.MyCommunityParams;
import com.idream.commons.lib.mapper.UserCommunityRelationMapper;
import com.idream.service.MyCommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JEFFERY
 */
@Service
public class MyCommunityServiceImpl implements MyCommunityService {

    @Autowired
    private UserCommunityRelationMapper userCommunityRelationMapper;

    /**
     * 我的社区icon是否展示
     */
    @Override
    public Boolean showIcon(Integer userId, CommunityActivityRequestDto communityActivityRequestDto) {
        Boolean flag = true;
        List<CommunityNameListResponseDto> myCommunityList = userCommunityRelationMapper.selectMyCommunityList(userId, communityActivityRequestDto.getLongitude(), communityActivityRequestDto.getLatitude());
        if (myCommunityList.size() == 0) {
            flag = false;
        }
        return flag;
    }

    /**
     * 我的社区列表查询
     */
    @Override
    public List<CommunityNameListResponseDto> selectMyCommunityList(Integer userId, MyCommunityParams myCommunityParams) {
        List<CommunityNameListResponseDto> myCommunityList = userCommunityRelationMapper.selectMyCommunityList(userId, myCommunityParams.getLongitude(), myCommunityParams.getLatitude());
        return myCommunityList;
    }

}
