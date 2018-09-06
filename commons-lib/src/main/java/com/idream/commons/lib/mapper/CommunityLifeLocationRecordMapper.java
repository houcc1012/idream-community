package com.idream.commons.lib.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.idream.commons.lib.dto.app.NeighborCommunityResponseDto;
import com.idream.commons.lib.model.CommunityLifeLocationRecord;

public interface CommunityLifeLocationRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommunityLifeLocationRecord record);

    int insertSelective(CommunityLifeLocationRecord record);

    CommunityLifeLocationRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommunityLifeLocationRecord record);

    int updateByPrimaryKey(CommunityLifeLocationRecord record);

    //查看邻居
    List<NeighborCommunityResponseDto> selectNeighborhoodAll(@Param(value = "communityId") Integer communityId);
}