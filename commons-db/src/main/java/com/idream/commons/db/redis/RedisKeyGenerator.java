package com.idream.commons.db.redis;

/**
 * 生成存如redis的key
 *
 * @author orc
 */
public class RedisKeyGenerator {
    /**
     * @param key
     * @param type
     */
    public static String generateKey(String key, String type) {
        if (type == null) {
            return key;
        } else {
            StringBuilder temp = new StringBuilder(type);
            temp.append(":").append(key);
            return temp.toString();
        }
    }

    /**
     * @param type
     */
    public static String generatePrefix(String type) {
        StringBuilder temp = new StringBuilder(type);
        temp.append(":");
        return temp.toString();
    }
}
