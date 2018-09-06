package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.SystemConfig;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SystemConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemConfig record);

    int insertSelective(SystemConfig record);

    SystemConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SystemConfig record);

    int updateByPrimaryKey(SystemConfig record);

    //根据配置代码查询配置value
    @Select("select config_value from system_config where config_code = #{configCode}")
    String selectByConfigCode(@Param("configCode") String configCode);

    //根据配置代码查询配置value
    @Select("select config_value from system_config where config_code = #{configCode}")
    Integer selectIntegerValueByConfigCode(@Param("configCode") String configCode);
}