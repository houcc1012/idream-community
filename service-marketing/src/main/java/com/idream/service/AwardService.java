package com.idream.service;

import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.admin.AwardDetailParams;
import com.idream.commons.lib.dto.marketing.*;

import java.util.List;

/**
 * Description :
 * Created by DELL2018-003 on 2018/4/12.
 */
public interface AwardService {
    /**
     * @description: 保存奖池
     */
    int saveAwardPool(AwardPoolParams awardPool);

    /**
     * @description: 修改奖池
     */
    int updateAwardBean(AwardPoolParams awardPool, int userId);

    /**
     * @description: 上下架
     */
    int updateStatus(Integer id, Integer status);

    /**
     * @description: 查询奖池
     */
    PagesDto<AdminAwardPoolDto> selectAwardList(MyCouponInfoParam myCouponInfoParam);

    /**
     * @description: app查询奖池
     */
    List<AwardPoolDto> selectAwardList(DrawAwardPoolParams params);

    /**
     * @description: 小程序查询奖池
     */
    List<AwardPoolDto> selectAwardListMiniProgram(DrawAwardPoolParams params);

    /**
     * @description: 兑换次数比较的平台奖品
     */
    List<AwardPoolDto> selectRecommendedAwardListMiniProgram(DrawAwardPoolParams params);

    /**
     * @description: APP兑换次数比较的平台奖品
     */
    List<AwardPoolDto> selectRecommendedAwardList(DrawAwardPoolParams params);

    /**
     * @description: 小程序离我最近的兑奖
     */
    List<AwardPoolDto> selectNearAwardListMiniProgram(DrawAwardPoolParams params);

    /**
     * app 离我最近的兑奖
     *
     * @param params
     */
    List<AwardPoolDto> selectNearAwardList(DrawAwardPoolParams params);

    /**
     * @description: 查询单条奖品
     */
    AdminAwardPoolDto selectAwardById(int id);

    /**
     * @description: 查询单条奖品以及用户的兑奖次数信息
     */
    AdminAwardPoolDto selectAwardByIdAndUserId(int id, int userId);

    //查询发现频道所需状态
    FoundStatusDto getFoundStatus(int authUserId);

    /**
     * 奖券兑换
     *
     * @param params
     */
    int doConvertCoupon(JSONPublicParam<ConvertCouponParams> params);

    /**
     * @description: 修改过期奖品状态
     */
    void updateOutDateStatus();

    /**
     * @description: 根据用户查询所有大社区
     * @auther:xiaogang
     */
    List<Integer> getRegionIdByUserId(int userId);

    /**
     * @description: 查询所有书屋ID
     * @auther:xiaogang
     */
    List<Integer> getAllBookId();

    //查询管理者我的兑奖记录
    PagesDto<MyAwardRecordDto> selectMyRecord(int userId, int type, String couponCode, int page, int rows);

    //管理者个人的积分兑奖
    JSONPublicDto updatePrizeStatusByCode(String code, int authUserId);

    void deleteAwardById(int id);


}
