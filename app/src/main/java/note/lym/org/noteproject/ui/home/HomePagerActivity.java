package note.lym.org.noteproject.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.base.SimpleActivity;
import note.lym.org.noteproject.fragment.HealthMessageFragment;
import note.lym.org.noteproject.fragment.NoteListFragment;
import note.lym.org.noteproject.fragment.SisterClassifyFragment;
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
    private HashMap<Integer, SupportFragment> mHashMap = new HashMap<>();
    private static final int EXIT_TIME = 1500;
    private long mExit_Time = 0;

    @Override
    protected int getLayout() {
        return R.layout.fragment_tab_pager_fragment;
    }

    @Override
    protected void initEventAndData() {
        setSwipeBackEnable(false);
        mNavView.setNavigationItemSelectedListener(this);
        mHashMap.put(R.id.menu_picture, new TabPagerFragment());
        mHashMap.put(R.id.menu_note, new NoteListFragment());
        mHashMap.put(R.id.menu_health, new HealthMessageFragment());
        mHashMap.put(R.id.menu_sister, new SisterClassifyFragment());
        mNavView.setCheckedItem(R.id.menu_picture);
        loadMultipleRootFragment(R.id.fl_container, 0, mHashMap.get(R.id.menu_picture)
                , mHashMap.get(R.id.menu_note), mHashMap.get(R.id.menu_health)
                , mHashMap.get(R.id.menu_sister));
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


    @Override
    public void onBackPressedSupport() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            return;
        }
        _exit();
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

}
