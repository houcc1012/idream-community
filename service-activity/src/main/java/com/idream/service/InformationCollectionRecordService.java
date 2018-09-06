/**
 *
 */
package com.idream.service;

import java.util.List;

import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.activity.ActivityInfoCollectionDto;
import com.idream.commons.lib.dto.activity.InformationCollectionRecordDto;
import com.idream.commons.lib.model.InformationCollectionRecord;

/**
 * @author xiaogang
 */
public interface InformationCollectionRecordService {


    public InformationCollectionRecord getEntityByPrimaryKey(Integer id);

    public void updateEntity(InformationCollectionRecord entity);

    public void daleteEntity(InformationCollectionRecord entity);

    public void addEntity(InformationCollectionRecord entity);

    //根据活动Id和userId查询用户活动信息
    public List<InformationCollectionRecordDto> getUserActivityInfo(Integer activityId, Integer userId);

    public void addUserActivityInfo(JSONPublicParam<List<ActivityInfoCollectionDto>> params);
}
