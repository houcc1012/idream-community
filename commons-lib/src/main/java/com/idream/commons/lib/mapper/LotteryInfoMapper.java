package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.activity.LotteryInfoResponseDto;
import com.idream.commons.lib.dto.activity.MiniActivityAssociationLottery;
import com.idream.commons.lib.dto.app.LotteryListResponseDto;
import com.idream.commons.lib.dto.marketing.*;
import com.idream.commons.lib.dto.region.UnityLotteryDto;
import com.idream.commons.lib.model.LotteryInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface LotteryInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(LotteryInfo record);

    int insertSelective(LotteryInfo record);

    LotteryInfo selectByPrimaryKey(@Param(value = "id") Integer id);

    int updateByPrimaryKeySelective(LotteryInfo record);

    int updateByEntity(LotteryInfo record);

    int updateByPrimaryKey(LotteryInfo record);

    List<LotteryInfoDto> selectLotteryPoolList(LotterySearchParams lotterySearchParams);

    List<LotteryInfoDto> selectLotteryListByUser(LotterySearchParams lotterySearchParams);

    List<ActivityInfoDto> selectActivityList(Integer communityid);

    LotteryInfoDto selectLotteryById(Integer id);

    //查询特定时间的开奖活动的详细信息
    SceneOpenAwardInfo selectSceneOpenAwardInfo(@Param("lotteryId") Integer lotteryId, @Param("date") Date date);

    List<WeChatLotteryInfoDto> selectLotteryListByNear(@Param("latitude") BigDecimal latitude, @Param("longitude") BigDecimal longitude, @Param("cityCode") String cityCode);

    //查询所有未结束的开奖活动
    @Select("select * from lottery_info where status != 3")
    List<LotteryInfo> selectLotteryInfoAll();

    //根据活动ID查询正在开奖开奖活动
    @Select("select count(*) from lottery_info a, lottery_detail b " +
            " where a.status != 3 and now() between  b.start_time and  b.end_time" +
            " and a.activity_id = #{activityId} and a.id = b.lottery_id")
    Integer selectByActivity(@Param("activityId") Integer activityId);

    //现场开奖 app端
    List<LotteryListResponseDto> selectLotteryList(@Param(value = "communityId") Integer communityId);

    //通过communityId现场开奖 app端
    List<UnityLotteryDto> selectLotteryInfoListByCommunity(@Param("communityId") Integer communityId);

    List<LotteryWinRecordDto> getLotteryList(LotteryWinRecordSearchParams lotterySearchParams);

    LotteryActivityInfoDto selectActivityInfoByLotteryId(@Param("lotteryId") Integer lotteryId);

    @Select("select id as id, start_time as startTime , end_time as endTime from lottery_info where activity_id = #{activityId} ")
    List<MiniActivityAssociationLottery> selectByActviity(@Param(value = "activityId") Integer activityId);
}