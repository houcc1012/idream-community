package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.marketing.LotteryAwardBean;
import com.idream.commons.lib.dto.marketing.LotteryPoolDto;
import com.idream.commons.lib.model.LotteryPool;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

public interface LotteryPoolMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LotteryPool record);

    int insertSelective(LotteryPool record);

    LotteryPool selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LotteryPool record);

    int updateByPrimaryKey(LotteryPool record);

    List<LotteryPoolDto> selectByLotteryIdAndPoolId(@Param(value = "lotteryId") Integer lotteryId, @Param(value = "poolId") Integer poolId);

    List<LotteryPoolDto> selectByLotteryIdAndTime(@Param(value = "lotteryId") Integer lotteryId, @Param(value = "time") Date time);

    //修改奖品剩余数量
    @Update("update lottery_pool set count = count + #{count} where id = #{id}")
    int updateCountById(@Param(value = "id") Integer id, @Param("count") int count);

    //根据开奖活动Id查询奖品信息
    @Select("select id, award_name name, count, probability prob from lottery_pool where lottery_id = #{lotteryId}")
    List<LotteryAwardBean> selectLotteryAwardBeanByLotteryId(@Param(value = "lotteryId") Integer lotteryId);

    //根据开奖时段详细信息查询开奖奖品明细
    @Select("select a.id, award_name name, count, probability prob from lottery_pool a, lottery_detail_pool_relation b " +
            "where b.detail_id = #{detailId} and b.pool_id = a.id")
    List<LotteryAwardBean> selectLotteryAwardBeanByLotteryDetailId(@Param("detailId") Integer detailId);

    @Select("select * from lottery_pool where lottery_id = #{lotteryId}")
    List<LotteryPool> selectByLotteryId(@Param(value = "lotteryId") Integer lotteryId);

    int updateBean(LotteryPool record);
}