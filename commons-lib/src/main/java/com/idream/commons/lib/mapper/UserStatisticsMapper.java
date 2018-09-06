package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.UserStatistics;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserStatisticsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserStatistics record);

    int insertSelective(UserStatistics record);

    UserStatistics selectByPrimaryKey(Integer id);

    @Select("select * from user_statistics where user_id = #{userId}")
    UserStatistics selectByUserId(Integer userId);

    int updateByPrimaryKeySelective(UserStatistics record);

    int updateByPrimaryKey(UserStatistics record);

    int updateBatchSelective(List<UserStatistics> userStatisticsList);
}