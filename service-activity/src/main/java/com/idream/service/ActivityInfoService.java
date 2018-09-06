package com.idream.service;

import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.activity.ActivityCardDto;
import com.idream.commons.lib.dto.activity.ActivityInfoDto;
import com.idream.commons.lib.dto.activity.PromotionRecordParams;
import com.idream.commons.lib.dto.app.CommunityActivityListResponseDto;
import com.idream.commons.lib.dto.app.NeighborRegionParams;
import com.idream.commons.lib.dto.app.RegionActivityDto;
import com.idream.commons.lib.dto.app.RegionActivityParams;
import com.idream.commons.lib.dto.appactivity.AppActivitySearchDto;
import com.idream.commons.lib.dto.appactivity.AppActivitySearchParams;
import com.idream.commons.lib.model.ActivityInfo;

import java.util.List;

public interface ActivityInfoService {
    List<ActivityInfo> getActivityInfoByCommunityId(Integer communityId);

    ActivityInfoDto getEntityById(Integer id);

    /**
     * 检查是否可以报名
     *
     * @param userId
     * @param activityId
     */
    void checkJoinActivity(Integer userId, Integer activityId, Integer cityCode);
    //app(社区)查询社区活动
    //CommunityActivityListResponseDto selectCommunityActivity(Integer userId,CommunityActivityRequestDto communityActivityRequestDto);

    //通过社区id查询社区活动
    CommunityActivityListResponseDto selectCommunityActivityListByCommunityId(Integer userId, Integer communityId);

    //通过条件判断显示社区活动
    //CommunityActivityListResponseDto selectCommunityActivityListByCriteria(Integer userId,CommunityActivityRequestDto communityActivityRequestDto);

    //通过大社区id查询社区信息
    CommunityActivityListResponseDto selectCommunityActivityListByRegionId(Integer userId, NeighborRegionParams neighborRegionParams);

    //通过小区id查询搜索页面跳转查询社区活动
    //CommunityActivityListResponseDto selectSearchCommunityActivityListByCommunityId(Integer userId,Integer communityId);

    //通过大社区id 城市编码 查询活动列表
    List<RegionActivityDto> selectActivityInfoByRegionId(RegionActivityParams regionActivityParams);

    //用户打卡
    JSONPublicDto addUserTask(JSONPublicParam<ActivityCardDto> dto);

    PagesDto<AppActivitySearchDto> getActivityByActivityTitle(Integer userId, AppActivitySearchParams params);

    //小程序推广扫二维码添加记录
    int addPromotionTeam(JSONPublicParam<PromotionRecordParams> param);
}
