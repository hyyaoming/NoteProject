package note.lym.org.noteproject.ui.girl.fragment;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.base.fragment.BaseFragment;
import note.lym.org.noteproject.model.bean.SisterList;
import note.lym.org.noteproject.presenter.sister.SisterListContract;
import note.lym.org.noteproject.presenter.sister.SisterListPresenter;
import note.lym.org.noteproject.ui.news.adapter.HealthClassifyAdapter;
import note.lym.org.noteproject.view.LoadStateView;

/**
 * belle list
 *
 * @author yaoming.li
 * @since 2017-05-12 17:14
 */
public class SisterClassifyFragment extends BaseFragment<SisterListPresenter> implements SisterListContract.View {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mVp;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    private HealthClassifyAdapter mAdapter;

    @Override
    protected void loadLazyData() {
        mPresenter.getSisterData();
    }

    @Override
    protected void updateViews() {
        initToolBar(mToolBar, true, getString(R.string.beautiful_sister));
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
    public void getSisterClassify(List<SisterList.ShowapiResBodyBean.DataBean> list) {
        LinkedList<Fragment> fragments = new LinkedList<>();
        LinkedList<String> titles = new LinkedList<>();
        for (SisterList.ShowapiResBodyBean.DataBean bean : list) {
            titles.add(bean.getTitle());
            fragments.add(SisterClassifyListFragment.newInstance(bean.getId()));
        }
        mAdapter.setItems(fragments, titles);
    }
}
