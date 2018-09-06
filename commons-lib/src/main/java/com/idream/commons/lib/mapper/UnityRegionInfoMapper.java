package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.UnityRegionInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UnityRegionInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UnityRegionInfo record);

    int insertSelective(UnityRegionInfo record);

    UnityRegionInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UnityRegionInfo record);

    int updateByPrimaryKey(UnityRegionInfo record);

    @Select("select * from unity_region_info where region_id=#{regionId}")
    List<UnityRegionInfo> selectByRegionId(@Param("regionId") Integer regionId);
}