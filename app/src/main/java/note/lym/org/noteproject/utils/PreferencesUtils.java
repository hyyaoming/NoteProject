package note.lym.org.noteproject.utils;

import android.content.Context;
import android.preference.PreferenceManager;

import note.lym.org.noteproject.app.NoteApplication;
import note.lym.org.noteproject.fragment.UserSetFragment;

/**
 * 存储一些简单的变量与数据
 *
 * @author yaoming.li
 * @since 2017-07-07 16:13
 */
public class PreferencesUtils {

    private PreferencesUtils() {
        throw new AssertionError();
    }

    /**
     * 是否开启背景音乐播放
     *
     * @return 返回true可以播放，反之不能播放
     */
    public static boolean isMusicPlay() {
        return getBoolean(UserSetFragment.BGM_MUSIC, true);
    }

    /**
     * 是否开启wifi浏览图片模式
     *
     * @return true在WiFi时才加载图片, false则不加载
     */
    public static boolean isLoadImage() {
        return getBoolean(UserSetFragment.LOAD_IMAGE, true);
    }

    /**
     * 获取一个Boolean类型从值
     *
     * @param key          key
     * @param defaultValue 默认值
     * @return 返回boolean
     */
    public static boolean getBoolean(String key, boolean defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(NoteApplication.getInstance()).getBoolean(key, defaultValue);
    }

    /**
     * 存储一个boolean类型值
     *
     * @param key     key
     * @param value   值
     * @return 返回值
     */
    public static boolean putBoolean(String key, boolean value) {
        return PreferenceManager.getDefaultSharedPreferences(NoteApplication.getInstance()).edit().putBoolean(key, value).commit();
    }

    /**
     * 存入一个字符串类型的值
     *
     * @param context 上下文
     * @param key     key
     * @param value   值
     * @return 是否成功
     */
    public static boolean setString(Context context, String key, String value) {
        return PreferenceManager.getDefaultSharedPreferences(context).edit().putString(key, value).commit();
    }

    /**
     * 获取一个指定key的字符串
     *
     * @param context      上下文
     * @param key          key值
     * @param defaultValue 默认值
     * @return 取出指定值
     */
    public static String getString(Context context, String key, String defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(key, defaultValue);
    }

}
