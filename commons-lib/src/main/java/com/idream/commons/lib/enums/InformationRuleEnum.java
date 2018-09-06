package com.idream.commons.lib.enums;

public class InformationRuleEnum {
    public enum InformationRuleStatusEnum {

        // 姓名
        NAME(1),
        // 多次活动
        AGE(2),
        // 周期性活动
        TAG(3);

        private Integer code;

        private InformationRuleStatusEnum(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }
    }

    public enum CollectionType {
        ACTIVITY(1), COUPON(2);
        private byte code;

        CollectionType(Integer code) {
            this.code = code.byteValue();
        }

        public byte getCode() {
            return code;
        }
    }
}
