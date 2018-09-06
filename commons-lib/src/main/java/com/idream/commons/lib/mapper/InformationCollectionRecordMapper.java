package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.activity.InformationCollectionRecordDto;
import com.idream.commons.lib.dto.appactivity.AppUserInfoResponseDto;
import com.idream.commons.lib.dto.information.InformationUserRecordDto;
import com.idream.commons.lib.model.InformationCollectionRecord;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface InformationCollectionRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InformationCollectionRecord record);

    int insertSelective(InformationCollectionRecord record);

    InformationCollectionRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InformationCollectionRecord record);

    int updateByPrimaryKey(InformationCollectionRecord record);

    List<InformationCollectionRecordDto> getUserActivityInfo(Integer activityId, Integer userId);

    List<AppUserInfoResponseDto> getLatestUserInfo(@Param("userId") Integer userId);

    @Select("SELECT * FROM information_collection_record WHERE user_id = #{userId} AND type=1 ORDER BY create_time DESC")
    List<InformationCollectionRecord> getUserInforByUserId(@Param("userId") Integer userId);

    //根据用户id和活动id查询有没有搜集到用户的信息
    @Select("SELECT count(*) FROM information_collection_record WHERE user_id = #{userId} AND type=1 AND relation_id= #{activityId}")
    int getInformationCollectionRecordByUserIdAndActivityId(@Param("userId") Integer userId, @Param("activityId") Integer activityId);

    List<InformationUserRecordDto> getLastUserRecord(@Param("userId") Integer userId);

}