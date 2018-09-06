package com.idream.utils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.idream.commons.db.redis.RedisCache;
import com.idream.commons.db.redis.RedisKeyConstants;
import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.user.DecodeWeiChatDto;
import com.idream.commons.lib.exception.BusinessException;
import com.vdurmont.emoji.EmojiParser;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.spec.InvalidParameterSpecException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hejiang
 */
@Component
public class WeichatMiniProgramUtils {

    @Value("${weixin.miniprogram.appid}")
    private String weixinMiniprogramAppId;

    @Value("${weixin.miniprogram.seceret}")
    private String weixinMiniprogramSeceret;

    private static Logger logger = LoggerFactory.getLogger(WeichatUserInfoUtils.class);

    //使用BouncyCastleProvider组件填充,用于解密
    static {
        // BouncyCastle是一个开源的加解密解决方案，主页在http://www.bouncycastle.org/
        Security.addProvider(new BouncyCastleProvider());
    }

    @Resource
    private RedisCache redisCache;

    /**
     * 获取小程序用户敏感数据
     *
     * @param encryptedData 明文,加密数据
     * @param iv            加密算法的初始向量
     * @param code          用户允许登录后，回调内容会带上 code（有效期五分钟），开发者需要将 code 发送到开发者服务器后台，使用code 换取
     *                      session_key api，将 code 换成 openid 和 session_key
     */
    public DecodeWeiChatDto getMiniProgramUserInfo(String encryptedData, String iv, String code) {
        DecodeWeiChatDto dto = null;
        // 登录凭证不能为空
        if (code == null || code.length() == 0) {
            return null;
        }
        String url = "https://api.weixin.qq.com/sns/jscode2session";

        // 授权（必填）
        String grant_type = "authorization_code";
        //1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid
        Map<String, String> map = Maps.newHashMap();
        map.put("appid", weixinMiniprogramAppId);
        map.put("secret", weixinMiniprogramSeceret);
        map.put("js_code", code);
        map.put("grant_type", grant_type);
        // 发送请求
        String sr = HttpRequestUtils.sendGet(url, map);
        // 解析相应内容（转换成json对象）
        JSONObject json = JSONObject.fromObject(sr);
        // 获取会话密钥（session_key）
        try {
            String session_key = json.get("session_key").toString();
            String result = decrypt(encryptedData, session_key, iv, "UTF-8");
            logger.info("weixin:{}", result);
            if (null != result && result.length() > 0) {
                dto = JSON.parseObject(result, DecodeWeiChatDto.class);
                dto.setNickName(EmojiParser.removeAllEmojis(dto.getNickName()));
                return dto;
            }
        } catch (Exception e) {
            logger.error("获取微信信息失败!", e);
//            dto = new DecodeWeiChatDto();
//            dto.setUnionId(System.currentTimeMillis() + "");
//            dto.setOpenId("123124");
//            dto.setNickName("测试昵称");
//            dto.setAvatarUrl("测试url");
//            dto.setCity("测试城市");
//            dto.setCountry("测试国家");
//            dto.setGender("1");
//            dto.setProvince("测试省份");
        }
        return dto;
    }

    /**
     * 获取accessToken
     */
    public String getMiniProgramAccessToken() {
        String accessToken = redisCache.getString(RedisKeyConstants.WX_MINIPROGRAM_TOKEN);
        if (StringUtils.isEmpty(accessToken)) {
            String url = "https://api.weixin.qq.com/cgi-bin/token";
            String grant_type = "client_credential";
            Map<String, String> map = Maps.newHashMap();
            map.put("appid", weixinMiniprogramAppId);
            map.put("secret", weixinMiniprogramSeceret);
            map.put("grant_type", grant_type);
            // 发送请求
            String str = HttpRequestUtils.sendGet(url, map);
            logger.info("获取微信小程序accessToken返回信息:" + str);
            // 解析相应内容（转换成json对象）
            JSONObject json = JSONObject.fromObject(str);
            accessToken = json.getString("access_token");
            if (StringUtils.isEmpty(accessToken)) {
                throw new BusinessException(RetCodeConstants.UNKOWN_ERROR, "获取微信AccessToken失败");
            }
            Integer expires_in = json.getInt("expires_in");
            //缓存accesstoken, 并设置过期时间为返回的失效时间少100秒,保证缓存的accesstoken永远有效
            redisCache.setex(RedisKeyConstants.WX_MINIPROGRAM_TOKEN, expires_in - 100, accessToken);
        }
        return accessToken;
    }

    /**
     * 发送微信模板消息
     *
     * @param openId     接收者（用户）的 openid
     * @param templateId 所需下发的模板消息的id
     * @param page       点击模板卡片后的跳转页面
     * @param formId     表单提交场景下，为 submit 事件带上的 formId；支付场景下，为本次支付的 prepay_id
     * @param data       模板内容，不填则下发空模板
     */
    public void sendTemplateMessage(String openId, String templateId, String page, String formId, String data) {
        String accessToken = this.getMiniProgramAccessToken();
        String url = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=" + accessToken;
        Map<String, String> map = Maps.newHashMap();
        map.put("touser", openId);
        map.put("template_id", templateId);
        map.put("page", page);
        map.put("form_id", formId);
        map.put("data", data);
        // 发送请求
        String str = HttpRequestUtils.sendJsonPost(url, map);
        logger.info("发送微信模板返回信息:" + str);
        JSONObject json = JSONObject.fromObject(str);
        int errCode = json.getInt("errcode");
        String errMsg = json.getString("errmsg");
        if (errCode != 0) {
            logger.error("发送模板消息失败:" + errMsg);
            throw new BusinessException(String.valueOf(errCode), errMsg);
        }
    }


    /**
     * AES解密
     *
     * @param data           密文，被加密的数据
     * @param key            秘钥
     * @param iv             偏移量
     * @param encodingFormat 解密后的结果需要进行的编码
     */
    private static String decrypt(String data, String key, String iv, String encodingFormat) throws Exception {
        // 被加密的数据
        byte[] dataByte = Base64.decodeBase64(data);
        // 加密秘钥
        byte[] keyByte = Base64.decodeBase64(key);
        // 偏移量
        byte[] ivByte = Base64.decodeBase64(iv);
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, encodingFormat);
                return result;
            }
            return null;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidParameterSpecException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] getQrcodeByte(Integer businessId, Byte businessType) {
        RestTemplate tpl = new RestTemplate();
        String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + getMiniProgramAccessToken();
        //请求头
        MultiValueMap<String, String> heard = new LinkedMultiValueMap<>();
        heard.add("Content-Type", "application/json");
        String encodeString = String.format("businessId:%d,businessType:%d", businessId, businessType);
        //参数
        Map<String, String> map = new HashMap<>();
        map.put("page", "pages/loading/loading");
        map.put("width", "1280");
        map.put("scene", encodeString);
        HttpEntity request = new HttpEntity<>(map, heard);


        ResponseEntity<byte[]> entity = tpl.postForEntity(url, request, byte[].class);
        return entity.getBody();
    }

}
