package note.lym.org.noteproject.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import note.lym.org.noteproject.app.NoteApplication;
import note.lym.org.noteproject.app.constant.Constants;

import static android.content.Context.WINDOW_SERVICE;

/**
 * 系统工具类
 *
 * @author yaoming.li
 * @since 2018年3月5日16:34:17
 */
public class SystemUtil {


    /**
     * 检查是否有可用网络
     *
     * @return netWork is connected
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) NoteApplication.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return (connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null) != null;
    }

    /**
     * 判断WIFI网络是否可用
     *
     * @param context the is context
     * @return isConnected
     */
    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager != null ? mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI) : null;
            if (mWiFiNetworkInfo != null && mWiFiNetworkInfo.isAvailable()) {
                return mWiFiNetworkInfo.isConnected();
            }
        }
        return false;
    }

    /**
     * 获取屏幕像素
     *
     * @return arr  int[]
     */
    public static int[] getScreenDensity() {
        int[] arr = new int[]{};
        arr[0] = NoteApplication.getInstance().getResources().getDisplayMetrics().widthPixels;
        arr[1] = NoteApplication.getInstance().getResources().getDisplayMetrics().heightPixels;
        return arr;
    }

    /**
     * 获取屏幕高度
     *
     * @return height
     */
    public static int getScreenHeight() {
        WindowManager wm = (WindowManager) Constants.CONTEXT.getSystemService(WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        if (wm != null) {
            wm.getDefaultDisplay().getMetrics(dm);
        }
        return dm.heightPixels;
    }

    /**
     * 获取屏幕宽度
     *
     * @return width
     */
    public static int getScreenWidth() {
        WindowManager wm = (WindowManager) Constants.CONTEXT.getSystemService(WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        if (wm != null) {
            wm.getDefaultDisplay().getMetrics(dm);
        }
        return dm.widthPixels;
    }

}