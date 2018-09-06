package com.idream.commons.lib.cache;

import com.alibaba.fastjson.JSON;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 本地缓存
 *
 * @author hejiang
 */
public class LocalCache {

    //默认的缓存容量
    private static int DEFAULT_CAPACITY = 5120;

    //最大容量
    private static int MAX_CAPACITY = 100000;

    //刷新缓存的频率
    private static int MONITOR_DURATION = 20;

    // 启动监控线程
    static {
        new Thread(new TimeoutTimerThread()).start();
    }

    //使用默认容量创建一个Map
    private static ConcurrentHashMap<String, CacheEntity> cache = new ConcurrentHashMap<String, CacheEntity>(DEFAULT_CAPACITY);

    public static ConcurrentHashMap<String, CacheEntity> getMap() {
        return cache;
    }

    /**
     * @param key
     * @param value
     * @param expireTime 过期时间，如果是-1 则表示永不过期
     */
    public static boolean putValue(String key, Object value, int expireTime) {
        return putCloneValue(key, value, expireTime);
    }

    /**
     * 保存失效时间为60秒的缓存数据
     *
     * @param key
     * @param value
     */
    public static boolean putValueExpireTime60(String key, Object value) {
        return putCloneValue(key, value, 60);
    }

    /**
     * 深拷贝 处理后保存到缓存中，解决值引用的问题
     *
     * @param key
     * @param value
     * @param expireTime
     */
    private static boolean putCloneValue(String key, Object value, int expireTime) {
        try {
            if (cache.size() >= MAX_CAPACITY) {
                return false;
            }
            // 序列化赋值
            CacheEntity entityClone = clone(new CacheEntity(value, System.nanoTime(), expireTime));
            cache.put(key, entityClone);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 序列化 深拷贝
     *
     * @param object
     */
    private static <T extends Serializable> T clone(T object) {
        T cloneObject = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            oos.close();
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            cloneObject = (T) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObject;
    }


    /**
     * 从本地缓存中获取key对应的值，如果该值不存则则返回null
     *
     * @param key
     */
    public static Object getValue(String key) {
        CacheEntity entity = cache.get(key);
        if (entity != null) {
            return entity.getValue();
        }
        return null;
    }

    public static <T> T getValue(String key, Class<T> clazz) {
        Object obj = getValue(key);
        if (obj == null) {
            return null;
        }
        return JSON.parseObject(JSON.toJSONString(obj), clazz);
    }

    /**
     * 获取String 类型的值
     *
     * @param key
     */
    public static String getStrValue(String key) {
        return (String) getValue(key);
    }

    /**
     * 清空所有
     */
    public static void clear() {
        cache.clear();
    }

    /**
     * 删除
     *
     * @param key
     */
    public static void del(String key) {
        System.out.println("删除前:" + cache.size());
        cache.remove(key);
        System.out.println("删除后:" + cache.size());
    }

    /**
     * 过期处理线程
     */
    static class TimeoutTimerThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(MONITOR_DURATION);
                    checkTime();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private void checkTime() throws Exception {
            for (String key : cache.keySet()) {
                CacheEntity rce = cache.get(key);
                long timeOutTime = TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - rce.getTimeStamp());
                if (rce.getExpireTime() > timeOutTime) {
                    continue;
                }
                cache.remove(key);
            }
        }
    }
}
