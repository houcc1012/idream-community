package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.activity.HotCityResponseDto;
import com.idream.commons.lib.dto.app.CityListResponseDto;
import com.idream.commons.lib.model.HotCity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface HotCityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HotCity record);

    int insertSelective(HotCity record);

    HotCity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HotCity record);

    int updateByPrimaryKey(HotCity record);

    List<HotCity> getHotCityByIndex();

    //城市列表
    @Select("select id cityId,city,'index' from hot_city order by 'index'")
    List<CityListResponseDto> selectCityList();

    //热门城市
    List<HotCityResponseDto> getHotCityList();
}