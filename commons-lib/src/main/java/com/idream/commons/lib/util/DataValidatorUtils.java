package com.idream.commons.lib.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

/**
 * @author zjzc
 */
public class DataValidatorUtils {

    // 整数
    private static Pattern INTEGER_PATTERN = Pattern.compile("^-?[1-9]\\d*$");

    // 正整数
    private static Pattern INTEGER_POSITIVE_PATTERN = Pattern
            .compile("^[1-9]\\d*$");

    // 负整数
    private static Pattern INTEGER_NAGETIVE_PATTERN = Pattern
            .compile("^-[1-9]\\d*$");

    // 正数（正整数 + 0）
    private static Pattern INTEGER_UNSIGNED_PATTERN = Pattern
            .compile("^[1-9]\\d*|0$");

    // 数字
    private static Pattern NUMBER_PATTERN = Pattern
            .compile("^([+-]?)\\d*\\.?\\d+$");

    // 金额 2位小数
    private static Pattern MONEY_2D_PATTERN = Pattern
            .compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$");

    // 金额 8位小数
    private static Pattern MONEY_8D_PATTERN = Pattern
            .compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,8})?$");

    // 浮点数
    private static Pattern DECIMAL_PATTERN = Pattern
            .compile("^-?([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0)$");

    // 负浮点数
    private static Pattern DECIMAL_NAGETIVE_PATTERN = Pattern
            .compile("^-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)$");

    // 非负浮点数（正浮点数 + 0）
    private static Pattern DECIMAL_UNSIGNED_PATTERN = Pattern
            .compile("^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0$");

    // 邮箱
    private static Pattern EMAIL_PATTERN = Pattern
            .compile("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$");

    // 颜色
    private static Pattern COLOR_PATTERN = Pattern.compile("^[a-fA-F0-9]{6}$");

    // url
    private static Pattern URL_PATTERN = Pattern
            .compile("^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$");

    // 中文
    private static Pattern CHINESE_PATTERN = Pattern
            .compile("^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$");

    // ACSII字符
    private static Pattern ACSII_PATTERN = Pattern.compile("^[\\x00-\\xFF]+$");

    // 邮编
    private static Pattern ZIPCODE_PATTERN = Pattern.compile("^\\d{6}$");

    // 手机
    private static Pattern MOBILE_PATTERN = Pattern
            .compile("^1[345678][0-9]{9}$");

    // ip
    private static Pattern IP_PATTERN = Pattern
            .compile("^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$");

    // 图片文件名
    private static Pattern PICTURE_PATTERN = Pattern
            .compile("(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$");

    // 压缩文件名
    private static Pattern RAR_PATTERN = Pattern
            .compile("(.*)\\.(rar|zip|7zip|tgz)$");

    // 图片文件名
    private static Pattern DATE_PATTERN = Pattern
            .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|(1?[0-9])|(2?[0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");

    // QQ号码
    private static Pattern QQ_PATTERN = Pattern.compile("^[1-9]*[1-9][0-9]*$");

    // 电话号码(包括验证国内区号,国际区号,分机号)
    private static Pattern TEL_PATTERN = Pattern
            .compile("^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$");

    // 用户名 匹配由数字、26个英文字母或者下划线组成的字符串
    private static Pattern USERNAME_PATTERN = Pattern
            .compile("^[a-zA-Z][A-Za-z0-9-_]{3,29}$");

    // 字母
    private static Pattern LETTER_PATTERN = Pattern.compile("^[A-Za-z]+$");

    // 大写字母
    private static Pattern LETTER_UP_PATTERN = Pattern.compile("^[A-Z]+$");

    // 小写字母
    private static Pattern LETTER_DOWN_PATTERN = Pattern.compile("^[a-z]+$");

    // 价格
    private static Pattern PRICE_PATTERN = Pattern
            .compile("^([1-9]{1}[0-9]{0,}(\\.[0-9]{0,2})?|0(\\.[0-9]{0,2})?|\\.[0-9]{1,2})$");

    // 身份证号 15位
    private static Pattern IDNO_15_PATTERN = Pattern
            .compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");

    // 身份证号 18位
    private static Pattern IDNO_18_PATTERN = Pattern
            .compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X|x)$");

    // 中国人姓名
    private static Pattern PERSON_NAME_PATTERN = Pattern
            .compile("[\\u4E00-\\u9FA5]{2,5}(?:·[\\u4E00-\\u9FA5]{2,5})*");

    // 密码
    private static Pattern PASSWORD_PATTERN = Pattern
            .compile("^(?!\\W*[0-9]+\\W*$)(?!\\W*[a-zA-Z]+\\W*$)[^\\s'\"]{6,16}$");

    // 有效字符串
    private static Pattern VALID_STR_PATTERN = Pattern
            .compile("^[a-zA-Z]\\w+$");

    /**
     * 是否是整数
     *
     * @param value
     */
    public static boolean isInteger(String value) {
        Matcher m = null;// 操作符表达式
        boolean b = false;
        m = INTEGER_PATTERN.matcher(value);
        b = m.matches();
        return b;
    }

    /**
     * 是否是正整数
     *
     * @param value
     */
    public static boolean isIntegerPositive(String value) {
        Matcher m = null;// 操作符表达式
        boolean b = false;
        m = INTEGER_POSITIVE_PATTERN.matcher(value);
        b = m.matches();
        return b;
    }

    /**
     * 是否是负整数
     *
     * @param value
     */
    public static boolean isIntegerNagetive(String value) {
        Matcher m = null;// 操作符表达式
        boolean b = false;
        m = INTEGER_NAGETIVE_PATTERN.matcher(value);
        b = m.matches();
        return b;
    }

    /**
     * 是否是正数（正整数 + 0）
     *
     * @param value
     */
    public static boolean isIntegerUnsigned(String value) {
        Matcher m = null;// 操作符表达式
        boolean b = false;
        m = INTEGER_UNSIGNED_PATTERN.matcher(value);
        b = m.matches();
        return b;
    }

    /**
     * 是否是数字
     *
     * @param value
     */
    public static boolean isNumber(String value) {
        Matcher m = null;// 操作符表达式
        boolean b = false;
        m = NUMBER_PATTERN.matcher(value);
        b = m.matches();
        return b;
    }

    /**
     * 判断是否金额格式 2位小数
     *
     * @param value
     */
    public static boolean isMoney2D(String value) {
        Matcher m = null;// 操作符表达式
        boolean b = false;
        m = MONEY_2D_PATTERN.matcher(value);
        b = m.matches();
        return b;
    }

    /**
     * 判断是否金额格式 8位小数
     *
     * @param value
     */
    public static boolean isMoney8D(String value) {
        Matcher m = null;// 操作符表达式
        boolean b = false;
        m = MONEY_8D_PATTERN.matcher(value);
        b = m.matches();
        return b;
    }

    /**
     * 是否是浮点数
     *
     * @param value
     */
    public static boolean isDecmal(String value) {
        Matcher m = null;// 操作符表达式
        boolean b = false;
        m = DECIMAL_PATTERN.matcher(value);
        b = m.matches();
        return b;
    }

    /**
     * 是否是负浮点数
     *
     * @param value
     */
    public static boolean isDecmalNagetive(String value) {
        Matcher m = null;// 操作符表达式
        boolean b = false;
        m = DECIMAL_NAGETIVE_PATTERN.matcher(value);
        b = m.matches();
        return b;
    }

    /**
     * 是否是非负浮点数（正浮点数 + 0）
     *
     * @param value
     */
    public static boolean isDecmalUnsigned(String value) {
        Matcher m = null;// 操作符表达式
        boolean b = false;
        m = DECIMAL_UNSIGNED_PATTERN.matcher(value);
        b = m.matches();
        return b;
    }

    /**
     * 是否是邮件
     *
     * @param value
     */
    public static boolean isEmail(String value) {
        Matcher m = null;// 操作符表达式
        boolean b = false;
        m = EMAIL_PATTERN.matcher(value);
        b = m.matches();
        return b;
    }

    /**
     * 是否是颜色
     *
     * @param value
     */
    public static boolean isColor(String value) {
        Matcher m = null;// 操作符表达式
        boolean b = false;
        m = COLOR_PATTERN.matcher(value);
        b = m.matches();
        return b;
    }

    /**
     * 是否是url
     *
     * @param value
     */
    public static boolean isUrl(String value) {
        Matcher m = null;// 操作符表达式
        boolean b = false;
        m = URL_PATTERN.matcher(value);
        b = m.matches();
        return b;
    }

    /**
     * 是否是中文
     *
     * @param value
     */
    public static boolean isChinese(String value) {
        Matcher m = null;// 操作符表达式
        boolean b = false;
        m = CHINESE_PATTERN.matcher(value);
        b = m.matches();
        return b;
    }

    /**
     * 是否是ACSII字符
     *
     * @param value
     */
    public static boolean isAscii(String value) {
        Matcher m = null;// 操作符表达式
        boolean b = false;
        m = ACSII_PATTERN.matcher(value);
        b = m.matches();
        return b;
    }

    /**
     * 是否是邮编
     *
     * @param value
     */
    public static boolean isZipcode(String value) {
        Matcher m = null;// 操作符表达式
        boolean b = false;
        m = ZIPCODE_PATTERN.matcher(value);
        b = m.matches();
        return b;
    }

    /**
     * 是否是手机
     *
     * @param value
     */
    public static boolean isMobile(String value) {
        Matcher m = null;// 操作符表达式
        boolean b = false;
        m = MOBILE_PATTERN.matcher(value);
        b = m.matches();
        return b;
    }

    /**
     * 是否是ip地址
     *
     * @param value
     */
    public static boolean isIp(String value) {
        Matcher m = null;// 操作符表达式
        boolean b = false;
        m = IP_PATTERN.matcher(value);
        b = m.matches();
        return b;
    }

    /**
     * 是否是图片
     *
     * @param value
     */
    public static boolean isPicture(String value) {
        Matcher m = null;// 操作符表达式
        boolean b = false;
        m = PICTURE_PATTERN.matcher(value);
        b = m.matches();
        return b;
    }

    /**
     * 是否是压缩文件
     *
     * @param value
     */
    public static boolean isRar(String value) {
        Matcher m = null;// 操作符表达式
        boolean b = false;
        m = RAR_PATTERN.matcher(value);
        b = m.matches();
        return b;
    }

    /**
     * 是否是日期
     *
     * @param value
     */
    public static boolean isDate(String value) {
        Matcher m = null;// 操作符表达式
        boolean b = false;
        m = DATE_PATTERN.matcher(value);
        b = m.matches();
        return b;
    }

    /**
     * 是否是QQ号码
     *
     * @param value
     */
    public static boolean isQq(String value) {
        Matcher m = null;// 操作符表达式
        boolean b = false;
        m = QQ_PATTERN.matcher(value);
        b = m.matches();
        return b;
    }

    /**
     * 是否是电话号码的函数(包括验证国内区号,国际区号,分机号)
     *
     * @param value
     */
    public static boolean isTel(String value) {
        Matcher m = null;// 操作符表达式
        boolean b = false;
        m = TEL_PATTERN.matcher(value);
        b = m.matches();
        return b;
    }

    /**
     * 用来用户注册。匹配由数字、26个英文字母或者下划线组成的字符串
     *
     * @param value
     */
    public static boolean isUsername(String value) {
        Matcher m = null;// 操作符表达式
        boolean b = false;
        m = USERNAME_PATTERN.matcher(value);
        b = m.matches();
        return b;
    }

    /**
     * 是否是字母
     *
     * @param value
     */
    public static boolean isLetter(String value) {
        Matcher m = null;// 操作符表达式
        boolean b = false;
        m = LETTER_PATTERN.matcher(value);
        b = m.matches();
        return b;
    }

    /**
     * 是否是大写字母
     *
     * @param value
     */
    public static boolean isLetter_u(String value) {
        Matcher m = null;// 操作符表达式
        boolean b = false;
        m = LETTER_UP_PATTERN.matcher(value);
        b = m.matches();
        return b;
    }

    /**
     * 是否是大写字母
     *
     * @param value
     */
    public static boolean isLetter_l(String value) {
        Matcher m = null;// 操作符表达式
        boolean b = false;
        m = LETTER_DOWN_PATTERN.matcher(value);
        b = m.matches();
        return b;
    }

    /**
     * 是否是价格
     *
     * @param value
     */
    public static boolean isPrice(String value) {
        Matcher m = null;// 操作符表达式
        boolean b = false;
        m = PRICE_PATTERN.matcher(value);
        b = m.matches();
        return b;
    }

    /**
     * 是否是身份证号
     *
     * @param value
     */
    public static boolean isIdNo(String value) {
        Matcher m = null;// 操作符表达式
        Matcher m1 = null;// 操作符表达式
        boolean b = false;
        boolean b1 = false;
        m = IDNO_15_PATTERN.matcher(value);
        b = m.matches();
        m1 = IDNO_18_PATTERN.matcher(value);
        b1 = m1.matches();
        return b || b1;
    }

    /**
     * 是否是中国人姓名
     *
     * @param value
     */
    public static boolean isPersonName(String value) {
        Matcher m = null;// 操作符表达式
        boolean b = false;
        m = PERSON_NAME_PATTERN.matcher(value);
        b = m.matches();
        return b;
    }

    /**
     * 是否符合密码格式
     *
     * @param value
     */
    public static boolean isPassword(String value) {
        Matcher matcher = PASSWORD_PATTERN.matcher(value);
        return matcher.matches();
    }

    public static boolean isValidStr(String value) {
        if (StringUtils.isEmpty(value)) {
            return true;
        }
        Matcher m = null;// 操作符表达式
        boolean b = false;
        m = VALID_STR_PATTERN.matcher(value);
        b = m.matches();
        return b;
    }

}
