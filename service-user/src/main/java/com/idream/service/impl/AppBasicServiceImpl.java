package com.idream.service.impl;

import com.idream.commons.lib.dto.basic.AppVersionDto;
import com.idream.commons.lib.mapper.AppVersionMapper;
import com.idream.commons.lib.model.AppVersion;
import com.idream.service.AppBasicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author hejiang
 */
@Service
public class AppBasicServiceImpl implements AppBasicService {

    @Resource
    private AppVersionMapper appVersionMapper;

    @Override
    public AppVersionDto getAppVersion(Byte deviceType, String appVersion) {
        AppVersionDto data = new AppVersionDto();
        AppVersion version = appVersionMapper.selectByDeviceType(deviceType);
        data.setDownUrl(version.getAppUrl());
        data.setForce(version.getIsForce());
        data.setVersion(version.getVersionNo());
        try {
            //判断服务器的版本号和app客户端的版本号高低
            String isHighVersion = appVersion.compareTo(data.getVersion()) > 0 ? "2" : appVersion.compareTo(data.getVersion()) == 0 ? "1" : "0";
            data.setIsHighVersion(isHighVersion);
        } catch (Exception e) {
            data.setIsHighVersion("1");//默认相同
        }
        return data;
    }
}
