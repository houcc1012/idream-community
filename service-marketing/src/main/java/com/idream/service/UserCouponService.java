package com.idream.service;

import com.idream.commons.lib.model.CouponInfo;

/**
 * 用户奖券相关
 *
 * @author hejiang
 */
public interface UserCouponService {

    /**
     * 新增用户奖券获取/使用记录
     *
     * @param couponCode   奖券类型
     * @param type         记录类型 1获得,2使用
     * @param fromType     来源渠道 1-抽奖,2-积分，3-现场开奖
     * @param couponInfoId 中奖记录ID
     */
    int addUserCouponRecord(String couponCode, int type, int fromType, int couponInfoId);

    /**
     * 新增用户卡券收集信息
     *
     * @param couponFromType 卡券来源 1-抽奖,2-积分，3-现场开奖
     * @param couponInfo     卡券详情
     */
    void addUserCouponCollectionInfo(Byte couponFromType, CouponInfo couponInfo);
}
