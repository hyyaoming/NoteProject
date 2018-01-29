package note.lym.org.noteproject.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.IntegerRes;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
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
import note.lym.org.noteproject.model.bean.Note;
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
     * @param act 上下文对象
     * @param img 设置加载的图片
     * @param url 图片地址
     */
    public static void load(Context act, ImageView img, String url, int defaultImage) {
        if (PreferencesUtils.isLoadImage() || SystemUtil.isWifiConnected(act)) {
            Glide.with(act).load(url).centerCrop().placeholder(defaultImage).dontAnimate().into(img);
        } else {
            img.setImageResource(defaultImage);
        }
    }

    /**
     * 这种情况一般指图片子指定了图片的大小，需要全部展示
     *
     * @param url          图片地址
     * @param defaultImage 默认图
     * @param imageView    需要加载的图片
     */
    public static void loadFitCenter(Context context, String url, int defaultImage, ImageView imageView) {
        if (PreferencesUtils.isLoadImage() || SystemUtil.isWifiConnected(context)) {
            Glide.with(NoteApplication.getContext()).load(url).fitCenter().dontAnimate().placeholder(defaultImage).into(imageView);
        } else {
            imageView.setImageResource(defaultImage);
        }
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
            Glide.with(NoteApplication.getContext()).load(url).override(width, height).fitCenter().dontAnimate().placeholder(defaultImage).into(imageView);
        } else {
            imageView.setImageResource(defaultImage);
        }
    }

    /**
     * 就加载一张图片，啥都不设置,在wifi情况下加载。
     *
     * @param act 上下文对象
     * @param img 设置加载的图片
     * @param url 图片地址
     */
    public static void loadWifi(Context act, ImageView img, String url, int defaultImage) {
        if (SystemUtil.isWifiConnected(NoteApplication.getInstance())) {
            Glide.with(act).load(url).centerCrop().into(img);
        } else {
            img.setImageResource(defaultImage);
        }
    }


    /**
     * 就加载一张图片，啥都不设置。
     *
     * @param context  上下文对象
     * @param view     设置加载的图片
     * @param url      图片地址
     * @param listener 回调
     */
    public static void loadCenterCrop(Context context, String url, ImageView view, RequestListener listener) {
        if (PreferencesUtils.isLoadImage() || SystemUtil.isWifiConnected(context)) {
            Glide.with(context).load(url).centerCrop().dontAnimate().listener(listener).into(view);
        } else {
            view.setImageResource(DefIconFactory.iconDefault());
        }
    }

    /**
     * 就加载一张图片，啥都不设置。
     *
     * @param context 上下文对象
     * @param view    设置加载的图片
     * @param url     图片地址
     */
    public static void loadFitCenter(Context context, String url, ImageView view, LoadingView loading) {
        if (PreferencesUtils.isLoadImage() || SystemUtil.isWifiConnected(context)) {
            Glide.with(context).load(url).fitCenter().dontAnimate().listener(loadRequestListener(loading, view)).into(view);
        } else {
            loading.setVisibility(View.GONE);
            view.setImageResource(DefIconFactory.iconDefault());
        }
    }

    private static RequestListener loadRequestListener(final LoadingView bar, final ImageView view) {
        return new RequestListener<String, GlideDrawable>() {

            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                ToastUtils.showToast(R.string.look_network_state);
                bar.stopLoading();
                return true;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                bar.stopLoading();
                view.setImageDrawable(resource);
                return true;
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
        if (SystemUtil.isWifiConnected(NoteApplication.getInstance())) {
            Glide.with(NoteApplication.getInstance()).load(url).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
        } else {
            iv.setImageResource(defaultImage);
        }
    }

    /**
     * 设置加载中的图片以及加载失败的占位图
     *
     * @param context      上下文
     * @param url          图片地址
     * @param errorImage   加载失败时的图片
     * @param defaultImage 加载中的图片
     * @param img          设置加载的图片
     */
    public static void load(Context context, String url, int errorImage, int defaultImage, ImageView img) {
        if (PreferencesUtils.isLoadImage() || SystemUtil.isWifiConnected(context)) {
            Glide.with(context).load(url).error(errorImage).dontAnimate().placeholder(defaultImage).into(img);
        } else {
            img.setImageResource(defaultImage);
        }
    }

    /**
     * 可设置加载图片的大小，并居中显示
     *
     * @param context      上下文
     * @param url          图片地址
     * @param errorImage   加载失败时的图片
     * @param defaultImage 加载中的图片
     * @param image        加载的图片
     * @param width        图片剪裁的宽度
     * @param height       图片剪裁的高度
     */
    public static void load(Context context, String url, int errorImage, int defaultImage, ImageView image, int width, int height) {
        if (PreferencesUtils.isLoadImage() || SystemUtil.isWifiConnected(context)) {
            Glide.with(context).load(url).centerCrop().error(errorImage).dontAnimate().placeholder(defaultImage).override(width, height).centerCrop().into(image);
        } else {
            image.setImageResource(defaultImage);
        }
    }

    /**
     * 可设置加载时的动画
     *
     * @param context      上下文
     * @param url          图片地址
     * @param errorImage   加载失败时的图片
     * @param defaultImage 加载中的图片
     * @param imageView    加载的图片
     * @param width        剪裁图片的宽度
     * @param height       剪裁图片的高度
     * @param animationRes 加载时的动画
     */
    public static void load(Context context, String url, int errorImage, int defaultImage, ImageView imageView, int width, int height, int animationRes) {
        if (PreferencesUtils.isLoadImage() || SystemUtil.isWifiConnected(context)) {
            Glide.with(context).load(url).error(errorImage).placeholder(defaultImage).override(width, height).crossFade().centerCrop().animate(animationRes).into(imageView);
        } else {
            imageView.setImageResource(defaultImage);
        }

    }

    /**
     * 加载一张圆形图片
     *
     * @param url 图片地址
     * @param iv  被加载的图片
     */
    public static void loadCircleImage(String url, final ImageView iv) {
        Glide.with(NoteApplication.getInstance()).load(url).asBitmap().fitCenter().into(new BitmapImageViewTarget(iv) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(NoteApplication.getInstance().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                iv.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    /**
     * 加载一张本地圆形图片
     *
     * @param imageRes  图片资源
     * @param imageView imageView
     */
    public static void loadCircleNativeImageView(int imageRes, final ImageView imageView) {
        Glide.with(NoteApplication.getInstance()).load(imageRes).asBitmap().fitCenter().into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable nativeImage = RoundedBitmapDrawableFactory.create(NoteApplication.getInstance().getResources(), resource);
                nativeImage.setCircular(true);
                imageView.setImageDrawable(nativeImage);
            }
        });
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
                            int width = SystemUtil.getScreenWidth(NoteApplication.getInstance()) / 2;
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
