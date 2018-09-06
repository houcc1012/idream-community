package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.activity.PublisherInfo;
import com.idream.commons.lib.dto.user.UserManagerDto;
import com.idream.commons.lib.model.RegionGroupInfo;
import com.idream.commons.lib.model.UserManager;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserManagerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserManager record);

    int insertSelective(UserManager record);

    UserManager selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserManager record);

    int updateByPrimaryKey(UserManager record);

    //根据ID修改状态
    @Update("update user_manager set status = #{status}, update_time = now() where id = #{id}")
    int updateStatusById(@Param("status") Byte status, @Param("id") Integer id);

    UserManagerDto selectUserManagerDtoByUserIdAndId(@Param("id") Integer id);

    @Select("select * from user_manager where user_id = #{userId}")
    UserManager selectByUserId(@Param("userId") Integer userId);

    @Select("select * from user_manager where user_id = #{userId} and id = #{id}")
    UserManager selectByUserIdAndId(@Param("userId") Integer userId, @Param("id") Integer id);

    List<UserManager> selectUserManagerByRegionId(@Param("regionIds") List<Integer> regionIds);

    @Select("select book_id from user_manager where `status` = 1 and user_id = #{userId} ")
    Integer getBookIdByUser(@Param("userId") int userId);

    //查询活动发布者书屋的名字
    PublisherInfo selectActivityPublisherBook(@Param("userId") Integer userId);

    @Select("select b.id, b.name from user_manager a, region_group_info b " +
            "where a.status = 1 and a.user_id = #{userId} and b.category=2 and a.book_id = b.id")
    List<RegionGroupInfo> selectBookInfoByUserId(Integer userId);
}