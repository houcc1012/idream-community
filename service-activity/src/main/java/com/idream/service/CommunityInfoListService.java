package com.idream.service;

import com.idream.commons.lib.dto.activity.BookHouseListDto;

import java.util.List;

/**
 * 社区列表
 *
 * @param
 *
 * @author zhanfeifei
 */
public interface CommunityInfoListService {

    //根据adCode查询书屋列表名称
    List<BookHouseListDto> selectBookHouseListByAdCode(String provinceCode, String cityCode, String adCode, String bookHouseName, Integer communityId);

}
