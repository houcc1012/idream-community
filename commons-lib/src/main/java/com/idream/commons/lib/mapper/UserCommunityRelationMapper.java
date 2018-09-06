package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.activity.AdminCommunityAuthDto;
import com.idream.commons.lib.dto.activity.AdminCommunityAuthParams;
import com.idream.commons.lib.dto.activity.AppCommunityInfoDto;
import com.idream.commons.lib.dto.app.CommunityNameListResponseDto;
import com.idream.commons.lib.dto.app.SuggestAttentionDto;
import com.idream.commons.lib.dto.user.AppUserInfoDto;
import com.idream.commons.lib.dto.user.UserCommunityRelationDto;
import com.idream.commons.lib.model.CommunityInfo;
import com.idream.commons.lib.model.RegionGroupInfo;
import com.idream.commons.lib.model.RegionInfo;
import com.idream.commons.lib.model.UserCommunityRelation;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

public interface UserCommunityRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserCommunityRelation record);

    int insertSelective(UserCommunityRelation record);

    UserCommunityRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserCommunityRelation record);

    int updateByPrimaryKey(UserCommunityRelation record);

    List<AppUserInfoDto.CommunityDisplayInfo> selectAuthCommunityByUserId(Integer userId);

    //查询我的社区列表
    List<CommunityNameListResponseDto> selectMyCommunityList(@Param(value = "userId") Integer userId, @Param(value = "longitude") BigDecimal longitude, @Param(value = "latitude") BigDecimal latitude);

    UserCommunityRelation selectByUserIdAndCommunityId(@Param("userId") Integer userId, @Param("communityId") Integer communityId);

    @Select("SELECT COUNT(*) FROM user_community_relation WHERE community_id = #{communityId}")
    int countUserOfCommunityByCommunityId(@Param("communityId") Integer communityId);

    List<UserCommunityRelation> selectByUserId(Integer userId);

    //批量查询社区列表
    List<UserCommunityRelationDto> selectBatchCommunityList(@Param("userIds") String userIds, @Param("status") Integer status);

    /**
     * 查询认证的用户信息
     *
     * @param query
     */
    List<AdminCommunityAuthDto> selectAuthUserByQuery(AdminCommunityAuthParams query);

    //根据用户ID查询用户的小社区ID集合
    @Select("SELECT community_id FROM user_community_relation WHERE user_id = #{userId} and `status` < 3")
    List<Integer> selectIdByUserId(@Param("userId") Integer userId);

    /**
     * 根据用户id,查询对应的社区
     *
     * @param userId
     */
    List<RegionGroupInfo> selectCommunityByUserId(@Param("userId") Integer userId);

    List<RegionInfo> selectAuthRegionByUserId(Integer userId);

    @Select("SELECT a.community_id,\tb.`name` AS communityName FROM\tuser_community_relation a LEFT JOIN region_group_info b ON a.community_id = b.id AND b.category = 1 where user_id = #{userId}")
    List<AppCommunityInfoDto> getMyCommunity(int userId);

    @Select("SELECT community_id  FROM user_community_relation  where user_id = #{userId}")
    List<Integer> selectCommunityIdByUserId(@Param("userId") Integer userId);

    //添加朋友 推荐关注
    List<SuggestAttentionDto> getSuggestAttentionByUserId(@Param("userId") Integer userId);

}