package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.activity.AppCityDto;
import com.idream.commons.lib.dto.excel.ExcelLocationDto;
import com.idream.commons.lib.dto.user.AdminOpenCityDto;
import com.idream.commons.lib.dto.user.MiniProgramOpenCityDto;
import com.idream.commons.lib.model.OpenCity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OpenCityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OpenCity record);

    int insertSelective(OpenCity record);

    OpenCity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OpenCity record);

    int updateByPrimaryKey(OpenCity record);

    /**
     * 查询所有开通的城市
     */
    List<AppCityDto> listOpenCity();

    int insertRefresh();

    int deleteAll();

    //查询所有开通城市
    List<AdminOpenCityDto> selectOpenCity();

    //查询开通城市
    AdminOpenCityDto selectOpenByCityCode(String cityCode);

    //小程序查询所有开通城市
    List<MiniProgramOpenCityDto> selectMiniOpenCity();

    int deleteOpenCityByCityCode(@Param(value = "cityCode") String cityCode);

    @Select("select * from district where city_name=#{city}")
    ExcelLocationDto selectLocationInfoByCityName(@Param("city") String city);

    @Select("select * from district where city_code=#{cityCode}")
    ExcelLocationDto selectLocationInfoByCityCode(@Param("cityCode") String cityCode);
}