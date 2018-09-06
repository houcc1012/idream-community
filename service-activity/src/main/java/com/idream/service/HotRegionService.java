package com.idream.service;

import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.PagesParam;
import com.idream.commons.lib.dto.app.HotRegionInfoResponseDto;

public interface HotRegionService {

    //热门社区(app端)
    PagesDto<HotRegionInfoResponseDto> selectHotCommunityList(PagesParam param);
}
