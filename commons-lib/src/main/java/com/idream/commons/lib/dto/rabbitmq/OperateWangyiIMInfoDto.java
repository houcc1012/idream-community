package com.idream.commons.lib.dto.rabbitmq;

/**
 * 操作网易相关如建群,加人等相关操作
 *
 * @author hejiang
 */
public class OperateWangyiIMInfoDto {

    //类型 1-建群;2-退群, 3-给群加人;4-加好友;5-删除好友
    private Byte type;

    //内容
    private Object obj;

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
