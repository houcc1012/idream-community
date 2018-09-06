package com.idream.service;

import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.app.*;

import java.util.List;

/**
 * @Author: jeffery
 * @Date: 2018/7/17 18:45
 */
public interface AppChattingService {

    //附近活动趣聊
    List<NeighborChatDto> getNeighborChat(Integer authUserId, NeighborChatParams param);

    //通讯录 我的趣聊
    PagesDto<MyChatDto> getMyChatByUserId(int page, int rows, Integer authUserId);

    //添加朋友 推荐关注
    PagesDto<SuggestAttentionDto> getSuggestAttentionByUserId(SuggestAttentionParams suggestAttentionParams);

    //添加朋友 输入昵称或手机号模糊查询
    PagesDto<SuggestAttentionFriendDto> getFriendByNickNameOrPhone(Integer authUserId, AddFriendParams addFriendParams);

    PagesDto<SearchChatListDto> getChatList(Integer authUserId, SearchChatListParams param);

    PagesDto<SearchAttentionListDto> getAttentionList(Integer authUserId, SearchAttentionListParams param);

    //附近活动趣聊 不感兴趣
    int addDisLikeActivity(JSONPublicParam<DislikeActivityParams> param);
}
