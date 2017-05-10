package note.lym.org.noteproject.base;

import android.os.Bundle;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import note.lym.org.noteproject.Dagger.Component.ActivityComponent;
import note.lym.org.noteproject.Dagger.Component.DaggerActivityComponent;
import note.lym.org.noteproject.Dagger.Modul.ActivityModule;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.app.NoteApplication;
import note.lym.org.noteproject.manage.ActivityManage;
import note.lym.org.noteproject.utils.SoftInputUtil;
import note.lym.org.noteproject.utils.StatusBarCompat;

/**
 * 基类
 *
 * @author yaoming.li
 * @since 2017-04-25 10:23
 */
public abstract class BaseActivity <T extends BasePresenter> extends BaseRunTimePermission  implements BaseView{

    protected static final String TAG = BaseActivity.class.getSimpleName();

    @Inject
    protected T mPresenter;

    private Unbinder mUnBinder;

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

        mUnBinder = ButterKnife.bind(this);
        initInject();
        if (mPresenter != null)
            mPresenter.attachView(this);
        bindView();
        initListener();
        initData();
    }

    protected abstract int getLayoutId();

    protected abstract void initListener();

    protected abstract void initData();

    protected abstract void bindView();

    protected abstract void initInject();

    /**
     * 退出程序
     */
    protected void exitApp() {
        ActivityManage.getInstance().backAllActivityToStack();
        android.os.Process.killProcess(Process.myPid());
    }

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(NoteApplication.getAppComponent())
                .activityModule(getActivityModule())
                .build();

    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        mUnBinder.unbind();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return false;
    }
}
