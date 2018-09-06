package com.idream.commons.lib.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.idream.commons.lib.dto.activity.ActivityTagResponseDto;
import com.idream.commons.lib.model.ActivityTypeTagRelation;

public interface ActivityTypeTagRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityTypeTagRelation record);

    int insertSelective(ActivityTypeTagRelation record);

    ActivityTypeTagRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityTypeTagRelation record);

    int updateByPrimaryKey(ActivityTypeTagRelation record);

    @Delete("delete from activity_type_tag_relation where type_id=#{typeId}")
    void deleteByTypeId(@Param("typeId") Integer typeId);

    List<ActivityTagResponseDto> selectActivityTypeTagByLabelAndType(@Param("label") String label, @Param("type") Integer type);

    @Select("SELECT * FROM activity_type_tag_relation WHERE type_id = #{typeId} AND tag_id = #{tagId}")
    ActivityTypeTagRelation selectRelationByTypeIdAndTagId(@Param("typeId") Integer typeId, @Param("tagId") Integer tagId);

    @Select("SELECT a.tag_id FROM activity_type_tag_relation a LEFT JOIN activity_tag b ON a.tag_id=b.id WHERE a.type_id = #{typeId} AND b.level=1")
    List<Integer> selectFirstTagIdsByTypeId(Integer typeId);

    @Select("SELECT a.tag_id FROM activity_type_tag_relation a LEFT JOIN activity_tag b ON a.tag_id=b.id WHERE a.type_id = #{typeId} AND b.level=2")
    List<Integer> selectSecondTagIdsByTypeId(@Param("typeId") Integer typeId);

}