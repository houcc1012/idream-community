package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.user.AdminUserStatisticsDetailDto;
import com.idream.commons.lib.dto.user.AdminUserStatisticsDetailParams;
import com.idream.commons.lib.dto.user.AdminUserStatisticsDto;
import com.idream.commons.lib.dto.user.AdminUserStatisticsParams;
import com.idream.commons.lib.model.UserLoginRecord;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface UserLoginRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserLoginRecord record);

    int insertSelective(UserLoginRecord record);

    UserLoginRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserLoginRecord record);

    int updateByPrimaryKey(UserLoginRecord record);

    List<AdminUserStatisticsDto> selectByDevice(@Param("deviceType") Integer deviceType, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<AdminUserStatisticsDto> selectByDate(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<AdminUserStatisticsDetailDto> selectDetailsByDateAndDevice(AdminUserStatisticsDetailParams params);

    UserLoginRecord selectRegisterByUserId(Integer userId);

    Integer selectTotalByDevice(@Param("deviceType") Integer deviceType);
}