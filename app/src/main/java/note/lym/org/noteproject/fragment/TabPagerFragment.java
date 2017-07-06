package note.lym.org.noteproject.fragment;

import android.animation.Animator;
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
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.adapter.TabFragmentAdapter;
import note.lym.org.noteproject.base.SimpleFragment;
import note.lym.org.noteproject.eventbus.LookerEvent;
import note.lym.org.noteproject.model.dao.Collect;
import note.lym.org.noteproject.ui.home.AttentionActivity;
import note.lym.org.noteproject.utils.AnimateHelper;
import note.lym.org.noteproject.utils.ToastUtils;

/**
 *
 * viewpager
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
    private List list = Arrays.asList("随便逛逛", "网易头条", "内涵段子", "搞笑段子", "轻松一刻", "这有福利");
    private Animator mLovedAnimator;
    private static final int ANIMATION_TIME = 2500;

    @Override
    protected void loadLazyData() {
        initToolBar(mToolBar, true, "随便逛逛");
        /**
         * 在fragment中嵌套fragment，尤其是使用了ViewPager的情况下的时候最好使用getChildFragmentManager
         * 而不要去使用 getActivity().getSupportFragmentManager() 避免一些buf的出现
         */
        mAdapter = new TabFragmentAdapter(getChildFragmentManager(), list);
        mVp.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mVp);
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        setLoveCount();
    }

    public void setLoveCount() {
        mIvCount.setVisibility(View.VISIBLE);
        int size = DataSupport.where("isCollect = ?", "1").find(Collect.class).size();
        mIvCount.setText(String.valueOf(size));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLookerEvent(LookerEvent event) {
        mIvCount.setText(String.valueOf(String.valueOf(getAttentionCount())));
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

    @Override
    public void onPause() {
        super.onPause();
        AnimateHelper.stopAnimator(mLovedAnimator);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mLovedAnimator == null) {
            mIvCount.post(new Runnable() {
                @Override
                public void run() {
                    mLovedAnimator = AnimateHelper.doHappyJump(mIvCount, mIvCount.getHeight() * 2 / 3, ANIMATION_TIME);
                }
            });
        } else {
            AnimateHelper.startAnimator(mLovedAnimator);
        }
    }

    @OnClick(R.id.fl_layout)
    public void jump() {
        if (getAttentionCount() > 0) {
            AttentionActivity.launch(this);
        }else{
            ToastUtils.showToast(R.string.no_save_looker_girl);
        }
    }

}
