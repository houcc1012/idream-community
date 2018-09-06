package com.idream.commons.lib.dto.rabbitmq;

/**
 * @author hejiang
 */
public class AwardAsyncDto {

    // 1- 积分抽奖；2-现场开奖
    private int type;

    // 异步插入奖券数据参数
    private Object obj;

    public AwardAsyncDto() {
    }

    public AwardAsyncDto(int type, Object obj) {
        this.type = type;
        this.obj = obj;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
