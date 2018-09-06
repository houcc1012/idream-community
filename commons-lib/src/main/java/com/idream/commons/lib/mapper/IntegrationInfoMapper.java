package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.IntegrationInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

public interface IntegrationInfoMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(IntegrationInfo record);

    int insertSelective(IntegrationInfo record);

    IntegrationInfo selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(IntegrationInfo record);

    int updateByPrimaryKey(IntegrationInfo record);

    //根据用户id修改用户积分和免费抽奖机会
    @Update("update integration_info set score = score + #{score} , free_lottery = free_lottery + #{freeLottery}, update_time = now() where user_id = #{userId}")
    int updateScoreAndFreeLotteryByUserId(@Param("score") int score, @Param("freeLottery") int freeLottery, @Param("userId") int userId);

    //根据用户id查询用户积分信息
    @Select("select * from integration_info where user_id = #{userId}")
    IntegrationInfo selectByUserId(@Param("userId") int userId);

    int insertAutoScore(IntegrationInfo record);

    @Update("update integration_info set score =score+#{score} ,update_time=#{createDate} where user_id=#{userId}")
    int updateScoreByUserId(@Param("userId") Integer userId, @Param("score") Integer score, @Param("createDate") Date createDate);

    @Update("update system_config  set config_value = #{score} where config_code = 'lottery_score'")
    int updateNeedScore(Integer score);

    @Select("select  config_value from system_config where config_code = 'lottery_score'")
    int selectScore();
}