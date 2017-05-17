package note.lym.org.noteproject.ui.home;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
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
import note.lym.org.noteproject.base.SimpleActivity;
import note.lym.org.noteproject.eventbus.LookerEvent;
import note.lym.org.noteproject.model.dao.Collect;
import note.lym.org.noteproject.utils.AlbumManager;
import note.lym.org.noteproject.utils.AnimateHelper;
import note.lym.org.noteproject.utils.GlideUtils;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
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
    private String mImageUrl;
    private boolean mIsHidden = false;
    private static final long TIME = 800L;
    @BindView(R.id.iv_count)
    TextView mTv;
    private Collect mCollect;
    public static final int DURATION = 1500;

    @Override
    protected int getLayout() {
        return R.layout.activity_big_belle;
    }

    @Override
    protected void initEventAndData() {
        initToolBar(mToolBar, true, "");
        mLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.light_black));
        mImageUrl = getIntent().getStringExtra(URL);
        mCollect = getCollect();
        if (null != mCollect && mCollect.isCollect.equals("1")) {
            mTv.setSelected(true);
        } else {
            mTv.setSelected(false);
        }
        GlideUtils.loadFitCenter(this, mImageUrl, mPhotoView, mProgressBar);
        mPhotoView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                requestPermission();
                return true;
            }
        });

        mPhotoView.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
            @Override
            public void onViewTap(View view, float x, float y) {
                hideOrShowToolbar();
            }
        });

    }

    @Override
    public void finish() {
        super.finish();
        EventBus.getDefault().post(new LookerEvent());
    }

    private void requestPermission() {
        requestRunTimePermission(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, new RequestPermissionListener() {
            @Override
            public void accredit() {
                saveImageToGallery(mImageUrl);
            }

            @Override
            public void decline(List<String> array) {
                Snackbar.make(mPhotoView, R.string.open_permission_download_belle, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    protected void hideOrShowToolbar() {
        mToolBar.animate()
                .translationY(mIsHidden ? 0 : -mToolBar.getHeight()).setDuration(TIME)
                .setInterpolator(new DecelerateInterpolator(2))
                .start();
        mIsHidden = !mIsHidden;
    }

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
    public static void action(Fragment fragment, String url) {
        Intent intent = new Intent(fragment.getActivity(), BigBelleActivity.class);
        intent.putExtra(URL, url);
        fragment.startActivity(intent);
    }

    @OnClick(R.id.fl_layout)
    public void love() {
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

    private Collect getCollect() {
        return DataSupport.where("url = ?", mImageUrl).findFirst(Collect.class);
    }
}
