package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.activity.UpdateGroupInfo;
import com.idream.commons.lib.dto.app.NeighborChatDto;
import com.idream.commons.lib.dto.app.SearchChatListDto;
import com.idream.commons.lib.dto.wangyi.ActivityGroupInfoResponseDto;
import com.idream.commons.lib.dto.wangyi.GroupMuteListResponseDto;
import com.idream.commons.lib.dto.wangyi.JoinGroupListResposeDto;
import com.idream.commons.lib.model.WximGroup;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface WximGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WximGroup record);

    int insertSelective(WximGroup record);

    WximGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WximGroup record);

    int updateByPrimaryKey(WximGroup record);

    //根据群组id查询
    @Select("select * from wxim_group where tid = #{tid}")
    WximGroup selectByTid(@Param("tid") Integer tid);

    @Delete("delete from wxim_group where tid = #{tid}")
    int deleteGroupByGroupId(@Param("tid") String tid);

    @Select("select * from wxim_group where business_id = #{activityId} and business_type = 1 ")
    WximGroup selectByActivityId(@Param("activityId") Integer activityId);

    //群详情页查询
    ActivityGroupInfoResponseDto selectActivityGroupInfo(Integer tid);

    //去租详情列表
    List<GroupMuteListResponseDto> selectgroupMuteList(Integer tid);

    //获取用户参与的群组列表
    List<JoinGroupListResposeDto> joinGroupList(Integer userId);

    //根据tid修改群名称和封面
    @Update("update wxim_group set imgae_url = #{image}, group_name = #{groupName}, update_time = now() where tid = #{tid}")
    int updateGroupNameAndImageByTid(@Param("groupName") String groupName, @Param("image") String image, @Param("tid") String tid);

    //根据ID修改tid
    @Update("update wxim_group set tid = #{tid} where id = #{id}")
    int updateTidById(@Param("tid") String tid, @Param("id") Integer id);

    //app端 未登录用户 附近活动趣聊
    List<NeighborChatDto> getNeighborChat(@Param("cityCode") String cityCode);

    //app端 已登录用户 附近活动趣聊
    List<NeighborChatDto> getNeighborChatByLogin(@Param("cityCode") String cityCode, @Param("userId") Integer userId, @Param("number") Integer number);

    //聊天搜索 趣聊(已加入活动的趣聊)
    List<SearchChatListDto> getChatList(@Param("userId") Integer userId, @Param("groupName") String groupName);

    //更新群组名称,头像,标题,公告
    int updateGroupInfo(UpdateGroupInfo updateGroupInfo);
}