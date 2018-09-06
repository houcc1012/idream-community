package com.idream.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.idream.commons.db.redis.RedisCache;
import com.idream.commons.db.redis.RedisKeyConstants;
import com.idream.commons.lib.dto.rabbitmq.IntegrationAwardAsyncInsertDto;
import com.idream.commons.lib.dto.rabbitmq.OpenLotteryAwardAsyncInsertDto;
import com.idream.commons.lib.enums.MarketingEnum;
import com.idream.commons.lib.mapper.CouponInfoMapper;
import com.idream.commons.lib.mapper.IntegrationPoolMapper;
import com.idream.commons.lib.mapper.LotteryInfoMapper;
import com.idream.commons.lib.mapper.LotteryPoolMapper;
import com.idream.commons.lib.model.CouponInfo;
import com.idream.commons.lib.model.IntegrationPool;
import com.idream.commons.lib.model.LotteryInfo;
import com.idream.commons.lib.model.LotteryPool;
import com.idream.commons.lib.util.DateUtils;
import com.idream.commons.lib.util.RandomUtils;
import com.idream.service.AsyncTaskService;
import com.idream.service.UserCouponService;
import com.idream.service.UserScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author: houcc
 * @Date: 2018/6/30
 */
@Service
//@Async
public class AsyncTaskServiceImpl implements AsyncTaskService {
    private Logger logger = LoggerFactory.getLogger(getClass().getName());
    //未中奖券码
    public static final String NOT_LOTTERY_AWARD = "NOT_LOTTERY_AWARD";
    @Autowired
    private IntegrationPoolMapper integrationPoolMapper;
    @Autowired
    private CouponInfoMapper couponInfoMapper;
    @Resource
    private UserScoreService userScoreService;
    @Resource
    private UserCouponService userCouponService;
    @Resource
    private RedisCache redisCache;
    @Resource
    private LotteryPoolMapper lotteryPoolMapper;
    @Resource
    private LotteryInfoMapper lotteryInfoMapper;

    @Override
    public void doRecordIntegrationDraw(int userId, int integrationPoolId) {
        try {
            IntegrationPool lotteryAward = integrationPoolMapper.selectByPrimaryKey(integrationPoolId);
            Date date = new Date();
            //记录用户奖券信息
            CouponInfo couponInfo = new CouponInfo();
            couponInfo.setAwardId(lotteryAward.getId());
            couponInfo.setUserId(userId);
            couponInfo.setType(lotteryAward.getProperty());
            couponInfo.setAwardName(lotteryAward.getName());
            couponInfo.setInstructions(lotteryAward.getInstructions());
            couponInfo.setCouponValue(lotteryAward.getIntegrationValue());
            if (lotteryAward.getExpireDay() == null || lotteryAward.getExpireDay() == 0) {
                couponInfo.setExpireDate(lotteryAward.getEndTime());
            } else {
                couponInfo.setExpireDate(DateUtils.getAfterADate(date, lotteryAward.getExpireDay()));
            }
            //积分类型的奖券自动兑换
            if (MarketingEnum.AwardProperty.SCORE.getCode().equals(lotteryAward.getProperty())) {
                couponInfo.setStatus(MarketingEnum.ConvertStatus.CONVERT_SUCCESS.getCode());

                JSONObject integrationValue = JSON.parseObject(lotteryAward.getIntegrationValue());
                //新增用户积分
                userScoreService.updateUserScoreAndRecord(integrationValue.getInteger(MarketingEnum.AwardValueKey.SCORE.getCode()),
                        MarketingEnum.ScoreFromType.LOTTERY_DRAW.getCode(),
                        MarketingEnum.CouponRecordType.GET.getCode(), userId);
            } else {
                couponInfo.setStatus(MarketingEnum.ConvertStatus.NOT_CONVERT.getCode());
            }
            couponInfo.setCouponCode(RandomUtils.getLotteryCode());
            couponInfo.setDescription(lotteryAward.getDescription());
            couponInfo.setInfoAble(lotteryAward.getInfoEnable());
            couponInfo.setInfoCompleted(false);
            couponInfo.setFromType(MarketingEnum.CouponFromType.LOTTERY_DRAW.getCode());
            couponInfo.setCreateTime(date);
            couponInfo.setUpdateTime(date);
            couponInfo.setImage(lotteryAward.getImage());
            couponInfoMapper.insertSelective(couponInfo);

            //记录用户奖券获得记录
            userCouponService.addUserCouponRecord(couponInfo.getCouponCode(),
                    MarketingEnum.CouponRecordType.GET.getCode().intValue(),
                    couponInfo.getFromType().intValue(),
                    couponInfo.getId());

            //记录奖券关联信息
            userCouponService.addUserCouponCollectionInfo(MarketingEnum.CouponFromType.LOTTERY_DRAW.getCode(), couponInfo);

            //同步奖品池奖品剩余数量
            integrationPoolMapper.updateCountById(lotteryAward.getId(), -1);

        } catch (Exception e) {
            //回滚数量
            redisCache.hincrBy(RedisKeyConstants.INTEGRATION_AWARD_COUNT, String.valueOf(integrationPoolId), 1);
            logger.error("抽奖失败！，userId=" + JSON.toJSONString(userId) + ",奖池id:" + integrationPoolId, e);
        }
    }

