package note.lym.org.noteproject.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.litepal.crud.DataSupport;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.adapter.TabFragmentAdapter;
import note.lym.org.noteproject.base.SimpleFragment;
import note.lym.org.noteproject.eventbus.LookerEvent;
import note.lym.org.noteproject.model.dao.Collect;
import note.lym.org.noteproject.ui.home.AttentionActivity;

/**
 * viewpager
 *
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
    @BindView(R.id.iv_count)
    TextView mIvCount;

    @Override
    protected void loadLazyData() {
        initToolBar(mToolBar, true, getString(R.string.casual_look));
        /**
         * 在fragment中嵌套fragment，尤其是使用了ViewPager的情况下的时候最好使用getChildFragmentManager
         * 而不要去使用 getActivity().getSupportFragmentManager() 避免一些buf的出现
         */
        mAdapter = new TabFragmentAdapter(getChildFragmentManager(), Arrays.asList(getResources().getStringArray(R.array.title)));
        mVp.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mVp);
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        setLoveCount();
    }

    private void setLoveCount() {
        mIvCount.setVisibility(getAttentionCount() > 0 ? View.VISIBLE : View.GONE);
        mIvCount.setText(String.valueOf(getAttentionCount()));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLookerEvent(LookerEvent event) {
        setLoveCount();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private int getAttentionCount() {
        return DataSupport.where("isCollect = ?", "1").find(Collect.class).size();
    }

    @Override
    protected int getLayoutResources() {
        return R.layout.activity_tab_home;
    }

    @OnClick(R.id.fl_layout)
    public void jump() {
        AttentionActivity.launch(this);
    }

}
