/**
 *
 */
package com.idream.service;

import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.activity.ShareInfoDto;
import com.idream.commons.lib.model.ActivityInvitationRecord;

/**
 * @author xiaogang
 */
public interface ActivityInvitationRecordService {

    void addInvitationRecord(JSONPublicParam<ShareInfoDto> param);

    int saveUserScore(int activityId, int userId);

}
