package com.idream.service;

import java.util.List;

import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.activity.*;
import com.idream.commons.lib.dto.admin.IntegrationConfigDto;
import com.idream.commons.lib.dto.admin.IntegrationConfigParams;
import com.idream.commons.lib.model.ActivityTag;

/**
 * 活动标签库接口
 *
 * @param
 *
 * @author zhanfeifei
 */
public interface ActivityTagService {

    //查询活动标签库
    PagesDto selectActivityTagListByActivityTag(String label, Integer type, int page, int rows);

    //新增活动标签
    int insertActivityTag(Integer authUserId, String label, Integer type);

    //编辑活动标签
    int updateActivityTag(Integer authUserId, String label, Integer type, Integer id);

    //关联二级标签
    int insertActivitySecondTag(SecondActivityTagDto secondActivityTagDto);

    //删除标签
    int updateActivityTagStatus(Integer id, Integer type);

    //通过一级标签id查询二级标签,并且对已关联一级标签的二级标签做区分
    ActivityTagLinkedDto selectSecondTagToFirstTag(Integer id);

    //通过空白数据查询所有活动标签
    PagesDto selectActivityTagAllList(int page, int rows);

    /**
     * 通过typeId,查询下面的所有二级标签
     *
     * @param typeId
     *
     * @return List<ActivityTag>
     */
    List<AppActivityTypeRelateTagResponseDto> listTagByTypeId(Integer typeId);

    PagesDto<ActivityTypeLibraryResponseDto> selectActivityTypeLibraryByExample(String type, int page, int rows);

    int insertActivityType(JSONPublicParam<ActivityTypeAddRequestDto> param);

    int updateActivityType(JSONPublicParam<ActivityUpdateTypeRequestDto> param);

    int deleteActivityTypeById(Integer typeId);

    List<ActivityTagTreeResponseDto> selectActivityTagsByTypeId(Integer typeId);

    List<ActivityTagTreeResponseDto> selectAllActivityTags();

    void insertActivityTypeRelateTag(ActivityTypeRelateTagRequestDto requestDto);

    int insertAllKindActivityTag(JSONPublicParam<ActivityTagAddRequestDto> param);

    int updateActivityTagById(JSONPublicParam<ActivityTagUpdateRequestDto> param);

    void deleteActivityTagById(Integer tagId);

    PagesDto selectActivityTagListByExample(String label, int page, int rows);

    IntegrationConfigDto selectActivityScore();

    void saveActivityScore(IntegrationConfigParams params);

}
