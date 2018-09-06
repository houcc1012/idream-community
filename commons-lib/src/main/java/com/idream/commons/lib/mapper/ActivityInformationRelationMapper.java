package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.information.InformationRuleDto;
import com.idream.commons.lib.model.ActivityInformationRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityInformationRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityInformationRelation record);

    int insertSelective(ActivityInformationRelation record);

    ActivityInformationRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityInformationRelation record);

    int updateByPrimaryKey(ActivityInformationRelation record);

    Integer getKeyByActivityIdAndInfoId(ActivityInformationRelation bean);

    /**
     * @param @param  activityId
     * @param @return
     *
     * @return List<ActivityInformationRelation>
     */
    List<ActivityInformationRelation> getActivityInformationRelationByActivityId(Integer activityId);


    Integer checkJoinActivityGetActivityInformationRelationByActivityId(Integer activityId);


    /**
     * @param @param activityId
     *
     * @return void
     */
    void deleteActivityInformationRelationByActivityId(Integer activityId);

    List<String> getRegistInfoByActivityId(Integer activityId);

    List<InformationRuleDto> selectInformationRuleList(@Param("activityId") Integer activityId);

    List<ActivityInformationRelation> getCustomInfoListByActivityId(@Param("activityId") Integer activityId);
}