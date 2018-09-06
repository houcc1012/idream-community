package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.app.EgisLikeRecordResponseDto;
import com.idream.commons.lib.model.EgisLikeRecord;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

public interface EgisLikeRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EgisLikeRecord record);

    int insertSelective(EgisLikeRecord record);

    EgisLikeRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EgisLikeRecord record);

    int updateByPrimaryKey(EgisLikeRecord record);

    //通过userId查询egis_like_record表中的like_record_id
    @Select("SELECT like_record_id likeRecordId,empty_id emptyId FROM egis_like_record WHERE user_id=#{userId}")
    EgisLikeRecordResponseDto selectLikeRecordId(@Param("userId") Integer userId);

    //通过userId修改emptyId为like_record_id的值,清空点赞
    @Update("UPDATE egis_like_record SET empty_id = #{emptyId},update_time = #{updateTime} WHERE user_id = #{userId}")
    int updateEmptyLike(@Param("userId") Integer userId, @Param("emptyId") Integer emptyId, @Param("updateTime") Date updateTime);

    @Select("SELECT * FROM egis_like_record WHERE user_id=#{userId}")
    EgisLikeRecord getByUserId(@Param("userId") Integer userId);

    int selectUnlikeCount(Integer userId);
}