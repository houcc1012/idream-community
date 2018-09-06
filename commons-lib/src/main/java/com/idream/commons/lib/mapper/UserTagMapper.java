package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.user.UserTagDto;
import com.idream.commons.lib.model.TagInfoTree;
import com.idream.commons.lib.model.UserTag;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserTag record);

    int insertSelective(UserTag record);

    UserTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserTag record);

    int updateByPrimaryKey(UserTag record);

    //通过标签名查询用户标签库
    List<UserTag> selectUserTagListByLabel(@Param("label") String label);

    //通过空白数据查询所哟普用户标签
    List<UserTag> selectUserTagListAll();

    //根据用户id查询用户标签信息
    @Select("select a.tag_id as id, a.tag_name as lable from user_tag_relation a where a.user_id = #{userId}")
    List<UserTagDto> selectUserTagsByUserId(@Param("userId") Integer userId);

    //查询label字段在数据库出现的次数
    @Select("select count(*) from user_tag where label = #{label} and pid = #{pid} and status = 1")
    int selectLabelCount(@Param("label") String label, @Param("pid") Integer pid);

    List<TagInfoTree> getAllParent();

    List<TagInfoTree> getAllChildren();

    String getTagInfoById(Integer id);

    @Select("SELECT id FROM user_tag WHERE pid = #{pid}")
    List<Integer> selectAllSecondUserTagByPid(@Param("pid") Integer pid);

    List<UserTag> selectUserTagListByUserTagLabel(@Param("label") String label);

    UserTag selectAllSecondUserTagById(@Param("pid") Integer pid);

    @Select("SELECT id,label,pid FROM user_tag WHERE `status`=1 AND category=1")
    List<UserTag> selectAllUserTag();
}