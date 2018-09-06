package com.idream.service;

import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.activity.*;
import com.idream.commons.lib.dto.marketing.CommunityDto;
import com.idream.commons.lib.dto.marketing.*;
import com.idream.commons.lib.model.CommunityLifeType;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

/**
 * @Description : 邻里动态服务类
 * @Created by xiaogang on 2018/5/2.
 */

public interface AppMyNeighborService {
    //新增动态
    Integer addMyDynamic(JSONPublicParam<AppCommunityLifeParams> appCommunityLifeParams);

    //删除动态
    void deleteMyDynamic(JSONPublicParam<AppLifeIdParam> param);

    //获取我的动态列表
    PagesDto<AppMyCommunityLifeByTimeDto> getMyDynamicList(int authUserId, int page, int rows);

    //获取用户社区/好友/所有最新动态列表
    PagesDto<AppCommunityLifeDto> getMyNeighborDynamicList(int page, int rows, int userId);

    //获取动态详情
    AppCommunityLifeDetailDto getDynamicDetail(int id, int userId);

    //获取评论列表
    List<AppCommentDto> getDynamicCommentList(int id);

    //获取评论详情
    AppCommentDto getDynamicCommentDetail(int id);

    //查询点赞数
    String getThumbUpNum(int id);

    //查询评论数
    int getCommentNum(int id);

    //消息内点赞数
    String getMsgThumbUpNum(int userId);

    //消息内点赞列表
    List<AppThumbUpDetailDto> getMsgThumbUpList(int userId);

    /**
     * 用户点赞
     *
     * @param param
     *
     * @return 返回lifeId
     */
    Integer addMyLike(JSONPublicParam<AppLifeIdParam> param);

    //取消赞
    void deleteMyLike(JSONPublicParam<AppLifeIdParam> param);

    //发布动态类型
    List<AppLifeTypeDto> getLifeType();

    //根据用户获取动态列表
    PagesDto<AdminMyDynamicDto> getMyDynamicByUserId(int userId, int page, int rows);

    //获取用户社区/所有邻里动态
    PagesDto<AdminMyNeighborDynamicDto> getAllNeighborDynamicList(AdminMyNeighborDynamicParam param);

    //获取点赞明细
    PagesDto<AdminThumbUpDetailDto> getThumbUpDetailList(int id, int page, int rows);

    //屏蔽动态
    void updateStatus(int id, byte status);

    /**
     * 同步邻里圈动态到邻里圈时间线表
     *
     * @param lifeId       动态ID
     * @param userId       发布用户ID
     * @param privacyLevel 隐私级别
     * @param communityIds 隐私级别为指定社区是, 指定社区集合
     * @param date         发布时间
     */
    void insertCommunityLifeTimeLine(Integer lifeId, Integer userId, Byte privacyLevel, List<Integer> communityIds, Date date);

    //查询小区
    List<CommunityDto> selectSmallCommunityByRegionId(CommunityInfoParams code);
}
