package note.lym.org.noteproject.base;

import android.os.Bundle;
import android.os.Process;
import android.support.annotation.Nullable;
import android.util.Log;

import butterknife.ButterKnife;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.manage.ActivityManage;
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
        /**
         * 这里必须这样配置不然不起效
         */
        StatusBarCompat.compat(this, getResources().getColor(R.color.little_gary));
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

    protected void exitApp() {
        ActivityManage.getInstance().backAllActivityToStack();
        android.os.Process.killProcess(Process.myPid());
    }

}
