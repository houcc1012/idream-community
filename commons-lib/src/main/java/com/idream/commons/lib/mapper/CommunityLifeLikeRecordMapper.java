package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.activity.*;
import com.idream.commons.lib.dto.app.EgisLikeRecordDto;
import com.idream.commons.lib.dto.app.LifeLikeResponseDto;
import com.idream.commons.lib.dto.appactivity.AppActivityGroupInteractionListResponseDto;
import com.idream.commons.lib.dto.appactivity.AppActivityGroupRequestDto;
import com.idream.commons.lib.model.CommunityLifeLikeRecord;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CommunityLifeLikeRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommunityLifeLikeRecord record);

    int insertSelective(CommunityLifeLikeRecord record);

    CommunityLifeLikeRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommunityLifeLikeRecord record);

    int updateByPrimaryKey(CommunityLifeLikeRecord record);

    int countCommunityLifeLikeByUserId(Integer userId);

    int countByLifeId(Integer lifeId);

    List<AppNeighborInfoDto> selectLikeUsersInfo(Integer lifeId);

    int getCountAllByUserId(Integer userId);

    List<AppThumbUpDetailDto> getMsgThumbUpList(Integer userId);

    @Select("SELECT ID FROM community_life_like_record WHERE user_id=#{authUserId} and life_id=#{lifeId}")
    Integer selectIdByOtherId(@Param("authUserId") int authUserId, @Param("lifeId") int lifeId);

    List<AdminThumbUpDetailDto> getThumbUpDetailList(AdminThumbUpParam param);

    int getThumbUpCount(AdminThumbUpParam param);

    List<AppCommunityLifeDto> getGroupInteractionList(AppActivityGroupRequestDto params);

    CommunityLifeLikeRecord getLikeRecordByUserIdAndLifeId(@Param("userId") Integer userId, @Param("lifeId") Integer lifeId);

    List<AppActivityGroupInteractionListResponseDto> getMyActivityGroup(@Param("userId") Integer userId);

    @Delete("DELETE FROM community_life_like_record WHERE life_id = #{lifeId}")
    void deleteByLifeId(@Param("lifeId") Integer lifeId);

    @Select("SELECT * FROM community_life_like_record WHERE life_id=#{lifeId}")
    List<CommunityLifeLikeRecord> selectByLifeId(@Param("lifeId") Integer lifeId);

    //通过user_id插入egis_like_record表中一条数据
    @Insert("insert into egis_like_record(user_id,like_record_id,create_time,update_time) values (#{userId},#{likeRecordId},#{createTime},#{updateTime})")
    int insertEgisLikeRecordByUserId(EgisLikeRecordDto egisLikeRecordDto);

    //通过userId更新egis_like_record表中的likeRecordId
    @Update("update egis_like_record set like_record_id = #{likeRecordId} where user_id=#{userId}")
    int updateEgisLikeRecordByUserId(@Param("likeRecordId") Integer likeRecordId, @Param("userId") Integer userId);

    //通过user_id,likeRecordId查询用户已读点赞
    List<LifeLikeResponseDto> selectCommunityLifeLikeChecked(@Param("userId") Integer userId, @Param("likeRecordId") Integer likeRecordId, @Param("emptyId") Integer emptyId);

    //通过user_id,likeRecordId查询用户未读点赞
    List<LifeLikeResponseDto> selectCommunityLifeLikeUnchecked(@Param("userId") Integer userId, @Param("likeRecordId") Integer likeRecordId);

    //通过user_id,likeRecordId查询用户未读点赞数量
    @Select("SELECT count(*) AS unCheckedCount FROM community_life_like_record WHERE owner_id=#{userId} AND id > #{likeRecordId} AND user_id != #{userId}")
    Integer selectCountCommunityLifeLikeUnchecked(@Param("userId") Integer userId, @Param("likeRecordId") Integer likeRecordId);

    //通过user_id,likeRecordId查询用户未读信息
    @Select("SELECT * FROM community_life_like_record WHERE owner_id=#{userId} AND id > #{likeRecordId} AND user_id != #{userId} ORDER BY id DESC")
    List<CommunityLifeLikeRecord> getCommunityLifeLikeUnchecked(@Param("userId") Integer userId, @Param("likeRecordId") Integer likeRecordId);

    //通过user_id查询community_life_record表中所有这个用户未读信息
    @Select("SELECT * FROM community_life_like_record WHERE owner_id=#{userId} AND user_id != #{userId} ORDER BY id DESC")
    List<CommunityLifeLikeRecord> getCountCommunityLifeLikeUnchecked(@Param("userId") Integer userId);

    //通过userid查询该记录的点赞数
    @Select("SELECT count(*) FROM community_life_like_record WHERE owner_id=#{userId} AND user_id != #{userId}")
    Integer selectLikeCount(@Param("userId") Integer userId);

    //通过点赞人id查询用户icon图片
    @Select("SELECT image FROM user_info WHERE id=#{userId}")
    String selectUserImage(@Param("userId") Integer userId);

    @Select("SELECT * FROM community_life_like_record WHERE life_id=#{lifeId}")
    List<CommunityLifeLikeRecord> getCommunityLifeLikeRecordByLifeId(@Param("lifeId") Integer lifeId);

    @Select("SELECT * FROM community_life_like_record WHERE life_id=#{lifeId} AND id > #{likeRecordId}")
    List<CommunityLifeLikeRecord> getByUserIdAndLikeRecordId(@Param("lifeId") Integer lifeId, @Param("likeRecordId") Integer likeRecordId);

    @Select("SELECT b.image FROM community_life_like_record a LEFT JOIN user_info b ON a.user_id=b.id WHERE a.life_id=#{lifeId} AND a.owner_id=#{userId}")
    List<String> getThumberImage(@Param("lifeId") Integer lifeId, @Param("userId") Integer userId);

    //查询该社区中 点赞数
    @Select("SELECT count(*) FROM community_life_like_record a INNER JOIN community_life b ON b.id = a.life_id INNER JOIN activity_info c ON c.id = b.activity_id INNER JOIN activity_group_relation d ON d.activity_id = c.id AND group_type = 40 INNER JOIN region_info e ON e.id = d.group_id WHERE e.id = #{regionId}")
    Integer getCountLifeLikeByRegionId(@Param("regionId") Integer regionId);

    List<CommunityLifeLikeRecord> selectLikeListByUserIdAndActivity(@Param("userId") Integer userId, @Param("activityId") Integer activityId, @Param("recordId") Integer recordId);
}