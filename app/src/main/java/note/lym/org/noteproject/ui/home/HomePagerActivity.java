package note.lym.org.noteproject.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.base.SimpleActivity;
import note.lym.org.noteproject.fragment.BelleListFragment;
import note.lym.org.noteproject.fragment.NoteListFragment;
import note.lym.org.noteproject.fragment.TabPagerFragment;

/**
 * 主页
 *
 * @author yaoming.li
 * @since 2017-04-25 11:35
 */
public class HomePagerActivity extends SimpleActivity implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    private SparseArray<String> mSparseTags = new SparseArray<>();
    private int mItemId = -1;
    private WeakHandler mHandler = new WeakHandler(this);
    private static final int EXIT_TIME = 1500;
    private long mExit_Time = 0;

    @Override
    protected int getLayout() {
        return R.layout.fragment_tab_pager_fragment;
    }

    @Override
    protected void initEventAndData() {
        _initDrawerLayout(mDrawerLayout, mNavView);
        mSparseTags.put(R.id.menu_note, getString(R.string.casual_write));
        mSparseTags.put(R.id.menu_picture, getString(R.string.around_work));
        mNavView.setCheckedItem(R.id.menu_picture);
        addFragment(R.id.fl_container, new TabPagerFragment(),  getString(R.string.around_work));
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

    /**
     * 初始化 DrawerLayout
     *
     * @param drawerLayout DrawerLayout
     * @param navView      NavigationView
     */
    private void _initDrawerLayout(DrawerLayout drawerLayout, NavigationView navView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
            //将侧边栏顶部延伸至status bar
            drawerLayout.setFitsSystemWindows(true);
            //将主页面顶部延伸至status bar
            drawerLayout.setClipToPadding(false);
        }
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerClosed(View drawerView) {
                mHandler.sendEmptyMessage(mItemId);
            }
        });
        navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        // 获取堆栈里有几个
        final int stackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else if (stackEntryCount == 1) {
            // 如果剩一个说明在主页，提示按两次退出app
            _exit();
        } else {
            // 获取上一个堆栈中保存的是哪个页面，根据name来设置导航项的选中状态
            final String tagName = getSupportFragmentManager().getBackStackEntryAt(stackEntryCount - 2).getName();
            mNavView.setCheckedItem(mSparseTags.keyAt(mSparseTags.indexOfValue(tagName)));
            super.onBackPressed();
        }
    }

    private void _exit() {
        if (System.currentTimeMillis() - mExit_Time > EXIT_TIME) {
            Snackbar.make(mDrawerLayout, R.string.exit_app, Snackbar.LENGTH_SHORT).setAction(R.string.exit_application, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    exitApp();
                }
            }).show();
            mExit_Time = System.currentTimeMillis();
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
        mItemId = item.getItemId();
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

    /**
     * 这里采用一个软引用队列，防止内存泄漏
     */
    private class WeakHandler extends Handler {

        private WeakReference<Activity> act;

        public WeakHandler(Activity activity) {
            act = new WeakReference<Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            Activity activity = act.get();
            if (activity == null) {
                return;
            }
            switch (msg.what) {
                case R.id.menu_picture:
                    replaceFragment(R.id.fl_container, new TabPagerFragment(), mSparseTags.get(R.id.menu_picture));
                    break;
                case R.id.menu_note:
                    replaceFragment(R.id.fl_container, new NoteListFragment(), mSparseTags.get(R.id.menu_note));
                    break;
            }
        }
    }

}
