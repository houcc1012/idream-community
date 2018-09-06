package com.idream.commons.lib.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class RandomUtils {

    public static String[] chars = new String[]{"a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

    /**
     * 生成4位手机验证码
     */
    public static String getRandom() {
        String[] beforeShuffle = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        List<String> list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        String str = list.stream().collect(Collectors.joining());
        return str.substring(5, 9);
    }

    /**
     * 生成6位手机验证码
     */
    public static String getSixNumRandom() {
        String[] beforeShuffle = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        List<String> list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        String str = list.stream().collect(Collectors.joining());
        return str.substring(3, 9);
    }

    /**
     * 8未短UUID
     */
    public static String getShortUUID() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString().toUpperCase();
    }

    /**
     * 生成不重复的兑奖券码
     */
    public static String getCouponCode() {
        return "DH" + getShortUUID();
    }

    /**
     * 生成不重复的抽奖券码
     */
    public static String getLotteryCode() {
        return "JF" + getShortUUID();
    }

    /**
     * 现场开奖的券码
     */
    public static String getSceneOpenAwardCode() {
        return "XC" + getShortUUID();
    }
}
