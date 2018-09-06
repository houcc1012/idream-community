package com.idream.service;

import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.marketing.*;
import com.idream.commons.lib.dto.region.UnityLotteryDto;
import com.idream.commons.lib.model.CouponCollection;
import com.idream.commons.lib.model.TagInfoTree;

import java.util.List;

/**
 * Description :抽奖服务类
 * Created by 肖刚 on 2018/4/11.
 */
public interface LotteryService {
    /**
     * @description: 保存开奖设置
     * @auther:xiaogang
     * @return:
     */
    int saveLotteryPool(LotteryPoolParams lotteryPoolParams);

    /**
     * @description: 保存奖品
     * @auther:xiaogang
     * @return:
     */
    int saveLottery(AddLotteryParams lotteryInfoDto);

    /**
     * @description: 修改奖品
     * @auther:xiaogang
     * @return:
     */
    int updateLotteryBean(AddLotteryParams lotteryInfoDto);

    /**
     * @description: 查询奖池列表
     * @auther:xiaogang
     * @return:
     */
    PagesDto<LotteryInfoDto> selectLotteryPoolList(LotterySearchParams params);

    /**
     * @description: 根据用户查询奖品
     * @auther:xiaogang
     * @return:
     */
    PagesDto<LotteryInfoDto> selectLotteryListByUser(LotterySearchParams params);

    /**
     * @description: 查询获奖记录
     * @auther:xiaogang
     * @return:
     */
    PagesDto<LotteryWinRecordDto> selectLotteryList(LotteryWinRecordSearchParams lotterySearchParams);

    /**
     * @description: 根据区ID查社区
     * @auther:xiaogang
     * @return:
     */
    List<CommunityDto> selectCommunityList(CommunityInfoParams code);

    /**
     * @description: 根据社区ID查询活动列表
     * @auther:xiaogang
     * @return:
     */
    List<ActivityInfoDto> selectActivityList(Integer communityid);

    /**
     * @description: 查询奖品
     * @auther:xiaogang
     * @return:
     */
    LotteryInfoDto selectLotteryById(Integer id);

    /**
     * @description: 查询奖券明细
     * @auther:xiaogang
     * @return:
     */
    List<CouponCollection> selectDetailByCouponId(Integer couponId);

    /**
     * @description: 是否需要填写信息
     * @auther:xiaogang
     * @return:
     */
    boolean wetherNeedWriteInfo(Integer poolId, Integer type);

    /**
     * @description: 获取信息项
     * @auther:xiaogang
     * @return:
     */
    List<InformationRuleDto> getInfoItems(Integer couponId);

    /**
     * @description: 保存个人信息
     * @auther:xiaogang
     * @return:
     */
    String savePersonalInfo(List<CouponCollectionParams> params);

    /**
     * @description: 查询附近有奖活动
     * @auther:xiaogang
     * @return:
     */
    List<WeChatLotteryInfoDto> selectLotteryPoolListByNear(LocationParams params);

    /**
     * @description: 获取标签
     * @auther:xiaogang
     * @return:
     */
    List<TagInfoTree> getAll();

    /**
     * @description: 查询开奖奖品
     * @auther:xiaogang
     * @return:
     */
    List<LotteryPoolDto> selectPoolByLotteryId(Integer lotteryId, Integer poolId);

    /**
     * @description: 修改奖品
     * @auther:xiaogang
     * @return:
     */
    int updatePool(LotteryPoolParams lotteryPoolDto);

    /**
     * @description: 查询开奖时间段
     * @auther:xiaogang
     * @return:
     */
    List<LotteryTimeDto> selectTime(Integer lotteryId);

    /**
     * @description: 查询开奖时间
     * @auther:xiaogang
     * @return:
     */
    LotteryTimeInfoDto selectTimeInfoByLotteryId(Integer lotteryId);

    /**
     * @Author: hejiang
     * @Description: 现场开奖
     * @Date: 16:02 2018/4/18
     */
    OpenLotteryAwardDto doOpenLotteryAward(JSONPublicParam<OpenLotteryAwardParams> params);

    List<InformationRuleValueDto> getInfoItemsValues(Integer couponId, int userId);

    LotteryActivityInfoDto selectActivityInfoByLotteryId(Integer userId, Integer lotteryId);

    List<UnityLotteryDto> selectLotteryByCommunityId(Integer communityId);

    void deleteLotteryById(int id);

}
