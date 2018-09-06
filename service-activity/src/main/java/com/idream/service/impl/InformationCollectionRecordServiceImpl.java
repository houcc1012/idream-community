/**
 *
 */
package com.idream.service.impl;

import com.github.pagehelper.util.StringUtil;
import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.activity.ActivityInfoCollectionDto;
import com.idream.commons.lib.dto.activity.InformationCollectionRecordDto;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.mapper.InformationCollectionRecordMapper;
import com.idream.commons.lib.mapper.UserActivityRecordMapper;
import com.idream.commons.lib.model.ActivityInformationRelation;
import com.idream.commons.lib.model.InformationCollectionRecord;
import com.idream.commons.lib.model.UserActivityRecord;
import com.idream.service.InformationCollectionRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author xiaogang
 */
@Service
public class InformationCollectionRecordServiceImpl implements InformationCollectionRecordService {

    @Autowired
    private InformationCollectionRecordMapper informationCollectionRecordMapper;
    @Autowired
    private UserActivityRecordMapper userActivityRecordMapper;

    //3,代表标签
    private static final Integer TAGINFO_NUM = 3;
    //1，代表活动
    private static final byte Information_Collection_Record_ActivityType = 1;

    private static final Logger logger = LoggerFactory.getLogger(InformationCollectionRecordServiceImpl.class);

    @Override
    public InformationCollectionRecord getEntityByPrimaryKey(Integer id) {
        return informationCollectionRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public void addEntity(InformationCollectionRecord entity) {
        informationCollectionRecordMapper.insertSelective(entity);
    }

    @Override
    public void updateEntity(InformationCollectionRecord entity) {
        informationCollectionRecordMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public void daleteEntity(InformationCollectionRecord entity) {
        informationCollectionRecordMapper.deleteByPrimaryKey(entity.getId());
    }

    @Override
    public List<InformationCollectionRecordDto> getUserActivityInfo(Integer activityId, Integer userId) {
        return informationCollectionRecordMapper.getUserActivityInfo(activityId, userId);
    }

    @Override
    public void addUserActivityInfo(JSONPublicParam<List<ActivityInfoCollectionDto>> params) {

        Date date = new Date();
        Integer userId = params.getAuthUserInfo().getUserId(); //params.getRequestParam().get(0).getUserId();
        Integer activityId = params.getRequestParam().get(0).getActivityId();

        UserActivityRecord record = userActivityRecordMapper.getUserActivityRecord(activityId, userId);
        if (record != null && record.getExitStatus() == 1) {
            logger.error("保存报名信息失败，已经参加过活动....................userId: " + userId + " activityId: " + activityId);
            throw new BusinessException(RetCodeConstants.ERROR_ACTIVITY_TASK_RECORD, "保存报名信息，已经参加过活动！");
        }
        //保存用户报名信息
        for (int i = 0; i < params.getRequestParam().size(); i++) {
            Integer id = params.getRequestParam().get(i).getActivityId();
            Integer infoId = params.getRequestParam().get(i).getId();
            String detail = params.getRequestParam().get(i).getDetail();
            //Integer acinfoid = 0;
            InformationCollectionRecord icr = new InformationCollectionRecord();
            ActivityInformationRelation air = new ActivityInformationRelation();
            if (id != 0 || infoId != 0) {
                //查询活动信息收集中间表ID
                if (StringUtil.isEmpty(detail)) {
                    //表明是标签，保存标签ID
                    air.setInfoId(TAGINFO_NUM);
                    icr.setDetail(infoId.toString());

                    air.setActivityId(id);
                    // acinfoid = activityInformationRelationMapper.getKeyByActivityIdAndInfoId(air);
                    //活动1
                    icr.setType(Information_Collection_Record_ActivityType);
                    icr.setInfoId(TAGINFO_NUM);
                    icr.setUserId(userId);
                    icr.setCreateTime(date);
                    icr.setUpdateTime(date);
                    icr.setRelationId(id);
                    informationCollectionRecordMapper.insertSelective(icr);
                } else {
                    //非标签保存具体值
                    air.setInfoId(infoId);
                    icr.setDetail(detail);
                    air.setActivityId(id);
                    //活动1
                    icr.setType(Information_Collection_Record_ActivityType);
                    icr.setInfoId(infoId);
                    icr.setUserId(userId);
                    icr.setCreateTime(date);
                    icr.setUpdateTime(date);
                    icr.setRelationId(id);
                    informationCollectionRecordMapper.insertSelective(icr);
                }
            } else {
                logger.error("第" + (i + 1) + "条信息传入有误，未保存！");
                throw new BusinessException(RetCodeConstants.PARAMS_ERROR, "标签信息有误，报名失败！");
            }
        }
    }
}
