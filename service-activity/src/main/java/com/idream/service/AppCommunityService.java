package com.idream.service;

import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.PagesParam;
import com.idream.commons.lib.dto.activity.*;
import com.idream.commons.lib.dto.app.CommunityInfoResponseDto;

import java.util.List;

public interface AppCommunityService {
    /**
     * 用户认证社区
     *
     * @param userId
     * @param requestParam
     */
    void addUserCommunityRelation(Integer userId, AppAuthCommunityParams requestParam);

    /**
     * 查询用户认证社区的信息
     *
     * @param userId
     * @param param
     */
    PagesDto<AppAuthCommunityDto> listAuthCommunity(Integer userId, PagesParam param);

    /**
     * 开通城市列表
     */
    List<AppCityDto> listOpenCity();

    /**
     * 用户重新认证
     *
     * @param userId
     * @param requestParam
     */
    void updateAuthCommunity(Integer userId, AppAuthCommunityParams requestParam);

    /**
     * 删除用户
     *
     * @param userId
     * @param communityId
     */
    void deleteAuthCommunity(Integer userId, Integer communityId);

    /**
     * 通过书屋编码,查找绑定的社区
     *
     * @param param
     */
    List<CommunityInfoResponseDto> listCommunitiesByBookCode(AppAuthBookHouseParams param);

    /**
     * 根据社区id查询活动
     *
     * @param regionId
     */
    List<ActivityByRegionAndCityCodeResonDto> getActivityByRegionId(Integer regionId);

    /**
     * 修改开通城市
     */
    void updateOpenCity();

    /**
     * 修改热门社区
     */
    void updateHotRegion();
}
