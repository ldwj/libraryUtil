package com.ldwj.library.util.utils;

import android.os.SystemClock;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by lishuai on 2019/2/25.
 */

public class DateTimeUitl {
    public static final long MILLIS_FOR_ONE_DAY = 86400000;

    public static final String FORMAT_DATE_YYMMDD = "yyyy-MM-dd";

    public static final String FORMAT_DATE_YYPMMPDD = "yyyy.MM.dd";

    public static final String FORMAT_DATE_YYMMDD_HHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_DATE_YYMMDD_HHMM = "yyyy-MM-dd HH:mm";

    public static final String FORMAT_DATE_MMDD_HHMM = "MM-dd HH:mm";

    public static final String FORMAT_DATE_HHMM = "HH:mm";

    private static final String TAG = DateTimeUitl.class.getSimpleName();

    /**
     * 获取指定时间字符串对应的毫秒. <br/>
     * @param date
     * @param template
     * @return
     */
    public static long getDateTimes(String date, String template) {
        if (date == null || ("").equals(date) || template == null || ("").equals(template)) {
            return -1;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(template, Locale.CHINA);
        try {
            return sdf.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 指定个是的日期字符串转成Date类型 String --> Date
     * @param date
     * @param template
     * @return
     */
    public static Date getDateFromString(String date,String template){
        SimpleDateFormat sdf = new SimpleDateFormat(template, Locale.CHINA);
        Date mDate= null;
        try {
            mDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mDate;
    }

    /**
     * 取指定日期为星期几.
     *
     * @param strDate 指定日期
     * @param inFormat 指定日期格式
     * @return String   星期几
     */
    public static String getWeekNumber(String strDate,String inFormat) {
        String week = "星期日";
        Calendar calendar = new GregorianCalendar();
        DateFormat df = new SimpleDateFormat(inFormat);
        try {
            calendar.setTime(df.parse(strDate));
        } catch (Exception e) {
            return "错误";
        }
        int intTemp = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        switch (intTemp){
            case 0:
                week = "星期日";
                break;
            case 1:
                week = "星期一";
                break;
            case 2:
                week = "星期二";
                break;
            case 3:
                week = "星期三";
                break;
            case 4:
                week = "星期四";
                break;
            case 5:
                week = "星期五";
                break;
            case 6:
                week = "星期六";
                break;
        }
        return week;
    }
    /**
     * 获取指定天数的毫秒增量. <br/>
     * @param days
     * @return
     */
    public static long getIncrementMillis(int days) {
        return MILLIS_FOR_ONE_DAY * days;
    }

    /**
     * 把指定的时间转换为对应的格式化字符串. <br/>
     * @param times
     * @param template
     * @return
     */
    public static String long2String(long times, String template) {
        if (times < 1 || template == null || ("").equals(template)) {
            return null;
        }
        Date date = new Date(times);
        SimpleDateFormat sdf = new SimpleDateFormat(template, Locale.CHINA);
        return sdf.format(date);
    }

    /**
     * 把指定的日期字符串转换为对应的date对象
     *
     * @param date     日期字符串
     * @param template 日期格式
     * @return
     */
    public static Date StringToDate(String date, String template) {
        SimpleDateFormat sdf = new SimpleDateFormat(template, Locale.CHINA);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            Log.i(TAG, "StringToDate()--->日期转换发生异常,date=" + date + ", template=" + template);
            return null;
        }
    }

    /**
     * 按格式生成日期
     *
     * @param year
     * @param month
     * @param day
     * @param format
     * @return
     */
    public static String getDateWithFormat(int year, int month, int day, String format) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DATE, day);
        return new SimpleDateFormat(format, Locale.CHINA).format(c.getTime());
    }

    /**
     * 把时间字符串转换为毫秒时间. <br/>
     * @param dateString
     * @param pattern
     * @return
     */
    public static long getTime(String dateString, String pattern) {
        if (dateString == null || ("").equals(dateString) || pattern == null
                || ("").equals(pattern)) {
            return 0;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.CHINA);
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
        return date.getTime();
    }

    /***
     * compareDate:比较两个时间的先后.
     * @param date 指定时间
     * @param targetDate 比较目标时间
     * @param style 时间指定格式
     * @return
     *              1 指定时间大于目标时间,
     *              0相等,
     *              -1指定时间小于目标时间
     */
    public static int compareDate(String date, String targetDate, String style) {
        long time = DateTimeUitl.getTime(date, style);
        long targetTime = DateTimeUitl.getTime(targetDate, style);

        if (time > targetTime) {
            return 1;
        }
        if (time == targetTime) {
            return 0;
        }
        return -1;
    }

    /**
     * 获取两个时间的时间间隔 单位：秒
     * @param startTime 当前时间
     * @param endTime   开始时间
     * @param style     时间格式
     * @return
     */
    public static int getPeriodDate(String startTime, String endTime, String style) {
        long time = DateTimeUitl.getTime(startTime, style);
        long targetTime = DateTimeUitl.getTime(endTime, style);
        int ll = (int) ((time - targetTime) / 1000 );
        return ll;
    }

    /**
     * 获取当前时间
     *
     * @param pattern 指定的格式
     * @return
     */
    public static String getCurrentDate(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.CHINA);
        return sdf.format(new Date());
    }

