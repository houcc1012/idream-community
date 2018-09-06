package com.idream.commons.lib.enums;

/**
 * @Description :
 * @Created by xiaogang on 2018/5/2.
 */

public class CommunityEnum {

    /**
     * 生活动态的状态
     */
    public enum CommunityLifeStatusEnum {
        //草稿
        DRAFT(1),
        //正常
        NORMAL(2),
        //屏蔽
        CLOSED(3),
        //删除
        DELETED(4);

        private Byte code;

        private CommunityLifeStatusEnum(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    /**
     * 隐私级别
     */
    public enum PrivacyLevelEnum {
        //同社区
        OPEN(1),
        //关注我的
        FANS(2),
        //仅自己
        CLOSE(3),
        //活动
        ACTIVITY(4),
        //选中的社区
        SELECTED_COMMUNITY(5);

        private Byte code;

        private PrivacyLevelEnum(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    /**
     * @description: 列表查询条件
     */
    public enum SearchTypeEnum {
        //为我的粉丝
        Myfans(1),
        //2为我的关注
        MyAttended(2),
        //3为互相关注
        AttendedEachother(3);

        private Byte code;

        private SearchTypeEnum(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }

    }

    /**
     * 社区认证状态
     * 1审核中,2审核通过,3不通过
     */
    public enum AuthCommunityStatus {
        CHECKING(1), PASS(2), REJECT(3);
        private Byte code;

        private AuthCommunityStatus(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    /**
     * 社区认证来源类型,1位置,2书屋
     */
    public enum AuthCommunityFromType {
        LOCATION(1), BOOKHOUSE(2);
        private Byte code;

        private AuthCommunityFromType(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    /**
     * 认证类型:1基础,2高级
     */
    public enum AuthCommunityAuthType {
        BASIS(1), SENIOR(2);
        private Byte code;

        private AuthCommunityAuthType(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    /**
     * 认证类型:1未开通,2开通
     */
    public enum CommunityStatus {
        PREPARE(1), OPEN(2);
        private Byte code;

        private CommunityStatus(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    /**
     * 动态源自类型,1动态,2活动
     */
    public enum CommunityLifeFromType {
        LIFE(1), ACTIVITY(2);
        private Byte code;

        private CommunityLifeFromType(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }


}
