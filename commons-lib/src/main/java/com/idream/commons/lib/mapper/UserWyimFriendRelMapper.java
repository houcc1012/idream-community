package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.UserWyimBlacklistRel;
import com.idream.commons.lib.model.UserWyimFriendRel;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserWyimFriendRelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserWyimFriendRel record);

    int insertSelective(UserWyimFriendRel record);

    UserWyimFriendRel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserWyimFriendRel record);

    int updateByPrimaryKey(UserWyimFriendRel record);

    //根据userId查询实体
    @Select("select * from user_wyim_friend_rel where user_id = #{userId} and friend_user_id = #{friendUserId}")
    UserWyimFriendRel selectByUserId(@Param("userId") Integer userId, @Param("friendUserId") Integer friendUserId);

    //根据用户userId和friend_user_id删除
    @Delete("delete from user_wyim_friend_rel where user_id = #{userId} and friend_user_id = #{friendUserId}")
    int deleteByUserIdAndFriendUserId(@Param("userId") Integer userId, @Param("friendUserId") Integer friendUserId);

    //将好友加入黑名单
    int addUserToBlackList(UserWyimBlacklistRel userWyimBlacklistRel);

}