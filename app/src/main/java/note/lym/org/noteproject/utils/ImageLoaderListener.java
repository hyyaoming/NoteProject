package note.lym.org.noteproject.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * banner加载图片
 *
 * @author yaoming.li
 * @since 2017-05-16 22:16
 */
public class ImageLoaderListener extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path).crossFade().into(imageView);
    }
}
