package com.idream.service;

import com.idream.commons.lib.dto.basic.AppVersionDto;

/**
 * @author hejiang
 */
public interface AppBasicService {

    /**
     * @Author: hejiang
     * @Description: 获取app版本号
     * @Date: 16:34 2018/4/27
     */
    AppVersionDto getAppVersion(Byte deviceType, String appVersion);
}
