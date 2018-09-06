package com.idream.service;

import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.activity.*;
import com.idream.commons.lib.dto.appactivity.AppActivityTypeResponseDto;
import com.idream.commons.lib.dto.user.ParticipateActivityDto;

import java.util.List;

/**
 * <p>Title: ActivityDisplayService.java</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: www.idream.com</p >
 *
 * @author penghekai
 * @version 1.0
 */
public interface ActivityDisplayService {
    /**
     * @Title: update
     * @Description: 修改活动
     * @param @param publishDto
     * @param @return    参数
     * @return ActivityTask    返回类型
     * @throws
     */
    //ActivityTask update(JSONPublicParam<PublishDto> publishDto);

    /**
     * @param @param  activityId
     * @param @return 参数
     *
     * @return PublishDto    返回类型
     */
    DisplayActivityResponseDto displayActivity(Integer activityId, Integer authUserId);

    PagesDto<ParticipateActivityDto> getUserActivityPartake(int authUserId, int page, int rows);

    //void removeUserJoinActivity(String activityIds, int authUserId);

    MiniActivityAssociationDto activityAssociation(Integer authUserId, Integer activityId);

    /**
     * 特色活动
     *
     * @param activityIds
     */
    List<EspeciallyActivityResponseDto> especiallyActivity(Integer authUserId, String activityIds);

    /**
     * 小程序发现页
     *
     * @param params
     */
    PagesDto<MiniActivityDiscoverypageResponseDto> miniDiscoverypage(DiscoveryPageRequestParams params);

    /**
     * 查询城市有活动的活动类型
     *
     * @param cityCode
     */
    List<AppActivityTypeResponseDto> getMiniActivityTypeByCityCode(String cityCode);

    /**
     * 小程序根据活动类型查询活动.
     *
     * @param params
     */
    PagesDto<MiniActivityDiscoverypageResponseDto> miniActivityByType(DiscoveryPageRequestParams params);
}
