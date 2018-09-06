/**
 *
 */
package com.idream.service;

import com.idream.commons.lib.dto.information.*;
import com.idream.commons.lib.model.InformationRule;

import java.util.List;

/**
 * @author xiaogang
 */
public interface InformationRuleService {

    List<InformationRuleDto> listInformationRules(Integer activityId);

    InformationRuleDetail getInformationRuleDetail();

    void saveActivityInformation(Integer userId, InformationActivityParams activityInformations);

    List<InformationUserRecordDto> getLastUserRecord(Integer userId);

    AppInformationRuleDetail getAppInformationRuleDetail();
}
