package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.UserDislikeRecord;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserDislikeRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserDislikeRecord record);

    int insertSelective(UserDislikeRecord record);

    UserDislikeRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserDislikeRecord record);

    int updateByPrimaryKey(UserDislikeRecord record);

    //用户不感兴趣的群聊
    @Select("SELECT count(*) FROM user_dislike_record WHERE user_id = #{userId} AND business_type = 4")
    Integer selectCountDislikeActivityByUserId(@Param("userId") Integer userId);

    @Select("SELECT * FROM user_dislike_record WHERE user_id = #{userId} AND business_id = #{tid} AND business_type = 3")
    UserDislikeRecord selectDislikeActivityByUserIdAndTid(@Param("userId") Integer userId, @Param("tid") Integer tid);
}