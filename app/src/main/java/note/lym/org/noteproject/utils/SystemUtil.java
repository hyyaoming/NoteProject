package note.lym.org.noteproject.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import note.lym.org.noteproject.app.NoteApplication;

/**
 * 系统工具类
 */
public class SystemUtil {


    /**
     * 检查是否有可用网络
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) NoteApplication.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }

    /**
     * 判断WIFI网络是否可用
     */
    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null && mWiFiNetworkInfo.isAvailable()) {
                return mWiFiNetworkInfo.isConnected();
            }
        }
        return false;
    }

}