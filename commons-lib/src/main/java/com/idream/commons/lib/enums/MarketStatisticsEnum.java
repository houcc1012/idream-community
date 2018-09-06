package com.idream.commons.lib.enums;

/**
 * @Author: houcc
 * @Date: 2018/6/6
 */
public enum MarketStatisticsEnum {
    LOTTERY(1, "抽奖次数"),
    COUPON(2, "兑奖次数"),
    OPEN(3, "开奖次数");


    private int code;
    private String value;

    MarketStatisticsEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }
}
