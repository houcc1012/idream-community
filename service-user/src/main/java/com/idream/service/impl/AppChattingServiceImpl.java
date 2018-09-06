package com.idream.service.impl;

import com.google.common.collect.Lists;
import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.app.*;
import com.idream.commons.lib.enums.UserEnum;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.mapper.*;
import com.idream.commons.lib.model.UserDislikeRecord;
import com.idream.commons.lib.model.UserInfo;
import com.idream.commons.lib.util.PageRowsUtils;
import com.idream.service.AppChattingService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: jeffery
 * @Date: 2018/7/17 18:45
 */
@Service
public class AppChattingServiceImpl implements AppChattingService {
    private final static Logger logger = LoggerFactory.getLogger(AppChattingServiceImpl.class);
    //推荐群聊总数
    private final Integer RECOMMEND_GROUP_CHAT = 6;

    @Autowired
    private WximGroupMapper wximGroupMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private WximGroupMembersMapper wximGroupMembersMapper;
    @Autowired
    private UserCommunityRelationMapper userCommunityRelationMapper;
    @Autowired
    private UserRegionRelationMapper userRegionRelationMapper;
    @Autowired
    private UserDislikeRecordMapper userDislikeRecordMapper;
    @Autowired
    private UserAttentionMapper userAttentionMapper;


    @Override
    public List<NeighborChatDto> getNeighborChat(Integer authUserId, NeighborChatParams param) {
        String cityCode = param.getCityCode();
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(authUserId);
        List<NeighborChatDto> list = Lists.newArrayList();
        if (userInfo == null) {
            throw new BusinessException(RetCodeConstants.ERROR_NO_USER, "该用户不存在");
        }
        //该用户为游客
        if (UserEnum.UserRoleEnum.VISITOR.getCode().equals(userInfo.getUserRole())) {
            list = wximGroupMapper.getNeighborChat(cityCode);
            //判断未登录(游客)的用户 推荐趣聊 是否为空
            if (CollectionUtils.isEmpty(list)) {
                return list;
            }
        } else {
            //该用户为登录用户
            //用户不感兴趣的群
            Integer num1 = userDislikeRecordMapper.selectCountDislikeActivityByUserId(authUserId);
            //已加入的群聊
            Integer num2 = wximGroupMembersMapper.selectCountJoinedGroupByUserId(authUserId);
            //用户应该被推荐多少个群聊
            Integer number = RECOMMEND_GROUP_CHAT - num1 - num2;
            if (number <= 0) {
                //判断已登录的用户 推荐趣聊 是否为空
                return Collections.EMPTY_LIST;
            }
            list = wximGroupMapper.getNeighborChatByLogin(cityCode, authUserId, number);
        }
        //判断群组成员用户图像 长度是否超过4个
        for (NeighborChatDto neighborChatDto : list) {
            for (int i = neighborChatDto.getImageList().size() - 1; i >= 0; i--) {
                if (neighborChatDto.getImageList().size() > 4) {
                    neighborChatDto.getImageList().remove(i);
                }
            }
        }
        return list;
    }

    @Override
    public PagesDto<MyChatDto> getMyChatByUserId(int page, int rows, Integer authUserId) {
        List<MyChatDto> list = wximGroupMembersMapper.getMyChatByUserId(authUserId);
        List<MyChatDto> myChatDtos = PageRowsUtils.getPageObj(list, page, rows);
        return new PagesDto(myChatDtos, list.size(), page, rows);
    }

