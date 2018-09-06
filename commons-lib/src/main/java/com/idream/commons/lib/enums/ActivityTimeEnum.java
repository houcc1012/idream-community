package com.idream.commons.lib.enums;

public class ActivityTimeEnum {

    public enum ActivityTimeRuleStatus {

        // 一次，长期活动
        SINGLE_TYPE(1),
        // 多次活动
        MUTIPLE_TYPE(2),
        // 周期性活动
        PERIOD_TYPE(3);

        private Integer code;

        private ActivityTimeRuleStatus(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }
    }
}
