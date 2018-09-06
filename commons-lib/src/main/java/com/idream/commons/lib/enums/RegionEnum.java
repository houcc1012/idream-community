package com.idream.commons.lib.enums;

public class RegionEnum {
    /**
     * 社区状态:1未开通,2开通
     */
    public enum RegionStatus {
        PREPARE(1), OPEN(2);
        private Byte code;

        private RegionStatus(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    /**
     * 社区级别:1级
     */
    public enum RegionLevel {
        FIRST(1);
        private Byte code;

        private RegionLevel(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }
}
