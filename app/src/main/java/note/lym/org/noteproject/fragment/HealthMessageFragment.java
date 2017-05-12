package note.lym.org.noteproject.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.adapter.HealthClassifyAdapter;
import note.lym.org.noteproject.base.BaseFragment;
import note.lym.org.noteproject.model.bean.Health;
import note.lym.org.noteproject.presenter.note.health.HealthPresenter;
import note.lym.org.noteproject.presenter.note.health.IHealthView;

/**
 * @author yaoming.li
 * @since 2017-05-12 10:14
 */
public class HealthMessageFragment extends BaseFragment<HealthPresenter> implements IHealthView {


    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mVp;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    private HealthClassifyAdapter mAdapter;

    @Override
    protected void loadLazyData() {
        mPresenter.getHealthClassifyList();
    }

    @Override
    protected void updateViews() {
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
    public void getHealthClassifyList(List<Health.ShowapiResBodyBean.ListBean> listBeanList) {
        LinkedList<Fragment> fragments = new LinkedList<>();
        LinkedList<String> titles = new LinkedList<>();
        for (Health.ShowapiResBodyBean.ListBean bean : listBeanList) {
            titles.add(bean.getName());
            fragments.add(ClassifyListFragment.newInstance(bean.getId()));
        }
        mAdapter.setItems(fragments,titles);
    }
}
