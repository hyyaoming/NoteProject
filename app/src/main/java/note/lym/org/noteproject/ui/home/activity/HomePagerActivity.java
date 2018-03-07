package note.lym.org.noteproject.ui.home.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatDelegate;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Flowable;
import me.yokeyword.fragmentation.SupportFragment;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.app.constant.Constants;
import note.lym.org.noteproject.base.activity.SimpleActivity;
import note.lym.org.noteproject.ui.news.fragment.HealthMessageFragment;
import note.lym.org.noteproject.ui.note.fragment.NoteListFragment;
import note.lym.org.noteproject.ui.girl.fragment.SisterClassifyFragment;
import note.lym.org.noteproject.ui.home.fragment.TabPagerFragment;
import note.lym.org.noteproject.ui.user.activity.UserSetActivity;
import note.lym.org.noteproject.utils.GlideUtils;
import note.lym.org.noteproject.utils.PreferencesUtils;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 主页
 *
 * @author yaoming.li
 * @version 1.0.0
 * @since 2017-04-25 11:35
 */
public class HomePagerActivity extends SimpleActivity implements NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener, EasyPermissions.PermissionCallbacks {
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @SuppressLint("UseSparseArrays")
    private HashMap<Integer, SupportFragment> mHashMap = new HashMap<>();
    private static final int EXIT_TIME = 1500;
    private static final int REQUEST_CODE_CHOOSE = 23;
    private static final int MAX_SELECT = 9;
    private static final float SCALE = 0.85f;
    private static final String FILE_PATH = "note.lym.org.noteproject.fileprovider";
    private long mExitTime = 0;
    private ImageView mIv;

    @Override
    protected int getLayout() {
        return R.layout.fragment_tab_pager_fragment;
    }

    @Override
    protected void initEventAndData() {
        initFragment();
        setSwipeBackEnable(false);
        initMenu();
    }

    private void initFragment() {
        mHashMap.put(R.id.menu_picture, new TabPagerFragment());
        mHashMap.put(R.id.menu_note, new NoteListFragment());
        mHashMap.put(R.id.menu_health, new HealthMessageFragment());
        mHashMap.put(R.id.menu_sister, new SisterClassifyFragment());
        mNavView.setCheckedItem(R.id.menu_picture);
        loadMultipleRootFragment(R.id.fl_container, 0, mHashMap.get(R.id.menu_picture),
                mHashMap.get(R.id.menu_note), mHashMap.get(R.id.menu_health),
                mHashMap.get(R.id.menu_sister));
    }

    private void initMenu() {
        mNavView.setNavigationItemSelectedListener(this);
        mIv = (ImageView) mNavView.getHeaderView(0).findViewById(R.id.iv_change_avatar);
        ImageView mIvChangeModel = (ImageView) mNavView.getHeaderView(0).findViewById(R.id.iv_change_model);
        mIv.setOnClickListener(this);
        mIvChangeModel.setOnClickListener(this);
        loadUserAvatarImageInUri();
    }


    /**
     * 启动当前的Activity
     *
     * @param activity activity
     */
    public static void action(Activity activity) {
        Intent intent = new Intent(activity, HomePagerActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    //解决切换日间模式时，fragment重叠的问题
    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
    }

    /**
     * DrawerLayout侧滑菜单开关
     */
    public void toggleDrawer() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }

    /**
     * 日夜间模式切换
     */
    private void switchNightMode() {
        boolean isNight = PreferencesUtils.getBoolean(Constants.SWITCH_MODE_KEY, false);
        if (isNight) {
            // 日间模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            PreferencesUtils.putBoolean(Constants.SWITCH_MODE_KEY, false);
        } else {
            // 夜间模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            PreferencesUtils.putBoolean(Constants.SWITCH_MODE_KEY, true);
        }
        recreate();
    }


    @Override
    public void onBackPressedSupport() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            return;
        }
        exit();
    }

    private void exit() {
        if (System.currentTimeMillis() - mExitTime > EXIT_TIME) {
            Snackbar.make(mDrawerLayout, R.string.exit_app, Snackbar.LENGTH_SHORT).setAction(R.string.exit_application, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    exitApp();
                }
            }).show();
            mExitTime = System.currentTimeMillis();
        } else {
            exitApp();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        if (item.isChecked()) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.menu_picture:
                showHideFragment(mHashMap.get(R.id.menu_picture));
                break;
            case R.id.menu_note:
                showHideFragment(mHashMap.get(R.id.menu_note));
                break;
            case R.id.menu_health:
                showHideFragment(mHashMap.get(R.id.menu_health));
                break;
            case R.id.menu_sister:
                showHideFragment(mHashMap.get(R.id.menu_sister));
                break;
            case R.id.menu_setting:
                UserSetActivity.launch(this);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            List<Uri> list = Matisse.obtainResult(data);
            caseResultImageUri(String.valueOf(list.get(0)));
        }
    }

    private void caseResultImageUri(String path) {
        PreferencesUtils.setString(Constants.AVATAR, path);
        loadUserAvatarImageInUri();
    }

    private void loadUserAvatarImageInUri() {
        String path = PreferencesUtils.getString(Constants.AVATAR, "");
        GlideUtils.loadImageInUri(Uri.parse(path), mIv);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    private void openPicture() {
        Matisse.from(HomePagerActivity.this)
                .choose(MimeType.ofAll(), false)
                .countable(true)
                .capture(true)
                .captureStrategy(
                        new CaptureStrategy(true, FILE_PATH))
                .maxSelectable(MAX_SELECT)
                .gridExpectedSize(
                        getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(SCALE)
                .imageEngine(new GlideEngine())
                .forResult(REQUEST_CODE_CHOOSE);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        String[] permission = {Constants.WRITE_EXTERNAL_STORAGE, Constants.READ_EXTERANL_STORANGE, Constants.CAMRAM};
        if (viewId == R.id.iv_change_avatar) {
            if (EasyPermissions.hasPermissions(this, permission)) {
                openPicture();
            } else {
                EasyPermissions.requestPermissions(this, getString(R.string.open_picture), 1, permission);
            }
        } else if (viewId == R.id.iv_change_model) {
            switchNightMode();
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        openPicture();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }
}
