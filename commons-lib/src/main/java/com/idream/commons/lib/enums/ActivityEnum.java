package com.idream.commons.lib.enums;

/**
 * 活动相关枚举
 */
public class ActivityEnum {
    /**
     * 0已发布数据,1创建完成,2提交审核,3审核通过4上架,5下架,6审核失败,7,有数据提交,8,有数据审核失败,9删除数据
     */
    public enum ActivityDBStatus {

        PUBLISHED(0), DRAFT(1), SUBMIT(2), PASS(3), PUTAWAY(4), UNSHELVE(5), FAIL(6), RESUBMIT(7), REFAIL(8), DELETE(9);

        private Byte code;

        ActivityDBStatus(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }

    }

    /**
     * 活动类型状态,1正常,2删除
     */
    public enum ActivityTypeStatus {
        NORMAL(1), DELETE(2);
        private byte code;

        ActivityTypeStatus(Integer code) {
            this.code = code.byteValue();
        }

        public byte getCode() {
            return code;
        }
    }

    /**
     * 活动标签状态,1正常,2删除
     */
    public enum ActivityTagStatus {
        //1正常
        NORMAL(1),
        //2删除
        DELETE(2);
        private byte code;

        ActivityTagStatus(Integer code) {
            this.code = code.byteValue();
        }

        public byte getCode() {
            return code;
        }
    }

    /**
     * activity_theme_type
     * 活动主题类型
     */
    public enum ActivityThemeTypeEnum {
        //默认主题
        DEFAULT(1),
        //自定义主题
        USER_DEFINED(2);

        private Byte code;

        private ActivityThemeTypeEnum(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    public enum UserJoinActivityStatus {
        //参加活动
        JOIN(1),
        //退出活动
        QUIT(2);

        private Byte code;

        private UserJoinActivityStatus(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }

    }

    /**
     * 参与级别,1全部,10省,20市,30区,40社区,60小区,,70书屋,100个人
     */
    public enum JoinDisplayType {

        ALL(1), PROVINCE(10), CITY(20), DISTINCT(30), REGION(40), GROUP(60), BOOKHOUSE(70), PERSON(100);

        private Byte code;

        JoinDisplayType(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    public enum OperateType {
        /**
         * 下架 编辑
         */
        DOWN(1, "取消"),
        /**
         * 上架 编辑
         */
        UP(2, "发布"),
        /**
         * 修改 编辑
         */
        MODIFY(3, "修改"),
        /**
         * 删除 编辑
         */
        REMOVE(4, "删除"),
        /**
         * 创建 编辑
         */
        CREATE(5, "创建"),
        /**
         * 通过 审核
         */
        SUCCESS(1, "通过"),
        /**
         * 失败 审核
         */
        FAIL(2, "不通过"),
        /**
         * 提交 审核
         */
        SUBMIT(3, "提交");
        private byte code;
        private String content;

        OperateType(Integer code, String content) {
            this.code = code.byteValue();
            this.content = content;
        }

        public byte getCode() {
            return code;
        }

        public String getContent() {
            return content;
        }
    }

    public enum OperateCategory {
        /**
         * 编辑
         */
        EDIT(1, "编辑"),
        /**
         * 审核
         */
        CHECK(2, "审核");
        private byte code;
        private String content;

        OperateCategory(Integer code, String content) {
            this.code = code.byteValue();
            this.content = content;
        }

        public byte getCode() {
            return code;
        }

        public String getContent() {
            return content;
        }
    }

    /**
     * 活动发布级别
     */
    public enum ActivityLevel{

        COUNTRY(1),//全国的
        CITY(2),//城市
        REGION(3),//社区
        COMMUNTIY(4);//小区

        private Byte code;

        ActivityLevel(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

}
