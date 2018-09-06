package com.idream.service;

import com.idream.commons.lib.dto.app.CommunityIndexDto;

/**
 * @Author: jeffery
 * @Date: 2018/6/5 19:41
 */
public interface CommunityInformationService {

    /**
     * 社区指数
     *
     * @param regionId
     */
    CommunityIndexDto getCommunityIndexById(Integer regionId);
}
