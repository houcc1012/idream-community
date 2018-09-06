package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.activity.AdminUserInfoResponseDto;
import com.idream.commons.lib.dto.activity.AdminUserResponseDto;
import com.idream.commons.lib.dto.app.SearchAttentionListDto;
import com.idream.commons.lib.dto.app.SuggestAttentionDto;
import com.idream.commons.lib.dto.app.SuggestAttentionFriendDto;
import com.idream.commons.lib.dto.user.*;
import com.idream.commons.lib.model.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);


    //查询参与的活动列表
    List<ParticipateActivityDto> getUserActivityPartake(Integer userId);

    //根据unionID查询用户信息
    @Select("select a.* from user_info a, user_account_relation b  where b.union_id = #{unionId} and a.id = b.user_id")
    UserInfo selectUserInfoByUnionId(@Param("unionId") String unionId);

    //根据用户ID修改手机号码
    @Update("update user_info set phone = #{phone}, update_time = now() where id = #{userId}")
    Integer updatePhoneByUserId(@Param("userId") int userId, @Param("phone") String phone);

    //根据用户id和活动id集合退出活动
    int deleteUserJoinActivity(@Param("userId") int userId, @Param("activityIds") String activityIds);

    //根据用户Id查询用户信息
//    FindUserInfoDto getUserInfoByUserId(@Param("userId") int userId);

    //根据手机号查询
    @Select("SELECT * FROM `user_info` where phone=#{phone}")
    UserInfo selectByPhone(@Param("phone") String phone);

    //根据用户信息修改unionId
    @Update("update user_info set union_id = #{unionId} where id = #{id}")
    void updateUnionIdByUserId(@Param("unionId") String unionId, @Param("id") Integer id);

    //查询前台用户信息信息
    UserDetailDto selectUserDetailsById(Integer userId);

    //管理端查询企业前台用户
    List<AdminClientUserListDto> selectClientUserList(AdminClientUserListParams params);

    //查询所有前台用户
    List<UserDetailDto> selectUserList();

    //查询管理者列表
    List<AdminClientManageUserListDto> selectClientManageUserList(AdminClientManageUserListParams params);

    //根据Id修改角色名称
    @Update("update user_info set user_role = #{userRole} where id = #{id}")
    void updateUserRoleByUserId(@Param("userRole") Byte userRole, @Param("id") Integer id);

    //修改姓名和证件号
    @Update("update user_info set real_name = #{realName}, identity = #{identity} where id = #{userId}")
    int updateIdentityAndRealNameByUserId(@Param("identity") String identity, @Param("realName") String realName, @Param("userId") Integer userId);

    //根据机器码查询用户
    @Select("select * from user_info where machine_code = #{machineCode}")
    UserInfo selectUserInfoByMachineCode(@Param("machineCode") String machineCode);

    UserInfo getUserAndActivityRelation(@Param("userId") Integer userId, @Param("activityId") Integer activityId);

    //后台管理 用户明细
    List<AdminUserInfoResponseDto> selectUserInfo(@Param("phone") String phone, @Param("nickName") String nickName, @Param("regionId") Integer regionId);

    List<AdminUserResponseDto> selectPhoneNickName(@Param("phone") String phone, @Param("nickName") String nickName);

    @Select("select * from user_info where id = #{userId}")
    UserInfo selectUserInfoByUserId(@Param("userId") Integer userId);

    List<AppUserSearchDto> selectUserByNickName(@Param("userId") Integer userId, @Param("nickName") String nickName);

    //添加朋友 推荐关注
    List<SuggestAttentionDto> getSuggestAttentionByUserId(@Param("userId") Integer userId);

    //添加朋友 输入昵称或手机号模糊查询
    List<SuggestAttentionFriendDto> getFriendByNickNameOrPhone(@Param("userId") Integer authUserId, @Param("nickNameOrPhone") String nickNameOrPhones);

    //聊天搜索 联系人(关注的朋友)
    List<SearchAttentionListDto> getAttentionList(@Param("userId") Integer userId, @Param("nickName") String nickName);
}