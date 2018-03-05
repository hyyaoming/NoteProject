package note.lym.org.noteproject.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.util.concurrent.ExecutionException;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.app.NoteApplication;
import note.lym.org.noteproject.app.constant.Constants;
import note.lym.org.noteproject.view.loading.LoadingView;

/**
 * Glide图片加载类
 * Glide这里有个坑，如果你设置了加载时的图片或者加载出错的图片时，需要关闭加载时的动画
 * Glide有两种缩放模式：1.CenterCrop、2.FitCenter。
 * CenterCrop这种模式情况下图片可能会填充满ImageView,并优先展示中间部分，但是图像不一定会展示完全，一半适用于位置宽高的情况。
 * FitCenter这种模式类似于Warp_Content包裹内容，一版适用于指定宽高的情况。
 * 这里加载图片控制一下，只有在wifi链接的情况，或者用户主动开启下载图片才开始加载
 *
 * @author yaoming.li
 * @since 2017-05-03 15:06
 */
public class GlideUtils {

    private GlideUtils() {
        throw new RuntimeException("GlideUtils cannot be initialized!");
    }

    /**
     * 就加载一张图片，啥都不设置。
     *
     * @param img 设置加载的图片
     * @param url 图片地址
     */
    public static void load(ImageView img, String url, int defaultImage) {
        if (PreferencesUtils.isLoadImage() || SystemUtil.isWifiConnected(Constants.CONTEXT)) {
            Glide.with(Constants.CONTEXT).setDefaultRequestOptions(getRequestOptions(defaultImage).centerCrop()).asBitmap().load(url).into(img);
        } else {
            img.setImageResource(defaultImage);
        }
    }

    private static RequestOptions getRequestOptions(int defaultImage) {
        return new RequestOptions()
                .placeholder(defaultImage) //加载中图片
                .dontAnimate()
                .error(defaultImage) //加载失败图片
                .fallback(defaultImage) //url为空图片
                .priority(Priority.HIGH) //优先级
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
    }

    /**
     * 图片请求返回了图片的宽高信息
     *
     * @param url          图片地址
     * @param defaultImage 加载时的图片
     * @param imageView    被加载的图片
     * @param width        图片宽度
     * @param height       图片高度
     */
    public static void loadFitCenter(String url, int defaultImage, ImageView imageView, int width, int height) {
        if (PreferencesUtils.isLoadImage() || SystemUtil.isWifiConnected(NoteApplication.getContext())) {
            Glide.with(Constants.CONTEXT).setDefaultRequestOptions(getRequestOptions(defaultImage).override(width, height)).asBitmap().load(url).into(imageView);
        } else {
            imageView.setImageResource(defaultImage);
        }
    }

    /**
     * 就加载一张图片，啥都不设置。
     *
     * @param view    设置加载的图片
     * @param url     图片地址
     * @param loading loading
     */
    public static void loadFitCenter(String url, ImageView view, LoadingView loading) {
        if (PreferencesUtils.isLoadImage() || SystemUtil.isWifiConnected(Constants.CONTEXT)) {
            Glide.with(Constants.CONTEXT).setDefaultRequestOptions(getRequestOptions(DefIconFactory.iconDefault())).asBitmap().load(url).listener(loadRequestListener(loading, view)).into(view);
        } else {
            loading.setVisibility(View.GONE);
            view.setImageResource(DefIconFactory.iconDefault());
        }
    }

    private static RequestListener loadRequestListener(final LoadingView bar, final ImageView view) {
        return new RequestListener<Bitmap>() {

            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                ToastUtils.showToast(R.string.look_network_state);
                bar.stopLoading();
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                bar.stopLoading();
                view.setImageBitmap(resource);
                return false;
            }
        };
    }

    /**
     * 加载一张gif图片,只在wifi情况下加载。
     *
     * @param url          资源
     * @param iv           图片
     * @param defaultImage 默认图
     */
    public static void loadGif(String url, ImageView iv, int defaultImage) {
        if (SystemUtil.isWifiConnected(Constants.CONTEXT)) {
            Glide.with(Constants.CONTEXT).setDefaultRequestOptions(new RequestOptions().centerCrop()).asGif().load(url).into(iv);
        } else {
            iv.setImageResource(defaultImage);
        }
    }

    /**
     * 设置加载中的图片以及加载失败的占位图
     *
     * @param url        图片地址
     * @param errorImage 加载失败时的图片
     * @param img        设置加载的图片
     */
    public static void load(String url, int errorImage, ImageView img) {
        if (PreferencesUtils.isLoadImage() || SystemUtil.isWifiConnected(Constants.CONTEXT)) {
            Glide.with(Constants.CONTEXT).setDefaultRequestOptions(getRequestOptions(errorImage)).asGif().load(url).into(img);
        } else {
            img.setImageResource(errorImage);
        }
    }

    /**
     * 加载一张圆形图片
     *
     * @param url 图片地址
     * @param iv  被加载的图片
     */
    public static void loadCircleImage(String url, final ImageView iv) {
        Glide.with(Constants.CONTEXT).setDefaultRequestOptions(getRequestOptions(DefIconFactory.iconDefault()).fitCenter()).asBitmap().load(url).apply(RequestOptions.circleCropTransform()).into(iv);
    }

    /**
     * 加载一张地址为uri的本地图片
     *
     * @param uri 图片地址
     * @param iv  作用的图片
     */
    public static void loadImageInUri(Uri uri, ImageView iv) {
        Glide.with(Constants.CONTEXT).setDefaultRequestOptions(getRequestOptions(DefIconFactory.iconDefault()).fitCenter()).asBitmap().load(uri).into(iv);
    }

    /**
     * 加载一张本地圆形图片
     *
     * @param imageRes  图片资源
     * @param imageView imageView
     */
    public static void loadCircleNativeImageView(int imageRes, final ImageView imageView) {
        Glide.with(Constants.CONTEXT).setDefaultRequestOptions(getRequestOptions(DefIconFactory.iconDefault()).fitCenter()).asBitmap().load(imageRes).apply(RequestOptions.circleCropTransform()).into(imageView);
    }

    /**
     * 暂停请求图片
     *
     * @param act Context
     */
    public static void pauseRequest(Context act) {
        Glide.with(act).pauseRequests();
    }

    /**
     * 恢复请求图片
     *
     * @param act Context
     */
    public static void resumeRequest(Context act) {
        Glide.with(act).resumeRequests();
    }


    /**
     * 计算图片分辨率
     *
     * @param url url
     * @return 计算后的图片
     */
    public static String calePhotoSize(String url) throws ExecutionException, InterruptedException {
        File file = Glide.with(NoteApplication.getInstance()).load(url)
                .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get();
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        return options.outWidth + "*" + options.outHeight;
    }

    /**
     * 针对请求的图片没有返回像素的处理情况
     *
     * @param iv  需要加载的图片
     * @param url 图片地址
     */
    public static void loadCutImage(final ImageView iv, final String url) {
        Flowable.create(new FlowableOnSubscribe<Object>() {
            @Override
            public void subscribe(FlowableEmitter<Object> e) throws Exception {
                e.onNext(calePhotoSize(url));
            }
        }, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        String size = (String) o;
                        if (!TextUtils.isEmpty(size)) {
                            int width = SystemUtil.getScreenWidth() / 2;
                            int height = TextUtils.calcPhotoHeight(size, width);
                            loadFitCenter(url, DefIconFactory.iconDefault(), iv, width, height);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.d("GlideUtils", "Error");
                    }
                });
    }

}
