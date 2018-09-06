package com.idream.rabbit;

import java.util.Date;

/**
 * socket stream 传输对象
 *
 * @author hejiang
 */
public class SocketStreamDto {

    //类型 1-用户中奖广播
    private Byte type;

    //内容
    private Object message;

    //时间
    private Date dateTime;

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
