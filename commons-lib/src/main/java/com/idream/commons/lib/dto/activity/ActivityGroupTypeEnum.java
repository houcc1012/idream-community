package com.idream.commons.lib.dto.activity;

/**
 * @Auther: penghekai
 * @Date: 2018/6/26 16:25
 * @Description:
 */
public class ActivityGroupTypeEnum {
    /**
     * 组织类型,1官方,40社区,60小区,70书屋,100个人
     */
    public enum ActivityGroupType {

        OFFICIAL(1), REGION(40), GROUP(60), BOOKHOUSE(70), PERSON(100);

        private Byte code;

        ActivityGroupType(Integer code) {
            this.code = code.byteValue();
        }

        public Byte getCode() {
            return code;
        }
    }
}

