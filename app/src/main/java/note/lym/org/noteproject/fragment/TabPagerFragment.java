package note.lym.org.noteproject.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.ViewParent;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.adapter.TabFragmentAdapter;
import note.lym.org.noteproject.base.SimpleFragment;

/**
 * @author yaoming.li
 * @since 2017-05-08 10:14
 */
public class TabPagerFragment extends SimpleFragment {
    private TabFragmentAdapter mAdapter;
    @BindView(R.id.view_pager)
    ViewPager mVp;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    private List list = Arrays.asList("随便逛逛","网易头条","轻松一刻","搞笑段子");

    @Override
    protected void loadLazyData() {
        initToolBar(mToolBar,true,"随便逛逛");
        mAdapter = new TabFragmentAdapter(getActivity().getSupportFragmentManager(),list);
        mVp.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mVp);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutResources() {
        return R.layout.activity_tab_home;
    }
}
