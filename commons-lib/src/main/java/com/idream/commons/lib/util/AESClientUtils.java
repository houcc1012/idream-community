package com.idream.commons.lib.util;

import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;


/**
 * 编码工具类
 * 1.将byte[]转为各种进制的字符串
 * 2.base 64 encode
 * 3.base 64 decode
 * 4.获取byte[]的md5值
 * 5.获取字符串md5值
 * 6.结合base64实现md5加密
 * 7.AES加密
 * 8.AES加密为base 64 code
 * 9.AES解密
 * 10.将base 64 code AES解密
 *
 * @author hejiang
 */
public class AESClientUtils {
    /**
     * 加密和解密密钥
     */
    public static final String KEY = "3b913d93-6044-4837-bf60-e2118cfc2e58";

    /**
     * 将byte[]转为各种进制的字符串
     *
     * @param bytes byte[]
     * @param radix 可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
     *
     * @return 转换后的字符串
     */
    public static String binary(byte[] bytes, int radix) {
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
    }

    /**
     * base 64 encode
     *
     * @param bytes 待编码的byte[]
     *
     * @return 编码后的base 64 code
     */
    public static String base64Encode(byte[] bytes) {
        return new BASE64Encoder().encode(bytes);
    }

    /**
     * base 64 decode
     *
     * @param base64Code 待解码的base 64 code
     *
     * @return 解码后的byte[]
     */
    public static byte[] base64Decode(String base64Code) throws Exception {
        return StringUtils.isEmpty(base64Code) ? null : new BASE64Decoder().decodeBuffer(base64Code);
    }

    /**
     * 获取byte[]的md5值
     *
     * @param bytes byte[]
     *
     * @return md5
     */
    public static byte[] md5(byte[] bytes) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5Utils");
        md.update(bytes);

        return md.digest();
    }

    /**
     * 获取字符串md5值
     *
     * @param msg
     *
     * @return md5
     */
    public static byte[] md5(String msg) throws Exception {
        return StringUtils.isEmpty(msg) ? null : md5(msg.getBytes());
    }

    /**
     * 结合base64实现md5加密
     *
     * @param msg 待加密字符串
     *
     * @return 获取md5后转为base64
     */
    public static String md5Encrypt(String msg) throws Exception {
        return StringUtils.isEmpty(msg) ? null : base64Encode(md5(msg));
    }

    /**
     * AES加密
     *
     * @param content 待加密的内容
     *
     * @return 加密后的byte[]
     */
    public static byte[] aesEncryptToBytes(String content) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(KEY.getBytes());
        kgen.init(128, secureRandom);

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));

        return cipher.doFinal(content.getBytes("utf-8"));
    }

    /**
     * AES加密入口
     * AES加密为base 64 code
     *
     * @param content 待加密的内容
     *
     * @return 加密后的base 64 code
     */
    public static String aesEncrypt(String content) throws Exception {
        return base64Encode(aesEncryptToBytes(content));
    }

    /**
     * AES解密
     *
     * @param encryptBytes 待解密的byte[]
     *
     * @return 解密后的String
     */
    public static String aesDecryptByBytes(byte[] encryptBytes) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(KEY.getBytes());
        kgen.init(128, secureRandom);

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);

        return new String(decryptBytes);
    }

    /**
     * AES解密入口
     * <p>
     * 将base 64 code AES解密
     *
     * @param encryptStr 待解密的base 64 code
     *
     * @return 解密后的string
     */
    public static String aesDecrypt(String encryptStr) throws Exception {
        return StringUtils.isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr));
    }

//	public static void main(String[] args) throws Exception {
//
//		String content = "fhzd123456";
//		System.out.println("加密前：" + content);
//		String encrypt = aesEncrypt(content);
//		System.out.println("加密后：" + encrypt);
//		String decrypt = aesDecrypt(encrypt);
//		System.out.println("解密后：" + decrypt);
//		System.out.println(aesDecrypt("HfNyxntdFTyXqu8IOljkeQ=="));
//	}

}
