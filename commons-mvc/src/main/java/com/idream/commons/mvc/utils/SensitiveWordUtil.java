package com.idream.commons.mvc.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 敏感字词处理类
 */
public class SensitiveWordUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(SensitiveWordUtil.class);

    //敏感词集合
    public static HashMap sensitiveWordMap;

    //临时敏感词集合 用于更新
    public static HashMap temporarySensitiveWordMap;

   //敏感词匹配规则
    public static final int MinMatchTYpe = 1;
    public static final int MaxMatchType = 2;

    /**
     * 初始化敏感词库，构建DFA算法模型
     * @param sensitiveWords 敏感词库
     */
    public static synchronized void init(List<String> sensitiveWords) {
        initSensitiveWordMap(sensitiveWords);
    }

    private static void initSensitiveWordMap(List<String> sensitiveWords) {
        sensitiveWordMap = new HashMap(sensitiveWords.size());
        Map nowMap;
        Map<String, String> newWorMap;
        //遍历 sensitiveWords
        for(String key : sensitiveWords){
            nowMap = sensitiveWordMap;
            try {
                for (int i = 0; i < key.length(); i++) {
                    //转换成char型
                    char keyChar = key.charAt(i);
                    //库中获取关键字
                    Object wordMap = nowMap.get(keyChar);
                    //如果存在该key，直接赋值，用于下一个循环获取
                    if (wordMap != null) {
                        nowMap = (Map) wordMap;
                    } else {
                        //不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
                        newWorMap = new HashMap<>();
                        //不是最后一个
                        newWorMap.put("isEnd", "0");
                        nowMap.put(keyChar, newWorMap);
                        nowMap = newWorMap;
                    }
                    if (i == key.length() - 1) {
                        //最后一个
                        nowMap.put("isEnd", "1");
                    }
                }
            } catch (Exception e) {
                LOGGER.error("敏感词初始化失败， word:{}", key, e);
            }
        }
    }

    public static synchronized void initTemporary(List<String> sensitiveWords) {
        initTemporarySensitiveWordMap(sensitiveWords);
        sensitiveWordMap = temporarySensitiveWordMap;
        temporarySensitiveWordMap = null;
    }

    // 临时敏感词map初始化
    private static void initTemporarySensitiveWordMap(List<String> sensitiveWords) {
        temporarySensitiveWordMap = new HashMap(sensitiveWords.size());
        Map nowMap;
        Map<String, String> newWorMap;
        //遍历 sensitiveWords
        for(String key : sensitiveWords){
            nowMap = temporarySensitiveWordMap;
            try {
                for (int i = 0; i < key.length(); i++) {
                    //转换成char型
                    char keyChar = key.charAt(i);
                    //库中获取关键字
                    Object wordMap = nowMap.get(keyChar);
                    //如果存在该key，直接赋值，用于下一个循环获取
                    if (wordMap != null) {
                        nowMap = (Map) wordMap;
                    } else {
                        //不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
                        newWorMap = new HashMap<>();
                        //不是最后一个
                        newWorMap.put("isEnd", "0");
                        nowMap.put(keyChar, newWorMap);
                        nowMap = newWorMap;
                    }
                    if (i == key.length() - 1) {
                        //最后一个
                        nowMap.put("isEnd", "1");
                    }
                }
            } catch (Exception e) {
                LOGGER.error("敏感词初始化失败， word:{}", key, e);
            }
        }
    }

    /**
     * 判断文字是否包含敏感字符
     * @param txt       文字
     * @param matchType 匹配规则 1：最小匹配规则，2：最大匹配规则
     * @return 若包含返回true，否则返回false
     */
    public static boolean contains(String txt, int matchType) {
        boolean flag = false;
        for (int i = 0; i < txt.length(); i++) {
            int matchFlag = checkSensitiveWord(txt, i, matchType); //判断是否包含敏感字符
            if (matchFlag > 0) {    //大于0存在，返回true
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 判断文字是否包含敏感字符
     * @param txt 文字
     * @return 若包含返回true，否则返回false
     */
    public static boolean contains(String txt) {
        return contains(txt, MaxMatchType);
    }

    /**
     * 获取文字中的敏感词
     * @param txt       文字
     * @param matchType 匹配规则 1：最小匹配规则，2：最大匹配规则
     * @return
     */
    public static Set<String> getSensitiveWord(String txt, int matchType) {
        Set<String> sensitiveWordList = new HashSet<>();

        for (int i = 0; i < txt.length(); i++) {
            //判断是否包含敏感字符
            int length = checkSensitiveWord(txt, i, matchType);
            if (length > 0) {//存在,加入list中
                sensitiveWordList.add(txt.substring(i, i + length));
                i = i + length - 1;//减1的原因，是因为for会自增
            }
        }
        return sensitiveWordList;
    }

    /**
     * 获取文字中的敏感词
     * @param txt 文字
     * @return
     */
    public static Set<String> getSensitiveWord(String txt) {
        return getSensitiveWord(txt, MaxMatchType);
    }

    /**
     * 检查文字中是否包含敏感字符，检查规则如下：<br>
     * @param txt
     * @param beginIndex
     * @param matchType
     * @return 如果存在，则返回敏感词字符的长度，不存在返回0
     */
    private static int checkSensitiveWord(String txt, int beginIndex, int matchType) {
        //敏感词结束标识位：用于敏感词只有1位的情况
        boolean flag = false;
        //匹配标识数默认为0
        int matchFlag = 0;
        char word;
        Map nowMap = sensitiveWordMap;
        for (int i = beginIndex; i < txt.length(); i++) {
            word = txt.charAt(i);
            //获取指定key
            nowMap = (Map) nowMap.get(word);
            if (nowMap != null) {//存在，则判断是否为最后一个
                //找到相应key，匹配标识+1
                matchFlag++;
                //如果为最后一个匹配规则,结束循环，返回匹配标识数
                if ("1".equals(nowMap.get("isEnd"))) {
                    //结束标志位为true
                    flag = true;
                    //最小规则，直接返回,最大规则还需继续查找
                    if (MinMatchTYpe == matchType) {
                        break;
                    }
                }
            } else {//不存在，直接返回
                break;
            }
        }
        if (matchFlag < 2 || !flag) {//长度必须大于等于1，为词
            matchFlag = 0;
        }
        return matchFlag;
    }
}
