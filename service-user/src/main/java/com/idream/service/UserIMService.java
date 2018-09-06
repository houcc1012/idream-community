package com.idream.service;

import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.wangyi.*;

import java.util.List;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/14 13:30
 * @Description:
 */
public interface UserIMService {

    /**
     * 获取网易IM用户信息
     *
     * @param authUserId
     */
    WangYiUserInfo doGetIMUser(int authUserId);

    /**
     * 获取用户信息
     *
     * @param userId
     */
    List<IMUserInfoResponseDto> getIMUserInfo(int userId);

    /**
     * 修改用户信息
     *
     * @param param
     */
    WangYiCommonResponseDto updateIMUserInfo(UpdateIMUserInfoParams param);

    /**
     * 网易用户添加好友
     *
     * @param param
     */
    int addIMFriend(AddFriendRequestParams param);


    /**
     * 给好友加备注
     *
     * @param param
     */
    int updateFriendAlias(JSONPublicParam<UpdateFriendRequestParams> param);

    /**
     * 删除好友
     *
     * @param param
     */
    int deleteFriend(DeleteFriendRequestParam param);

    /**
     * 将好友加入黑名单
     *
     * @param param
     */
    int addUserToBlackList(JSONPublicParam<AddUserToBlackListRequestParam> param);

    /**
     * 将好友移除黑名单
     *
     * @param param
     */
    int removeUserFromBlackList(JSONPublicParam<RemoveUserFromBlackListRequestParam> param);

    /**
     * 查看用户的黑名单列表
     *
     * @param authUserId
     */
    List<QueryBlackListResponseParams> queryBlackListByUserId(int authUserId);


    /**
     * 根据用户的userID查询accid
     *
     * @param userId
     */
    QueryAccidByUserIdResponseDto queryAccidByUserId(Integer userId);
}
