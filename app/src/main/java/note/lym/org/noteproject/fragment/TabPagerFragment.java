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
    private List list = Arrays.asList("随便逛逛","网易头条","内涵段子","搞笑段子","轻松一刻","这有福利");

    @Override
    protected void loadLazyData() {
        initToolBar(mToolBar,true,"随便逛逛");
        /**
         * 在fragment中嵌套fragment，尤其是使用了ViewPager的情况下的时候最好使用getChildFragmentManager
         * 而不要去使用 getActivity().getSupportFragmentManager() 避免一些buf的出现
         */
        mAdapter = new TabFragmentAdapter(getChildFragmentManager(),list);
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
