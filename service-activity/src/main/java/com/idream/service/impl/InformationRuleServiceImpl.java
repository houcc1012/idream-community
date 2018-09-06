/**
 *
 */
package com.idream.service.impl;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.information.*;
import com.idream.commons.lib.enums.InformationRuleEnum;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.mapper.*;
import com.idream.commons.lib.model.InformationCollectionRecord;
import com.idream.commons.lib.model.InformationRule;
import com.idream.commons.lib.model.UserTag;
import com.idream.service.InformationRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author charles
 */
@Service
public class InformationRuleServiceImpl implements InformationRuleService {

    @Autowired
    private InformationRuleMapper informationRuleMapper;
    @Autowired
    private ActivityInformationRelationMapper activityInformationRelationMapper;
    @Autowired
    private UserAgeInfoMapper userAgeInfoMapper;
    @Autowired
    private UserTagMapper userTagMapper;
    @Autowired
    private InformationCollectionRecordMapper informationCollectionRecordMapper;

    @Override
    public List<InformationRuleDto> listInformationRules(Integer activityId) {
        return activityInformationRelationMapper.selectInformationRuleList(activityId);
    }

    @Override
    public InformationRuleDetail getInformationRuleDetail() {
        //营销服务有相同的代码需要维护 com.idream.service.impl.InformationMarketServiceImpl.getInformationRuleDetail
        List<InformationUserAgeDto> ageInfos = userAgeInfoMapper.selectAll();
        List<UserTag> rusult = userTagMapper.selectAllUserTag();

        List<InformationUserTagDto> userTags = rusult.stream().map(InformationUserTagDto::convertUserTag).collect(Collectors.toList());
        List<InformationUserTagDto> collect = userTags.stream().filter(i -> i.getPid().equals(0)).collect(Collectors.toList());
        collect.forEach(i -> {
            List<InformationUserTagDto> child = userTags.stream().filter(u -> u.getPid().equals(i.getId())).collect(Collectors.toList());
            i.setChildren(child);
        });

        InformationRuleDetail detail = new InformationRuleDetail();
        detail.setTag(collect);
        detail.setAge(ageInfos);
        return detail;
    }

    @Override
    public void saveActivityInformation(Integer userId, InformationActivityParams activityInformations) {
        Integer activityId = activityInformations.getActivityId();

        //判断信息搜集表是不是已经数据
        int recordCount = informationCollectionRecordMapper.getInformationCollectionRecordByUserIdAndActivityId(userId, activityId);
        if (recordCount == 0) {
            List<InformationRuleParams> infos = activityInformations.getInfos();
            Date date = new Date();
            infos.forEach(i -> {
                InformationCollectionRecord record = new InformationCollectionRecord();
                record.setDetail(i.getDetail());
                record.setInfoId(i.getInfoId());
                record.setCreateTime(date);
                record.setUpdateTime(date);
                record.setUserId(userId);
                record.setRelationId(activityId);
                record.setType(InformationRuleEnum.CollectionType.ACTIVITY.getCode());
                record.setInfoName(i.getInfoName());
                informationCollectionRecordMapper.insertSelective(record);
            });
        } else {
            throw new BusinessException(RetCodeConstants.ALREADY_HAVE, "报名信息已经搜集");
        }
    }

    @Override
    public List<InformationUserRecordDto> getLastUserRecord(Integer userId) {
        return informationCollectionRecordMapper.getLastUserRecord(userId);
    }

    @Override
    public AppInformationRuleDetail getAppInformationRuleDetail() {
        AppInformationRuleDetail detail = new AppInformationRuleDetail();

        List<InformationUserAgeDto> ageInfos = userAgeInfoMapper.selectAll();
        List<UserTag> tags = userTagMapper.selectAllUserTag();
        List<AppInformationUserTagDto> collect = tags.stream().map(AppInformationUserTagDto::convertUserTag).collect(Collectors.toList());
        detail.setAge(ageInfos);
        detail.setTag(collect);
        return detail;
    }

}
