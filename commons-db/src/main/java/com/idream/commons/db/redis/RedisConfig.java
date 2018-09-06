package com.idream.commons.db.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisConfig.class);

    @Value("${spring.redis.uri}")
    private String uriStr;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.pool.max-total}")
    private int maxTotal;

    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.pool.max-wait}")
    private long maxWaitMillis;

    @Value("${spring.redis.pool.min-idle}")
    private int minIdle;

    @Bean
    public JedisPool redisPoolFactory() throws URISyntaxException {
        LOGGER.info("JedisPool注入成功！！");
        LOGGER.info("redis地址：" + uriStr);
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setMinIdle(minIdle);
        URI uri = new URI(uriStr);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, uri, timeout);
        return jedisPool;
    }

//    @Bean
//    public RedisConnectionFactory lettuceConnectionFactory() {
//    	
//      RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
//      .master("mymaster")
//      .sentinel("redis.zjdev.com", 6379);
//      sentinelConfig.setPassword(RedisPassword.of("1234567"));
//      
//      
//      return new LettuceConnectionFactory(sentinelConfig);
//    }

}
