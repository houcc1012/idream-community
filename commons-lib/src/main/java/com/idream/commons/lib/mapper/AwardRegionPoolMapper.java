package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.AwardPool;
import com.idream.commons.lib.model.AwardRegionPool;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface AwardRegionPoolMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AwardRegionPool record);

    int insertSelective(AwardRegionPool record);

    AwardRegionPool selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AwardRegionPool record);

    int updateByPrimaryKey(AwardRegionPool record);

    AwardPool selectAwardInfoByPrimaryKey(Integer id);

    //修改奖品剩余数量
    @Update("update award_region_pool set count = count + #{count} where id = #{id}")
    int updateCountById(@Param(value = "id") Integer id, @Param("count") int count);
}