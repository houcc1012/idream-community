package com.idream.commons.lib.enums;

public class ActivityOperateRecordEnum {

    public enum ActivityOperateType {

        // 1取消操作 取消成功
        CANCEL_SUCCESS((byte) 1),
        // 2审核操作 审核失败
        CHECK_FAIL((byte) 2);

        private byte code;

        ActivityOperateType(byte code) {
            this.code = code;
        }

        public byte getCode() {
            return code;
        }
    }
}
