package note.lym.org.noteproject.ui.news.fragment;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.base.fragment.BaseFragment;
import note.lym.org.noteproject.model.bean.Health;
import note.lym.org.noteproject.presenter.health.HealthContract;
import note.lym.org.noteproject.presenter.health.HealthPresenter;
import note.lym.org.noteproject.ui.news.adapter.HealthClassifyAdapter;
import note.lym.org.noteproject.utils.eventbus.FlbEvent;
import note.lym.org.noteproject.view.LoadStateView;

/**
 * @author yaoming.li
 * @since 2017-05-12 10:14
 */
public class HealthMessageFragment extends BaseFragment<HealthPresenter> implements HealthContract.View {


    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mVp;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    private HealthClassifyAdapter mAdapter;
    @BindView(R.id.fab)
    FloatingActionButton mActionButton;

    @Override
    protected void loadLazyData() {
        mPresenter.getHealthClassifyList();
    }

    @Override
    protected void updateViews() {
        EventBus.getDefault().unregister(this);
        mActionButton.setVisibility(View.VISIBLE);
        initToolBar(mToolBar, true, getString(R.string.health_message));
        /**
         * 在fragment中嵌套fragment，尤其是使用了ViewPager的情况下的时候最好使用getChildFragmentManager
         * 而不要去使用 getActivity().getSupportFragmentManager() 避免一些buf的出现
         */
        mAdapter = new HealthClassifyAdapter(getChildFragmentManager());
        mVp.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mVp);
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutResources() {
        return R.layout.fragment_health_message;
    }

    @Override
    public void showError(LoadStateView.OnRequestListener listener) {
        Snackbar.make(mVp,R.string.no_network,Snackbar.LENGTH_LONG).setAction(R.string.again, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.getHealthClassifyList();
            }
        }).show();
    }

    @OnClick(R.id.fab)
    public void onClick(){
        EventBus.getDefault().post(new FlbEvent());
    }

    @Override
    public void getHealthClassifyList(List<Health.ShowapiResBodyBean.ListBean> listBeanList) {
        LinkedList<Fragment> fragments = new LinkedList<>();
        LinkedList<String> titles = new LinkedList<>();
        for (Health.ShowapiResBodyBean.ListBean bean : listBeanList) {
            titles.add(bean.getName());
            fragments.add(ClassifyListFragment.newInstance(bean.getId()));
        }
        mAdapter.setItems(fragments,titles);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
