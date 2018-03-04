package note.lym.org.noteproject.ui.girl.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;

import butterknife.BindView;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.ui.girl.adapter.ViewPagerAdapter;
import note.lym.org.noteproject.base.activity.SimpleActivity;
import note.lym.org.noteproject.model.dao.CollectDao;

/**
 * 关注页
 *
 * @author yaoming.li
 * @since 2017-05-17 13:49
 */
public class AttentionActivity extends SimpleActivity {

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.view_pager)
    HorizontalInfiniteCycleViewPager mVpList;

    @Override
    protected int getLayout() {
        return R.layout.activity_attention_layout;
    }

    public static void launch(Fragment fragment) {
        Intent intent = new Intent(fragment.getActivity(), AttentionActivity.class);
        fragment.startActivity(intent);
    }

    @Override
    protected void initEventAndData() {
        initToolBar(mToolBar, true, R.string.the_secret_mistress);
        ViewPagerAdapter mAdapter = new ViewPagerAdapter(CollectDao.findAllCollectById("isCollect = ?", "1"));
        mVpList.setAdapter(mAdapter);
    }
}
