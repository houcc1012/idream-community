package com.idream.commons.db.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: hejiang
 * @Description: redis 分布式锁
 * @Date: 15:22 2018/4/20
 */
@Component
public class RedisLock {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Resource
    private RedisCache redisCache;

    //锁超时时间1分钟
    private static final Long LOCK_TIME_OUT = 60000L;

    //加锁阻塞等待时间
    private static final Long THREAD_SLEEP_TIME = 500L;

    //隔离各个线程内的局部变量，用于锁删除
    private static final ThreadLocal<Boolean> delLockFlag = new ThreadLocal<Boolean>() {
        @Override
        protected Boolean initialValue() {//初始化false,当获得锁时改为true
            return false;
        }
    };

    /**
     * @param key     需要加锁的key 格式：类名+方法名称+变量
     * @param timeOut 等待时间，超过此时间如果没有获得锁自动返回false
     */
    public boolean lock(String key, Long timeOut) {
        logger.info("lock start ... " + key);
        //锁key
        try {
            while (timeOut >= 0) {
                //锁到期时间
                long expires = System.currentTimeMillis() + LOCK_TIME_OUT;
                String expiresStr = String.valueOf(expires);
                //setnx获得返回结果，1时表示设置成功，0表示已存在
                long value = redisCache.setnx(key, RedisKeyConstants.REDIS_LOCK, expiresStr);
                //未加锁，返回true
                if (value == 1) {
                    delLockFlag.set(true);
                    redisCache.pexpireAt(key, RedisKeyConstants.REDIS_LOCK, expires);
                    return true;
                } else {
                    //获得当前锁主键的值,即过期时间
                    String lockVal = redisCache.getString(key, RedisKeyConstants.REDIS_LOCK);
                    //锁已超时
                    if (lockVal != null && Long.parseLong(lockVal) < System.currentTimeMillis()) {
                        //获取lock的旧的时间戳并且重写进当前执行此操作时的时间戳
                        String oldLockVal = redisCache.getSet(key, RedisKeyConstants.REDIS_LOCK, expiresStr);
                        redisCache.pexpireAt(key, RedisKeyConstants.REDIS_LOCK, expires);
                        //比较两个时间戳的值，并发执行时，只有一个线程能拿到锁
                        if (oldLockVal != null && lockVal.equals(oldLockVal)) {
                            delLockFlag.set(true);
                            return true;
                        }
                    }
                }
                timeOut -= THREAD_SLEEP_TIME;
                Thread.sleep(THREAD_SLEEP_TIME);
            }
        } catch (Exception e) {
            logger.error("获取锁失败！", e);
        }
        return false;
    }


    public boolean lock(String key) {
        return lock(key, 0L);
    }

    /**
     * 解锁
     *
     * @param key 需要解锁的key 格式：类名+方法名称+变量
     */
    public void releaseLock(String key) {
        logger.info("del lock start ... " + key);
        try {
            if (delLockFlag.get()) {
                Long result = redisCache.del(key, RedisKeyConstants.REDIS_LOCK);
                if (result == 1) {
                    logger.info("del lock success!");
                }
                return;
            }
        } catch (Exception e) {
            logger.error("删除锁失败！", e);
        } finally {
            delLockFlag.set(false);//修改成功改变变量的值
        }
    }
}
