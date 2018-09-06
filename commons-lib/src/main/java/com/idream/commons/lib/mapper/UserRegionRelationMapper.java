package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.app.SuggestAttentionDto;
import com.idream.commons.lib.dto.marketing.UserRegionPoolInfo;
import com.idream.commons.lib.dto.user.AppUserRegionDto;
import com.idream.commons.lib.model.RegionInfo;
import com.idream.commons.lib.model.UserRegionRelation;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserRegionRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRegionRelation record);

    int insertSelective(UserRegionRelation record);

    UserRegionRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRegionRelation record);

    int updateByPrimaryKey(UserRegionRelation record);

    List<RegionInfo> selectRegionInfoListByUserId(@Param("userId") Integer userId);

    @Delete("DELETE FROM user_region_relation WHERE user_id=#{userId} AND region_id=#{regionId}")
    int deleteByUserIdAndRegionId(@Param("userId") Integer userId, @Param("regionId") Integer regionId);

    @Select("SELECT region_id FROM user_region_relation WHERE user_id = #{userId}")
    List<Integer> selectRegionIdByUserId(@Param("userId") Integer userId);

    //添加朋友 推荐关注
    List<SuggestAttentionDto> getSuggestAttentionByUserId(@Param("userId") Integer userId);

    @Select("select * FROM user_region_relation WHERE user_id=#{userId} AND region_id=#{regionId}")
    UserRegionRelation selectByUserIdAndRegionId(@Param("userId") Integer userId, @Param("regionId") Integer regionId);

    List<UserRegionPoolInfo> selectRegionByUserId(@Param("userId") Integer userId);

}