    @Override
    public PagesDto<SuggestAttentionDto> getSuggestAttentionByUserId(SuggestAttentionParams suggestAttentionParams) {
        Integer authUserId = suggestAttentionParams.getAuthUserId();
        int page = suggestAttentionParams.getPage();
        int rows = suggestAttentionParams.getRows();
        List<SuggestAttentionDto> list = Lists.newArrayList();
        List<SuggestAttentionDto> sad = userCommunityRelationMapper.getSuggestAttentionByUserId(authUserId);
        List<SuggestAttentionDto> sa = userRegionRelationMapper.getSuggestAttentionByUserId(authUserId);
        List<SuggestAttentionDto> s = userInfoMapper.getSuggestAttentionByUserId(authUserId);
        //我关注的
        List<Integer> myAttentionOtherList = userAttentionMapper.getMyAttentionOtherList(authUserId);
        list.addAll(sad);
        list.addAll(sa);
        list.addAll(s);
        //去重(按userId)
        List<SuggestAttentionDto> collect = list.stream().collect(
                Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getUserId()))),
                        ArrayList::new));
        //去除 我已经关注的
        if (CollectionUtils.isNotEmpty(myAttentionOtherList)) {
            for (Integer i : myAttentionOtherList) {
                collect.removeIf(c -> c.getUserId().equals(i));
            }
        }
        //排序 过滤
        List<SuggestAttentionDto> data = collect.stream().sorted(Comparator.comparing(SuggestAttentionDto::getFromWhere)).filter(
                c -> !authUserId.equals(c.getUserId())).collect(Collectors.toList());
        //分页
        List<SuggestAttentionDto> sList = PageRowsUtils.getPageObj(data, page, rows);
        return new PagesDto(sList, data.size(), page, rows);
    }

    @Override
    public PagesDto<SuggestAttentionFriendDto> getFriendByNickNameOrPhone(Integer authUserId, AddFriendParams addFriendParams) {
        int page = addFriendParams.getPage();
        int rows = addFriendParams.getRows();
        List<SuggestAttentionFriendDto> list = userInfoMapper.getFriendByNickNameOrPhone(authUserId, addFriendParams.getNickNameOrPhone());
        List<SuggestAttentionFriendDto> data = PageRowsUtils.getPageObj(list, page, rows);
        return new PagesDto(data, list.size(), page, rows);
    }

    @Override
    public PagesDto<SearchChatListDto> getChatList(Integer authUserId, SearchChatListParams param) {
        int page = param.getPage();
        int rows = param.getRows();
        String groupName = param.getGroupName();
        List<SearchChatListDto> chatList = wximGroupMapper.getChatList(authUserId, groupName);
        List<SearchChatListDto> data = PageRowsUtils.getPageObj(chatList, page, rows);
        return new PagesDto(data, chatList.size(), page, rows);
    }

    @Override
    public PagesDto<SearchAttentionListDto> getAttentionList(Integer authUserId, SearchAttentionListParams param) {
        int page = param.getPage();
        int rows = param.getRows();
        String nickName = param.getNickName();
        List<SearchAttentionListDto> attentionList = userInfoMapper.getAttentionList(authUserId, nickName);
        List<SearchAttentionListDto> data = PageRowsUtils.getPageObj(attentionList, page, rows);
        return new PagesDto(data, attentionList.size(), page, rows);
    }

    @Override
    public int addDisLikeActivity(JSONPublicParam<DislikeActivityParams> param) {
        Integer userId = param.getAuthUserInfo().getUserId();
        Integer tid = param.getRequestParam().getTid();
        UserDislikeRecord u = userDislikeRecordMapper.selectDislikeActivityByUserIdAndTid(userId, tid);
        if (u != null) {
            throw new BusinessException(RetCodeConstants.DISLIKE_WANGYI_FAILED, "已经将该趣聊设置为不感兴趣...请勿重复操作!!!");
        }
        UserDislikeRecord userDisLikeRecord = new UserDislikeRecord();
        userDisLikeRecord.setUserId(userId);
        userDisLikeRecord.setBusinessId(tid);
        //业务类型,1活动,2活动精彩,3生活动态,4不感兴趣的群
        userDisLikeRecord.setBusinessType((byte) 4);
        userDisLikeRecord.setCreateTime(new Date());
        int i = userDislikeRecordMapper.insertSelective(userDisLikeRecord);
        return i;
    }


}
