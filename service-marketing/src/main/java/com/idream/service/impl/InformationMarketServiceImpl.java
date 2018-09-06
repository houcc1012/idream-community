package com.idream.service.impl;

import com.idream.commons.lib.dto.information.*;
import com.idream.commons.lib.enums.InformationRuleEnum;
import com.idream.commons.lib.mapper.*;
import com.idream.commons.lib.model.InformationCollectionRecord;
import com.idream.commons.lib.model.UserTag;
import com.idream.service.InformationMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InformationMarketServiceImpl implements InformationMarketService {

    @Autowired
    private CouponInfoMapper couponInfoMapper;
    @Autowired
    private CouponCollectionMapper couponCollectionMapper;
    @Autowired
    private InformationCollectionRecordMapper informationCollectionRecordMapper;
    @Autowired
    private UserTagMapper userTagMapper;
    @Autowired
    private UserAgeInfoMapper userAgeInfoMapper;

    @Override
    public List<InformationRuleDto> listInformationRulesByCouponId(Integer couponId) {
        return couponCollectionMapper.selectInformationRule(couponId);
    }

    @Override
    public void saveCouponInformations(Integer userId, InformationCouponParams couponInformations) {
        List<InformationRuleParams> infos = couponInformations.getInfos();

        Integer couponId = couponInformations.getCouponId();
        Date date = new Date();
        infos.forEach(i -> {
            couponCollectionMapper.updateCouponCollection(couponId, i.getInfoId(), i.getDetail());
            InformationCollectionRecord record = new InformationCollectionRecord();
            record.setType(InformationRuleEnum.CollectionType.COUPON.getCode());
            record.setRelationId(couponId);
            record.setUserId(userId);
            record.setDetail(i.getDetail());
            record.setInfoId(i.getInfoId());
            record.setUpdateTime(date);
            record.setCreateTime(date);
            record.setInfoName(i.getInfoName());
            informationCollectionRecordMapper.insertSelective(record);
        });
        List<InformationRuleParams> tag = infos.stream().filter(i -> "tag".equals(i.getInfoCode())).collect(Collectors.toList());
        if (!tag.isEmpty()) {
            InformationRuleParams informationRuleParams = tag.get(0);
            couponCollectionMapper.updateCouponCollection(couponId, informationRuleParams.getInfoId(), tag.stream().map(InformationRuleParams::getDetail).collect(Collectors.joining(",")));
        }
        couponInfoMapper.updateCompletedByCouponId(couponId);

    }

    @Override
    public InformationRuleDetail getInformationRuleDetail() {
        //活动服务相同的代码 com.idream.service.impl.InformationRuleServiceImpl.getInformationRuleDetail
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
