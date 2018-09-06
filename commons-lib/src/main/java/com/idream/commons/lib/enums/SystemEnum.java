package com.idream.commons.lib.enums;

/**
 * @author hejiang
 */
public class SystemEnum {

    /**
     * 客户端渠道
     * 1-ios ;2-andriod; 3-微信小程序；4-admin web; 5- manager web ; 6-app h5; 9-未知渠道
     */
    public enum ClientChannelEnum {

        IOS(1),
        ANDROID(2),
        WECHAT(3),
        ADMIN_WEB(4),
        MANAGER_WEB(5),
        APP_H5(6),
        UNKNOW(9);

        private Byte code;

        private ClientChannelEnum(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    public enum TrueFalseCode {
        TRUE(1),
        FALSE(0);

        private Byte code;

        TrueFalseCode(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    /**
     * 短信发送模板
     * 1-小程序短信验证码
     * 2-管理端后台密码通知
     */
    public enum SmsSendType {
        VERIFY_CODE(1),
        USER_ACCOUNT_PASSWORD(2);

        private Byte code;

        SmsSendType(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    /**
     * socketType
     * 1-用户中奖广播
     */
    public enum SocketType {
        DRAW(1);

        private Byte code;

        SocketType(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    /**
     * 发送状态 1-发送中;2-成功;3-失败;4-丢弃
     */
    public enum SendMqMessageStatus {
        SENDING(1), SEND_SUCCESS(2), SEND_FAIL(3), SEND_DROP(4);

        private Byte code;

        SendMqMessageStatus(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    /**
     * mq业务类型
     * 1-网易IM;2-邻里圈;3-积分抽奖;4-现场开奖
     */
    public enum SendMqMessageBusinessType {
        WANGYI_IM(1), COMMUNITY_LIFR(2), INTEGRATION_AWARD(3), LOTTERY_DRAW(4);

        private Byte code;

        SendMqMessageBusinessType(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    /**
     * 1-用户头像;2-邻里生活;3-活动图片;4-banner;5-系统图片;6-奖品;7-投诉
     */
    public enum UploadImgType {
        USER_LOGO(1), COMMUNITY_LIFR(2), ACTIVITY(3), BANNER(4), SYSTEM(5), AWARD(6), COMPLAIN(7);

        private Byte code;

        UploadImgType(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    public enum UploadImgFileFolder {
        USER_LOGO("userlogo/"), COMMUNITY_LIFR("communityLife/"), ACTIVITY("activity/"),
        BANNER("banner/"), SYSTEM("system/"), AWARD("award/"), COMPLAIN("complain/"), MINI_QRCODE("miniQrcode/");

        private String code;

        UploadImgFileFolder(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    public enum BannerImageType {
        NOMARL(1), ACTIVITY_LIST(2), ACTIVITY_RECOMMEND(3);
        private byte code;

        BannerImageType(Integer code) {
            this.code = code.byteValue();
        }

        public byte getCode() {
            return code;
        }
    }

    public enum NoticeType {
        SYSTEM(1);
        private Byte type;

        NoticeType(Integer type) {
            this.type = type.byteValue();
        }

        public Byte getType() {
            return type;
        }
    }

    /**
     * 小程序二维码类型,1书屋,2推广团队
     */
    public enum MiniQrCodeType {
        BOOK(1), TEAM(2);
        private byte code;

        MiniQrCodeType(Integer code) {
            this.code = code.byteValue();
        }

        public byte getCode() {
            return code;
        }
    }


}
