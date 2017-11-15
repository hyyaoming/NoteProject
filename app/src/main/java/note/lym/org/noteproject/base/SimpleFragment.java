package note.lym.org.noteproject.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 *
 * @author yaoming.li
 * @since 2017-05-03 21:30
 */
public abstract class SimpleFragment extends SupportFragment {

    private Context mContext;
    private View mRootView;
    private Unbinder mBinder;
    private boolean mLoad =false;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(null == mRootView){
            mRootView = inflater.inflate(getLayoutResources(),null);
            mBinder = ButterKnife.bind(this,mRootView);
            initView();
        }
        ViewGroup group = (ViewGroup) container.getParent();
        if(null != group){
            group.removeView(mRootView);
        }
        return mRootView;
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
    public void onDestroy() {
        super.onDestroy();
        mBinder.unbind();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if(isVisible() && isVisibleToUser && mRootView!=null && !mLoad){
            mLoad =true;
            loadLazyData();
        }else{
            super.setUserVisibleHint(isVisibleToUser);
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

    protected abstract void loadLazyData();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext =context;
    }

    protected abstract void initView();


    protected abstract int getLayoutResources();
}
