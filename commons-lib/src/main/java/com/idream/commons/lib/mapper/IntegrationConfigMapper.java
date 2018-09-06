package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.admin.IntegrationConfigDto;
import com.idream.commons.lib.model.IntegrationConfig;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface IntegrationConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IntegrationConfig record);

    int insertSelective(IntegrationConfig record);

    IntegrationConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IntegrationConfig record);

    int updateByPrimaryKey(IntegrationConfig record);

    @Select("select config_value from integration_config where config_code=#{code}")
    Integer getIntegerByCode(@Param("code") String code);

    @Select("select config_value from integration_config where config_code=#{code}")
    Boolean getBooleanByCode(@Param("code") String code);

    @Update("update integration_config set config_value=#{configValue} where config_code=#{code}")
    int updateValueByCode(@Param("code") String code, @Param("configValue") Integer configValue);


}