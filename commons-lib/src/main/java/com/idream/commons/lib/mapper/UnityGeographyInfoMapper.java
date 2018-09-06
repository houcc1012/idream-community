package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.region.UnityGroupDto;
import com.idream.commons.lib.model.UnityGeographyInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UnityGeographyInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UnityGeographyInfo record);

    int insertSelective(UnityGeographyInfo record);

    UnityGeographyInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UnityGeographyInfo record);

    int updateByPrimaryKey(UnityGeographyInfo record);

    List<UnityGroupDto> selectRegionGroupByUnityId(@Param("unityId") String unityId);
}