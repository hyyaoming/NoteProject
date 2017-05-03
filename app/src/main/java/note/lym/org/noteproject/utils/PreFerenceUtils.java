package note.lym.org.noteproject.utils;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * 轻量级存储工具类
 *
 * @author yaoming.li
 * @since 2017-05-03 15:45
 */
public class PreferenceUtils {

    private PreferenceUtils() {
        throw new RuntimeException("PreferenceUtils cannot be initialized!");
    }

    public static void putBoolean(Context context, boolean flag, String name) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(name, flag).apply();
    }

    public static boolean getBoolean(Context context, String key) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(key, false);
    }

    public static void putInt(Context context, String key, int value) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(key, value).apply();
    }

    public static int getInt(Context context, String key) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(key, -1);
    }

    public static void putString(Context context, String key, String value) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(key, value).apply();
    }

    public static String getString(Context context, String key) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(key, null);
    }
}