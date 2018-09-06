package com.idream.commons.lib.util;

import com.google.common.collect.Lists;
import com.idream.commons.lib.dto.marketing.LotteryAwardBean;
import com.idream.commons.lib.model.IntegrationPool;
import org.apache.commons.collections4.CollectionUtils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 抽奖返回抽奖结果
 *
 * @author hejiang
 */
public class DrawLotteryUtils {

    /**
     * 抽奖奖品
     *
     * @param awardList
     */
    public static IntegrationPool drawGift(List<IntegrationPool> awardList) {
        List<IntegrationPool> awards = awardList.stream().filter(award -> award.getCount() > 0).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(awardList)) {
            // 计算概率总和
            BigDecimal sumRate = BigDecimal.ZERO;
            for (IntegrationPool award : awards) {
                BigDecimal prob = award.getProbability();
                sumRate = sumRate.add(prob);
            }
            List<BigDecimal> sortRateList = Lists.newArrayList();
            if (sumRate.compareTo(BigDecimal.ZERO) > 0) {
                BigDecimal rate = BigDecimal.ZERO;  //概率所占比例
                for (IntegrationPool award : awards) {
                    BigDecimal prob = award.getProbability();
                    rate = rate.add(prob);
                    // 构建一个比例区段组成的集合(避免概率和不为1)
                    sortRateList.add(rate.divide(sumRate, 1, BigDecimal.ROUND_HALF_UP));
                }
                // 随机生成一个随机数，并排序
                BigDecimal decimal = new BigDecimal(Math.random()).setScale(1, BigDecimal.ROUND_HALF_UP);
                sortRateList.add(decimal);
                Collections.sort(sortRateList);
                // 返回该随机数在比例集合中的索引
                int index = sortRateList.indexOf(decimal);
                return awards.get(index);
            }
        }
        return null;
    }

    /**
     * 现场开奖奖品
     *
     * @param awardList
     */
    public static LotteryAwardBean drawSenceOpenGift(List<LotteryAwardBean> awardList) {
        List<LotteryAwardBean> awards = awardList.stream().filter(award -> award.getCount() > 0).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(awardList)) {
            // 计算概率总和
            BigDecimal sumRate = BigDecimal.ZERO;
            for (LotteryAwardBean award : awards) {
                BigDecimal prob = award.getProb();
                sumRate = sumRate.add(prob);
            }
            List<BigDecimal> sortRateList = Lists.newArrayList();
            if (sumRate.compareTo(BigDecimal.ZERO) > 0) {
                BigDecimal rate = BigDecimal.ZERO;  //概率所占比例
                for (LotteryAwardBean award : awards) {
                    BigDecimal prob = award.getProb();
                    rate = rate.add(prob);
                    // 构建一个比例区段组成的集合(避免概率和不为1)
                    sortRateList.add(rate.divide(sumRate, 1, BigDecimal.ROUND_HALF_UP));
                }
                // 随机生成一个随机数，并排序
                BigDecimal decimal = new BigDecimal(Math.random()).setScale(1, BigDecimal.ROUND_HALF_UP);
                sortRateList.add(decimal);
                Collections.sort(sortRateList);
                // 返回该随机数在比例集合中的索引
                int index = sortRateList.indexOf(decimal);
                return awards.get(index);
            }
        }
        return null;
    }


//    public static void main(String[] args) {
//        IntegrationPool iphone = new IntegrationPool();
//        iphone.setId(101);
//        iphone.setName("苹果手机");
//        iphone.setProbability(new BigDecimal("50"));
//        iphone.setCount(1);
//
//        IntegrationPool thanks = new IntegrationPool();
//        thanks.setId(102);
//        thanks.setName("再接再厉");
//        thanks.setProbability(new BigDecimal("0.1"));
//        thanks.setCount(0);
//
//        IntegrationPool vip = new IntegrationPool();
//        vip.setId(103);
//        vip.setName("优酷会员");
//        vip.setProbability(new BigDecimal("0.4"));
//        vip.setCount(1);
//
//        List<IntegrationPool> list = new ArrayList<IntegrationPool>();
//        list.add(vip);
//        list.add(thanks);
//        list.add(iphone);
//        for(int i=0;i<10;i++){
//            IntegrationPool ip = drawGift(list);
//            System.out.println(JSON.toJSONString(ip));
//        }
//    }
}
