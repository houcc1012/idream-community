package com.idream.service;

import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.user.*;
import com.idream.commons.lib.model.HelpType;

import java.util.List;

/**
 * @Description :
 * @Created by xiaogang on 2018/7/19.
 */

public interface AdminPlatFromSettingService {

    void saveHelpInfo(HelpInfoParams helpInfoParams);

    void updateHelpInfo(HelpInfoParams helpInfoParams);

    void deleteHelpInfoById(int id);

    PagesDto<HelpInfoDto> selectHelpInfoList(HelpInfoSearchParams params);

    HelpInfoDto selectHelpInfoById(int id);

    List<HelpTypeDto> selectHelpType();

    List<AdminOpenCityDto> selectOpenCity();

    void saveOpenCity(AdminOpenCityParams adminOpenCityParams);

    void deleteOpenCityByCityCode(String cityCode);

    List<AppHelpInfoDto> selectHelpTitleByTypeId(int typeId);

    String selectContentByHelpTitle(int id);

    List<MiniProgramOpenCityDto> selectMiniOpenCity();
}
