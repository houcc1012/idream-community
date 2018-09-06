package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.UserNoticeInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserNoticeInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserNoticeInfo record);

    int insertSelective(UserNoticeInfo record);

    UserNoticeInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserNoticeInfo record);

    int updateByPrimaryKey(UserNoticeInfo record);

    // 根据用户ID和类型查询当前用户的通知消息ID最大值
    @Select("SELECT IFNULL(MAX(system_notice_id), 0) systemNoticeId FROM user_notice_info WHERE receive_id = #{userId} and type = #{type}")
    int selectMaxNoticeIdByUserId(@Param("userId") Integer userId, @Param("type") Byte type);

    void insertBatchByNoticeIdAndUserId(@Param("systemNoticeId") int systemNoticeId, @Param("userId") int userId);

    @Select("SELECT COUNT(*) FROM user_notice_info a WHERE a.type=1 AND a.`status`=1 AND a.receive_id=#{userId}")
    int selectUnReadByUserId(@Param("userId") Integer userId);
}