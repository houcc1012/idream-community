package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.activity.AdminActivityStatisticsDto;
import com.idream.commons.lib.model.UserVisitRecord;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

public interface UserVisitRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserVisitRecord record);

    int insertSelective(UserVisitRecord record);

    UserVisitRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserVisitRecord record);

    int updateByPrimaryKey(UserVisitRecord record);

    List<AdminActivityStatisticsDto> selectViewsByDate(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    Integer selectTotalViews();

    // 查询活动的浏览量
    @Select("select sum(count) from user_visit_record where business_type =1 and business_id = #{activityId}")
    Integer getActivityVisitCount(@Param("activityId") Integer activityId);

    // 根据业务ID和业务类型和用户ID 修改访问数量
    @Update("update user_visit_record set count = count + 1 " +
            "where business_type = #{businessType} and business_id = #{businessId} and user_id = #{userId} and date(create_time) = date(now())")
    int updateCountByBusinessIdAndTypeAndUserId(@Param("businessId") int businessId, @Param("businessType") byte businessType, @Param("userId") int userId);
}