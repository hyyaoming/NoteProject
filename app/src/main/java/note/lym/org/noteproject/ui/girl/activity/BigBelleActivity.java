package note.lym.org.noteproject.ui.girl.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.base.activity.SimpleActivity;
import note.lym.org.noteproject.utils.eventbus.LookerEvent;
import note.lym.org.noteproject.model.bean.Collect;
import note.lym.org.noteproject.model.dao.CollectDao;
import note.lym.org.noteproject.service.MusicPlayService;
import note.lym.org.noteproject.model.bean.Music;
import note.lym.org.noteproject.utils.AlbumManager;
import note.lym.org.noteproject.utils.GlideUtils;
import note.lym.org.noteproject.utils.MediaUtils;
import note.lym.org.noteproject.utils.PreferencesUtils;
import note.lym.org.noteproject.utils.SystemUtil;
import note.lym.org.noteproject.utils.ToastUtils;
import note.lym.org.noteproject.view.likeview.LikeView;
import note.lym.org.noteproject.view.loading.LoadingView;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * 查看大图页面，新加背景音乐播放功能
 *
 * @author yaoming.li
 * @since 2017-05-04 14:28
 */
public class BigBelleActivity extends SimpleActivity {
    @BindView(R.id.iv_photo)
    PhotoView mPhotoView;
    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    @BindView(R.id.loading_view)
    LoadingView mLoadingView;
    @BindView(R.id.fl_layout)
    FrameLayout mLayout;
    public static final String URL = "image_url";
    public static final String BOOLEAN_FLAG = "boolean_flag";
    private String mImageUrl;
    private boolean mIsHidden = false;
    private static final long TIME = 800L;
    @BindView(R.id.like_view)
    LikeView mTv;
    private Collect mCollect;
    private boolean isTitleBar = true;
    private String[] permission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

    @Override
    protected int getLayout() {
        return R.layout.activity_big_belle;
    }

    @Override
    protected void initEventAndData() {
        requestPermission();
        getIntentData();
        bindView();
        initListener();
    }

    /**
     * 绑定事件
     */
    private void initListener() {
        mPhotoView.setOnLongClickListener(longClickListener);
        mPhotoView.setOnViewTapListener(tapListener);
    }

    /**
     * 获取intent传值
     */
    private void getIntentData() {
        mImageUrl = getIntent().getStringExtra(URL);
        isTitleBar = getIntent().getBooleanExtra(BOOLEAN_FLAG, false);
    }

    /**
     * 操作控件，或给控件赋值
     */
    private void bindView() {
        bindToolBar();
        loadBigBelleImage();
        bindLikeView();
    }

    /**
     * 点赞按钮相关设置
     */
    private void bindLikeView() {
        mCollect = getCollect();
        mTv.setVisibility((PreferencesUtils.isLoadImage() || SystemUtil.isWifiConnected(this)) ? View.VISIBLE : View.GONE);
        mTv.setState(null != mCollect && mCollect.isCollect.equals("1"));
    }

    /**
     * 加载大图片
     */
    private void loadBigBelleImage() {
        GlideUtils.loadFitCenter(mImageUrl, mPhotoView, mLoadingView);
    }

    /**
     * toolbar相关设置
     */
    private void bindToolBar() {
        mToolBar.setVisibility(isTitleBar ? View.VISIBLE : View.GONE);
        initToolBar(mToolBar, true, "");
        mLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.light_black));
    }

    /**
     * View长按事件
     */
    private View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            saveImageToGallery(mImageUrl);
            return true;
        }
    };

    /**
     * photoView单击事件
     */
    private PhotoViewAttacher.OnViewTapListener tapListener = new PhotoViewAttacher.OnViewTapListener() {
        @Override
        public void onViewTap(View view, float x, float y) {
            hideOrShowToolbar();
        }
    };

    @Override
    public void finish() {
        super.finish();
        EventBus.getDefault().post(new LookerEvent());
    }

    /**
     * 这需要对运行时权限处理一下，不然会崩溃
     */
    private void requestPermission() {
        requestRunTimePermission(permission, new RequestPermissionListener() {
            @Override
            public void accredit() {
                if (PreferencesUtils.isMusicPlay()) {
                    Observable.create(new ObservableOnSubscribe<List<Music>>() {
                        @Override
                        public void subscribe(ObservableEmitter<List<Music>> e) throws Exception {
                            ArrayList<Music> list = MediaUtils.queryMusic(mContext);
                            e.onNext(list);
                        }

                    }).subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<List<Music>>() {
                                @Override
                                public void accept(List<Music> musics) throws Exception {
                                    if (null != musics && musics.size() > 0) {
                                        MusicPlayService.startService(mContext, (ArrayList<Music>) musics);
                                    } else {
                                        ToastUtils.showToast(R.string.i_think_you_must_down_music);
                                    }
                                }
                            });
                }
            }

            @Override
            public void decline(List<String> array) {
                Snackbar.make(mPhotoView, R.string.open_permission_download_belle, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    /**
     * 隐藏或显示toolbar
     */
    protected void hideOrShowToolbar() {
        mToolBar.animate()
                .translationY(mIsHidden ? 0 : -mToolBar.getHeight()).setDuration(TIME)
                .setInterpolator(new DecelerateInterpolator(2))
                .start();
        mIsHidden = !mIsHidden;
    }

    /**
     * 将当前的美图存入手机中
     *
     * @param url 图片地址
     */
    private void saveImageToGallery(final String url) {
        AlertDialog.Builder builder = new AlertDialog.Builder(BigBelleActivity.this);
        builder.setTitle(R.string.download_belle);
        builder.setNegativeButton(android.R.string.cancel, null);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AlbumManager.download(url);
            }
        }).show();
    }

    /**
     * 注意，这里如果想要fragment接收到回传值那么必须用fragment启动activity，不然收不到回传值。
     *
     * @param context      上下文
     * @param url          地址链接
     * @param showTitleBar 是否显示头部
     */
    public static void action(Context context, String url, boolean showTitleBar) {
        Intent intent = new Intent(context, BigBelleActivity.class);
        intent.putExtra(URL, url);
        intent.putExtra(BOOLEAN_FLAG, showTitleBar);
        context.startActivity(intent);
    }

    /**
     * 点击爱心按钮时的处理逻辑
     */
    private void clickLoveButton() {
        mCollect = mCollect == null ? new Collect() : mCollect;
        boolean status = mTv.getState();
        mCollect.isCollect = status ? "1" : "0";
        mCollect.url = mImageUrl;
        CollectDao.insertCollect(mCollect);
    }

    @OnClick(R.id.like_view)
    public void love() {
        clickLoveButton();
    }

    private Collect getCollect() {
        return CollectDao.findCollectById("url = ?", mImageUrl);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MusicPlayService.stopService(this);
    }
}
