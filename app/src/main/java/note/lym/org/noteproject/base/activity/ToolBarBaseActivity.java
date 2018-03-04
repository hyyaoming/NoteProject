package note.lym.org.noteproject.base.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.utils.SoftInputUtil;
import note.lym.org.noteproject.utils.StatusBarCompat;


/**
 * toolbar与fragment转场动画操作类
 *
 * @author yaoming.li
 * @since 2017-05-08 12:32
 */
public class ToolBarBaseActivity extends SwipeBackActivity {

    private boolean isEnableStatusBar = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }


    protected boolean enableBar(){
        return isEnableStatusBar;
    }

    /**
     * 添加 Fragment
     *
     * @param containerViewId 内容布局
     * @param fragment        被替换的fragment
     */
    protected void addFragment(int containerViewId, Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        // 设置tag，不然下面 findFragmentByTag(tag)找不到
        fragmentTransaction.add(containerViewId, fragment, tag);
        //加入到回退栈中
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }

    /**
     * 替换 Fragment
     *
     * @param containerViewId 内容布局
     * @param fragment        需要展示的fragment
     */
    protected void replaceFragment(int containerViewId, Fragment fragment, String tag) {
        if (getSupportFragmentManager().findFragmentByTag(tag) == null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            //设置fragment之间的转场动画
            fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
            // 设置tag
            fragmentTransaction.replace(containerViewId, fragment, tag);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            // 这里要设置tag，上面也要设置tag
            fragmentTransaction.addToBackStack(tag);
            fragmentTransaction.commit();
        } else {
            // 存在则弹出在它上面的所有fragment，并显示对应fragment
            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).commit();
            getSupportFragmentManager().popBackStack(tag, 0);
        }
    }

    /**
     * 初始化 Toolbar
     *
     * @param toolbar         Toolbar
     * @param homeAsUpEnabled 是否显示menu按钮
     * @param title           toolbar标题
     */
    public void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
    }

    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, int resTitle) {
        initToolBar(toolbar, homeAsUpEnabled, getString(resTitle));
    }

    /**
     * 隐藏系统软键盘
     */
    protected void hideSoftInput() {
        if (SoftInputUtil.isOpen()) {
            SoftInputUtil.hideSysSoftInput(this);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
