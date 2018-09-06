package com.idream.commons.lib.enums;

/**
 * @Author: houcc
 * @Date: 2018/6/6
 */
public enum ActivityStatisticsEnum {
    BROWSE(1, "活动浏览量"),
    SIGN(2, "活动报名数"),
    TASK(3, "活动打卡数"),
    INVITE(4, "活动邀请数"),
    COMMENT(5, "活动留言数");


    private int code;
    private String value;

    ActivityStatisticsEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }
}
