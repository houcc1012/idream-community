package com.idream.service;

import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.activity.*;

import java.util.List;

/**
 * @Description : 邻里动态服务类
 * @Created by xiaogang on 2018/5/2.
 */

public interface AppMyFansService {
    //是否认证过社区
    boolean getWetherAuthenticated(int authUserId);

    /**
     * 新增关注
     *
     * @param param
     *
     * @return 用户id
     */
    Integer addMyNotice(JSONPublicParam<AppUserIdParam> param);

    //取消关注
    void deleteMyNotice(JSONPublicParam<AppUserIdParam> param);

    //是否关注过该用户
    Boolean getWhetherAttended(int authUserId, int userId);

    //粉丝列表
    PagesDto<AppFansInfoDto> getMyFansList(AppSearchFansInfoParam param);

    //新进粉丝
    PagesDto<AppFansInfoDto> getMyNewFansList(AppSearchFansInfoParam param);

    void updateMyNewFansList(JSONPublicParam<UpdateMyNewFansFlagParams> param);

    //我关注的
    PagesDto<AppFansInfoDto> getMyAttendList(AppMyAttendParam param);

    //获取社区信息
    List<AppCommunityInfoDto> getMyCommunity(int authUserId);

    //粉丝明细
    PagesDto<AdminFansInfoDto> getFansList(AdminSearchFansInfoParam adminSearchFansInfoParam);
}
