package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.app.HotRegionInfoResponseDto;
import com.idream.commons.lib.model.HotRegion;

import java.util.List;

public interface HotRegionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HotRegion record);

    int insertSelective(HotRegion record);

    HotRegion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HotRegion record);

    int updateByPrimaryKey(HotRegion record);

    int insertRefresh();

    int deleteAll();

    //app端  热门社区
    List<HotRegionInfoResponseDto> selectHotRegionAll();
}