package com.idream.commons.lib.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;

public class IdentityExtractorUtils {

    private static Logger logger = LoggerFactory.getLogger(IdentityExtractorUtils.class);

    // 省份
    private String province;
    // 城市    
    private String city;
    // 区县    
    private String region;
    // 年份    
    private int year;
    // 月份    
    private int month;
    // 日期    
    private int day;
    // 性别    
    private Byte gender;
    // 出生日期    
    private Date birthday;
    //年龄  
    private int age;

    private static Map<String, String> cityCodeMap = new HashMap<String, String>() {
        {
            this.put("11", "北京");
            this.put("12", "天津");
            this.put("13", "河北");
            this.put("14", "山西");
            this.put("15", "内蒙古");
            this.put("21", "辽宁");
            this.put("22", "吉林");
            this.put("23", "黑龙江");
            this.put("31", "上海");
            this.put("32", "江苏");
            this.put("33", "浙江");
            this.put("34", "安徽");
            this.put("35", "福建");
            this.put("36", "江西");
            this.put("37", "山东");
            this.put("41", "河南");
            this.put("42", "湖北");
            this.put("43", "湖南");
            this.put("44", "广东");
            this.put("45", "广西");
            this.put("46", "海南");
            this.put("50", "重庆");
            this.put("51", "四川");
            this.put("52", "贵州");
            this.put("53", "云南");
            this.put("54", "西藏");
            this.put("61", "陕西");
            this.put("62", "甘肃");
            this.put("63", "青海");
            this.put("64", "宁夏");
            this.put("65", "新疆");
            this.put("71", "台湾");
            this.put("81", "香港");
            this.put("82", "澳门");
            this.put("91", "国外");
        }
    };

    public IdentityExtractorUtils() {
        super();
    }

    /**
     * 通过构造方法初始化各个成员属性
     */
    public IdentityExtractorUtils(String idcard) {
        try {
            if (IdentityValidatorUtils.isValidatedAllIdcard(idcard)) {
                if (idcard.length() == 15) {
                    idcard = IdentityValidatorUtils.convertIdcarBy15bit(idcard);
                }
                // 获取省份    
                String provinceId = idcard.substring(0, 2);
                Set<String> key = cityCodeMap.keySet();
                for (String id : key) {
                    if (id.equals(provinceId)) {
                        this.province = cityCodeMap.get(id);
                        break;
                    }
                }
                // 获取性别    
                String id17 = idcard.substring(16, 17);
                if (Integer.parseInt(id17) % 2 != 0) {
                    this.gender = 1;
                } else {
                    this.gender = 2;
                }

                // 获取出生日期    
                String birthday = idcard.substring(6, 14);
                Date birthdate = new SimpleDateFormat("yyyyMMdd")
                        .parse(birthday);
                this.birthday = birthdate;
                GregorianCalendar currentDay = new GregorianCalendar();
                currentDay.setTime(birthdate);
                this.year = currentDay.get(Calendar.YEAR);
                this.month = currentDay.get(Calendar.MONTH) + 1;
                this.day = currentDay.get(Calendar.DAY_OF_MONTH);

                //获取年龄  
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
                String year = simpleDateFormat.format(new Date());
                this.age = Integer.parseInt(year) - this.year;

            }
        } catch (Exception e) {
            logger.error("get Card info is error idCardCode=" + idcard);
        }
    }

    /**
     * 省份
     */
    public static String getProvince(String idcard) {
        if (idcard.length() == 15) {
            idcard = IdentityValidatorUtils.convertIdcarBy15bit(idcard);
        }
        // 获取省份
        String provinceId = idcard.substring(0, 2);
        Set<String> key = cityCodeMap.keySet();
        for (String id : key) {
            if (id.equals(provinceId)) {
                return cityCodeMap.get(id);
            }
        }
        return null;
    }

    /**
     * 性别
     */
    public static Byte getGender(String idcard) {
        if (idcard.length() == 15) {
            idcard = IdentityValidatorUtils.convertIdcarBy15bit(idcard);
        }
        // 获取性别
        String id17 = idcard.substring(16, 17);
        if (Integer.parseInt(id17) % 2 != 0) {//男
            return 1;
        } else {//女    
            return 2;
        }
    }

    /**
     * 生日
     */
    public static Date getBirthday(String idcard) {
        if (idcard.length() == 15) {
            idcard = IdentityValidatorUtils.convertIdcarBy15bit(idcard);
        }
        // 获取出生日期
        String birthday = idcard.substring(6, 14);
        return DateUtils.getDate(birthday, "yyyyMMdd");
    }

    @Override
    public String toString() {
        return "省份：" + this.province + ",性别：" + this.gender + ",出生日期："
                + this.birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @return the region
     */
    public String getRegion() {
        return region;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * @return the day
     */
    public int getDay() {
        return day;
    }

}  
