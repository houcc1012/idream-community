package com.idream.commons.db.token;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import com.google.common.collect.Maps;
import com.idream.commons.db.redis.RedisCache;
import com.idream.commons.db.redis.RedisKeyConstants;
import com.idream.commons.lib.dto.token.AuthUserInfo;
import com.idream.commons.lib.dto.token.TokenVerifyDto;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Map;


/**
 * @Author: hejiang
 * @Description: JWT token操作
 * @Date: 16:38 2018/3/22
 */
@Component
public class JWTTokenService {

    private Logger logger = LoggerFactory.getLogger(JWTTokenService.class);

    @Value("${auth.token.secret:123456}")
    private String authTokenSecret;

    @Value("${auth.token.expire:604800}")
    private Integer authTokenExpire;

    @Autowired
    private RedisCache redisCache;

    private JWTSigner signer;

    private JWTVerifier jwtVerifier;

    public JWTSigner getSigner() {
        if (signer == null) {
            signer = new JWTSigner(authTokenSecret);
        }
        return signer;
    }

    public JWTVerifier getJwtVerifier() {
        if (jwtVerifier == null) {
            jwtVerifier = new JWTVerifier(authTokenSecret);
        }
        return jwtVerifier;
    }

    /**
     * @Author: hejiang
     * @Description: 创建token
     * @Param: authUserInfo 用户信息
     * @Date: 9:13 2018/3/23
     */
    public String generateToken(AuthUserInfo authUserInfo) {
        //删除原来的token
        String token = redisCache.hget(RedisKeyConstants.AUTH_TOKEN_REL, authUserInfo.getUserId() + "-" + authUserInfo.getUserType());
        if (StringUtils.isNotEmpty(token)) {
            AuthUserInfo redisUserInfo = redisCache.getObject(token, RedisKeyConstants.AUTH_TOKEN, AuthUserInfo.class);
            if (redisUserInfo != null && redisUserInfo.getUserRole().equals(authUserInfo.getUserRole())) {
                //刷新原来的token过期时间
                refreshExpire(token);
                return token;
            }
        }
        Map<String, Object> tokenParams = Maps.newHashMap();
        tokenParams.put("userId", authUserInfo.getUserId());
        tokenParams.put("nickName", authUserInfo.getNickName());
        tokenParams.put("userName", authUserInfo.getUserName());
        tokenParams.put("phone", authUserInfo.getPhone());
        tokenParams.put("ts", System.currentTimeMillis());
        tokenParams.put("userType", authUserInfo.getUserType());
        tokenParams.put("userRole", authUserInfo.getUserRole());
        //产生Token
        token = getSigner().sign(tokenParams);
        //新的token存入redis
//		redisCache.setex(token, RedisKeyConstants.AUTH_TOKEN, authTokenExpire, JSON.toJSONString(authUserInfo));
        redisCache.setString(token, JSON.toJSONString(authUserInfo), RedisKeyConstants.AUTH_TOKEN);
        //存入token 和用户 关联
        redisCache.hset(RedisKeyConstants.AUTH_TOKEN_REL, authUserInfo.getUserId() + "-" + authUserInfo.getUserType(), token);
        return token;

    }

    /**
     * @Author: hejiang
     * @Description: 删除token
     * @Param: token
     * @Date: 9:14 2018/3/23
     */
    public boolean removeToken(String token) {
        //删除redis
        Long result = redisCache.del(token, RedisKeyConstants.AUTH_TOKEN);
        if (result != null && result > 0) {
            return true;
        }
        return false;
    }

    /**
     * @Author: hejiang
     * @Description: token验证
     * @Param: token
     * @Date: 9:34 2018/3/23
     */
    public TokenVerifyDto verify(String token) {
        TokenVerifyDto tokenVerifyDto = new TokenVerifyDto();
        try {
            // 取出redis里的token信息
            AuthUserInfo authUserInfo = redisCache.getObject(token, RedisKeyConstants.AUTH_TOKEN, AuthUserInfo.class);
            if (authUserInfo == null) {
                logger.error("获取redis token信息失败！");
                tokenVerifyDto.setVerifyResult(false);
                return tokenVerifyDto;
            }
            // 解析token信息
            Map<String, Object> authUserMap = getJwtVerifier().verify(token);
            if (authUserMap != null
                    && authUserMap.get("userId").toString().equals(String.valueOf(authUserInfo.getUserId()))
                    && authUserMap.get("userType").toString().equals(String.valueOf(authUserInfo.getUserType()))) {
                tokenVerifyDto.setToken(token);
                tokenVerifyDto.setAuthUserInfo(authUserInfo);
            } else {
                logger.error("token 用户信息不匹配！token=" + token + ",authUserInfo=" + JSON.toJSONString(authUserInfo));
                tokenVerifyDto.setVerifyResult(false);
                return tokenVerifyDto;
            }
        } catch (InvalidKeyException | NoSuchAlgorithmException
                | IllegalStateException | SignatureException | IOException
                | JWTVerifyException e) {
            logger.error("token 校验失败！token=" + token, e);
            tokenVerifyDto.setVerifyResult(false);
            return tokenVerifyDto;
        } catch (Exception e) {
            logger.error("token 校验失败！token=" + token, e);
            tokenVerifyDto.setVerifyResult(false);
            return tokenVerifyDto;
        }

        return tokenVerifyDto;
    }

    /**
     * @Author: hejiang
     * @Description: 刷新token时间
     * @Param: token
     * @Date: 9:34 2018/3/23
     */
    public void refreshExpire(String token) {
        if (redisCache.exists(token, RedisKeyConstants.AUTH_TOKEN)) {
            redisCache.expire(token, RedisKeyConstants.AUTH_TOKEN, authTokenExpire);
        }
    }


}
