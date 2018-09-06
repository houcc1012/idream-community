package com.idream.service;

import com.idream.commons.lib.dto.region.UnityBulletinDto;
import com.idream.commons.lib.dto.region.UnityGroupDto;
import com.idream.commons.lib.dto.region.UnityRegionDto;

import java.util.List;

public interface UnityService {
    UnityBulletinDto getRegionBulletin(Integer regionId);

    /**
     * 通过大区id,查询社区模型信息
     *
     * @param regionId
     */
    List<UnityRegionDto> getRegionByRegionId(Integer regionId);

    /**
     * 通过社区模型id,查询建筑物列表
     *
     * @param unityId
     */
    List<UnityGroupDto> getRegionGroupByUnityId(String unityId);
}
