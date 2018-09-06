package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.activity.AppImageParam;
import com.idream.commons.lib.model.CommunityLifeImage;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

public interface CommunityLifeImageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommunityLifeImage record);

    int insertSelective(CommunityLifeImage record);

    CommunityLifeImage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommunityLifeImage record);

    int updateByPrimaryKey(CommunityLifeImage record);

    List<AppImageParam> selectByLifeId(Integer lifeId);

    @Delete("DELETE FROM community_life_image WHERE life_id = #{lifeId}")
    void deleteByLifeId(@Param("lifeId") Integer lifeId);

}