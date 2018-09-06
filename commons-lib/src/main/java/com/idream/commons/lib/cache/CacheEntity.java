package com.idream.commons.lib.cache;

import java.io.Serializable;

/**
 * 缓存实体类
 */
public class CacheEntity implements Serializable {

    //值
    private Object value;

    //保存时间 单位 nanoTime
    private long timeStamp;

    //过期时间 单位秒
    private int expireTime;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(int expireTime) {
        this.expireTime = expireTime;
    }

    public CacheEntity(Object value, long timeStamp, int expireTime) {
        super();
        this.value = value;
        this.timeStamp = timeStamp;
        this.expireTime = expireTime;
    }
}