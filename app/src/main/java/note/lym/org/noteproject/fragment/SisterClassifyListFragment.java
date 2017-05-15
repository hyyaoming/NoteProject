package note.lym.org.noteproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.List;

import butterknife.BindView;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.adapter.BelleSisterListAdapter;
import note.lym.org.noteproject.adapter.HealthListAdapter;
import note.lym.org.noteproject.base.BaseFragment;
import note.lym.org.noteproject.model.bean.SisterClassList;
import note.lym.org.noteproject.presenter.note.sister.ISisterClassifyView;
import note.lym.org.noteproject.presenter.note.sister.SisterClassifyPresenter;
import project.recyclerview.lym.org.recyclerviewlibrary.adapter.BaseFastAdapter;

/**
 * 漂亮姐姐列表页
 *
 * @author yaoming.li
 * @since 2017-05-12 17:21
 */
public class SisterClassifyListFragment extends BaseFragment<SisterClassifyPresenter> implements ISisterClassifyView, BaseFastAdapter.RequestLoadMoreListener {

    public static final String SISTER_ID = "sister_id";
    @BindView(R.id.rv_news_list)
    RecyclerView mRvList;
    private BelleSisterListAdapter mAdapter;
    private int mPage = 1;
    private static final int ITEM_COUNT = 10;
    private int mType;

    @Override
    protected void loadLazyData() {
        mPresenter.getPrettySister(mPage, ITEM_COUNT, mType);
    }

    @Override
    protected void updateViews() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRvList.setLayoutManager(layoutManager);
        mRvList.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new BelleSisterListAdapter(R.layout.layout_belle_sister_item, null);
        mRvList.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mType = getArguments().getInt(SISTER_ID);
        }
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutResources() {
        return R.layout.fragment_news_list;
    }

    public static Fragment newInstance(int id) {
        SisterClassifyListFragment fragment = new SisterClassifyListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(SISTER_ID, id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void getPrettySisterList(List<SisterClassList.ShowapiResBodyBean.DataBean> list) {
        if (mPage > 1) {
            mAdapter.loadMoreComplete();
        }
        mAdapter.addData(list);
    }

    @Override
    public void onLoadMoreRequested() {
        mPage++;
        mPresenter.getPrettySister(mPage, ITEM_COUNT, mType);
    }
}
