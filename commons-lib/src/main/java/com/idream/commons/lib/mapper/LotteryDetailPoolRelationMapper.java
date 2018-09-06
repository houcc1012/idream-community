package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.marketing.LotteryAwardBean;
import com.idream.commons.lib.model.LotteryDetailPoolRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LotteryDetailPoolRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LotteryDetailPoolRelation record);

    int insertSelective(LotteryDetailPoolRelation record);

    LotteryDetailPoolRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LotteryDetailPoolRelation record);

    int updateByPrimaryKey(LotteryDetailPoolRelation record);

    int deleteByPoolId(Integer poolId);

    List<Integer> selectByPoolIdAndLotteryId(@Param(value = "poolId") Integer poolId, @Param(value = "lotteryId") Integer lotteryId);

    // 根据开奖活动详情ID查询奖品列表
    List<LotteryAwardBean> selectByLotteryDetailId(@Param(value = "lotteryDetailId") Integer lotteryDetailId);

    int deleteByDetailId(@Param(value = "detailId") Integer detailId);

    List<LotteryDetailPoolRelation> selectByDetailId(@Param(value = "detailId") Integer detailId);
}