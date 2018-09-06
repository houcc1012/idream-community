package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.activity.AppCommunityInfoDto;
import com.idream.commons.lib.dto.app.CommunityRegionResponseDto;
import com.idream.commons.lib.dto.app.RegionCommunityInfoDto;
import com.idream.commons.lib.model.CommunityRegionInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommunityRegionInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommunityRegionInfo record);

    int insertSelective(CommunityRegionInfo record);

    CommunityRegionInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommunityRegionInfo record);

    int updateByPrimaryKey(CommunityRegionInfo record);

    //通过regionId查询communityId和regionName
    @Select("select a.id regionId,a.region_name regionName,b.communityId FROM community_region_info a LEFT JOIN (SELECT region_id regionId,community_id communityId from community_region_relation) b ON b.regionId=a.id WHERE a.id=#{regionId}")
    List<CommunityRegionResponseDto> selectRegionNameByRegionId(@Param("regionId") Integer regionId);

    /**
     * 通过userId,查找
     *
     * @param userId
     */
    List<CommunityRegionInfo> selectByUserId(@Param("userId") Integer userId);

    /**
     * 通过userId,查找
     *
     * @param userId
     */
    List<AppCommunityInfoDto> getMyCommunity(@Param("userId") Integer userId);

    //通过书屋id查询大区regionId
    @Select("SELECT id regionId FROM community_region_info WHERE book_id = #{bookHouseId}")
    Integer selectRegionIdByBookId(@Param("bookHouseId") Integer bookHouseId);

    //通过大社区id查询大社区信息
    @Select("SELECT a.id regionId, a.region_name regionName, c.id communityId, c.community_name communityName, c.longitude,c.latitude FROM community_region_info a LEFT JOIN community_region_relation b ON b.region_id = a.id LEFT JOIN community_info c ON c.id = b.community_id WHERE a.id = #{regionId}")
    List<RegionCommunityInfoDto> selectRegionCommunityInfo(@Param("regionId") Integer regionId);
}