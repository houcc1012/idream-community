package com.idream.service;

import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.setting.OperateSensitiveWordParams;
import com.idream.commons.lib.dto.setting.SearchSensitiveWordsDto;
import com.idream.commons.lib.dto.setting.SearchSensitiveWordsParams;

/**
 * @author hejiang
 * @date 2018/8/28
 */
public interface SensitiveWordService {

    /**
     * 敏感字列表查询
     * @return
     */
    PagesDto<SearchSensitiveWordsDto> getSensitiveWords(SearchSensitiveWordsParams param);

    /**
     * 敏感字新增
     * @param param
     */
    void addSensitiveWord(JSONPublicParam<OperateSensitiveWordParams> param);

    /**
     * 敏感字修改
     * @param param
     */
    void updateSensitiveWord(JSONPublicParam<OperateSensitiveWordParams> param);

    /**
     * 敏感字删除
     * @param authUserId
     * @param id
     */
    void deleteSensitiveWord(Integer authUserId, Integer id);
}
