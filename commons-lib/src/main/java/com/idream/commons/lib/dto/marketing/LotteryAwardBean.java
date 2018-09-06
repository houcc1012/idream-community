package com.idream.commons.lib.dto.marketing;

import java.math.BigDecimal;

/**
 * 抽奖奖品实体类
 *
 * @author hejiang
 */
public class LotteryAwardBean {

    //id
    private int id;

    //奖品名称
    private String name;

    //奖品数量(缓存中不可信,需要每次去取)
    private int count;

    //中奖概率
    private BigDecimal prob;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BigDecimal getProb() {
        return prob;
    }

    public void setProb(BigDecimal prob) {
        this.prob = prob;
    }
}
