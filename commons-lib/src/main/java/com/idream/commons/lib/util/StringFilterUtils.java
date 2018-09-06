package com.idream.commons.lib.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串过滤
 *
 * @author charles
 */
public class StringFilterUtils {
    public static final String ESCAPE_CHAR = "\\\\";

    public static String escapeFilter(String str) {
        if (StringUtils.isNotBlank(str)) {
            str = str.replaceAll("'", "''")
                    .replaceAll(ESCAPE_CHAR, ESCAPE_CHAR + ESCAPE_CHAR)
                    .replaceAll("_", ESCAPE_CHAR + "_")
                    .replaceAll("%", ESCAPE_CHAR + "%");
        }
        return str;
    }

    public static String emojiFilter(String str) {
        if (StringUtils.isNotBlank(str)) {
            String pattern = "[\\ud83c\\udc00-\\ud83c\\udfff]|[\\ud83d\\udc00-\\ud83d\\udfff]|[\\u2600-\\u27ff]";
            Pattern compile = Pattern.compile(pattern);
            Matcher matcher = compile.matcher(str);
            str = matcher.replaceAll("");
        }
        return str;
    }

    public static String emojiAndEscapeFilter(String str) {
        return escapeFilter(emojiFilter(str));
    }
}
