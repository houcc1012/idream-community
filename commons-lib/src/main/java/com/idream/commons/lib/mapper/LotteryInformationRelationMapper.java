package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.marketing.LotteryInformationRelationDto;
import com.idream.commons.lib.model.LotteryInformationRelation;

import java.util.List;

public interface LotteryInformationRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LotteryInformationRelation record);

    int insertSelective(LotteryInformationRelation record);

    LotteryInformationRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LotteryInformationRelation record);

    int updateByPrimaryKey(LotteryInformationRelation record);

    //根据其他ID获取主键
    List<Integer> selectIdByOtherId(Integer lotteryId, Integer infoId);

    List<LotteryInformationRelationDto> selectByLotteryId(Integer lotteryId);

    int deleteByPoolId(Integer poolId);
}