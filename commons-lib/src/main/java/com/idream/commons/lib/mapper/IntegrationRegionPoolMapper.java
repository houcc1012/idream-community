package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.IntegrationPool;
import com.idream.commons.lib.model.IntegrationRegionPool;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IntegrationRegionPoolMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IntegrationRegionPool record);

    int insertSelective(IntegrationRegionPool record);

    IntegrationRegionPool selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IntegrationRegionPool record);

    int updateByPrimaryKey(IntegrationRegionPool record);

    List<IntegrationPool> selectRegionPoll(@Param(value = "regionId") Integer regionId);

    //修改奖品剩余数量
    @Update("update integration_region_pool set count = count + #{count} where id = #{id}")
    int updateCountById(@Param(value = "id") Integer id, @Param("count") int count);

}