package note.lym.org.noteproject.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.BufferedSink;
import okio.Okio;

/**
 * @author yaoming.li
 * @since 2017-05-10 13:56
 */
public class AlbumManager {
    /**
     * 下载图片
     *
     * @param imageUrl
     */
    public static void download(final String imageUrl) {
        Flowable.create(new FlowableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(FlowableEmitter<Boolean> e) throws Exception {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(imageUrl).build();
                Call call = client.newCall(request);
                Response response = call.execute();
                if (response != null && response.body() != null) {
                    BufferedSink sink = null;
                    try {
                        sink = Okio.buffer(Okio.sink(FileUtils.createFileFrom(imageUrl)));
                        sink.write(response.body().bytes());
                        e.onNext(true);
                    } catch (Exception exception) {
                        Log.e("_stone_", "AlbumManager-download-subscribe(): " + exception.getMessage());
                        e.onNext(false);
                    } finally {
                        if (sink != null) sink.close();
                    }
                } else {
                    e.onNext(false);
                }
            }
        }, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean result) throws Exception {
                        //将下载的图片插入到系统相册, 并同步刷新系统相册(更新UI)
                        if (result) {
                            insertSystemAlbumAndRefresh(imageUrl);
                        }
                        ToastUtils.showToast(R.string.save_image_success);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.d("_stone_", "AlbumManager-download-OnError-accept: " + throwable.getMessage());
                        ToastUtils.showToast(R.string.save_image_error);
                    }
                });
    }

    /**
     * 插入到系统相册, 并刷新系统相册
     *
     * @param imageUrl
     */
    private static void insertSystemAlbumAndRefresh(final String imageUrl) {
        Flowable.create(new FlowableOnSubscribe<Object>() {
            @Override
            public void subscribe(FlowableEmitter<Object> e) throws Exception {
                File file = FileUtils.createFileFrom(imageUrl);
                String imageUri = MediaStore.Images.Media.insertImage(NoteApplication.getInstance().getContentResolver(), file.getAbsolutePath(), file.getName(), "图片: " + file.getName());
                Log.d("_stone_", "insertSystemAlbumAndRefresh-subscribe: imageUri=" + imageUri);
                syncAlbum(imageUrl);
            }
        }, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    /**
     * 同步刷新系统相册
     *
     * @param imageUrl the fileName
     */
    private static void syncAlbum(String imageUrl) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            final Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            final Uri contentUri = Uri.fromFile(FileUtils.createFileFrom(imageUrl).getAbsoluteFile());
            scanIntent.setData(contentUri);
            NoteApplication.getInstance().sendBroadcast(scanIntent);
        } else {
            final Intent intent = new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + Environment.getExternalStorageDirectory()));
            NoteApplication.getInstance().sendBroadcast(intent);
        }
    }

    /**
     * View to bitmap.
     *
     * @param view    The view.
     * @param context The context.
     */
    public static void view2BitmapAndSaveToGallery(Context context, final View view) {
        if (view != null) {
            Bitmap ret = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(ret);
            Drawable bgDrawable = view.getBackground();
            if (bgDrawable != null) {
                bgDrawable.draw(canvas);
            } else {
                canvas.drawColor(Color.WHITE);
            }
            view.draw(canvas);
            saveImageToGallery(context, ret);
        }
    }

    /**
     * 将一张bitmap插入到系统中
     *
     * @param context the context
     * @param bmp     this bitmap
     */
    public static void saveImageToGallery(Context context, Bitmap bmp) {
        if (null != bmp) {
            // 首先保存图片
            File appDir = new File(Environment.getExternalStorageDirectory(), "demo");
            if (!appDir.exists()) {
                appDir.mkdir();
            }
            String fileName = System.currentTimeMillis() + ".jpg";
            File file = new File(appDir, fileName);
            try {
                FileOutputStream fos = new FileOutputStream(file);
                bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 其次把文件插入到系统图库
            try {
                MediaStore.Images.Media.insertImage(context.getContentResolver(),
                        file.getAbsolutePath(), fileName, null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            // 最后通知图库更新
            syncAlbum(fileName);
        }
    }
}
