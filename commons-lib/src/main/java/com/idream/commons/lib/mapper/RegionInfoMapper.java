package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.admin.MyRegionInfoListDto;
import com.idream.commons.lib.dto.app.NeighborCommunityRegionResponseDto;
import com.idream.commons.lib.model.RegionInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

public interface RegionInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RegionInfo record);

    int insertSelective(RegionInfo record);

    RegionInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RegionInfo record);

    int updateByPrimaryKey(RegionInfo record);

    //通过communityId 和 type 查询与之绑定的大社区信息
    RegionInfo getRegionInfoByCommunityId(@Param("type") Integer type, @Param("communityId") Integer communityId);

    //通过大社区id查询所有小社区id
    @Select("SELECT a.id communityId FROM region_group_info a INNER JOIN region_group_relation b ON b.group_id = a.id INNER JOIN region_info c ON c.id = b.region_id WHERE a.category = 1 AND c.id = #{regionId}")
    List<Integer> getListCommunityIdByRegionId(@Param("regionId") Integer regionId);

    //通过经纬度查询附近的大社区
    List<NeighborCommunityRegionResponseDto> selectNeighborCommunityRegion(@Param("longitude") BigDecimal longitude, @Param("latitude") BigDecimal latitude);

    //管理端 终端 我的社区
    List<MyRegionInfoListDto> getMyRegionInfoList(@Param("bookId") Integer bookId);

    //查询表中的所有regionId
    @Select("select * from region_info")
    List<RegionInfo> getAllRegionInfo();

    //通过communityId 查询与之绑定的大社区信息
    RegionInfo getRegionByCommunityId(@Param("communityId") Integer communityId);

    //根据大社区ID查询小区ID集合
    @Select("select group_id from region_group_relation a, region_group_info b where b.category = 1 and b.id = a.group_id and a.region_id in (${regionIdStr})")
    List<Integer> selectGroupIdByRegionIds(@Param("regionIdStr") String regionIdStr);
}