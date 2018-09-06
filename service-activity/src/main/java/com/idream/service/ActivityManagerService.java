package com.idream.service;

import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.activity.*;
import com.idream.commons.lib.dto.activity.Manager.ActivityListRequestDto;
import com.idream.commons.lib.dto.wangyi.CreateGroupRequestParam;
import com.idream.commons.lib.model.RegionInfo;

import java.util.List;

/**
 * @Auther: penghekai
 * @Date: 2018/7/5 16:39
 * @Description:
 */
public interface ActivityManagerService {


    PagesDto<FindAllActivityResponseDto> getActivityList(Integer userId, ActivityListRequestDto param);

    Integer addActivity(Integer createId, AdminActivityPublishDto aapd);

    Integer updateActivity(JSONPublicParam<AdminActivityPublishDto> aapd);

    AdminActivityDisplayDto getByActivityId(Integer activityId);

    Integer updateActivityStatusSubmit(Integer activityId);

    void updateActivityStatusPutWay(Integer activityId);

    Integer deleteActivity(Integer activityId);

    String selectDistrictCodeByUserId(Integer userId);

    List<RegionInfo> selectRegionListByUserId(Integer userId);

    PagesDto<FindPublishedActivityDetailResponseDto> getPublishedActivityDetail(Integer userId, Integer page, Integer rows);

    Integer operateActivityCancel(AdminActivityOperateRecordRequestDto param);

    List<AdminActivityStatisticsResponseDto> getActivityStatisticsByActivityId(Integer activityId);

    void addCreateGroupChat(Integer userId, CreateGroupRequestParam param);
}
