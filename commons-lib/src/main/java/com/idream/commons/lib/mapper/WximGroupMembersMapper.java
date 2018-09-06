package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.app.MyChatDto;
import com.idream.commons.lib.model.WximGroupMembers;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface WximGroupMembersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WximGroupMembers record);

    int insertSelective(WximGroupMembers record);

    WximGroupMembers selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WximGroupMembers record);

    int updateByPrimaryKey(WximGroupMembers record);

    //根据群组id和当前用户id查询
    @Select("select * from wxim_group_members where group_id = #{tid} and user_id = #{userId}")
    WximGroupMembers selectByTidAndUserId(@Param("tid") String tid, @Param("userId") Integer userId);

    //根据群组id和群主查询
    @Select("select * from wxim_group_members where group_id = #{tid} and group_identity = 1")
    WximGroupMembers selectOwnerAccidByTid(@Param("tid") String tid);

    @Select("select * from wxim_group_members where user_id = #{userId}")
    WximGroupMembers selectByUserId(@Param("userId") Integer userId);

    //根据用户accid和tid删除
    @Delete("delete from wxim_group_members where acc_id = #{accid} and group_id = #{tid}")
    int deleteByAccidAndTid(@Param("accid") String accid, @Param("tid") String tid);

    //根据tid删除
    @Delete("delete from wxim_group_members where group_id = #{tid}")
    int deleteByTid(@Param("tid") String tid);

    //通讯录 我的趣聊
    List<MyChatDto> getMyChatByUserId(@Param("userId") Integer userId);

    //通过userId查询已参加的群组id
    @Select("SELECT count(*) FROM wxim_group_members WHERE user_id = #{userId}")
    Integer selectCountJoinedGroupByUserId(@Param("userId") Integer userId);

}