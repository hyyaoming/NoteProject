package note.lym.org.noteproject.utils;

import android.content.Context;
import android.net.ConnectivityManager;

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

}