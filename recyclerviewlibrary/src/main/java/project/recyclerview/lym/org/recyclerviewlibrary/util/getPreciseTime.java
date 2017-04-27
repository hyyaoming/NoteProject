package project.recyclerview.lym.org.recyclerviewlibrary.util;

/**
 * Desp.
 *
 * @author yaoming.li
 * @version 8.1.0
 * @since 2017-03-17 17:09
 */
public class getPreciseTime {

    private static final String TIMER_ZERO = "00:00";
    private static final int SIXTY = 60;
    private static final int TEN = 10;
    private static final int ONE_THOUSAND = 1000;


    public static  String getLeftTime(int leftTime) {
        if (leftTime <= 0) {
            return TIMER_ZERO;
        }
        int scd = leftTime / ONE_THOUSAND; // 得到多少秒;
        if (leftTime < ONE_THOUSAND) {
            scd = 1;
        }
        int min = scd / SIXTY; // 得到多少分
        scd = scd % SIXTY; // 得到多少秒
        StringBuffer sb = new StringBuffer();
        if (min < TEN) {
            sb.append("0");
        }
        sb.append(min);
        sb.append(":");
        if (scd < TEN) {
            sb.append("0");
        }
        sb.append(scd);
        return sb.toString();
    }
}
