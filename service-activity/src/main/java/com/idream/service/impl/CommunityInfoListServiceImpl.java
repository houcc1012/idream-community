package com.idream.service.impl;

import com.idream.commons.lib.dto.activity.BookHouseListDto;
import com.idream.commons.lib.mapper.*;
import com.idream.service.CommunityInfoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityInfoListServiceImpl implements CommunityInfoListService {

    @Autowired
    private BookHouseMapper bookHouseMapper;

    @Override
    public List<BookHouseListDto> selectBookHouseListByAdCode(String provinceCode, String cityCode, String adCode, String bookHouseName, Integer communityId) {
        List<BookHouseListDto> bookHouseList = bookHouseMapper.selectBookHouseListByAddress(provinceCode, cityCode, adCode, bookHouseName, communityId);
        return bookHouseList;
    }

}
