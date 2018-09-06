package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.AppVersion;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface AppVersionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppVersion record);

    int insertSelective(AppVersion record);

    AppVersion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppVersion record);

    int updateByPrimaryKey(AppVersion record);

    //根据设备类型查询app版本信息
    @Select("select * from app_version where app_type = #{deviceType}")
    AppVersion selectByDeviceType(@Param("deviceType") Byte deviceType);
}