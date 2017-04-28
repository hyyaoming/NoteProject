package note.lym.org.noteproject.base;

import android.os.Bundle;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.WindowManager;

import butterknife.ButterKnife;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.manage.ActivityManage;
import note.lym.org.noteproject.utils.SoftInputUtil;
import note.lym.org.noteproject.utils.StatusBarCompat;

/**
 * 基类
 *
 * @author yaoming.li
 * @since 2017-04-25 10:23
 */
public abstract class BaseActivity extends BaseRunTimePermission {

    protected static final String TAG = BaseActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setContentView(getLayoutId());
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        /**
         * 这里必须这样配置不然不起效
         */
        StatusBarCompat.compat(this, ContextCompat.getColor(this,R.color.status_bar_color));
        Log.i(TAG, getClass().getSimpleName());
        ButterKnife.bind(this);
        initData();
        bindView();
        initListener();
    }

    protected abstract int getLayoutId();

    protected abstract void initListener();

    protected abstract void initData();

    protected abstract void bindView();

    /**
     * 退出程序
     */
    protected void exitApp() {
        ActivityManage.getInstance().backAllActivityToStack();
        android.os.Process.killProcess(Process.myPid());
    }

    /**
     * 隐藏系统软键盘
     */
    protected void hideSoftInput(){
        if (SoftInputUtil.isOpen()) {
            SoftInputUtil.hideSysSoftInput(this);
        }
    }

}
