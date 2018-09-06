package com.idream.service;

import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.marketing.BookHouseDto;
import com.idream.commons.lib.dto.marketing.BookHouseParams;
import com.idream.commons.lib.dto.marketing.CouponInfoDto;
import com.idream.commons.lib.dto.marketing.CouponInfoParam;
import com.idream.commons.lib.dto.marketing.CouponRecordDto;
import com.idream.commons.lib.dto.marketing.IntegrationDrawDto;
import com.idream.commons.lib.dto.marketing.IntegrationDrawParams;
import com.idream.commons.lib.dto.marketing.IntegrationPoolDto;
import com.idream.commons.lib.dto.marketing.IntegrationPoolParams;
import com.idream.commons.lib.dto.marketing.MarketingRecordDto;
import com.idream.commons.lib.dto.marketing.RegionPollParams;
import com.idream.commons.lib.model.InformationRule;
import com.idream.commons.lib.model.IntegrationPool;
import com.idream.commons.lib.model.UserInfo;

import java.util.List;

/**
 * Description :抽奖服务类
 * Created by 肖刚 on 2018/4/11.
 */
public interface IntegrationService {
    /**
     * @param : null
     */
    void saveIntegrationPool(IntegrationPoolParams bean);

    /**
     * @param : null
     */
    int updateStatus(Integer authUserId, Integer id, Integer status);

    /**
     * @param : null
     */
    void updatePrizeStatus(String code, Integer authUserId);

    /**
     * @param : null
     */
    String selectPrizeStatus(String code);

    /**
     * @param : null
     */
    List<InformationRule> getAwardInformationRules();

    /**
     * @param : null
     */
    PagesDto<CouponInfoDto> selectCouponList(CouponInfoParam couponInfoParam);

    /**
     * @param : null
     */
    PagesDto<CouponInfoDto> selectCouponByUserId(int page, int rows, int userId);

    /**
     * @param : null
     */
    PagesDto<IntegrationPoolDto> selectIntegrationPoolList(int page, int rows, int userId, Integer type);

    /**
     * @param : null
     */
    PagesDto<IntegrationPool> selectMiniIntegrationPoolList(int page, int rows);

    /**
     * @param : null
     */
    UserInfo getUserPhone(int authUserId);

    /**
     * @param : null
     */
    CouponInfoDto selectOneByCouponId(int couponId);

    /**
     * @param : null
     */
    IntegrationPoolDto selectIntegrationById(int id);

    /**
     * @param : null
     */
    int updateIntegrationPool(IntegrationPoolParams bean);

    /**
     * @param : null
     */
    int updateIntegrationPoolByBookId(IntegrationPoolParams bean);

    /**
     * @Author: hejiang
     * @Description: 积分抽奖
     * @Date: 20:46 2018/4/16
     */
    IntegrationDrawDto doIntegrationDraw(JSONPublicParam<IntegrationDrawParams> params);

    /**
     * 查询24小时的中奖纪录
     */
    List<String> get24HoursWinningRecord();

    /**
     * @param : null
     */
    void updateOutDateStatus();

    /**
     * 获取抽奖社区奖池
     *
     * @param params
     */
    Integer getDrawBookId(RegionPollParams params);

    JSONPublicDto updatePrizeStatusByCode(String code, int type, int authUserId);

    void updateNeedScore(Integer score);

    Integer selectScore();

    List<BookHouseDto> selectBookHouse(BookHouseParams param);

    Integer selectIntegrationCountByUser(int authUserId);

    Integer selectAwardCostScore();

    List<MarketingRecordDto> listLastRecord();

    /**
     * @param : couponId
     */
    void deleteCouponById(int couponId);

    /**
     * @param : null
     */
    PagesDto<CouponInfoDto> selectNotDeletedCouponByUserId(int page, int rows, int userId);

    void deleteIntegrationById(int id);

    List<CouponRecordDto> selectCouponRecord();

    List<String> selectIntegrationImage();

    void updateStatusByBookId(int authUserId, Integer id, Byte status, Integer bookId);
}
