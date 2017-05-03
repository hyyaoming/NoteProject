package project.recyclerview.lym.org.recyclerviewlibrary.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Desp.
 *
 * @author yaoming.li
 * @version 8.3.0
 * @since 2017-04-26 13:43
 */
public class DateUtils {
    public static final long m_second = 1000;
    public static final long m_minute = m_second * 60;
    public static final long m_hour = m_minute * 60;
    public static final long m_day = m_hour * 24;
    private static String[] cnweek = { "", "周日", "周一", "周二", "周三", "周四", "周五", "周六" };

    public static long GEWARA_TIME = System.currentTimeMillis();

    public static long LOCAL_TIME = System.currentTimeMillis();

    public static final long timeMillis(){
        return GEWARA_TIME;
    }

    public static long setGewaraTime(long time){
        GEWARA_TIME = time;
        return GEWARA_TIME;
    }

    public static void setLocalTime(long local) {
        LOCAL_TIME = local;
    }


    /**
     * 得到当前的时间，精确到毫秒,共14位 返回格式:yyyy-MM-dd
     *
     * @return String
     */
    public static String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        long time = System.currentTimeMillis();
        Date NowDate = new Date(time);
        return formatter.format(NowDate);
    }

    /**
     *
     * @description 获取当前月份的当前天数
     * @createDate 2014-7-16
     * @return
     */
    public static String getCurrentDay() {
        Date NowDate = new Date(System.currentTimeMillis());

        SimpleDateFormat formatter = new SimpleDateFormat("dd", Locale.getDefault());
        return formatter.format(NowDate);
    }

    /**
     * 得到当前的年份 返回格式:yyyy
     *
     * @return String
     */
    public static String getCurrentYear() {
        Date NowDate = new Date(System.currentTimeMillis());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy", Locale.getDefault());
        return formatter.format(NowDate);
    }

    /**
     * 得到当前的月份 返回格式:MM
     *
     * @return String
     */
    public static String getCurrentMonth() {
        Date NowDate = new Date(System.currentTimeMillis());

        SimpleDateFormat formatter = new SimpleDateFormat("MM", Locale.getDefault());
        return formatter.format(NowDate);
    }

    /**
     * 根据字串获取日期
     *
     * @description
     * @param str
     * @return
     */
    public static Date getDateFormat(String str) {
        Date date = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            date = formatter.parse(str);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * return yyyy-MM-dd
     *
     * @return
     */
    public static String getFormatDate(Date date) {
        SimpleDateFormat dateSDF = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            return dateSDF.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * return yyyy-MM-dd
     *
     * @return
     */
    public static String getFormatMonth(Date date) {
        SimpleDateFormat dateSDF = new SimpleDateFormat("MM月dd日", Locale.getDefault());
        try {
            return dateSDF.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * return yyyy-MM-dd
     *
     * @return
     */
    public static String getFormatYear(Date date) {
        SimpleDateFormat dateSDF = new SimpleDateFormat("yyyy年", Locale.getDefault());
        try {
            return dateSDF.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getFormatYear1(Date date) {
        SimpleDateFormat dateSDF = new SimpleDateFormat("yyyy", Locale.getDefault());
        try {
            return dateSDF.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    public static String getCnWeek(Date date) {
        if (date == null)
            return null;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return cnweek[c.get(Calendar.DAY_OF_WEEK)];
    }

    /**
     * return 周x hh:mm
     */
    public static String getFormatTime(Date date) {
        SimpleDateFormat dateSDF = new SimpleDateFormat("E HH:mm", Locale.getDefault());
        try {
            return dateSDF.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * return 周x
     */
    public static String getWeekTime(Date date) {
        SimpleDateFormat dateSDF = new SimpleDateFormat("E", Locale.getDefault());
        try {
            return dateSDF.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * return HH:mm
     */
    public static String getHourTime(Date date) {
        SimpleDateFormat dateSDF = new SimpleDateFormat("HH:mm", Locale.getDefault());
        try {
            return dateSDF.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * @param strDate
     * @param pattern
     * @return
     */
    public static final Date parseDate(String strDate, String pattern){
        SimpleDateFormat df = null;
        Date date = null;
        df = new SimpleDateFormat(pattern);
        try {
            date = df.parse(strDate);
            return date;
        } catch (Exception pe) {
            return null;
        }
    }

}
