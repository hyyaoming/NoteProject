package note.lym.org.noteproject.utils;

import java.util.Random;

import note.lym.org.noteproject.R;

/**
 * 默认图片工厂类
 *
 * @author yaoming.li
 * @since 2017-05-04 16:55
 */
public class DefIconFactory {

    private static int[] defaultImage = new int[]{
            R.drawable.ic_default_1, R.drawable.ic_default_2, R.drawable.ic_default_3, R.drawable.ic_default_4, R.drawable.ic_default_5};

    private static Random sRandom = new Random();

    private DefIconFactory() {
        throw new RuntimeException("cannot be instantiated");
    }

    public static int iconDefault() {
        int x = sRandom.nextInt(defaultImage.length);
        return defaultImage[x];
    }

}
