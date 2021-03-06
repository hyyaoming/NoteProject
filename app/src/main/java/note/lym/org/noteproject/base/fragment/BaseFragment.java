package note.lym.org.noteproject.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.app.NoteApplication;
import note.lym.org.noteproject.base.activity.BaseRunTimePermission;
import note.lym.org.noteproject.base.presenter.BasePresenter;
import note.lym.org.noteproject.base.presenter.BaseView;
import note.lym.org.noteproject.dagger.component.DaggerFragmentComponent;
import note.lym.org.noteproject.dagger.component.FragmentComponent;
import note.lym.org.noteproject.dagger.modul.FragmentModule;
import note.lym.org.noteproject.ui.home.activity.HomePagerActivity;
import note.lym.org.noteproject.utils.SoftInputUtil;
import note.lym.org.noteproject.view.LoadStateView;

/**
 * @author yaoming.li
 * @version 1.0.0
 * @since 2017-05-03 20:46
 */
public abstract class BaseFragment<T extends BasePresenter> extends SupportFragment implements BaseView, View.OnClickListener {
    /**
     * 子类打印log日志时可以方便一些
     */
    protected static final String TAG = BaseFragment.class.getSimpleName();
    /**
     * 方便子类获取上下文对象
     */
    protected Context mContext;
    /**
     * 是否开始加载数据标记位
     */
    private boolean mLoad = false;
    @Inject
    protected T mPresenter;
    private View mRootView;
    @Nullable
    /**
     * 子类布局文件中必须写一个progressbar,并且id要一致
     */
    @BindView(R.id.loading_state)
    LoadStateView mStateView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == mRootView) {
            mRootView = inflater.inflate(getLayoutResources(), null);
            ButterKnife.bind(this, mRootView);
            initInject();
            if (mPresenter != null) {
                mPresenter.attachView(this);
            }
            updateViews();
        }
        ViewGroup group = (ViewGroup) mRootView.getParent();
        if (null != group) {
            group.removeView(mRootView);
        }
        return mRootView;
    }

    //当前fragment挂载到宿主Activity时回调
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mPresenter) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }

    /**
     * 初始化 Toolbar
     *
     * @param toolbar         toolbar
     * @param homeAsUpEnabled home is enable
     * @param title           title content
     */
    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
        ((BaseRunTimePermission) getActivity()).initToolBar(toolbar, homeAsUpEnabled, title);
        toolbar.setNavigationOnClickListener(this);
    }

    /**
     * 隐藏系统软键盘
     */
    protected void hideSoftInput() {
        if (SoftInputUtil.isOpen()) {
            BaseRunTimePermission activity = (BaseRunTimePermission) getActivity();
            SoftInputUtil.hideSysSoftInput(activity);
        }
    }

    //宿主Activity创建时回调
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint() && null != mRootView && !mLoad) {
            mLoad = true;
            loadLazyData();
        }
    }

    @Override
    public void showLoading() {
        if (mStateView != null) {
            mStateView.setLoadingState(LoadStateView.LOADING_SHOW);
        }
    }

    @Override
    public void showNoData() {
        if (mStateView != null) {
            mStateView.setLoadingState(LoadStateView.LOADING_NO_DATE);
        }
    }

    @Override
    public void hideLoading() {
        if (mStateView != null) {
            mStateView.setLoadingState(LoadStateView.LOADING_HIDE);
        }
    }

    @Override
    public void showError(LoadStateView.OnRequestListener listener) {
        if (mStateView != null) {
            mStateView.setRequestListener(listener);
            mStateView.setLoadingState(LoadStateView.LOADING_NO_NETWORK);
        }
    }

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(NoteApplication.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    //当前fragment是否可见
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisible() && isVisibleToUser && null != mRootView && !mLoad) {
            mLoad = true;
            loadLazyData();
        } else {
            super.setUserVisibleHint(isVisibleToUser);
        }
    }

    protected abstract void loadLazyData();

    protected abstract void updateViews();

    protected abstract void initInject();

    protected abstract int getLayoutResources();

    @Override
    public void onClick(View view) {
        if (getActivity() instanceof HomePagerActivity) {
            HomePagerActivity activity = (HomePagerActivity) getActivity();
            activity.toggleDrawer();
        }
    }
}
