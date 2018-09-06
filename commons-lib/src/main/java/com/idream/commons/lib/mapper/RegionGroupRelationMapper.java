package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.app.CommunityInfoResponseDto;
import com.idream.commons.lib.dto.region.RegionCommunityDto;
import com.idream.commons.lib.model.RegionGroupInfo;
import com.idream.commons.lib.model.RegionGroupRelation;
import com.idream.commons.lib.model.RegionInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

public interface RegionGroupRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RegionGroupRelation record);

    int insertSelective(RegionGroupRelation record);

    RegionGroupRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RegionGroupRelation record);

    int updateByPrimaryKey(RegionGroupRelation record);

    //后台管理 小区或书屋和社区取消关联查询
    @Select("SELECT * FROM region_group_relation WHERE group_id = #{groupId} AND region_id = #{regionId}")
    RegionGroupRelation getRegionGroupRelationByGroupIdAndRegionId(@Param("groupId") Integer groupId, @Param("regionId") Integer regionId);

    //后台管理 判断小区是否有关联社区
    @Select("SELECT * FROM region_group_relation a INNER JOIN region_info b ON a.region_id = b.id AND b.type = 2 WHERE group_id = #{groupId}")
    RegionGroupRelation getRegionGroupRelationByGroupId(@Param("groupId") Integer groupId);

    //后台管理 判断书屋是否有关联社区
    @Select("SELECT * FROM region_group_relation a INNER JOIN region_info b ON a.region_id = b.id AND b.type = 2 WHERE group_id = #{groupId}")
    List<RegionGroupRelation> getBookHouseAndRegionByGroupId(@Param("groupId") Integer groupId);

    List<Integer> selectRegionIdByBookHouse(int bookId);

    List<Integer> selectRegionIdsByGroupId(Integer groupId);

    //通过regionId查询对应的communityId
    @Select("SELECT a.group_id communityId FROM region_group_relation a INNER JOIN region_group_info b ON b.id = a.group_id AND b.category = 1 WHERE a.region_id = #{regionId}")
    List<Integer> selectCommunityIdByRegionId(@Param("regionId") Integer regionId);

    List<RegionCommunityDto> selectCommunityByRegionId(@Param("regionId") Integer regionId);

    List<RegionInfo> selectBookRelateRegionByUserId(@Param("userId") Integer userId);

    @Select("SELECT * FROM region_group_info a LEFT JOIN region_group_relation b ON a.id = b.group_id WHERE b.region_id=#{id} AND a.category=1")
    List<RegionGroupInfo> selectRegionGroupInfoByRegionId(@Param("id") Integer id);

    @Select("SELECT b.* FROM region_group_relation a LEFT JOIN region_info b ON a.region_id=b.id WHERE a.group_id= #{regionGroupId} AND b.type=2")
    RegionInfo getRegionIdByRegionGroupId(@Param("regionGroupId") Integer regionGroupId);

    @Select("SELECT GROUP_CONCAT(DISTINCT(b.`name`)) FROM region_group_relation a LEFT JOIN region_group_info b ON a.group_id = b.id AND b.category =2 WHERE a.region_id in (${regionIds})")
    String selectBookNamesByRegionIds(@Param("regionIds") String regionIds);

    List<CommunityInfoResponseDto> selectCommunityRegionId(@Param("groupId") Integer groupId, @Param("longitude") BigDecimal longitude, @Param("latitude") BigDecimal latitude);
}