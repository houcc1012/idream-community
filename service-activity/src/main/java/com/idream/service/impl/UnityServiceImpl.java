package com.idream.service.impl;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.region.UnityBulletinDto;
import com.idream.commons.lib.dto.region.UnityGroupDto;
import com.idream.commons.lib.dto.region.UnityRegionDto;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.mapper.UnityGeographyInfoMapper;
import com.idream.commons.lib.mapper.UnityRegionInfoMapper;
import com.idream.commons.lib.model.UnityRegionInfo;
import com.idream.service.UnityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnityServiceImpl implements UnityService {
    @Autowired
    private UnityRegionInfoMapper unityRegionInfoMapper;
    @Autowired
    private UnityGeographyInfoMapper unityGeographyInfoMapper;

    @Override
    public UnityBulletinDto getRegionBulletin(Integer regionId) {
        return null;
    }

    @Override
    public List<UnityRegionDto> getRegionByRegionId(Integer regionId) {
        List<UnityRegionInfo> unityRegionInfos = unityRegionInfoMapper.selectByRegionId(regionId);
        if (unityRegionInfos.isEmpty()) {
            throw new BusinessException(RetCodeConstants.UNKOWN_ERROR, "对应的模型社区不存在");
        }

        return unityRegionInfos.stream().map(i -> {
            UnityRegionDto dto = new UnityRegionDto();
            dto.setId(i.getUnityRegionId());
            dto.setName(i.getUnityRegionName());
            dto.setVersionId(i.getVersion());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<UnityGroupDto> getRegionGroupByUnityId(String unityId) {
        return unityGeographyInfoMapper.selectRegionGroupByUnityId(unityId);
    }
}
