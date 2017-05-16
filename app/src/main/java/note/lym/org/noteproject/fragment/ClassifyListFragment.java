package note.lym.org.noteproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.adapter.HealthListAdapter;
import note.lym.org.noteproject.base.BaseFragment;
import note.lym.org.noteproject.model.bean.HealthList;
import note.lym.org.noteproject.presenter.note.health.HelthListPresenter;
import note.lym.org.noteproject.presenter.note.health.IHealthListView;
import note.lym.org.noteproject.ui.home.detail.HealthDetailActivity;
import project.recyclerview.lym.org.recyclerviewlibrary.adapter.BaseFastAdapter;
import project.recyclerview.lym.org.recyclerviewlibrary.listener.OnItemClickListener;
import project.recyclerview.lym.org.recyclerviewlibrary.util.FullSpanUtil;

/**
 * 健康资讯列表
 *
 * @author yaoming.li
 * @since 2017-05-12 10:46
 */
public class ClassifyListFragment extends BaseFragment<HelthListPresenter> implements IHealthListView, BaseFastAdapter.RequestLoadMoreListener {

    public static final String HEALTH_ID = "health_id";
    private String mHealthId;
    @BindView(R.id.rv_news_list)
    RecyclerView mRvList;
    private HealthListAdapter mAdapter;
    private int mPage = 1;

    @Override
    protected void loadLazyData() {
        mPresenter.getHealthListData(mHealthId, mPage);
    }

    @Override
    protected void updateViews() {
        mAdapter = new HealthListAdapter(R.layout.item_news_list, null);
        FullSpanUtil.setLinearLayoutManage(mRvList, mAdapter, LinearLayoutManager.VERTICAL);
        mAdapter.setOnLoadMoreListener(this);
        mRvList.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseFastAdapter adapter, View view, int position) {
                HealthList.ShowapiResBodyBean.PagebeanBean.ContentlistBean bean = (HealthList.ShowapiResBodyBean.PagebeanBean.ContentlistBean) adapter.getItem(position);
                HealthDetailActivity.launch(ClassifyListFragment.this, bean.getId());
            }
        });
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mHealthId = getArguments().getString(HEALTH_ID);
        }
    }

    @Override
    protected int getLayoutResources() {
        return R.layout.fragment_news_list;
    }

    public static ClassifyListFragment newInstance(String id) {
        ClassifyListFragment fragment = new ClassifyListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(HEALTH_ID, id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void getHealthListData(List<HealthList.ShowapiResBodyBean.PagebeanBean.ContentlistBean> list) {
        if (mPage > 1) {
            mAdapter.loadMoreComplete();
        }
        mAdapter.addData(list);
    }

    @Override
    public void onLoadMoreRequested() {
        mPage++;
        mPresenter.getHealthListData(mHealthId, mPage);
    }
}
