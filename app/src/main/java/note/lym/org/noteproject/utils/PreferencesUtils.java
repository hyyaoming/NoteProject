package note.lym.org.noteproject.utils;

import android.content.Context;
import android.preference.PreferenceManager;

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
     * @param context 上下文
     * @return 返回true可以播放，反之不能播放
     */
    public static boolean isMusicPlay(Context context) {
        return getBoolean(context, UserSetFragment.BGM_MUSIC, true);
    }

    /**
     * 是否开启wifi浏览图片模式
     *
     * @param context 上下文
     * @return true在WiFi时才加载图片, false则不加载
     */
    public static boolean isLoadImage(Context context) {
        return getBoolean(context, UserSetFragment.LOAD_IMAGE, true);
    }

    /**
     * 获取一个Boolean类型从值
     *
     * @param context      上下文
     * @param key          key
     * @param defaultValue 默认值
     * @return 返回boolean
     */
    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(key, defaultValue);
    }

}
