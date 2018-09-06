package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.activity.ActivityTagResponseDto;
import com.idream.commons.lib.dto.activity.ActivityTypeLibraryResponseDto;
import com.idream.commons.lib.dto.activity.AppActivityTypeRelateTagResponseDto;
import com.idream.commons.lib.model.ActivityTag;
import com.idream.commons.lib.model.TagInfoTree;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ActivityTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityTag record);

    int insertSelective(ActivityTag record);

    ActivityTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityTag record);

    int updateByPrimaryKey(ActivityTag record);

    List<TagInfoTree> getAllParent();

    List<TagInfoTree> getAllChildren();

    //通过前端传来的参数查询活动标签库中的二级标签
    List<ActivityTagResponseDto> selectActivityTagListByActivityTag(@Param("label") String label);

    //查询活动类型下所有的二级标签
    List<ActivityTag> selectAllSecondTag(Integer id);

    //查询所有的已经关联当前一级标签的二级标签(id为一级标签id)
    List<ActivityTag> selectSecondTagToFirstTag(Integer id);

    //通过空白数据查询所有活动标签
    List<ActivityTagResponseDto> selectActivityTagAllList();

    List<ActivityTagResponseDto> getActivityTagByActivityType(Integer typeId);

    @Select("SELECT a.id FROM activity_tag a INNER JOIN activity_tag_relation b on a.id=b.tag_id WHERE b.activity_id = #{activityId}")
    List<Integer> getTagsByActivityId(Integer activityId);

    @Select("select * from activity_tag where status =1")
    List<ActivityTag> selectTagsList();

    List<ActivityTypeLibraryResponseDto> selectActivityTypeLibrary(@Param("type") String type);

    List<ActivityTag> selectActivityTagsByTypeId(@Param("typeId") Integer typeId);

    @Select("SELECT * FROM activity_tag WHERE pid = #{pid} and status=1")
    List<ActivityTag> getActivityTagListByPid(@Param("pid") Integer pid);

    @Select("SELECT * FROM activity_tag WHERE level=1 and status=1")
    List<ActivityTag> selectAllFirstActivityTags();

    List<ActivityTag> selectActivityTagLibraryByExample(@Param("label") String label);

    @Select("SELECT * FROM activity_tag WHERE label= #{label} and status=1")
    ActivityTag selectActivityTagByLabel(@Param("label") String label);

    @Select("SELECT * FROM activity_tag WHERE pid = #{id} and status=1")
    List<ActivityTag> setSecondActivityTagsByPid(@Param("id") Integer id);

    @Select("SELECT * FROM activity_tag WHERE id = #{tagId} and status=1")
    ActivityTag selectActivityByTagId(@Param("tagId") Integer tagId);

    List<AppActivityTypeRelateTagResponseDto> getActivityTypeRelateTagList(@Param("typeId") Integer typeId);

    // 根据标签ID字符串查询标签集合
    @Select("SELECT * FROM activity_tag WHERE status = 1 and id in (${tagIdStr})")
    List<ActivityTag> selectTagsByTagIds(@Param("tagIdStr") String tagIdStr);
}