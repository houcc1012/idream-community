package com.idream.commons.lib.mapper;


import com.idream.commons.lib.dto.marketing.IntegrationPoolDto;
import com.idream.commons.lib.dto.marketing.IntegrationPoolParams;
import com.idream.commons.lib.dto.marketing.UserRegionPoolInfo;
import com.idream.commons.lib.model.IntegrationPool;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IntegrationPoolMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IntegrationPool record);

    int insertSelective(IntegrationPool record);

    IntegrationPool selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IntegrationPool record);

    int updateByPrimaryKey(IntegrationPool record);

    //获取所有奖品信息
    List<IntegrationPoolDto> selectIntegrationPoolList(@Param(value = "bookId") Integer bookId, @Param(value = "type") Integer type);

    int insertEntity(IntegrationPoolParams record);

    //修改兑奖状态
    int updateStatus(@Param(value = "id") int id, @Param(value = "status") int status);

    //查询可供抽奖的抽奖产品
    @Select("select * from integration_pool where status = 1")
    List<IntegrationPool> selectLotteryAwrad();

    //修改奖品剩余数量
    @Update("update integration_pool set count = count + #{count} where id = #{id}")
    int updateCountById(@Param(value = "id") Integer id, @Param("count") int count);

    IntegrationPoolDto selectIntegrationById(Integer id);

    int updatePool(IntegrationPoolParams integrationPoolParams);

    //小程序查询抽奖奖池
    List<IntegrationPool> selectMiniIntegrationPoolList();

    //查询平台奖池里上架的奖品
    List<IntegrationPool> selectListByStatus();

    //查询管理者奖池里上架的奖品
    List<IntegrationPool> selectListByStatusAndBookId(Integer bookId);

    //根据管理者查询奖池的奖品
    List<IntegrationPool> selectListByUserId(int userId);

    //查询过期奖品
    List<IntegrationPool> selectOutDateGuys();

    //查询平台奖池
    @Select("select * from integration_pool where status = 1 and region_id =-1")
    List<IntegrationPool> selectIntegrationPool();

    List<UserRegionPoolInfo> selectIntegrationPoolByRegions(@Param("regionIds") String regionIds);

    List<UserRegionPoolInfo> selectIntegrationPoolByCity(@Param(value = "cityCode") String cityCode);

    List<IntegrationPool> selectIntegrationPoolByBookId(@Param("bookId") Integer bookId);

    int updateBean(IntegrationPool integrationPool);

    //后台管理 书屋列表 抽奖奖品设置
    List<IntegrationPoolDto> getIntegrationDetailByBookId(@Param("bookId")Integer bookId);

    List<IntegrationPool> selectListByBookId(@Param("bookId")Integer bookId);
}