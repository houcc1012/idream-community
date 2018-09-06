package com.idream.commons.lib.enums;

/**
 * 事件状态
 *
 * @author charles
 */
public class EventEnum {
    public enum EventType {
        BASIS(1), SIGN(2), ACTIVITY(3), ATTENTION(4), COMMUNITY_AUTH(5), DYNAMIC(6), ACHIEVEMENT(7), INVITE(8), REAL_NAME(9), LIKE(10), TASK(11);
        private Integer code;

        EventType(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }
    }

    public enum EventCategory {
        ACHIEVEMENT(1), TASK(2);
        private byte code;

        EventCategory(Integer code) {
            this.code = code.byteValue();
        }

        public byte getCode() {
            return code;
        }
    }

    public enum EventStatus {
        UP(1), DOWN(2);
        private Byte code;

        EventStatus(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    public enum EventState {
        FOREVER(1), PERIOD(2);
        private Byte code;

        EventState(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }

    public enum EventCyleType {
        DAY(1), WEEK(2), MONTH(3);
        private byte code;

        EventCyleType(Integer code) {
            this.code = code.byteValue();
        }

        public byte getCode() {
            return code;
        }
    }

}
