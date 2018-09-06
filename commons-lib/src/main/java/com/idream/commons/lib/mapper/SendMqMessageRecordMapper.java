package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.SendMqMessageRecord;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface SendMqMessageRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SendMqMessageRecord record);

    int insertSelective(SendMqMessageRecord record);

    SendMqMessageRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SendMqMessageRecord record);

    int updateByPrimaryKey(SendMqMessageRecord record);

    //根据主键修改状态信息
    @Update("update send_mq_message_record set status = #{status} where id = #{id}")
    int updateStatusById(@Param("status") Byte status, @Param("id") Integer id);

    //根据msgId修改状态信息
    @Update("update send_mq_message_record set status = #{status} where msg_id = #{msgId}")
    int updateStatusByMsgId(@Param("status") Byte status, @Param("msgId") String msgId);

    //查询失败的信息
    @Select("select * from send_mq_message_record where status = 3")
    List<SendMqMessageRecord> selectUnsuccessfulMsg();

    //根据msgID查询消息记录
    @Select("select * from send_mq_message_record where msg_id = #{msgId}")
    SendMqMessageRecord selectByMsgId(@Param("msgId") String msgId);

    //修改重试次数
    @Update("update send_mq_message_record set retry_no = #{retryNo} where msg_id = #{msgId}")
    int addOneRetryNoByMsgId(@Param("msgId") String msgId, @Param("retryNo") int retryNo);
}