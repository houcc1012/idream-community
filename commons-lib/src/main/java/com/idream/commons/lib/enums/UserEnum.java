package com.idream.commons.lib.enums;

/**
 * @author hejiang
 */
public class UserEnum {

    /**
     * user_role
     * 用户角色 1管理,2组织,3商户,4高级居民,5普通居民,7普通用户,9游客
     */
    public enum UserRoleEnum {

        MANAGEER(1), ORGANIZE(2), MERCHANT(3), OWNER(4), TENANT(5), ORDINARY_USER(7), VISITOR(9);

        private Byte code;

        private UserRoleEnum(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    /**
     * 用户类型 -1模拟用户,1-前台用户;2-管用户
     */
    public enum UserType {

        MOCK_USER(-1),MOBILE_USER(1), MANAGE_WEB_USER(2);

        private Byte code;

        UserType(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    /**
     * 用户积分获得来源: 1签到,2打卡,9未知
     */
    public enum IntegrationFromType {
        SIGN(1), CLOCK_ON(2), UN_KNOWN(9);

        private Integer code;

        IntegrationFromType(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }
    }

    /**
     * 用户积分记录类型 1获取,2使用
     */
    public enum IntegrationRecordType {
        ADD(1), SUBTRACT(2);

        private Byte code;

        IntegrationRecordType(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    /**
     * 管理端账号状态
     * 0-未开通，1-正常；2-冻结；
     */
    public enum AdminAccountStatus {

        NOT_OPEN(0), NORMAL(1), FORZEN(2);

        private Byte code;

        AdminAccountStatus(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    /**
     * 性别 1-男;2女
     */
    public enum UserGender {
        MAN(1), FEMALE(2);
        private Byte code;

        UserGender(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    /**
     * 用户第三方信息关联
     * 1-微信
     */
    public enum UserThirdInfoType {
        WEIXIN(1);

        private Byte code;

        UserThirdInfoType(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    /**
     * 用户第三方信息参数Code
     * WX_MINI_PROGRAM_OPENID -- 小程序OpenID
     * WX_APP_OPENID -- 微信APP openID
     */
    public enum UserThirdInfoParamCode {
        WX_MINI_PROGRAM_OPENID("wxMiniProgramOpenID"),
        WX_APP_OPENID("wxAppOpenID"),
        WX_UNIONID("wxUnionID");

        private String code;

        UserThirdInfoParamCode(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    /**
     * 用户投诉状态,1提交,2通过,3拒绝
     */
    public enum UserComplaintStatus {
        SUBMIT(1), PASS(2), REJECT(3);

        private Byte code;

        UserComplaintStatus(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    /**
     * 用户投诉处理类型,ban禁言
     */
    public enum UserComplaintHandle {
        HANDLE_BAN("ban");

        private String code;

        UserComplaintHandle(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    /**
     * 用户投诉状态,1正常,2取消
     */
    public enum UserComplaintHandleStatus {
        NORMAL(1), CANCEL(2);

        private Byte code;

        UserComplaintHandleStatus(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    /**
     * 用户投诉状态,1正常,2取消
     */
    public enum UserComplaintBussinessType {
        USER(1), GROUP(2);

        private Byte code;

        UserComplaintBussinessType(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    /**
     * 账号类型 1-手机号账号;2-普通账号;3-微信账号;4-机器码账户
     */
    public enum UserAccountType {
        PHONE(1), ORDINARY(2), WEICHAT(3), MACHINE_CODE(4);

        private Byte code;

        UserAccountType(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }


}
