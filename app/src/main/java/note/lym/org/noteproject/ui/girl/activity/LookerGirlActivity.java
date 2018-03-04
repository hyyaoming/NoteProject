package note.lym.org.noteproject.ui.girl.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.base.activity.BaseActivity;
import note.lym.org.noteproject.model.bean.LookerGirl;
import note.lym.org.noteproject.presenter.girl.ILookerGirlPresenter;
import note.lym.org.noteproject.presenter.girl.LookerContract;
import note.lym.org.noteproject.ui.girl.adapter.LookerAdapter;
import project.recyclerview.lym.org.recyclerviewlibrary.adapter.BaseFastAdapter;
import project.recyclerview.lym.org.recyclerviewlibrary.listener.OnItemClickListener;
import project.recyclerview.lym.org.recyclerviewlibrary.util.FullSpanUtil;

/**
 * 美图
 *
 * @author yaoming.li
 * @since 2017-05-16 23:47
 */
public class LookerGirlActivity extends BaseActivity<ILookerGirlPresenter> implements LookerContract.View, BaseFastAdapter.RequestLoadMoreListener {
    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    public static final String TYPE = "type";
    public static final String NAME = "name";
    private int mPage = 1;
    private LookerAdapter mAdapter;
    private String type;
    private String name;
    @BindView(R.id.tv_no_date)
    TextView mTvNoData;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_looker_girl;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mPresenter.getLookerList(mPage, type);
    }

    @Override
    protected void bindView() {
        type = getIntent().getStringExtra(TYPE);
        name = getIntent().getStringExtra(NAME);
        initToolBar(mToolBar, true, name);
        mAdapter = new LookerAdapter(R.layout.layout_belle_sister_item, null);
        FullSpanUtil.setStaggeredGridLayoutManager(mRvList, mAdapter, 2, StaggeredGridLayoutManager.VERTICAL);
        mAdapter.setOnLoadMoreListener(this);
        mRvList.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseFastAdapter adapter, View view, int position) {
                LookerGirl.ShowapiResBodyBean.PagebeanBean.ContentlistBean.ListBean bean = mAdapter.getItem(position).getList().get(0);
                BigBelleActivity.action(LookerGirlActivity.this, bean.getBig(), true);
            }
        });
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    public static void launch(Context context, String type, String name) {
        Intent intent = new Intent(context, LookerGirlActivity.class);
        intent.putExtra(NAME, name);
        intent.putExtra(TYPE, type);
        context.startActivity(intent);
    }

    @Override
    public void getLookerGirl(List<LookerGirl.ShowapiResBodyBean.PagebeanBean.ContentlistBean> list) {
        loadEnd();
        mAdapter.setNewData(list);
    }

    private void loadEnd() {
        if (mPage > 1) {
            mAdapter.loadMoreComplete();
        }
    }

    @Override
    public void noDataView() {
        mTvNoData.setVisibility(View.VISIBLE);
    }

    @Override
    public void noMoreData() {
        mAdapter.loadMoreEnd();
    }

    @Override
    public void onLoadMoreRequested() {
        mPage++;
        mPresenter.getLookerList(mPage, type);
    }
}
