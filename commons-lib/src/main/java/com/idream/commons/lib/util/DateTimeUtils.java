package com.idream.commons.lib.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * @author hejiang
 */
public class DateTimeUtils {

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_LINE = "yyyy_MM_dd";
    public static final String YYYY_MM_DD_CHINESE = "yyyy年MM月dd日";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    public static final String YYYYMMDD_HHMM = "yyyyMMdd_HHmm";
    public static final String HH_MM_SS = "HH:mm:ss";
    public static final String HHMMSS = "HHmmss";

    /**
     * 获取系统当前日期
     */
    public static Date getCurrentDate() {
        return new Date();
    }


    /**
     * 获取系统当前日期
     */
    public static LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now();
    }

    /**
     * 根据时间格式返回对应的String类型的时间
     *
     * @param format
     */
    public static String getCurDateTime(String format) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        String dataTime = now.format(dateTimeFormatter);
        return dataTime;
    }

    /**
     * 得到当前日期
     *
     * @return String 当前日期 yyyy-MM-dd HH:mm:ss格式
     */
    public static String getCurDateTimeFull() {
        return getCurDateTime(YYYY_MM_DD_HH_MM_SS);
    }


    /**
     * 得到当前日期
     *
     * @return String 当前日期 yyyyMMdd格式
     */
    public static String getCurDateYYYYMMDD() {
        return getCurDateTime(YYYYMMDD);
    }

    /**
     * 判断是否是今天
     *
     * @param strDate
     */
    public static boolean isCurrentDay(String strDate) {
        boolean bRet = false;
        LocalDate strLocalDate = LocalDate.parse(strDate);
        if (LocalDate.now().getYear() == strLocalDate.getYear()) {
            MonthDay monthDay = MonthDay.from(strLocalDate);
            MonthDay today = MonthDay.from(LocalDate.now());
            return monthDay.equals(today);
        }
        return bRet;
    }

    /**
     * 获取几小时后的时间
     *
     * @param hour
     * @param format 格式只能是时间格式,不能带日期
     */
    public static String getAfterDateTime(int hour, String format) {
        LocalTime localTime = LocalTime.now().plusHours(hour);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        String dataTime = localTime.format(dateTimeFormatter);
        return dataTime;
    }


    /**
     * 日期转字符串
     *
     * @param date   日期时间
     * @param format 格式
     */
    public static String parseDateToString(Date date, String format) {
        if (date != null) {
            Instant instant = date.toInstant();
            ZoneId zone = ZoneId.systemDefault();
            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
            return localDateTime.format(dateTimeFormatter);
        }
        return null;
    }


    /**
     * date 转换成 yyyy-MM-dd hh:MM:ss格式的字符串
     *
     * @param date
     */
    public static String parseDateToString(Date date) {
        return parseDateToString(date, YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 字符串转日期
     *
     * @return Date
     */
    public static Date parseStringToDate(String dateStr, String format) {
        DateFormat sdf = new SimpleDateFormat(format);
        Date dd1 = null;
        try {
            dd1 = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dd1;
    }

    /**
     * 由String型日期转成format形式String
     *
     * @param format1 原先格式
     * @param format2 转化格式
     *
     * @return String
     */
    public static String parseFormatDateString(String dateStr, String format1, String format2) {
        if (dateStr == null) {
            return "";
        }
        if (dateStr.length() >= format1.length() && format1.length() >= format2.length()) {
            return parseDateToString(parseStringToDate(dateStr, format1), format2);
        }
        return dateStr;
    }

    /**
     * 得到当前日期的前N天时间
     *
     * @param format 只能穿日期类型的格式
     * @param day
     */
    public static String beforeNDaysDate(String format, int day) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        if (day > 0) {
            return LocalDateTime.now().minusDays(day).format(dateTimeFormatter);
        }
        return null;
    }

    /**
     * 获得N个月后的日期
     *
     * @param theDate 时间字符串
     * @param month   月份
     * @param format  格式
     */
    public static String afterNMonthDate(String theDate, int month, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(theDate, dateTimeFormatter)
                .plusMonths(month)
                .format(dateTimeFormatter);

    }

    /**
     * 得到N天后的日期
     *
     * @param theDate 时间字符串
     * @param nDayNum N天
     * @param format  格式
     */
    public static String afterNDaysDate(String theDate, Integer nDayNum, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(theDate, dateTimeFormatter)
                .plusDays(nDayNum)
                .format(dateTimeFormatter);
    }

    /**
     * 得到N小时后的日期
     *
     * @param theDate  时间字符串
     * @param nHourNum N小时
     * @param format   格式
     */
    public static String afterNHoursDate(String theDate, Integer nHourNum, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(theDate, dateTimeFormatter)
                .plusHours(nHourNum)
                .format(dateTimeFormatter);
    }

    /**
     * 得到N分钟后的日期
     *
     * @param theDate 时间字符串
     * @param nMinNum N分钟
     * @param format  格式
     */
    public static String afterNMinsDate(String theDate, Integer nMinNum, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(theDate, dateTimeFormatter)
                .plusMinutes(nMinNum)
                .format(dateTimeFormatter);
    }

    /**
     * 得到N秒后的日期
     *
     * @param theDate 时间字符串
     * @param nSecNum N秒
     * @param format  格式
     */
    public static String afterNSecondsDate(String theDate, Integer nSecNum, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(theDate, dateTimeFormatter)
                .plusSeconds(nSecNum)
                .format(dateTimeFormatter);
    }

    /**
     * 比较两个字符串格式日期大小,带格式的日期
     *
     * @param strdat1
     * @param strdat2
     * @param format
     */
    public static boolean isBefore(String strdat1, String strdat2, String format) {
        try {
            Date dat1 = parseStringToDate(strdat1, format);
            Date dat2 = parseStringToDate(strdat2, format);
            return dat1.before(dat2);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 比较两个字符串格式日期大小,带格式的日期,返回毫秒值
     *
     * @param strdat1 比较的日期字符串1
     * @param strdat2 比较的日期字符串2
     * @param format  日期格式
     */
    public static long isBefore_int(String strdat1, String strdat2, String format) {
        long result = 0;
        try {
            Date dat1 = parseStringToDate(strdat1, format);
            Date dat2 = parseStringToDate(strdat2, format);
            return dat2.getTime() - dat1.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 计算两个日期相差天数
     *
     * @param end   结束日期
     * @param start 开始日期
     */
    public static int getSubDays(String end, String start) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        Long between = ChronoUnit.DAYS.between(startDate, endDate);
        return between.intValue();
    }

    /**
     * 比较两个日期类型 大小
     *
     * @param startDateStr
     * @param endDateStr
     */
    public static int compareDate(String startDateStr, String endDateStr) {
        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);
        int result = startDate.compareTo(endDate);
        return result;
    }

    public static int compareDate(String startDateStr, Date endDate) {
        LocalDate startLocalDate = LocalDate.parse(startDateStr);
        Instant instant = endDate.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate endLocalDate = localDateTime.toLocalDate();
        return startLocalDate.compareTo(endLocalDate);
    }

    public static int compareDate(Date startDate, String endDate) {
        LocalDate endLocalDate = LocalDate.parse(endDate);
        Instant instant = startDate.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate startLocalDate = localDateTime.toLocalDate();
        return startLocalDate.compareTo(endLocalDate);
    }


    public static int compareDate(Date startDate, Date endDate) {
        ZoneId zone = ZoneId.systemDefault();

        //开始时间
        Instant startInstant = startDate.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(startInstant, zone);
        LocalDate startLocalDate = localDateTime.toLocalDate();

        //结束时间
        Instant endInstant = endDate.toInstant();
        localDateTime = LocalDateTime.ofInstant(endInstant, zone);
        LocalDate endLocalDate = localDateTime.toLocalDate();
        return startLocalDate.compareTo(endLocalDate);
    }

    /**
     * 比较时间格式 大小
     *
     * @param startTimeStr
     * @param endTimeStr
     */
    public static int compareTime(String startTimeStr, String endTimeStr) {
        LocalTime startTime = LocalTime.parse(startTimeStr);
        LocalTime endTime = LocalTime.parse(endTimeStr);
        int result = startTime.compareTo(endTime);
        return result;
    }

    public static int compareTime(String startTimeStr, Date endTime) {
        LocalTime startTime = LocalTime.parse(startTimeStr);

        ZoneId zone = ZoneId.systemDefault();
        Instant instant = endTime.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalTime endLocalTime = localDateTime.toLocalTime();
        return startTime.compareTo(endLocalTime);
    }

    public static int compareTime(Date startTime, String endTimeStr) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = startTime.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalTime startLocalTime = localDateTime.toLocalTime();
        LocalTime endTime = LocalTime.parse(endTimeStr);
        return startLocalTime.compareTo(endTime);
    }

    public static int compareTime(Date startTime, Date endTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant satrtInstant = startTime.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(satrtInstant, zone);
        LocalTime startLocalTime = localDateTime.toLocalTime();

        Instant endInstant = endTime.toInstant();
        localDateTime = LocalDateTime.ofInstant(endInstant, zone);
        LocalTime endLocalTime = localDateTime.toLocalTime();
        return startLocalTime.compareTo(endLocalTime);
    }

    /**
     * 获取某一天的结束时间
     *
     * @param date
     */
    public static Date getDateEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 将秒转换为小时分秒等
     *
     * @param sec
     */
    public String changeTime(int sec) {
        String temp = "";
        if (sec < 60) {
            temp = "" + sec + "秒";
        } else if (sec < 3600) {
            temp = "" + sec / 60 + "分" + sec % 60 + "秒";
        } else {
            temp = "" + sec / 3600 + "小时" + (sec % 3600) / 60 + "分" + sec % 60 + "秒";
        }
        return temp;
    }


    public static String getTimeDiff(Date endTime, Date startTime) throws Exception {
        long timeDiff = endTime.getTime() - startTime.getTime();
        String returnStr = "";
        long day = timeDiff / (24 * 60 * 60 * 1000);
        if (day > 0) {
            returnStr += (day + "天");
        }
        long hour = (timeDiff / (60 * 60 * 1000) - day * 24);
        if (hour > 0) {
            returnStr += (hour + "小时");
        }
        long min = ((timeDiff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        if (min > 0) {
            returnStr += (min + "分");
        }
        long s = (timeDiff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        if (s > 0) {
            returnStr += (s + "秒");
        }
        return returnStr;
    }

    /**
     * @param : comparedDate
     */
    public static boolean isOutDate(Date comparedDate) {
        boolean result = false;
        if (compareDate(comparedDate, new Date()) < 0) {
            result = true;
        }
        return result;
    }

    /**
     * 获取给定日期的后N天日期
     *
     * @param date
     * @param num
     */
    public static Date getAfterDate(Date date, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, num);
        return calendar.getTime();
    }

    /**
     * 获取给定日期的后N分钟日期
     *
     * @param date
     * @param min
     */
    public static Date getAfterMin(Date date, int min) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, min);
        return calendar.getTime();
    }

    /**
     * @param getDate 取日期
     * @param getTime 取时分秒
     *
     * @return Date
     */
    public static Date mergeDateTime(Date getDate, Date getTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = getDate.toInstant();
        LocalDate localDate = instant.atZone(zoneId).toLocalDate();

        Instant instant2 = getTime.toInstant();
        LocalTime localTime = instant2.atZone(zoneId).toLocalTime();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        return Date.from(localDateTime.atZone(zoneId).toInstant());
    }
}