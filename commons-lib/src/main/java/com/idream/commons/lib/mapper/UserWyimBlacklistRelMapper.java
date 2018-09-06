package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.wangyi.QueryBlackListResponseParams;
import com.idream.commons.lib.model.UserWyimBlacklistRel;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserWyimBlacklistRelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserWyimBlacklistRel record);

    int insertSelective(UserWyimBlacklistRel record);

    UserWyimBlacklistRel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserWyimBlacklistRel record);

    int updateByPrimaryKey(UserWyimBlacklistRel record);

    //根据用户id和friendUserId删除
    @Delete("delete from user_wyim_blacklist_rel where user_id = #{userId} and blacklist_user_id = #{friendUserId}")
    int deleteByUserIdAndFriendUserId(@Param("userId") Integer userId, @Param("friendUserId") Integer friendUserId);

    List<QueryBlackListResponseParams> queryBlackListByUserId(Integer userId);

    @Select("select * from user_wyim_blacklist_rel where user_id = #{userId} and blacklist_user_id = #{friendUserId}")
    UserWyimBlacklistRel selectByUserIdAndFriendUserId(@Param("userId") Integer userId, @Param("friendUserId") Integer friendUserId);


}