package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.integration.FindUserSignRankingListDto;
import com.idream.commons.lib.dto.user.UseIntergralDto;
import com.idream.commons.lib.model.IntegrationRecord;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IntegrationRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IntegrationRecord record);

    int insertSelective(IntegrationRecord record);

    IntegrationRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IntegrationRecord record);

    int updateByPrimaryKey(IntegrationRecord record);

    //根据用户id和积分获取来源查询当天的的积分记录
    @Select("select * from integration_record where user_id = #{userId} and from_type = #{fromType} and Date(create_time) = Date(now())")
    IntegrationRecord selectByUserIdAndFromTypeAndTime(@Param("userId") int userId, @Param("fromType") int fromType);

    //查询指定用户当前连续签到天数
    Integer selectSignDayByUserId(@Param("userId") int userId);

    //查询用户是否断签
    @Select("select count(*) from integration_record where user_id = #{userId} and DATE(create_time) = DATE(DATE_SUB(now(), INTERVAL 1 DAY))")
    Integer selectSignOffByUserId(@Param("userId") int userId);

    //查询用户签到排行版列表
    List<FindUserSignRankingListDto> selectUserSignRankingList();

    @Select("select id, score, type, from_type, create_time from integration_record where user_id = #{userId} order by create_time desc")
    List<UseIntergralDto> selectIntegrationByUserId(@Param("userId") int userId);

    //统计当天用户签到记录
    @Select("select count(*) from integration_record where user_id = #{userId} and DATE(create_time) = DATE(now()) and from_type = 1")
    int selectByUserIdAndToday(int authUserId);

    @Select("SELECT COUNT(*) FROM integration_record WHERE user_id=#{userId} AND from_type=1")
    int countSignByUserId(@Param("userId") Integer userId);

    @Select("select IFNULL(sum(score),0) from integration_record where user_id =#{userId} and from_type = 6 and type = 1  and DATE(create_time) = DATE(now())")
    int getScoreByActivityIdAndUserId(@Param("userId") int userId);
}