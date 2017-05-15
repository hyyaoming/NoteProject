package note.lym.org.noteproject.utils;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

    /**
     * 过滤所有空白
     * 包括：空格" "，换行，\t（一个tab空格）
     *
     * @param str 字符串
     * @return String
     */
    public static String trimAllWhitespace(String str) {
        return str.replaceAll(" ", "").replaceAll("\n", "").replaceAll("\t", "").toString();
    }

    /**
     * 过滤"\n"空白
     *
     * @param str 字符串
     * @return String
     */
    public static String replaceWhiteSpace(String str) {
        return str.replaceAll("\n", "");
    }

    public static boolean isEmpty(String str) {
        return (str == null || str.length() == 0);
    }

    public static String utf8Encode(String str) {
        if (!isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("UnsupportedEncodingException occurred. ", e);
            }
        }
        return str;
    }

    /**
     * 计算图片要显示的高度
     *
     * @param pixel 原始分辨率
     * @param width 要显示的宽度
     * @return
     */
    public static int calcPhotoHeight(String pixel, int width) {
        int height = -1;
        int index = pixel.indexOf("*");
        if (index != -1) {
            try {
                int widthPixel = Integer.parseInt(pixel.substring(0, index));
                int heightPixel = Integer.parseInt(pixel.substring(index + 1));
                height = (int) (heightPixel * (width * 1.0f / widthPixel));
            } catch (NumberFormatException e) {
                return -1;
            }
        }
        return height;
    }
}
