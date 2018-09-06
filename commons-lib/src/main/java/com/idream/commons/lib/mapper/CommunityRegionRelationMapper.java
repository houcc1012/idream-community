package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.app.CommunityRegionResponseDto;
import com.idream.commons.lib.model.CommunityRegionRelation;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CommunityRegionRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommunityRegionRelation record);

    int insertSelective(CommunityRegionRelation record);

    CommunityRegionRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommunityRegionRelation record);

    int updateByPrimaryKey(CommunityRegionRelation record);

    //通过communityId查询对应的regionId
    @Select("select id,region_id regionId,community_id communityId from community_region_relation where community_id = #{communityId}")
    CommunityRegionResponseDto selectRegionIdByCommunityId(@Param("communityId") Integer communityId);


    //通过小区id修改对应关联表中的regionId
    @Update("update community_region_relation set region_id = #{regionId},defaulted = #{defaulted},update_time = #{updateTime} where community_id = #{communityId}")
    int updateRegionIdByCommunityId(CommunityRegionRelation communityRegionRelation);
}