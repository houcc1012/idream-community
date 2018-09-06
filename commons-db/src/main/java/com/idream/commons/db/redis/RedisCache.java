package com.idream.commons.db.redis;

import com.alibaba.fastjson.JSON;
import com.idream.commons.lib.exception.RedisException;
import com.idream.commons.lib.util.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

@Component
public class RedisCache {

    @Autowired
    private JedisPool jedisPool;

    /*******************************String处理******************************/

    public String getString(String key) throws RedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public String getString(String key, String type) throws RedisException {
        String generatedKey = RedisKeyGenerator.generateKey(key, type);
        return getString(generatedKey);
    }

    public boolean setString(String key, String value) throws RedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String result = jedis.set(key, value);
            if ("ok".equalsIgnoreCase(result)) {
                return true;
            }
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public void setString(String key, String value, int timeout) throws RedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.setex(key, timeout, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public boolean setString(String key, String value, String type) throws RedisException {
        String generatedKey = RedisKeyGenerator.generateKey(key, type);
        return setString(generatedKey, value);
    }

    public void setString(String key, String value, String type, int timeout) throws RedisException {
        String generatedKey = RedisKeyGenerator.generateKey(key, type);
        setString(generatedKey, value, timeout);
    }

    /*******************************Object处理******************************/
    public Object getObject(String key) throws RedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return SerializationUtils.deserialize(jedis.get(key.getBytes()));
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Object getObject(String key, String type) throws RedisException {
        String generatedKey = RedisKeyGenerator.generateKey(key, type);
        return getObject(generatedKey);
    }

    public void setObject(String key, Object value) throws RedisException {
        setString(key, JSON.toJSONString(value));
    }

    public void setObject(String key, Object value, String type) throws RedisException {
        String generatedKey = RedisKeyGenerator.generateKey(key, type);
        setObject(generatedKey, value);
    }

    /**
     * @Author: hejiang
     * @Description: 删除redis key
     * @Param: key
     * @Date: 9:36 2018/3/23
     */
    public Long delete(String key) throws RedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.del(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * @Author: hejiang
     * @Description: redis get 返回特定类型
     * @Param: key redis Key
     * @Param: clazz 泛型
     * @Date: 9:25 2018/3/23
     */
    public <T> T getObject(String key, Class<T> clazz) {
        String result = getString(key);
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(result)) {
            return JSON.parseObject(result, clazz);
        }
        return null;
    }

    /**
     * @Author: hejiang
     * @Description: redis get 返回特定类型
     * @Param: key
     * @Param: type
     * @Param: clazz 泛型
     * @Date: 9:25 2018/3/23
     */
    public <T> T getObject(String key, String type, Class<T> clazz) {
        String result = getString(key, type);
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(result)) {
            return JSON.parseObject(result, clazz);
        }
        return null;
    }

