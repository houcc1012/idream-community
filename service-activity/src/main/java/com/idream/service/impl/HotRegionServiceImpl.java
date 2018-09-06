package com.idream.service.impl;

import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.PagesParam;
import com.idream.commons.lib.dto.app.HotRegionInfoResponseDto;
import com.idream.commons.lib.mapper.HotRegionMapper;
import com.idream.commons.lib.util.PageRowsUtils;
import com.idream.service.HotRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotRegionServiceImpl implements HotRegionService {

    @Autowired
    private HotRegionMapper hotRegionMapper;

    @Override
    public PagesDto<HotRegionInfoResponseDto> selectHotCommunityList(PagesParam param) {
        int page = param.getPage();
        int rows = param.getRows();
        List<HotRegionInfoResponseDto> hotRegionList = hotRegionMapper.selectHotRegionAll();
        List<HotRegionInfoResponseDto> list = PageRowsUtils.getPageObj(hotRegionList, page, rows);
        PagesDto<HotRegionInfoResponseDto> pagesDto = new PagesDto(list, hotRegionList.size(), page, rows);
        return pagesDto;
    }

}
