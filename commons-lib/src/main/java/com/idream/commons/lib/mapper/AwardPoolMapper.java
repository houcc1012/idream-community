package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.marketing.AdminAwardPoolDto;
import com.idream.commons.lib.dto.marketing.AwardPoolDto;
import com.idream.commons.lib.dto.marketing.AwardPoolParams;
import com.idream.commons.lib.dto.marketing.UserRegionPoolInfo;
import com.idream.commons.lib.model.AwardPool;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.List;

public interface AwardPoolMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AwardPool record);

    int insertSelective(AwardPool record);

    AwardPool selectByPrimaryKey(Integer id);

    AdminAwardPoolDto selectAwardById(Integer id);

    AdminAwardPoolDto selectAwardByIdAndUserId(@Param(value = "id") Integer id, @Param(value = "userId") Integer userId);

    int updateByPrimaryKeySelective(AwardPool record);

    int updateByPrimaryKey(AwardPool record);

    //平台查询奖池
    List<AwardPoolParams> selectAwardList(AwardPool apool);

    //管理者查询奖池
    List<AdminAwardPoolDto> selectAwardListByBookId(@Param(value = "bookId") Integer bookId, @Param(value = "type") Integer type, @Param(value = "status") Integer status);

    int updateStatus(@Param(value = "id") int id, @Param(value = "status") int status);

    //查询用户参与的有奖活动个数
    int countPrizeActivityByUserId(int userId);

    //修改奖品剩余数量
    @Update("update award_pool set count = count + #{count} where id = #{id}")
    int updateCountById(@Param(value = "id") Integer id, @Param("count") int count);

    //查询可供兑奖的兑奖奖品集合
    @Select("select * from award_pool where status = 1 and count > 0 and DATE(start_time) <= DATE(now()) and DATE(end_time) >= DATE(now())")
    List<AwardPool> selectAwardAll();

    //查询用户能兑的通用券
    List<AwardPoolDto> selectAwardsByBookId(@Param("bookId") int bookId);

    //查询推荐的兑换券 最多6条
    List<AwardPoolDto> selectRecommendedAwardsByBookIds(@Param("bookIds") String bookIds);

    //查询最近的书屋奖券
    List<AwardPoolDto> selectNearAwards(@Param("latitude") BigDecimal latitude, @Param("longitude") BigDecimal longitude, @Param("cityCode") String cityCode);


    //查询用户能兑的兑换券列表
    List<AwardPoolDto> selectAwardsByUserAndBookIds(@Param("userId") int userId, @Param("bookIds") String bookIds);

    //查询过期奖品
    List<AwardPool> selectOutDateGuys();

    //查询用户大社区ID列表
    @Select("select region_id from user_region_relation where user_id =#{userId} ")
    List<Integer> getRegionIdByUserId(@Param("userId") int userId);

    //查询所有书屋ID
    @Select("select id from region_group_info where category = 2")
    List<Integer> getAllRegionId();

    List<UserRegionPoolInfo> selectAwardPoolsByUserId(@Param("userId") Integer userId);

    List<UserRegionPoolInfo> selectShelfAwardPoolsByUserId(@Param("userId") Integer userId);

    List<UserRegionPoolInfo> selectAllAwardPoolsByRegion(@Param("cityCode") String cityCode);

    int updateBean(AwardPool awardPool);

    List<AdminAwardPoolDto> getAwardDetailByBookId(@Param("bookId")Integer bookId);

}