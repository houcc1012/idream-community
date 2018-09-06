package com.idream.basic;

import org.springframework.stereotype.Service;

/**
 * 基础数据相关服务
 *
 * @author hejiang
 */
@Service
public class BasicService {

    private static final int AWARD_NO = 8;

    public static final String NULL_AWARD_NAME = "很遗憾，未挖到哦～";

//    @Resource
//    private RedisCache redisCache;
//
//    @Resource
//    private IntegrationPoolMapper integrationPoolMapper;
//
//    @Resource
//    private SystemConfigMapper systemConfigMapper;
//
//    @Resource
//    private AwardPoolMapper awardPoolMapper;
//
//    public boolean initLotteryAwardCache(){
//        logger.info("init lottery award start ...");
//        //查询数据库可供抽奖的奖品
//        List<IntegrationPool> integrationPools = integrationPoolMapper.selectLotteryAwrad();
//        //实物抽奖概率
//        BigDecimal entityProb = BigDecimal.ZERO;
//        for(IntegrationPool ip : integrationPools){
//            entityProb = entityProb.add(ip.getProbability());
//        }
//        if(CollectionUtils.isEmpty(integrationPools)){
//            logger.error("初始化抽奖奖品失败, 暂时未配置奖品!");
//        }
//        //获得默认配置的抽奖奖品
//        Integer lotteryAwardNo = systemConfigMapper.selectIntegerValueByConfigCode(SystemConfigCodeConstans.LOTTERY_AWARD_NO);
//        if(lotteryAwardNo == null || lotteryAwardNo == 0){
//            lotteryAwardNo = AWARD_NO;
//        }
//        BigDecimal fullProb = new BigDecimal("100");
//        if(integrationPools.size() < lotteryAwardNo && entityProb.compareTo(fullProb) < 0){
//            int no = lotteryAwardNo - integrationPools.size();
//            //计算空气奖概率
//            BigDecimal prob = fullProb.subtract(entityProb)
//                    .divide(new BigDecimal(no), 2, BigDecimal.ROUND_HALF_UP);
//            //不足的数量由空奖补足
//            for(int i = 0; i < no; i++){
//                IntegrationPool bean = new IntegrationPool();
//                bean.setName(BasicService.NULL_AWARD_NAME);
//                bean.setId(-lotteryAwardNo - i);
//                bean.setProbability(prob);
//                bean.setCount(9999);
//                bean.setType(MarketingEnum.CouponType.NULL.getCode());
//                bean.setDescription("未中奖");
//                bean.setInstructions("");
//                bean.setScore(0);
//                bean.setExpireDay(0);
//                bean.setStartTime(null);
//                bean.setEndTime(null);
//                bean.setInfoEnable(false);
//                bean.setStatus(MarketingEnum.AwardPublishStatus.UP_PUBLISH.getCode());
//                integrationPools.add(bean);
//            }
//        }
//        //存储redis
//        boolean result = redisCache.setString(RedisKeyConstants.LOTTERY_AWARD, JSON.toJSONString(integrationPools));
//        logger.info("init lottery award end!");
//        return result;
//    }
//
//    public void initConvertAward(){
//        logger.info("init convert award start ...");
//        List<AwardPool> awardPools = awardPoolMapper.selectAwardAll();
//        if(CollectionUtils.isNotEmpty(awardPools)){
//            for(AwardPool awardPool : awardPools){
//                String key = awardPool.getId() + "";
//                //存储奖品数量
//                redisCache.setnx(key, RedisKeyConstants.AWARD_PRE, awardPool.getCount().toString());
//                //设置失效时间
//                redisCache.pexpireAt(key, RedisKeyConstants.AWARD_PRE, awardPool.getEndTime().getTime());
//            }
//        }
//        logger.info("init convert award end!");
//    }
//
//
//    //获得抽奖奖品
//    public List<IntegrationPool> getLotteryAward(){
//        return redisCache.getList(RedisKeyConstants.LOTTERY_AWARD,IntegrationPool.class);
//    }


}
