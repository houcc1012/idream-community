package com.idream.service;

import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.PagesParam;
import com.idream.commons.lib.dto.achievement.*;

/**
 * @author charles
 */
public interface AdminAchievementService {
    /**
     * 查询成就列表
     *
     * @param param
     */
    PagesDto<AchievementPageDto> getAchievementPage(AdminAchievementPageParams param);

    /**
     * 成就上下架
     *
     * @param achievementId
     * @param status
     */
    void updateAchievementStatus(Integer achievementId, byte status);

    /**
     * 保存奖品
     *
     * @param requestParam
     */
    void saveAchievementPool(AdminAchievementAwardParams requestParam);

    /**
     * 成就用户明细
     *
     * @param params
     */
    PagesDto<AdminAchievementUserDto> listUsers(AdminAchievementUserParams params);
}
