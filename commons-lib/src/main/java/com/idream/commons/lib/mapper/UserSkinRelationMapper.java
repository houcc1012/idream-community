package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.UserSkinRelation;
import org.apache.ibatis.annotations.Select;

public interface UserSkinRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserSkinRelation record);

    int insertSelective(UserSkinRelation record);

    UserSkinRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserSkinRelation record);

    int updateByPrimaryKey(UserSkinRelation record);

    @Select("select image_url from user_skin_relation where user_id = #{userId} ")
    String selectImageUrlByUserId(Integer userId);
}