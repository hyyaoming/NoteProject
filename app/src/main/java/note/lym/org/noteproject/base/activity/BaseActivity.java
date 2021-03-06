package note.lym.org.noteproject.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import note.lym.org.noteproject.base.presenter.BasePresenter;
import note.lym.org.noteproject.base.presenter.BaseView;
import note.lym.org.noteproject.dagger.component.ActivityComponent;
import note.lym.org.noteproject.dagger.component.DaggerActivityComponent;
import note.lym.org.noteproject.dagger.modul.ActivityModule;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.app.NoteApplication;
import note.lym.org.noteproject.view.LoadStateView;

/**
 * 基类
 *
 * @author yaoming.li
 * @since 2017-04-25 10:23
 */
public abstract class BaseActivity<T extends BasePresenter> extends BaseRunTimePermission implements BaseView {

    protected static final String TAG = BaseActivity.class.getSimpleName();

    @Inject
    protected T mPresenter;

    private Unbinder mUnBinder;

    @BindView(R.id.loading_state)
    LoadStateView mLoadState;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setContentView(getLayoutId());
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        Log.i(TAG, getClass().getSimpleName());

        mUnBinder = ButterKnife.bind(this);
        initInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        bindView();
        initListener();
        initData();
    }

    @Override
    public void showError(LoadStateView.OnRequestListener listener) {
        if (mLoadState != null) {
            mLoadState.setLoadingState(LoadStateView.LOADING_NO_NETWORK);
            mLoadState.setRequestListener(listener);
        }
    }

    @Override
    public void showLoading() {
        if (mLoadState != null) {
            mLoadState.setLoadingState(LoadStateView.LOADING_SHOW);
        }
    }

    @Override
    public void showNoData() {
        if (mLoadState != null) {
            mLoadState.setLoadingState(LoadStateView.LOADING_NO_DATE);
        }
    }

    @Override
    public void hideLoading() {
        if (mLoadState != null) {
            mLoadState.setLoadingState(LoadStateView.LOADING_HIDE);
        }
    }

    protected abstract int getLayoutId();

    protected abstract void initListener();

    protected abstract void initData();

    protected abstract void bindView();

    protected abstract void initInject();


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
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return false;
    }
}
