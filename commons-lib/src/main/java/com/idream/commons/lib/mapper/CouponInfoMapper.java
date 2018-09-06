package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.marketing.*;
import com.idream.commons.lib.model.CouponInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

public interface CouponInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(CouponInfo record);

    int insertSelective(CouponInfo record);

    CouponInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CouponInfo record);

    int updateByPrimaryKey(CouponInfo record);

    //查询抽奖中奖记录
    List<CouponInfoDto> selectCouponList(CouponInfoParam couponInfoParam);

    //查询券码兑奖状态
    Integer selectStatus(String code);

    CouponInfo selectOneByCode(String code);

    //修改券码兑奖状态
    int updateStatus(@Param(value = "code") String code, @Param(value = "userId") int userId);

    List<CouponInfoDto> selectCouponByUserId(@Param(value = "userId") Integer userId);

    /**
     * 查询用户为删除的券码
     *
     * @param userId
     */
    List<CouponInfoDto> selectNotDeletedCouponByUserId(@Param(value = "userId") Integer userId);

    CouponInfoDto selectOneByCouponId(@Param(value = "couponId") Integer couponId);

    //查询用户某个奖品的当天兑奖次数
    @Select("select count(*) from coupon_info where user_id = #{userId} and DATE(create_time) = DATE(now()) and from_type = 2 and award_id = #{awardId}")
    int countEveryDayConvertCountByUserIdAndAwardId(@Param(value = "userId") int userId, @Param("awardId") int awardId);

    //查询某个奖品用户总的兑奖次数
    @Select("select count(*) from coupon_info where  from_type = 2 and award_id = #{awardId} and user_id = #{userId}")
    int countConvertCountByAwardId(@Param(value = "userId") int userId, @Param("awardId") int awardId);

    Integer wetherNeedWriteInfo(@Param(value = "poolId") Integer poolId, @Param(value = "type") Integer type);

    List<UserWinningRecord> select24HoursWinningRecord();

    List<MarketStaticsDto> selectCouponsByDate(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<MarketStaticsDto> selectLotteryByDate(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<MarketStaticsDto> selectOpenByDate(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<MarketStaticsDto> selectAllByDate(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<MyAwardRecordDto> selectMyRecord(@Param("userId") int userId, @Param("type") int type, @Param("couponCode") String couponCode);

    Integer selectCouponRegion(@Param("code") String code, @Param("type") int type);

    @Select("select book_id from user_manager where user_id =#{userId} and status = 1")
    List<Integer> selectUserRegion(@Param(value = "userId") int userId);

    Integer selectIntegrationCouponCountByUserId(@Param("userId") int userId);

    @Update("update coupon_info set info_completed = true where id=#{couponId}")
    void updateCompletedByCouponId(@Param("couponId") Integer couponId);

    //根据id 修改状态和兑换人信息
    @Update("update coupon_info set status = #{status}, operate_user_id = #{userId} where id = #{id}")
    int updateStatusAndCouponUserIdById(@Param("status") Byte status, @Param("userId") Integer userId, @Param("id") Integer id);

    List<MarketingRecordDto> selectLastRecord(@Param("fromType") Byte fromType);

    List<CouponRecordDto> selectCouponRecord();

    //修改券码前端展示状态  1代表不展示
    @Update("update coupon_info set delete_flag = 1 where id=#{couponId}")
    int updateDeleteFlag(@Param(value = "couponId") int couponId);
    //抽奖明细
    List<MarketIntegrationStaticsDto> selectIntegrationDetail(MarketDetailStaticsParams params);
    //兑奖明细
    List<MarketAwardStaticsDto> selectAwardDetail(MarketDetailStaticsParams params);
   //现场开奖明细
    List<MarketLotteryStaticsDto> selectLotteryDetail(MarketDetailStaticsParams params);

    MarketStaticsDto selectTotalCouponByType(@Param(value = "fromType") Integer fromType);

}