    /**
     * 获取指定时间的指定时间字符串
     *
     * @param pattern 指定的格式
     * @return
     */
    public static String getDateToString(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.CHINA);
        return sdf.format(date);
    }


    /**
     * 已知的一个时间字符串 转换成想要的数据格式
     *
     * @param currentStr    原来的时间字符串
     * @param currentFomart 原来的时间字符串格式
     * @param desFomart     目标的时间字符串格式
     * @return
     */
    public static String StringToStringFomart(String currentStr, String currentFomart, String desFomart) {
        Date data = StringToDate(currentStr, currentFomart);
        return new SimpleDateFormat(desFomart).format(data);
    }


    /*
     * 判断是否是闰年
     */
    public static boolean isRn(int year) {
        /* 判断是否是闰年 */
        if (year % 4 == 0 && !(year % 100 == 0) || year % 400 == 0) {
            return true; // 闰年
        } else {
            return false;// 平年
        }
    }

    /**
     * 返回当月的天数
     * @param year
     * @param month
     * @return
     */
    public static int getDaysOfMonth(int year, int month) {
        int days = 0;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                days = 31;
                break;
            case 2:
                if (isRn(year)) {
                    days = 29;
                } else {
                    days = 28;
                }
                break;
            default:
                days = 30;
                break;
        }
        return days;
    }

    /**
     * 根据当天的数据，返回年月日的字符串(位数不够的补“0”)
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static String get_Year_Month_Day(int year, int month, int day) {
        String myear = "" + year;
        String mmonth = "";
        String mday = "";
        if (day < 10)
            mday = "0" + day;
        else
            mday = "" + day;
        if (month < 10)
            mmonth = "0" + month;
        else
            mmonth = "" + month;

        return myear + "-" + mmonth + "-" + mday;
    }

    /*
     * 毫秒转化时分秒毫秒
     */
    public static String formatTime(Long ms) {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        // Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * message_arrow_icon;

        StringBuffer sb = new StringBuffer();
        if (day >= 0) {
            if (day > 99) {// 天
                sb.append(day + "");
            } else {
                if (day > 9) {
                    sb.append("0" + day + "天");
                } else {
                    sb.append("00" + day + "天");
                }
            }
        }
        if (hour >= 0) {// 小时
            if (hour > 9) {
                sb.append(hour + "小时");
            } else {
                sb.append("0" + hour + "小时");
            }
        }
        if (minute >= 0) {// 分
            if (minute > 9) {
                sb.append(minute + "分");
            } else {
                sb.append("0" + minute + "分");
            }
        }
        // if(second >= 0) {//秒
        // if(second>9){
        // sb.append(second+"秒");
        // }else{
        // sb.append("0"+second+"秒");
        // }
        // }
        // if(milliSecond > 0) {
        // sb.append(milliSecond+"毫秒");
        // }
        return sb.toString();
    }

    /*
     * 毫秒转化时分秒毫秒
     */
    public static String getsecond(Long ms) {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        StringBuffer sb = new StringBuffer();
        if (second >= 0) {// 秒
            if (second > 9) {
                sb.append(second + "");
            } else {
                sb.append("0" + second + "");
            }
        }
        return sb.toString();
    }

    /*
     * 毫秒转化时分秒毫秒
     */
    public static String getminute(Long ms) {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;

        StringBuffer sb = new StringBuffer();
        if (minute >= 0) {// 分
            if (minute > 9) {
                sb.append(minute + "");
            } else {
                sb.append("0" + minute + "");
            }
        }
        return sb.toString();
    }

    public static String gethour(Long ms) {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;

        StringBuffer sb = new StringBuffer();
        if (hour >= 0) {// 小时
            if (hour > 9) {
                sb.append(hour + "");
            } else {
                sb.append("0" + hour + "");
            }
        }
        return sb.toString();
    }

    public static String getday(Long ms) {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;
        Long day = ms / dd;
        StringBuffer sb = new StringBuffer();
        if (day >= 0) {
            if (day > 99) {// 天
                sb.append(day + "");
            } else {
                if (day > 9) {
                    sb.append("0" + day + "");
                } else {
                    sb.append("00" + day + "");
                }
            }
        }
        return sb.toString();
    }

    /**
     * 当月第一天
     *
     * @return
     */
    public static String getFirstDayTime() {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_DATE_YYMMDD);
        Calendar calendar = Calendar.getInstance();
        Date theDate = calendar.getTime();

        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gcLast.getTime());
        StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
        return str.toString();

    }

    /**
     * 当月最后一天
     *
     * @return
     */
    public static String getLastDayTime() {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_DATE_YYMMDD);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date theDate = calendar.getTime();
        String s = df.format(theDate);
        StringBuffer str = new StringBuffer().append(s).append(" 23:59:59");
        return str.toString();

    }

    /**
     * 得到几天前的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateBefore(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }

    /**
     * 得到几天后的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }



    /**
     * 距离今天多久
     * @param date
     * @return
     *
     */
    public static String fromToday(String date) {
        long ONE_MINUTE = 60;
        long ONE_HOUR = 3600;
        long ONE_DAY = 86400;
        long ONE_MONTH = 2592000;
        long ONE_YEAR = 31104000;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getDateFromString(date,FORMAT_DATE_YYMMDD_HHMMSS));

        long time = getTime(date,FORMAT_DATE_YYMMDD_HHMMSS) / 1000;
        long now = new Date().getTime() / 1000;
        long ago = now - time;
        if (ago <= ONE_HOUR)
            return ago / ONE_MINUTE + "分钟前";
        else if (ago <= ONE_DAY)
            return ago / ONE_HOUR + "小时" + (ago % ONE_HOUR / ONE_MINUTE)
                    + "分钟前";
        else if (ago <= ONE_DAY * 2)
            return "昨天" + calendar.get(Calendar.HOUR_OF_DAY) + "点"
                    + calendar.get(Calendar.MINUTE) + "分";
        else if (ago <= ONE_DAY * 3)
            return "前天" + calendar.get(Calendar.HOUR_OF_DAY) + "点"
                    + calendar.get(Calendar.MINUTE) + "分";
        else if (ago <= ONE_MONTH) {
            long day = ago / ONE_DAY;
            return day + "天前" + calendar.get(Calendar.HOUR_OF_DAY) + "点"
                    + calendar.get(Calendar.MINUTE) + "分";
        } else if (ago <= ONE_YEAR) {
            long month = ago / ONE_MONTH;
            long day = ago % ONE_MONTH / ONE_DAY;
            return month + "个月" + day + "天前"
                    + calendar.get(Calendar.HOUR_OF_DAY) + "点"
                    + calendar.get(Calendar.MINUTE) + "分";
        } else {
            long year = ago / ONE_YEAR;
            int month = calendar.get(Calendar.MONTH) + 1;// JANUARY which is 0 so month+1
            return year + "年前" + month + "月" + calendar.get(Calendar.DATE)
                    + "日";
        }

    }
    /**
     * 格式化年月日  前补0
     * @param i
     * @param count
     * @return
     */
    public static String addZero(int i, int count) {
        if (count == 2) {
            if (i < 10) {
                return "0" + i;
            }
        } else if (count == 4) {
            if (i < 10) {
                return "000" + i;
            } else if (i < 100 && i > 10) {
                return "00" + i;
            } else if (i < 1000 && i > 100) {
                return "0" + i;
            }
        }
        return "" + i;
    }

    /**
     * 已知的一个时间字符串 转换成想要的数据格式
     *
     * @param currentStr    原来的时间字符串
     * @param currentFomart 原来的时间字符串格式
     * @param desFomart     目标的时间字符串格式
     * @return
     */
    public static String stringToStringFomart(String currentStr, String currentFomart, String desFomart) {
        Date data = StringToDate(currentStr, currentFomart);
        return new SimpleDateFormat(desFomart).format(data);
    }
    /**
     * 计算某年某月有多少天
     *
     * @param year
     * @param month
     * @return
     */
    public static int getDateNum(int year, int month) {
        Calendar time = Calendar.getInstance();
        time.clear();
        time.set(Calendar.YEAR, year + 1900);
        time.set(Calendar.MONTH, month);
        return time.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    //根据日期取得星期几
    public static String getWeek(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        String week = sdf.format(date);
        return week;
    }

    private static String mYear;

    private static String mMonth;

    private static String mDay;

    private static String mWay;

    public static String getDateAndWeek()
    {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT"));

        mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
        mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));

        if ("1".equals(mWay))
        {
            mWay = "天";
        }
        else if ("2".equals(mWay))
        {
            mWay = "一";
        }
        else if ("3".equals(mWay))
        {
            mWay = "二";
        }
        else if ("4".equals(mWay))
        {
            mWay = "三";
        }
        else if ("5".equals(mWay))
        {
            mWay = "四";
        }
        else if ("6".equals(mWay))
        {
            mWay = "五";
        }
        else if ("7".equals(mWay))
        {
            mWay = "六";
        }
        return mYear + "年" + mMonth + "月" + mDay + "日" + "\t星期" + mWay;
    }

    public static String getDate()
    {
        try
        {

            final Calendar c = Calendar.getInstance();
            mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
            mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
            mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码

        }
        catch (Exception e)
        {}
        return mYear + "-" + mMonth + "-" + mDay + "";
    }