    @Override
    public void doRecordIntegrationDraw(IntegrationAwardAsyncInsertDto awardSyncInsertDto) {
        doRecordIntegrationDraw(awardSyncInsertDto.getUserId(), awardSyncInsertDto.getIntegrationPoolId());
    }

    @Override
    public void doRecordOpenLotteryAward(int lotteryAwardId, int userId, String redisKey) {
        //记录信息
        try {
            LotteryPool lotteryPool = lotteryPoolMapper.selectByPrimaryKey(lotteryAwardId);
            Date date = new Date();
            //记录用户奖券信息
            CouponInfo couponInfo = new CouponInfo();
            couponInfo.setAwardId(lotteryPool.getId());
            couponInfo.setUserId(userId);
            couponInfo.setType(MarketingEnum.AwardProperty.GOODS.getCode());
            couponInfo.setAwardName(lotteryPool.getAwardName());
            couponInfo.setDescription(lotteryPool.getDescription());
            couponInfo.setInstructions(lotteryPool.getInstructions());

            if (lotteryPool.getExpireDay() == null || lotteryPool.getExpireDay() == 0) {
                // 查询开奖活动
                LotteryInfo lotteryInfo = lotteryInfoMapper.selectByPrimaryKey(lotteryPool.getLotteryId());
                if (lotteryInfo != null) {
                    couponInfo.setExpireDate(lotteryInfo.getEndTime());
                }
            } else {
                couponInfo.setExpireDate(DateUtils.getAfterADate(date, lotteryPool.getExpireDay()));
            }
            couponInfo.setStatus(MarketingEnum.ConvertStatus.NOT_CONVERT.getCode());
            couponInfo.setCouponCode(RandomUtils.getSceneOpenAwardCode());
            couponInfo.setInfoAble(lotteryPool.getInfoEnable());
            couponInfo.setInfoCompleted(false);
            couponInfo.setFromType(MarketingEnum.CouponFromType.SCENE_OPEN_AWARD.getCode());
            couponInfo.setCreateTime(date);
            couponInfo.setUpdateTime(date);
            couponInfo.setImage(lotteryPool.getImage());
            couponInfoMapper.insertSelective(couponInfo);

            //记录用户奖券获得记录
            userCouponService.addUserCouponRecord(couponInfo.getCouponCode(),
                    MarketingEnum.CouponRecordType.GET.getCode().intValue(),
                    couponInfo.getFromType().intValue(),
                    couponInfo.getId());

            //记录奖券关联信息
            userCouponService.addUserCouponCollectionInfo(MarketingEnum.CouponFromType.SCENE_OPEN_AWARD.getCode(), couponInfo);
            //同步奖品池奖品剩余数量
            lotteryPoolMapper.updateCountById(lotteryAwardId, -1);
        } catch (Exception e) {
            logger.error("现场开奖保存奖券信息失败！，lotteryAwardId:{}, userID:{}", lotteryAwardId, userId, e);
            //回滚数量
            redisCache.incr(redisKey, RedisKeyConstants.SCENE_OPEN_AWARD_COUNT);
        }
    }

    @Override
    public void doRecordOpenLotteryAward(OpenLotteryAwardAsyncInsertDto awardAsyncInsertDto) {
        doRecordOpenLotteryAward(awardAsyncInsertDto.getLotteryAwardId(), awardAsyncInsertDto.getUserId(), awardAsyncInsertDto.getRedisKey());
    }
}
