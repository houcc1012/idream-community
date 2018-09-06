package com.idream.commons.lib.enums;

/**
 * @author hejiang
 */
public class MarketingEnum {

    /**
     * 奖品上下架 1-上架；2-下架
     */
    public enum AwardPublishStatus {
        UP_PUBLISH(1), DOWN_PUBLISH(2);

        private Byte code;

        AwardPublishStatus(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    /**
     * 兑换状态,1未兑换,2兑换成功,3过期
     */
    public enum ConvertStatus {
        NOT_CONVERT(1), CONVERT_SUCCESS(2), EXPIRE(3);

        private Byte code;

        ConvertStatus(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    /**
     * 奖券获得渠道,1-抽奖,2-积分，3-现场开奖
     */
    public enum CouponFromType {
        LOTTERY_DRAW(1), INTEGRATION(2), SCENE_OPEN_AWARD(3);

        private Byte code;

        CouponFromType(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    /**
     * 奖券记录途径:1获得,2使用
     */
    public enum CouponRecordType {
        GET(1), USE(2);

        private Byte code;

        CouponRecordType(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    /**
     * 积分获得类型,1签到,2打卡,3抽奖,4兑换,5成就，6分享
     */
    public enum ScoreFromType {
        SIGN(1), CLOCK_ON(2), LOTTERY_DRAW(3), CONVERT(4), ACHIEVEMENT(5), SHARE(6);

        private Byte code;

        ScoreFromType(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    /**
     * 抽奖类型 1-积分抽奖；2-免费抽奖
     */
    public enum IntegrationAwardType {
        SCORE(1), FREE(2);

        private Byte code;

        IntegrationAwardType(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    /**
     * 抽奖活动状态 0-未开始;1开启,2进行中，3结束
     */
    public enum LotteryActivityStatus {
        NOT_START(0), OPEN(1), RUNING(2), CLOSE(3);

        private Byte code;

        LotteryActivityStatus(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    /*
       * activity_status
       * 开奖状态
       */
    public enum LotteryStatusEnum {
        //未开始
        WILL(1),
        //进行中
        ING(2),
        //已结束
        ENDED(3);

        private Integer code;

        private LotteryStatusEnum(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }
    }

    /*
     * activity_status
     * 开奖状态
     */
    public enum AdminLotteryStatusEnum {
        //进行中
        ING(1),
        //未开始
        WILL(2),
        //已结束
        ENDED(3);

        private Integer code;

        private AdminLotteryStatusEnum(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }
    }

    /**
     * @description: 概率
     */
    public enum ProbabilityEnum {
        //100%
        All(100);

        private Integer code;

        private ProbabilityEnum(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }
    }

    public enum CouponTypeEnum {

        COMMON(1),//通用

        REGION(2);//区域

        private Byte code;

        private CouponTypeEnum(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }

    }

    /**
     * 异步插入奖券信息类型 1-积分抽奖;2-现场开奖
     */
    public enum AynscInsertCouponType {

        INTEGRATION_AWARD(1), OPEN_DRAM_AWARD(2);

        private Integer code;

        private AynscInsertCouponType(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }

    }

    /**
     * 奖品属性;1实物；2满减；3折扣；4代金; 5积分
     */
    public enum AwardProperty {

        NULL(0), GOODS(1), FULL_CUT(2), DISCOUNT(3), REPLACE_CASH(4), SCORE(5);

        private Byte code;

        private AwardProperty(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }


    }

    /**
     * 奖品json key
     * full 满; cut 减; discount 折扣; cash 代金券; score 积分
     */
    public enum AwardValueKey {

        FULL("full"), CUT("cut"), DISCOUNT("discount"), CASH("cash"), SCORE("score");

        private String code;

        private AwardValueKey(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

    }

}
