package note.lym.org.noteproject.ui.home;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.async.MusicAsyncTask;
import note.lym.org.noteproject.base.SimpleActivity;
import note.lym.org.noteproject.eventbus.LookerEvent;
import note.lym.org.noteproject.model.dao.Collect;
import note.lym.org.noteproject.service.MusicPlayService;
import note.lym.org.noteproject.utils.AlbumManager;
import note.lym.org.noteproject.utils.AnimateHelper;
import note.lym.org.noteproject.utils.GlideUtils;
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
    @BindView(R.id.progress)
    ProgressBar mProgressBar;
    @BindView(R.id.fl_layout)
    FrameLayout mLayout;
    public static final String URL = "image_url";
    public static final String BOOLEAN_FLAG = "boolean_flag";
    private String mImageUrl;
    private boolean mIsHidden = false;
    private static final long TIME = 800L;
    @BindView(R.id.iv_count)
    TextView mTv;
    private Collect mCollect;
    public static final int DURATION = 1500;
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
        initToolBar(mToolBar, true, "");
        mLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.light_black));
        if (!isTitleBar) {
            mToolBar.setVisibility(View.GONE);
        }
        mCollect = getCollect();
        if (null != mCollect && mCollect.isCollect.equals("1")) {
            mTv.setSelected(true);
        } else {
            mTv.setSelected(false);
        }
        GlideUtils.loadFitCenter(this, mImageUrl, mPhotoView, mProgressBar);
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
                new MusicAsyncTask(BigBelleActivity.this).execute();
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
     */
    public static void action(Context activity, String url, boolean showTitleBar) {
        Intent intent = new Intent(activity, BigBelleActivity.class);
        intent.putExtra(URL, url);
        intent.putExtra(BOOLEAN_FLAG, showTitleBar);
        activity.startActivity(intent);
    }

    /**
     * 点击爱心按钮时的处理逻辑
     */
    private void clickLoveButton() {
        mCollect = getCollect();
        if (mCollect != null) {
            if (mCollect.isCollect.equals("1")) {
                mCollect.isCollect = "0";
                mTv.setSelected(false);
                setResult(RESULT_OK);
            } else {
                mCollect.isCollect = "1";
                mTv.setSelected(true);
            }
            mCollect.url = mImageUrl;
            mCollect.save();
        } else {
            Collect collect = new Collect();
            collect.isCollect = "1";
            collect.url = mImageUrl;
            mTv.setSelected(true);
            collect.save();
        }
        AnimateHelper.doLike(mTv, DURATION);
    }


    @OnClick(R.id.fl_layout)
    public void love() {
        clickLoveButton();
    }

    private Collect getCollect() {
        return DataSupport.where("url = ?", mImageUrl).findFirst(Collect.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MusicPlayService.stopService(this);
    }
}
