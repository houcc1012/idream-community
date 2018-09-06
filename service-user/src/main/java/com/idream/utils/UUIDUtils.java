package com.idream.utils;

import java.util.UUID;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/14 14:34
 * @Description:
 */
public class UUIDUtils {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}

