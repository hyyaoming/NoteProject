package note.lym.org.noteproject.ui.home;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import butterknife.BindView;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.base.SimpleActivity;
import note.lym.org.noteproject.utils.AlbumManager;
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
    public static final String URL = "image_url";
    private String mImageUrl;
    private boolean mIsHidden = false;
    private static final long TIME = 800L;

    @Override
    protected int getLayout() {
        return R.layout.activity_big_belle;
    }

    @Override
    protected void initEventAndData() {
        initToolBar(mToolBar, true, "");
        mImageUrl = getIntent().getStringExtra(URL);
        RequestListener<String, GlideDrawable> listener = new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                mProgressBar.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                mProgressBar.setVisibility(View.GONE);
                mPhotoView.setImageDrawable(resource);
                return true;
            }
        };
        GlideUtils.loadCenterCrop(this, mImageUrl, mPhotoView, listener);
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

    public static void action(Fragment fragment, String url) {
        Intent intent = new Intent(fragment.getActivity(), BigBelleActivity.class);
        intent.putExtra(URL, url);
        fragment.startActivity(intent);
    }
}
