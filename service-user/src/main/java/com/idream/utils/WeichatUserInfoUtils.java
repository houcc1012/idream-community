package com.idream.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Maps;
import com.idream.commons.lib.dto.user.DecodeWeiChatDto;
import com.vdurmont.emoji.EmojiParser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class WeichatUserInfoUtils {

    private static Logger logger = LoggerFactory.getLogger(WeichatUserInfoUtils.class);

    @Value("${weixin.open.appid}")
    private String weixinOpenAppId;

    @Value("${weixin.open.seceret}")
    private String weixinOpenApiSeceret;

    /**
     * 通过code向微信开放平台请求access_token
     *
     * @param code
     */
    private Map<String, String> getAccessToken(String code) {
        //拼接请求access_token的链接
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
        //请求参数
        Map<String, String> paramMap = Maps.newHashMap();
        paramMap.put("appid", weixinOpenAppId);
        paramMap.put("grant_type", "authorization_code");
        paramMap.put("code", code);
        paramMap.put("secret", weixinOpenApiSeceret);

        String resultJson = HttpRequestUtils.sendGet(url, paramMap);
        Map<String, String> map = null;
        if (StringUtils.isNotEmpty(resultJson)) {
            map = JSON.parseObject(resultJson, new TypeReference<Map<String, String>>() {
            });
        }
        return map;
    }

    /**
     * 函数名称: refresh_access_token
     * 函数描述: access_token超时,使用refresh_token重新获得一个access_token
     *
     * @param refreshToken
     *
     * @return Map<String, String>
     */
    public Map<String, String> refreshAccessToken(String refreshToken) {
        //access_token超时,此时需要重新获得一个access_token。
        String url_access = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
        //请求参数
        Map<String, String> paramMap = Maps.newHashMap();
        paramMap.put("appid", weixinOpenAppId);
        paramMap.put("grant_type", "refresh_token");
        paramMap.put("refresh_token", refreshToken);

        String resultJson = HttpRequestUtils.sendGet(url_access, paramMap);
        Map<String, String> map = null;
        if (StringUtils.isNotEmpty(resultJson)) {
            map = JSON.parseObject(resultJson, new TypeReference<Map<String, String>>() {
            });
        }
        return map;
    }

    /**
     * 函数名称: get_userinfo
     * 函数描述: 通过access_token去获取用户的信息
     *
     * @param accessToken
     */
    public Map<String, Object> getUserinfo(String accessToken, String openid, boolean flag) {
        String url = "https://api.weixin.qq.com/sns/userinfo";
        //请求参数
        Map<String, String> paramMap = Maps.newHashMap();
        paramMap.put("access_token", accessToken);
        paramMap.put("openid", openid);
        Map<String, Object> map = null;
        try {
            String resultJson = HttpRequestUtils.sendGet(url, paramMap);
            if (StringUtils.isNotEmpty(resultJson)) {
                map = JSON.parseObject(resultJson, new TypeReference<Map<String, Object>>() {
                });
            }
            //access_token超时,需要重新获得access_token超时，再请求用户信息
            if (map != null && "42001".equals(map.get("errcode"))) {
                Map<String, String> refreshAccessTokenMap = refreshAccessToken(accessToken);
                String refreshAccessToken = refreshAccessTokenMap.get("access_token");
                String refreshOpenId = refreshAccessTokenMap.get("openid");
                if (flag) {
                    getUserinfo(refreshAccessToken, refreshOpenId, false);
                }
            }
        } catch (Exception e) {
            logger.error("获取微信用户信息失败", e);
        }
        return map;
    }

    /**
     * 获取微信用户信息
     *
     * @param code
     */
    public DecodeWeiChatDto getUserinfo(String code) {
        Map<String, String> accessTokenMap = getAccessToken(code);
        if (accessTokenMap == null) {
            logger.info("获取微信 accessToken 失败!");
            return null;
        }
        String accessToken = accessTokenMap.get("access_token");
        if (StringUtils.isEmpty(accessToken)) {
            logger.error("获取微信accessToken失败!");
            return null;
        }
        String openid = accessTokenMap.get("openid");
        //获取微信用户信息
        Map<String, Object> map = getUserinfo(accessToken, openid, true);
        DecodeWeiChatDto decodeWeiChatDto = null;
        try {
            if (map != null) {
                decodeWeiChatDto = new DecodeWeiChatDto();
                decodeWeiChatDto.setAvatarUrl(map.get("headimgurl").toString());
                decodeWeiChatDto.setCity(map.get("city").toString());
                decodeWeiChatDto.setCountry(map.get("country").toString());
                decodeWeiChatDto.setGender(map.get("sex").toString());
                decodeWeiChatDto.setNickName(EmojiParser.removeAllEmojis(map.get("nickname").toString()));
                decodeWeiChatDto.setOpenId(map.get("openid").toString());
                decodeWeiChatDto.setUnionId(map.get("unionid").toString());
                decodeWeiChatDto.setProvince(map.get("province").toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
//			decodeWeiChatDto = new DecodeWeiChatDto();
//			decodeWeiChatDto.setUnionId(System.currentTimeMillis() + "");
//			decodeWeiChatDto.setOpenId("123124");
//			decodeWeiChatDto.setNickName("测试昵称");
//			decodeWeiChatDto.setAvatarUrl("测试url");
//			decodeWeiChatDto.setCity("测试城市");
//			decodeWeiChatDto.setCountry("测试国家");
//			decodeWeiChatDto.setGender("1");
//			decodeWeiChatDto.setProvince("测试省份");
        }
        return decodeWeiChatDto;
    }
}
