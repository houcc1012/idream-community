package com.idream.service;

import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.integration.FindUserIntergrationInfoDto;
import com.idream.commons.lib.dto.integration.FindUserSignRankingListDto;
import com.idream.commons.lib.dto.integration.OldIntegrationConfigDto;

public interface UserIntegralService {

    /**
     * @Author: hejiang
     * @Description: 修改签到积分配置
     * @Date: 14:45 2018/4/12
     */
    int updateSignCofig(JSONPublicParam<OldIntegrationConfigDto> param);

    /**
     * @Author: hejiang
     * @Description: 查询签到积分配置
     * @Date: 14:45 2018/4/12
     */
    OldIntegrationConfigDto getSignCofig();

    /**
     * @Author: hejiang
     * @Description: 签到获取积分
     * @Date: 14:45 2018/4/12
     */
    void doSignGetIntegration(int authUserId);

    /**
     * @Author: hejiang
     * @Description: 查询用户签到积分详情页参数
     * @Date: 14:45 2018/4/12
     */
    JSONPublicDto<FindUserIntergrationInfoDto> getUserIntegrationInfo(int authUserId);

    /**
     * @param page
     * @param rows
     */
    PagesDto<FindUserSignRankingListDto> getUserSignRankingList(int page, int rows);
}
