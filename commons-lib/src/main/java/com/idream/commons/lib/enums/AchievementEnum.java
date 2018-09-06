package com.idream.commons.lib.enums;

/**
 * 成就相关枚举
 *
 * @author charles
 */
public class AchievementEnum {

    public enum PoolType {
        TITLE(1), INTEGRATION(2);
        private byte code;

        PoolType(Integer code) {
            this.code = code.byteValue();
        }

        public byte getCode() {
            return code;
        }
    }

    public enum Status {
        UP(1), DOWN(2);
        private byte code;

        Status(Integer code) {
            this.code = code.byteValue();
        }

        public byte getCode() {
            return code;
        }
    }
}



