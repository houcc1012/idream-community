package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.marketing.LotteryDetailParams;
import com.idream.commons.lib.dto.marketing.LotteryTimeDto;
import com.idream.commons.lib.model.LotteryDetail;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface LotteryDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LotteryDetail record);

    int insertSelective(LotteryDetail record);

    LotteryDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LotteryDetail record);

    int updateByPrimaryKey(LotteryDetail record);

    List<LotteryDetailParams> selectByLotteryId(Integer lotteryId);

    List<LotteryDetailParams> selectByLotteryIds(@Param(value = "lotteryIds") String lotteryIds);

    List<LotteryTimeDto> selectTimeByPoolId(Integer poolId);

    LotteryDetail selectIdByLotteryIdAndTime(@Param(value = "lotteryId") Integer lotteryId, @Param(value = "time") Date time);

    List<LotteryTimeDto> selectTimeByLotteryId(Integer lotteryId);

    @Select("select * from lottery_detail where lottery_id = #{lotteryId}")
    List<LotteryDetail> selectLotteryDetailByLotteryId(Integer lotteryId);

}