//    public static void setDateTime(String datetime)
//    {
//        try
//        {
//            Process process = Runtime.getRuntime().exec("su");
//            DataOutputStream os = new DataOutputStream(process.getOutputStream());
//            os.writeBytes("setprop persist.sys.timezone GMT\n");
//            os.writeBytes("/system/bin/date -s " + datetime + "\n");
//            os.writeBytes("clock -w\n");
//            os.writeBytes("exit\n");
//            os.flush();
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//    }

    public static void setDateTime(int year, int month, int day, int hour, int minute)
            throws IOException, InterruptedException
    {

        requestPermission();

        Calendar c = Calendar.getInstance();

        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, day);
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, minute);

        SystemClock.setCurrentTimeMillis(c.getTimeInMillis());

        long now = Calendar.getInstance().getTimeInMillis();
        Log.d("tm", "now tm=" + now);

    }

    public static void setDate(int year, int month, int day)
            throws IOException, InterruptedException
    {

        requestPermission();

        Calendar c = Calendar.getInstance();

        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        long when = c.getTimeInMillis();

        SystemClock.setCurrentTimeMillis(when);

        long now = Calendar.getInstance().getTimeInMillis();
        // Log.d(TAG, "set tm="+when + ", now tm="+now);

        if (now - when > 1000) throw new IOException("failed to set Date.");
    }

    public static void setTime(int hour, int minute)
            throws IOException, InterruptedException
    {

        requestPermission();

        Calendar c = Calendar.getInstance();

        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, minute);
        long when = c.getTimeInMillis();

        SystemClock.setCurrentTimeMillis(when);

        long now = Calendar.getInstance().getTimeInMillis();
        // Log.d(TAG, "set tm="+when + ", now tm="+now);

        if (now - when > 1000) throw new IOException("failed to set Time.");
    }

    static void requestPermission()
            throws InterruptedException, IOException
    {
        createSuProcess("chmod 666 /dev/alarm").waitFor();
    }

    static Process createSuProcess()
            throws IOException
    {
        File rootUser = new File("/system/xbin/ru");
        if (rootUser.exists())
        {
            return Runtime.getRuntime().exec(rootUser.getAbsolutePath());
        }
        else
        {
            return Runtime.getRuntime().exec("su");
        }
    }

    static Process createSuProcess(String cmd)
            throws IOException
    {

        DataOutputStream os = null;
        Process process = createSuProcess();

        try
        {
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(cmd + "\n");
            os.writeBytes("exit $?\n");
        }
        finally
        {
            if (os != null)
            {
                try
                {
                    os.close();
                }
                catch (IOException e)
                {}
            }
        }

        return process;
    }

    public static void main(String[] args)
    {
        String webUrl1 = "http://www.bjtime.cn";// bjTime
        String webUrl2 = "http://www.baidu.com";// 百度
        String webUrl3 = "http://www.taobao.com";// 淘宝
        String webUrl4 = "http://www.ntsc.ac.cn";// 中国科学院国家授时中心
        String webUrl5 = "http://www.360.cn";// 360
        String webUrl6 = "http://www.beijing-time.org";// beijing-time
        System.out.println(getNetDatetime(webUrl1) + " [bjtime]");
        System.out.println(getNetDatetime(webUrl2) + " [百度]");
        System.out.println(getNetDatetime(webUrl3) + " [淘宝]");
        System.out.println(getNetDatetime(webUrl4) + " [中国科学院国家授时中心]");
        System.out.println(getNetDatetime(webUrl5) + " [360安全卫士]");
        System.out.println(getNetDatetime(webUrl6) + " [beijing-time]");
    }

    /**
     * 获取指定网站的日期时间
     */
    private static String getNetDatetime(String webUrl)
    {
        try
        {
            URL url = new URL(webUrl);// 取得资源对象
            URLConnection uc = url.openConnection();// 生成连接对象
            uc.connect();// 发出连接
            long ld = uc.getDate();// 读取网站日期时间
            Date date = new Date(ld);// 转换为标准时间对象
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);// 输出北京时间
            return sdf.format(date);
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
