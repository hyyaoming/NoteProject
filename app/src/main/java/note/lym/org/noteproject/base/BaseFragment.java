package note.lym.org.noteproject.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import note.lym.org.noteproject.Dagger.Component.DaggerFragmentComponent;
import note.lym.org.noteproject.Dagger.Component.FragmentComponent;
import note.lym.org.noteproject.Dagger.Modul.FragmentModule;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.app.NoteApplication;
import note.lym.org.noteproject.ui.home.HomePagerActivity;
import note.lym.org.noteproject.utils.SoftInputUtil;

/**
 * @author yaoming.li
 * @since 2017-05-03 20:46
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {
    protected static final String TAG = BaseFragment.class.getSimpleName();
    protected Context mContext;
    private boolean mLoad = false;
    @Inject
    protected T mPresenter;
    private View mRootView;
    private Unbinder mBinder;
    @BindView(R.id.progress)
    protected ProgressBar mBar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == mRootView) {
            mRootView = inflater.inflate(getLayoutResources(), null);
            mBinder = ButterKnife.bind(this, mRootView);
            initInject();
            if (mPresenter != null) {
                mPresenter.attachView(this);
            }
            updateViews();
        }
        ViewGroup group = (ViewGroup) container.getParent();
        if (null != group) {
            group.removeView(mRootView);
        }
        return mRootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBinder.unbind();
        if(null != mPresenter){
            mPresenter.detachView();
            mPresenter = null;
        }
    }

    /**
     * 初始化 Toolbar
     *
     * @param toolbar
     * @param homeAsUpEnabled
     * @param title
     */
    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
        ((ToolBarBaseActivity)getActivity()).initToolBar(toolbar, homeAsUpEnabled, title);
    }

    /**
     * 隐藏系统软键盘
     */
    protected void hideSoftInput(){
        if (SoftInputUtil.isOpen()) {
            ToolBarBaseActivity activity = (ToolBarBaseActivity) getActivity();
            SoftInputUtil.hideSysSoftInput(activity);
        }
    }

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
        if(mBar != null){
            mBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideLoading() {
        mBar.setVisibility(View.GONE);
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
}
