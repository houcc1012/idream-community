package com.idream.service.impl;

import com.idream.commons.lib.enums.MarketingEnum;
import com.idream.commons.lib.enums.SystemEnum;
import com.idream.commons.lib.mapper.CouponCollectionMapper;
import com.idream.commons.lib.mapper.CouponRecordMapper;
import com.idream.commons.lib.mapper.InformationRuleMapper;
import com.idream.commons.lib.model.CouponCollection;
import com.idream.commons.lib.model.CouponInfo;
import com.idream.commons.lib.model.CouponRecord;
import com.idream.commons.lib.model.InformationRule;
import com.idream.service.UserCouponService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author hejiang
 */
@Service
public class UserCouponServiceImpl implements UserCouponService {

    @Resource
    private CouponRecordMapper couponRecordMapper;

    @Resource
    private CouponCollectionMapper couponCollectionMapper;

    @Resource
    private InformationRuleMapper informationRuleMapper;

    @Override
    public int addUserCouponRecord(String couponCode, int type, int fromType, int couponInfoId) {
        //记录用户奖券获得记录
        CouponRecord couponRecord = new CouponRecord();
        couponRecord.setCouponId(couponInfoId);
        couponRecord.setCouponCode(couponCode);
        couponRecord.setType(type);
        couponRecord.setFromType(fromType);
        couponRecord.setFromDescription("");
        Date date = new Date();
        couponRecord.setCreateTime(date);
        couponRecord.setUpdateTime(date);
        couponRecordMapper.insertSelective(couponRecord);
        return 0;
    }

    @Override
    public void addUserCouponCollectionInfo(Byte couponFromType, CouponInfo couponInfo) {
        if (SystemEnum.TrueFalseCode.FALSE.getCode().equals(couponInfo.getInfoAble())) {
            return;
        }
        List<InformationRule> relations = null;
        if (couponFromType.equals(MarketingEnum.CouponFromType.LOTTERY_DRAW.getCode())) {//抽奖
            relations = informationRuleMapper.selectByLotteryDrawRuleByAwardId(couponInfo.getAwardId());
        } else if (couponFromType.equals(MarketingEnum.CouponFromType.INTEGRATION.getCode())) {//积分兑换
            relations = informationRuleMapper.selectByIntegrationRuleByAwardId(couponInfo.getAwardId());
        } else if (couponFromType.equals(MarketingEnum.CouponFromType.SCENE_OPEN_AWARD.getCode())) {//现场开奖
            relations = informationRuleMapper.selectByRunALotteryRuleByAwardId(couponInfo.getAwardId());
        }
        for (InformationRule rule : relations) {
            CouponCollection couponCollection = new CouponCollection();
            couponCollection.setCouponId(couponInfo.getId());
            couponCollection.setCouponCode(couponInfo.getCouponCode());
            couponCollection.setInfoId(rule.getId());
            couponCollection.setInfoCode(rule.getCode());
            couponCollection.setInfoSort(rule.getSort().byteValue());
            couponCollection.setInfoName(rule.getName());
            couponCollection.setDetail("");
            Date date = new Date();
            couponCollection.setCreateTime(date);
            couponCollection.setUpdateTime(date);
            couponCollectionMapper.insertSelective(couponCollection);
        }
    }
}
