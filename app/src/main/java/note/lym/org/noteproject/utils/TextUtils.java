package note.lym.org.noteproject.utils;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串操作类
 *
 * @author yaoming.li
 * @since 2017-04-28 11:32
 */
public class TextUtils {
    /**
     * 匹配关键字并改变颜色
     *
     * @param content 文本内容
     * @param key     关键字
     * @param color   颜色
     * @return 返回改变后的色值
     */
    public static SpannableString marryKeyChangeColor(String content, String key, int color) {
        SpannableString sp;
        try {
            sp = new SpannableString(content);
            Pattern pattern = Pattern.compile(key);
            Matcher matcher = pattern.matcher(sp);
            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                sp.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        } catch (Exception e) {
            return null;
        }
        return sp;
    }

}