    /**
     * @Author: hejiang
     * @Description: 查看redis key是否存在
     * @Param: key
     * @Date: 9:36 2018/3/23
     */
    public boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * @Author: hejiang
     * @Description: 查看redis key是否存在
     * @Param: key
     * @Param: type
     * @Date: 9:36 2018/3/23
     */
    public boolean exists(String key, String type) {
        Jedis jedis = null;
        try {
            String generatedKey = RedisKeyGenerator.generateKey(key, type);
            jedis = jedisPool.getResource();
            return jedis.exists(generatedKey);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    /**
     * @Author: hejiang
     * @Description: 设置redis key失效时间
     * @Param: key
     * @Param: seconds 失效时间 单位秒
     * @Date: 9:37 2018/3/23
     */
    public void expire(String key, int seconds) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.expire(key, seconds);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public long pexpireAt(String key, long expireTime) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.pexpireAt(key, expireTime);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public long pexpireAt(String key, String type, long expireTime) {
        String generatedKey = RedisKeyGenerator.generateKey(key, type);
        return pexpireAt(generatedKey, expireTime);
    }

    /**
     * @Author: hejiang
     * @Description: 设置redis key失效时间
     * @Param: key
     * @Param: type
     * @Param: seconds 失效时间 单位秒
     * @Date: 9:37 2018/3/23
     */
    public void expire(String key, String type, int seconds) {
        Jedis jedis = null;
        try {
            String generatedKey = RedisKeyGenerator.generateKey(key, type);
            jedis = jedisPool.getResource();
            jedis.expire(generatedKey, seconds);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    /**
     * @Author: hejiang
     * @Description: 设置单个值并且设置失效时间
     * @Date: 16:23 2018/3/23
     */
    public void setex(String key, int seconds, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.setex(key, seconds, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * @Author: hejiang
     * @Description: 设置单个值并且设置失效时间
     * @Date: 16:23 2018/3/23
     */
    public void setex(String key, String type, int seconds, String value) {
        Jedis jedis = null;
        try {
            String generatedKey = RedisKeyGenerator.generateKey(key, type);
            jedis = jedisPool.getResource();
            jedis.setex(generatedKey, seconds, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * @param key
     */
    public Long del(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.del(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * @param key
     */
    public Long del(String key, String type) {
        Jedis jedis = null;
        try {
            String generatedKey = RedisKeyGenerator.generateKey(key, type);
            jedis = jedisPool.getResource();
            return jedis.del(generatedKey);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    /**
     * @param
     */
    public String hget(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hget(key, field);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public String hget(String key, String type, String field) {
        String generatedKey = RedisKeyGenerator.generateKey(key, type);
        return hget(generatedKey, field);
    }

    /**
     * @param key   key
     * @param field field
     * @param value value
     *
     * @return 返回 1 设置一个新值成功; 返回0 覆盖一个旧值成功
     */
    public Long hset(String key, String field, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hset(key, field, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Long hset(String key, String type, String field, String value) {
        String generatedKey = RedisKeyGenerator.generateKey(key, type);
        return hset(generatedKey, field, value);
    }


    public long incr(String key) {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.incr(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public long incr(String key, String type) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String generatedKey = RedisKeyGenerator.generateKey(key, type);
            return jedis.incr(generatedKey);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Long hincrBy(String key, String field, long value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hincrBy(key, field, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Long hincrBy(String key, String type, String field, long value) {
        String generatedKey = RedisKeyGenerator.generateKey(key, type);
        return hincrBy(generatedKey, field, value);
    }

    public Long decrBy(String key, long value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.decrBy(key, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Long decr(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.decr(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Long decr(String key, String type) {
        String generatedKey = RedisKeyGenerator.generateKey(key, type);
        return decr(generatedKey);
    }

    /**
     * 生成对应的集合
     *
     * @param key
     * @param type
     * @param clazz
     *
     * @return List<T>
     */
    public <T> List<T> getList(String key, String type, Class<T> clazz) {
        String result = getString(key, type);
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(result)) {
            return JSON.parseArray(result, clazz);
        }
        return null;
    }

    public <T> List<T> getList(String key, Class<T> clazz) {
        String result = getString(key);
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(result)) {
            return JSON.parseArray(result, clazz);
        }
        return null;
    }

    public long hdel(String key, String... fields) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            long count = 0;
            for (String field : fields) {
                long result = jedis.hdel(key, field);
                count += result;
            }
            return count;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public long hdel(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hdel(key, field);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public long hdel(String key, String type, String field) {
        String generatedKey = RedisKeyGenerator.generateKey(key, type);
        return hdel(generatedKey, field);
    }

    public Long setnx(String key, String type, String value) {
        String generatedKey = RedisKeyGenerator.generateKey(key, type);
        return setnx(generatedKey, value);
    }

    public Long setnx(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.setnx(key, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public String getSet(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.getSet(key, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public String getSet(String key, String type, String value) {
        String generatedKey = RedisKeyGenerator.generateKey(key, type);
        return getSet(generatedKey, value);
    }
}
